/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizer.graph;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

/**
 *
 * @author wilson
 */
public class Renderer implements RepresentativeRenderer {
    
    @Override
    public  void render(Graphics g, Representative rep) {
        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;

        g2.setStroke(new BasicStroke(1.3f));
        g2.setColor(java.awt.Color.GRAY);
        g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
        g2.drawRect(rep.getRectangle().x, rep.getRectangle().y, rep.getRectangle().width, rep.getRectangle().height);
        g2.setStroke(new BasicStroke(1.0f));
        
        
        Point position = new Point();
        position.x = rep.getRectangle().x;
        position.y = rep.getRectangle().y;
        
        if( Representative.isShowRepresentative() || rep.isShowThisRepresentative() ) {
            
            
            if( rep.isTransparent() ) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
                rep.setTransparent(false); 
            }           
            
            g2.drawImage(rep.getRepresentacao(), position.x, position.y, null);
            
            // desenha borda
            int w = rep.getRepresentacao().getWidth(null);
            int h = rep.getRepresentacao().getHeight(null);
            g2.setStroke(new BasicStroke(2.0f));
            g2.setColor(rep.getBorderColor());
            g2.drawRect(position.x - 2, position.y - 2 , w + 3, h + 3);
            
          //  showThisRepresentative = hide;
        }
        
        if( rep.getClusterId() != null ) {
            g.setFont(new Font("Courier New", 1, 30));
            g2.drawString(rep.getClusterId(), position.x-6, position.y-6);
        }
    }

    @Override
    public boolean inside(Point p, Representative rep) {
        return (p.x > rep.getRectangle().x && p.x < (rep.getRectangle().x + rep.getRectangle().width) &&
                p.y > rep.getRectangle().y && p.y < (rep.getRectangle().y + rep.getRectangle().height));
    }

    @Override
    public Rectangle bounds(Representative rep) {
        List<Vertex> vertex = rep.getVertex();
        
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
    
    
    
    
    
}
