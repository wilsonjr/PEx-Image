/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MovePointsView.java
 *
 * Created on 24/04/2009, 20:12:54
 */
package visualizer.view.tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import net.sf.epsgraphics.ColorMode;
import net.sf.epsgraphics.EpsGraphics;
import visualizer.graph.Vertex;
import visualizer.util.SaveDialog;
import visualizer.util.filefilter.EPSFilter;
import visualizer.util.filefilter.PNGFilter;
import visualizer.view.ProjectionExplorerView;
import visualizer.view.ProjectionViewer;

/**
 *
 * @author Danilo M Eler
 */
public class MovePointsView extends javax.swing.JDialog {

    /** Creates new form MovePointsView */
    public MovePointsView(javax.swing.JFrame parent) {
        super(parent);
        this.view = new ViewPanelMove(2000, 2000);
        initComponents();
        projComboModel = (DefaultComboBoxModel) projectionsjComboBox.getModel();
        ProjectionExplorerView p = (ProjectionExplorerView) this.getParent();
        for (int i = 0; i < p.getDesktop().getAllFrames().length; i++) {
            if (p.getDesktop().getAllFrames()[i] instanceof ProjectionViewer) {
                ProjectionViewer proj = (ProjectionViewer) p.getDesktop().getAllFrames()[i];
                this.projections.add(proj);
                projComboModel.addElement("Projection " + proj.getId());
            }
        }
        // this.projectionsjList.setModel(projListModel);
        this.pack();
    }

    public static MovePointsView getInstance(javax.swing.JFrame parent) {
        if (instance == null) {
            instance = new MovePointsView(parent);
        }
        return instance;
    }

    public void display() {
        this.setLocationRelativeTo(this.getParent());
        this.setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        data_jPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        timejTextField = new javax.swing.JTextField();
        alphaLinejButton = new javax.swing.JButton();
        projectionsjComboBox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        projectionsjList = new javax.swing.JList(projListModel);
        refreshProjectionsjButton = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        saveArrowEPSjButton = new javax.swing.JButton();
        view_jPanel = new javax.swing.JPanel();
        view_jScrollPane = new javax.swing.JScrollPane(this.view);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 700));

        data_jPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        data_jPanel.setPreferredSize(new java.awt.Dimension(569, 130));

        jButton1.setText("View Line");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("View Opacity Animation");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("View Animation");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("Time");

        timejTextField.setText("30");

        alphaLinejButton.setText("View Alpha Arrows");
        alphaLinejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alphaLinejButtonActionPerformed(evt);
            }
        });

        projectionsjComboBox.setMinimumSize(new java.awt.Dimension(20, 18));
        projectionsjComboBox.setPreferredSize(new java.awt.Dimension(50, 20));

        jScrollPane1.setViewportView(projectionsjList);

        refreshProjectionsjButton.setText("Refresh");
        refreshProjectionsjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshProjectionsjButtonActionPerformed(evt);
            }
        });

        jButton5.setText("Add");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Clear");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton4.setText("Save to PNG");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        saveArrowEPSjButton.setText("Save Alpha Arrows to EPS");
        saveArrowEPSjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveArrowEPSjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout data_jPanelLayout = new javax.swing.GroupLayout(data_jPanel);
        data_jPanel.setLayout(data_jPanelLayout);
        data_jPanelLayout.setHorizontalGroup(
            data_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(data_jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(data_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(data_jPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(41, 41, 41)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(alphaLinejButton)
                        .addGap(37, 37, 37)
                        .addComponent(refreshProjectionsjButton))
                    .addGroup(data_jPanelLayout.createSequentialGroup()
                        .addComponent(timejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(data_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(data_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(saveArrowEPSjButton)
                            .addComponent(jButton4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(data_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(projectionsjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        data_jPanelLayout.setVerticalGroup(
            data_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(data_jPanelLayout.createSequentialGroup()
                .addGroup(data_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(data_jPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(data_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(alphaLinejButton)
                            .addComponent(projectionsjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(refreshProjectionsjButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(data_jPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(data_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(data_jPanelLayout.createSequentialGroup()
                        .addGroup(data_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(timejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2)
                            .addComponent(jButton4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(data_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(saveArrowEPSjButton)))
                    .addGroup(data_jPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)))
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(data_jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(data_jPanel, java.awt.BorderLayout.PAGE_START);

        view_jPanel.setLayout(new java.awt.BorderLayout());
        view_jPanel.add(view_jScrollPane, java.awt.BorderLayout.CENTER);

        getContentPane().add(view_jPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ProjectionExplorerView p = (ProjectionExplorerView) this.getParent();
        Graphics2D g2 = (Graphics2D) this.view.getImageBuffer().getGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, 1999, 1999);

        for (int k = 0; k < projListModel.getSize() - 1; k++) {
            int pos1 = projComboModel.getIndexOf((String) projListModel.elementAt(k));
            int pos2 = projComboModel.getIndexOf((String) projListModel.elementAt(k + 1));

            ArrayList<Vertex> v1 = projections.get(pos1).getGraph().getVertex();
            ArrayList<Vertex> v2 = projections.get(pos2).getGraph().getVertex();


            for (int i = 0; i < v1.size(); i++) {
                g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
                g2.setColor(v1.get(i).getColor());
                g2.fillOval((int) v1.get(i).getX() - v1.get(i).getRay(),
                        (int) v1.get(i).getY() - v1.get(i).getRay(),
                        v1.get(i).getRay() * 2, v2.get(i).getRay() * 2);
                g2.fillOval((int) v2.get(i).getX() - v2.get(i).getRay(),
                        (int) v2.get(i).getY() - v2.get(i).getRay(),
                        v2.get(i).getRay() * 2, v2.get(i).getRay() * 2);
                g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.1f));
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(2.0f));
                g2.drawLine((int) v1.get(i).getX(), (int) v1.get(i).getY(),
                        (int) v2.get(i).getX(), (int) v2.get(i).getY());
            }
        }
        this.view.repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.opt = 0;
        this.start();
        this.time = Integer.parseInt(timejTextField.getText());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.opt = 1;
        this.start();
        this.time = Integer.parseInt(timejTextField.getText());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void drawArrow(Graphics2D g2, Point s, Point t, float size) {
        float r = (float) Math.sqrt(Math.pow(s.x - t.x, 2) + Math.pow(s.y - t.y, 2));
        float cos = (t.x - s.x) / r;
        float sen = (t.y - s.y) / r;

        //rodo e translado
        //Point pa = new Point(Math.round(-sen*size)+(t.x+s.x)/2,Math.round(cos*size)+(t.y+s.y)/2);
        //Point pb = new Point(Math.round(-sen*-size)+(t.x+s.x)/2,Math.round(cos*-size)+(t.y+s.y)/2);
        //Point pc = new Point(Math.round(cos*size)+(t.x+s.x)/2,Math.round(sen*size)+(t.y+s.y)/2);

        Point pa = new Point(Math.round(-sen * size) + (t.x), Math.round(cos * size) + (t.y));
        Point pb = new Point(Math.round(-sen * -size) + (t.x), Math.round(cos * -size) + (t.y));
        Point pc = new Point(Math.round(cos * size) + (t.x), Math.round(sen * size) + (t.y));

        g2.drawLine(pa.x, pa.y, pc.x, pc.y);
        g2.drawLine(pb.x, pb.y, pc.x, pc.y);
    }

    private void alphaLinejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alphaLinejButtonActionPerformed
        // TODO add your handling code here:
        ProjectionExplorerView p = (ProjectionExplorerView) this.getParent();
        Graphics2D g2 = (Graphics2D) this.view.getImageBuffer().getGraphics();
        drawAlphaArrows(g2);
        this.view.repaint();
    }//GEN-LAST:event_alphaLinejButtonActionPerformed

    private void drawAlphaArrows(Graphics2D g2){
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, 1999, 1999);
        //g2.scale(2.0, 2.0);

        for (int k = 0; k < projListModel.getSize() - 1; k++) {
            int pos1 = projComboModel.getIndexOf((String) projListModel.elementAt(k));
            int pos2 = projComboModel.getIndexOf((String) projListModel.elementAt(k + 1));

            ArrayList<Vertex> v1 = projections.get(pos1).getGraph().getVertex();
            ArrayList<Vertex> v2 = projections.get(pos2).getGraph().getVertex();

            for (int i = 0; i < v1.size(); i++) {
                g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
                g2.setColor(v1.get(i).getColor());
                /*
                g2.fillOval((int) v1.get(i).getX() - v1.get(i).getRay(),
                (int) v1.get(i).getY() - v1.get(i).getRay(),
                v1.get(i).getRay() * 2, v2.get(i).getRay() * 2);
                g2.fillOval((int) v2.get(i).getX() - v2.get(i).getRay(),
                (int) v2.get(i).getY() - v2.get(i).getRay(),
                v2.get(i).getRay() * 2, v2.get(i).getRay() * 2);
                 * */
                g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.3f));
                //g2.setColor(Color.BLACK);

                GradientPaint gradPaint = new GradientPaint((int) v1.get(i).getX(), (int) v1.get(i).getY(),
                        Color.WHITE, (int) v2.get(i).getX(), (int) v2.get(i).getY(),
                        v1.get(i).getColor());
                g2.setPaint(gradPaint);
                g2.setStroke(new BasicStroke(2.0f));
                g2.drawLine((int) v1.get(i).getX(), (int) v1.get(i).getY(),
                        (int) v2.get(i).getX(), (int) v2.get(i).getY());

                g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.7f));
                drawArrow(g2, new Point((int) v1.get(i).getX(), (int) v1.get(i).getY()),
                        new Point((int) v2.get(i).getX(), (int) v2.get(i).getY()),
                        5.0f);

            }
            g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.7f));
            for (int i = 0; i < v1.size(); i++) {
                g2.setColor(v1.get(i).getColor());
                g2.drawLine((int) v1.get(i).getX() - 1, (int) v1.get(i).getY() - 1, (int) v1.get(i).getX() + 1, (int) v1.get(i).getY() - 1);
                g2.drawLine((int) v1.get(i).getX() - 1, (int) v1.get(i).getY(), (int) v1.get(i).getX() + 1, (int) v1.get(i).getY());
                g2.drawLine((int) v1.get(i).getX() - 1, (int) v1.get(i).getY() + 1, (int) v1.get(i).getX() + 1, (int) v1.get(i).getY() + 1);
            }
        }
    }
    
    private void refreshProjectionsjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshProjectionsjButtonActionPerformed
        // TODO add your handling code here:
        ProjectionExplorerView p = (ProjectionExplorerView) this.getParent();
        this.projections.clear();
        this.projComboModel.removeAllElements();
        for (int i = 0; i < p.getDesktop().getAllFrames().length; i++) {
            if (p.getDesktop().getAllFrames()[i] instanceof ProjectionViewer) {
                ProjectionViewer proj = (ProjectionViewer) p.getDesktop().getAllFrames()[i];
                this.projections.add(proj);
                projComboModel.addElement("Projection " + proj.getId());
            }
        }
}//GEN-LAST:event_refreshProjectionsjButtonActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:        
        this.projListModel.addElement((String) this.projComboModel.getSelectedItem());
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        this.projListModel.removeAllElements();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String filename = "image.png";

        int result = SaveDialog.showSaveDialog(new PNGFilter(), this, filename);

        if (result == JFileChooser.APPROVE_OPTION) {
            filename = SaveDialog.getFilename();
            try {
                ImageIO.write(this.view.getImageBuffer(), "png", new File(filename));
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void saveArrowEPSjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveArrowEPSjButtonActionPerformed
        // TODO add your handling code here:

        String filename = "image.eps";

        int result = SaveDialog.showSaveDialog(new EPSFilter(), this, filename);

        if (result == JFileChooser.APPROVE_OPTION) {
            filename = SaveDialog.getFilename();
            try {
                // Save this document to example.eps
                FileOutputStream outputStream = new FileOutputStream(filename);

                EpsGraphics g = new EpsGraphics(filename, outputStream, 0, 0,
                        2000, 2000, ColorMode.COLOR_RGB);

                //create the image
                g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
                g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
                g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


                g.setColor(Color.WHITE);
                g.fillRect(0, 0, 1999, 1999);
                g.setFont(this.getFont());

                drawAlphaArrows(g);


                // Flush and close the document (don't forget to do this!)

                g.flush();
                g.close();
            } catch (java.io.IOException ioe) {
                ioe.printStackTrace();
            }
        }
}//GEN-LAST:event_saveArrowEPSjButtonActionPerformed

    public void start() {
        switch (this.opt) {
            case 0:
                this.t = new Thread() {

                    @Override
                    public void run() {
                        ProjectionExplorerView p = (ProjectionExplorerView) getParent();

                        Graphics2D g2 = (Graphics2D) view.getImageBuffer().getGraphics();
                        g2.setColor(Color.WHITE);
                        g2.fillRect(0, 0, 1999, 1999);


                        int numElem = 0;
                        if (projections.size() > 0) {
                            numElem = projections.get(0).getGraph().getVertex().size();
                        }

                        for (int i = 0; i < numElem; i++) {
                            for (int k = 0; k < projListModel.getSize() - 1; k++) {
                                int pos1 = projComboModel.getIndexOf((String) projListModel.elementAt(k));
                                int pos2 = projComboModel.getIndexOf((String) projListModel.elementAt(k + 1));

                                ArrayList<Vertex> v1 = projections.get(pos1).getGraph().getVertex();
                                ArrayList<Vertex> v2 = projections.get(pos2).getGraph().getVertex();

                                float px1 = v1.get(i).getX();
                                float py1 = v1.get(i).getY();
                                float px2 = v2.get(i).getX();
                                float py2 = v2.get(i).getY();
                                float dx = px2 - px1;
                                float dy = py2 - py1;
                                int nS = 10;
                                float nSX = dx / nS;
                                float nSY = dy / nS;
                                g2.setColor(v1.get(i).getColor());
                                for (int j = 0; j < nS; j++) {
                                    g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f / (nS - j + 1)));
                                    g2.fillOval((int) (px1 + (nSX * j)), (int) (py1 + (nSY * j)), v1.get(i).getRay() * 2, v1.get(i).getRay() * 2);
                                    try {
                                        Thread.sleep(time);
                                        view.repaint();
                                    } catch (java.lang.InterruptedException ie) {
                                        ie.printStackTrace();
                                    }
                                }
                            }
                        }
                    // this.view.repaint();
                    }
                };
                break;

            case 1:
                this.t = new Thread() {

                    @Override
                    public void run() {
                        //ProjectionExplorerView p = (ProjectionExplorerView) getParent();
                        //System.out.println(p.getDesktop().getAllFrames().length);
                        //ArrayList<Vertex> v1 = ((ProjectionViewer) p.getDesktop().getAllFrames()[0]).getGraph().getVertex();
                        //ArrayList<Vertex> v2 = ((ProjectionViewer) p.getDesktop().getAllFrames()[1]).getGraph().getVertex();
                        //System.out.println(v1.size());
                        Graphics2D g2 = (Graphics2D) view.getImageBuffer().getGraphics();
                        g2.setColor(Color.WHITE);
                        g2.fillRect(0, 0, 1999, 1999);
                        int nS = 10;
                        float px1 = 0;
                        float py1 = 0;
                        float px2 = 0;
                        float py2 = 0;
                        float dx = 0;
                        float dy = 0;
                        float nSX = 0;
                        float nSY = 0;
                        for (int k = 0; k < projListModel.getSize() - 1; k++) {
                            int pos1 = projComboModel.getIndexOf((String) projListModel.elementAt(k));
                            int pos2 = projComboModel.getIndexOf((String) projListModel.elementAt(k + 1));

                            ArrayList<Vertex> v1 = projections.get(pos1).getGraph().getVertex();
                            ArrayList<Vertex> v2 = projections.get(pos2).getGraph().getVertex();

                            for (int j = 0; j < nS; j++) {
                                g2.setColor(Color.WHITE);
                                g2.fillRect(0, 0, 1999, 1999);
                                for (int i = 0; i < v1.size(); i++) {
                                    px1 = v1.get(i).getX();
                                    py1 = v1.get(i).getY();
                                    px2 = v2.get(i).getX();
                                    py2 = v2.get(i).getY();
                                    dx = px2 - px1;
                                    dy = py2 - py1;
                                    nSX = dx / nS;
                                    nSY = dy / nS;
                                    g2.setColor(v1.get(i).getColor());
                                    g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
                                    g2.fillOval((int) (px1 + (nSX * j)), (int) (py1 + (nSY * j)), v1.get(i).getRay() * 2, v1.get(i).getRay() * 2);
                                }
                                try {
                                    view.repaint();
                                    Thread.sleep(time);
                                } catch (java.lang.InterruptedException ie) {
                                    ie.printStackTrace();
                                }
                            }
                        // this.view.repaint();
                        }
                    }
                };
                break;
        }


        t.start();
    }

    public void stop() {
        if (this.t.isAlive()) {
            this.t.interrupt();
            this.dispose();
            Runtime.getRuntime().gc();
        }
    }
    private static MovePointsView instance;
    private ViewPanelMove view;
    private Thread t;
    private int opt;
    private int time;
    private DefaultComboBoxModel projComboModel;
    private DefaultListModel projListModel = new DefaultListModel();
    private ArrayList<ProjectionViewer> projections = new ArrayList<ProjectionViewer>();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alphaLinejButton;
    private javax.swing.JPanel data_jPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox projectionsjComboBox;
    private javax.swing.JList projectionsjList;
    private javax.swing.JButton refreshProjectionsjButton;
    private javax.swing.JButton saveArrowEPSjButton;
    private javax.swing.JTextField timejTextField;
    private javax.swing.JPanel view_jPanel;
    private javax.swing.JScrollPane view_jScrollPane;
    // End of variables declaration//GEN-END:variables
}
