/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizer.graph;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author wilson
 */
public class ImageBox {
    
    private Image image;
    private Rectangle rectangle;
   
    public ImageBox(Image image) {
        this.image = image;
    }
    
    public java.awt.Rectangle draw(Graphics g, java.awt.Point position, java.awt.Font font, boolean selected) {
        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;

        //Getting the label size
        int width = image.getWidth(null);
        int height = image.getHeight(null);        
        //Creating the rectangle to be drawn
        java.awt.Rectangle rect = new java.awt.Rectangle(position.x - 2, position.y - 2, width + 4, height + 4);

        if (selected) {
            g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
            g2.setPaint(java.awt.Color.BLUE);
        } else {
            g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.85f));
            //g2.setPaint(java.awt.Color.YELLOW);
            g2.setPaint(new java.awt.Color(255, 255, 204));
        }

        g2.drawRect(rect.x - 1, rect.y - 1, rect.width + 2, rect.height + 2);
        g2.fill(rect);

        if (selected) {
            g2.setPaint(java.awt.Color.WHITE);
        } else {
            g2.setPaint(java.awt.Color.BLACK);
        }
        
        g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
        g2.drawRect(rect.x, rect.y, rect.width, rect.height);
        g2.drawImage(image, position.x, position.y + height-2, null);
        
        this.rectangle = rect;

        return rect;
    }
    
    public boolean isInside(Point point) {
        return (point.x > this.rectangle.x && point.x < (this.rectangle.x + this.rectangle.width) &&
                point.y > this.rectangle.y && point.y < (this.rectangle.y + this.rectangle.height));
    }
    
    
}
