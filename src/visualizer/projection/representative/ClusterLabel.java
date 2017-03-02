/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizer.projection.representative;

import java.awt.Rectangle;
import java.util.List;
import visualizer.graph.Vertex;

/**
 *
 * @author wilson
 */
public class ClusterLabel {
    private List<Vertex> vertex;
    /** Creates a new instance of Topic
     * @param vertex 
     */
    public ClusterLabel(List<Vertex> vertex) {
        this.calculateRectangle(vertex);

        //removing the non-valid vertex
        for (int i = 0; i < vertex.size(); i++) {
            if (!vertex.get(i).isValid()) {
                vertex.remove(i);
                
                i--;
            }
        }
    }
    
    public List<Vertex> getVertex() {
        return vertex;
    }
    
    public java.awt.Rectangle getRectangle() {
        return rectangle;
    }

    public float weightDistance(java.awt.Point point) {
        if (this.isInside(point)) {
            int cx = this.rectangle.x + this.rectangle.width / 2;
            int cy = this.rectangle.y + this.rectangle.height / 2;
            return (float) (Math.sqrt((cx - point.x) * (cx - point.x) + (cy - point.y) * 
                    (cy - point.y)) * (this.rectangle.width));
        } else {
            return -1;
        }
    }

    public boolean isInside(java.awt.Point point) {
        return (point.x > this.rectangle.x && point.x < (this.rectangle.x + this.rectangle.width) &&
                point.y > this.rectangle.y && point.y < (this.rectangle.y + this.rectangle.height));
    }
    
    protected void calculateRectangle(List<Vertex> vertex) {
        this.rectangle = calcRect(vertex);
    }

    protected Rectangle calcRect(List<Vertex> vertex) {
        Rectangle rect = new java.awt.Rectangle();
        
        if (vertex.size() > 0) {
            int maxX = (int) vertex.get(0).getX();
            int minX = (int) vertex.get(0).getX();
            int maxY = (int) vertex.get(0).getY();
            int minY = (int) vertex.get(0).getY();

            for (int v = 1; v < vertex.size(); v++) {
                int x = (int) vertex.get(v).getX();
                int y = (int) vertex.get(v).getY();

                if (x > maxX) {
                    maxX = x;
                } else if (x < minX) {
                    minX = x;
                }

                if (y > maxY) {
                    maxY = y;
                } else if (y < minY) {
                    minY = y;
                }
            }

            rect.x = minX - vertex.get(0).getRayBase() - 2;
            rect.y = minY - vertex.get(0).getRayBase() - 2;
            rect.width = maxX - minX + vertex.get(0).getRayBase() * 2 + 4;
            rect.height = maxY - minY + vertex.get(0).getRayBase() * 2 + 4;
            return rect;
        } else {
            rect.x = -1;
            rect.y = -1;
            rect.width = 0;
            rect.height = 0;
            return rect;
        }
    }
    
    protected java.awt.Rectangle rectangle;
    
    
}
