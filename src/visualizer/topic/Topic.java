/* ***** BEGIN LICENSE BLOCK *****
 *
 * Copyright (c) 2005-2007 Universidade de Sao Paulo, Sao Carlos/SP, Brazil.
 * All Rights Reserved.
 *
 * This file is part of Projection Explorer (PEx).
 *
 * How to cite this work:
 *  
@inproceedings{paulovich2007pex,
author = {Fernando V. Paulovich and Maria Cristina F. Oliveira and Rosane 
Minghim},
title = {The Projection Explorer: A Flexible Tool for Projection-based 
Multidimensional Visualization},
booktitle = {SIBGRAPI '07: Proceedings of the XX Brazilian Symposium on 
Computer Graphics and Image Processing (SIBGRAPI 2007)},
year = {2007},
isbn = {0-7695-2996-8},
pages = {27--34},
doi = {http://dx.doi.org/10.1109/SIBGRAPI.2007.39},
publisher = {IEEE Computer Society},
address = {Washington, DC, USA},
}
 *  
 * PEx is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) 
 * any later version.
 *
 * PEx is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details.
 *
 * This code was developed by members of Computer Graphics and Image
 * Processing Group (http://www.lcad.icmc.usp.br) at Instituto de Ciencias
 * Matematicas e de Computacao - ICMC - (http://www.icmc.usp.br) of 
 * Universidade de Sao Paulo, Sao Carlos/SP, Brazil. The initial developer 
 * of the original code is Fernando Vieira Paulovich <fpaulovich@gmail.com>, 
 * Roberto Pinho <robertopinho@yahoo.com.br>.
 *
 * Contributor(s): Rosane Minghim <rminghim@icmc.usp.br>
 *
 * You should have received a copy of the GNU General Public License along 
 * with PEx. If not, see <http://www.gnu.org/licenses/>.
 *
 * ***** END LICENSE BLOCK ***** */

package visualizer.topic;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import visualizer.corpus.Corpus;
import visualizer.graph.Vertex;
import visualizer.projection.representative.ClusterLabel;

/**
 *
 * @author Fernando Vieira Paulovich, Roberto Pinho
 */
public abstract class Topic extends ClusterLabel {

    /** Creates a new instance of Topic
     * @param vertex 
     */
    public Topic(ArrayList<Vertex> vertex) {
        super(vertex);        
    }

    public void drawTopic(Graphics g, java.awt.Font font, boolean selected) {
        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;

        g2.setFont(font);
        g2.setStroke(new BasicStroke(1.3f));
        g2.setColor(java.awt.Color.GRAY);
        g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
        g2.drawRect(this.rectangle.x, this.rectangle.y, this.rectangle.width, this.rectangle.height);
        g2.setStroke(new BasicStroke(1.0f));

        if (selected || showTopics || showThisTopic) {
            if (selected) {
                g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.2f));
                g2.setPaint(java.awt.Color.BLUE);
                g2.fillRect(this.rectangle.x, this.rectangle.y, this.rectangle.width, this.rectangle.height);
            }

            //draw the first label
            if (this.boxes.size() > 0) {
                java.awt.Point position = new java.awt.Point();
                position.x = this.rectangle.x + this.rectangle.width / 2;
                position.y = this.rectangle.y;
                java.awt.Rectangle rect = this.boxes.get(0).draw(g2, position, font, selected);

                //draw all the other ones
                for (int i = 1; i < this.boxes.size(); i++) {
                    position = new java.awt.Point();
                    position.x = this.rectangle.x + this.rectangle.width / 2;
                    position.y = rect.y + rect.height + 6;
                    rect = this.boxes.get(i).draw(g2, position, font, selected);
                }
            }
            
            
//                java.awt.Point position = new java.awt.Point();
//                position.x = this.rectangle.x + this.rectangle.width / 2;
//                position.y = this.rectangle.y;
//                java.awt.Rectangle rect = box.draw(g2, position, font, selected);

               
            
        }
    }

    public static boolean isShowTopics() {
        return showTopics;
    }

    public static void setShowTopics(boolean aShowTopics) {
        showTopics = aShowTopics;
    }

    public boolean isShowThisTopic() {
        return showThisTopic;
    }

    public void setShowThisTopic(boolean showThisTopic) {
        this.showThisTopic = showThisTopic;
    }

    public String getQuery() {
        return null;
    }

    protected abstract void createTopic(ArrayList<Vertex> vertex, Corpus datasource);

    public boolean isBoxInside(Point point) {
        return false;
    }

    protected ArrayList<StringBox> boxes = new ArrayList<>();
    protected StringBox box = new StringBox("Ola, este é um teste");
    protected static boolean showTopics = false;
    protected boolean showThisTopic = false;
}
