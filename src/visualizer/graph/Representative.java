/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizer.graph;

import java.awt.Color;
import java.awt.Image;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import visualizer.projection.representative.ClusterLabel;

/**
 *
 * @author wilson
 */
public class Representative extends ClusterLabel {
    private Image representacao; 
    private boolean transparent;
    private Color borderColor;
    private JPanel representativeAsPanel;
    private String clusterId;
    protected static boolean showRepresentative = false;
    protected boolean showThisRepresentative = false;
    
    public Representative(List<Vertex> vertex, Image representacao, Color borderColor, JPanel representativeAsPanel) {
        super(vertex);        
        this.representacao = representacao;
        this.borderColor = borderColor;
        this.representativeAsPanel = representativeAsPanel;
    }
    
    public int getPosX() {
        return rectangle.x;
    }
    
    public int getPosY() {
        return rectangle.y;
    }
    
    public boolean isTransparent() {
        return transparent;
    }

    public void setTransparent(boolean transparent) {
        this.transparent = transparent;
    }
    
//    public void draw(Graphics g, boolean hide) {
//        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
//
//        g2.setStroke(new BasicStroke(1.3f));
//        g2.setColor(java.awt.Color.GRAY);
//        g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
//        g2.drawRect(super.rectangle.x, super.rectangle.y, super.rectangle.width, super.rectangle.height);
//        g2.setStroke(new BasicStroke(1.0f));
//        
//        if( isShowRepresentative() || isShowThisRepresentative() ) {
//            Point position = new Point();
//            position.x = rectangle.x;
//            position.y = rectangle.y;
//            
//            if( isTransparent() ) {
//                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
//                setTransparent(false); 
//            }           
//            
//            g2.drawImage(representacao, position.x, position.y, null);
//            
//            // desenha borda
//            int w = representacao.getWidth(null);
//            int h = representacao.getHeight(null);
//            g2.setStroke(new BasicStroke(2.0f));
//            g2.setColor(borderColor);
//            g2.drawRect(position.x - 2, position.y - 2 , w + 3, h + 3);
//            
//            showThisRepresentative = hide;
//        }
//        
//    }
    
    public Image getRepresentacao() {
        return representacao;
    }
    
    public static boolean isShowRepresentative() {
        return showRepresentative;
    }

    public static void setShowRepresentative(boolean aShowTopics) {
        showRepresentative = aShowTopics;
    }

    public boolean isShowThisRepresentative() {
        return showThisRepresentative;
    }

    public void setShowThisRepresentative(boolean showThisTopic) {
        this.showThisRepresentative = showThisTopic;
    }
    
    public Color getBorderColor() {
        return borderColor;
    }
    
    public JPanel getRepresentativeAsPanel() {
        return representativeAsPanel;
    }
    
    public void listenClick() {
        JFrame frame = new JFrame("Cluster Analysis");
        frame.setContentPane(representativeAsPanel);   
        frame.setSize(658, 620);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    
    public void setClusterId(String id) {
        clusterId = id;
    }
    
    public String getClusterId() {
        return clusterId;
    }
    
}
