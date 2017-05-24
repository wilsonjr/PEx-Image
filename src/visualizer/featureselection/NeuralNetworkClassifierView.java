/*
 * NeuralNetworkClassifierView.java
 *
 * Created on 6 de Maio de 2008, 19:04
 */
package visualizer.featureselection;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import visualizer.util.OpenDialog;
import visualizer.util.SaveDialog;
import visualizer.util.SystemPropertiesManager;
import visualizer.util.Util;
import visualizer.util.filefilter.CLASSESFilter;
import visualizer.util.filefilter.DATAFilter;
import visualizer.util.filefilter.NAMESFilter;
import visualizer.util.filefilter.SCALARFilter;
import visualizer.util.filefilter.WEIGHTFilter;

/**
 *
 * @author  Danilo M Eler
 */
public class NeuralNetworkClassifierView extends javax.swing.JFrame {

    /** Creates new form NeuralNetworkClassifierView */
    public NeuralNetworkClassifierView() {
        initComponents();
        initModels();
        this.classificationResultjTable.setModel(this.classificationResultTableModel);
    }

    private void initModels() {
        String[] classificationTitles;

        classificationTitles = new String[]{"Sample", "Class"};
        

        this.classificationResultTableModel = new DefaultTableModel(null, classificationTitles);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        classifier_jPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        weightButton = new javax.swing.JButton();
        weightTextField = new javax.swing.JTextField();
        execRanking_jButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        trainingTextField = new javax.swing.JTextField();
        trainingButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        classesTextField = new javax.swing.JTextField();
        classesButton = new javax.swing.JButton();
        normalizejCheckBox = new javax.swing.JCheckBox();
        scalarGenerationjPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        namesTextField = new javax.swing.JTextField();
        namesButton = new javax.swing.JButton();
        classificationResultjScrollPane = new javax.swing.JScrollPane();
        classificationResultjTable = new javax.swing.JTable();
        generateScalarjButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        classifier_jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Neural Network Classifier"));

        jLabel2.setText("Weight File");

        weightButton.setText("Search...");
        weightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                weightButtonActionPerformed(evt);
            }
        });

        weightTextField.setColumns(35);

        execRanking_jButton.setText("Execute Classifier");
        execRanking_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                execRanking_jButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Points File");

        trainingTextField.setColumns(35);

        trainingButton.setText("Search...");
        trainingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trainingButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Classes File");

        classesTextField.setColumns(35);

        classesButton.setText("Search...");
        classesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classesButtonActionPerformed(evt);
            }
        });

        normalizejCheckBox.setText("Normalize Before Classification");

        javax.swing.GroupLayout classifier_jPanelLayout = new javax.swing.GroupLayout(classifier_jPanel);
        classifier_jPanel.setLayout(classifier_jPanelLayout);
        classifier_jPanelLayout.setHorizontalGroup(
            classifier_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(classifier_jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(classifier_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(classifier_jPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(classesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(classifier_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(classifier_jPanelLayout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(trainingTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(classifier_jPanelLayout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(weightTextField)))
                    .addComponent(normalizejCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(classifier_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(execRanking_jButton)
                    .addComponent(weightButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(trainingButton, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(classesButton, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(weightButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(trainingButton, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                .addContainerGap())
        );
        classifier_jPanelLayout.setVerticalGroup(
            classifier_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(classifier_jPanelLayout.createSequentialGroup()
                .addGroup(classifier_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(weightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(weightButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(classifier_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(trainingTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(trainingButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(classifier_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(classesButton)
                    .addComponent(classesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(classifier_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(execRanking_jButton)
                    .addGroup(classifier_jPanelLayout.createSequentialGroup()
                        .addComponent(normalizejCheckBox)
                        .addContainerGap())))
        );

        scalarGenerationjPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Scalar Generation"));

        jLabel3.setText("Names File");

        namesTextField.setColumns(35);

        namesButton.setText("Search...");
        namesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namesButtonActionPerformed(evt);
            }
        });

        classificationResultjScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Classification Result"));

        classificationResultjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sample", "Class"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        classificationResultjScrollPane.setViewportView(classificationResultjTable);

        generateScalarjButton.setText("Generate Scalar from Classification");
        generateScalarjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateScalarjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout scalarGenerationjPanelLayout = new javax.swing.GroupLayout(scalarGenerationjPanel);
        scalarGenerationjPanel.setLayout(scalarGenerationjPanelLayout);
        scalarGenerationjPanelLayout.setHorizontalGroup(
            scalarGenerationjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scalarGenerationjPanelLayout.createSequentialGroup()
                .addGroup(scalarGenerationjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(scalarGenerationjPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(namesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(namesButton, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                    .addGroup(scalarGenerationjPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(classificationResultjScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(generateScalarjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        scalarGenerationjPanelLayout.setVerticalGroup(
            scalarGenerationjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scalarGenerationjPanelLayout.createSequentialGroup()
                .addGroup(scalarGenerationjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(namesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namesButton))
                .addGroup(scalarGenerationjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(scalarGenerationjPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(classificationResultjScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE))
                    .addGroup(scalarGenerationjPanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(generateScalarjButton)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(scalarGenerationjPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(classifier_jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(classifier_jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scalarGenerationjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void weightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_weightButtonActionPerformed
        // TODO add your handling code here:
        int result = OpenDialog.showOpenDialog(new WEIGHTFilter(), this);

        if (result == JFileChooser.APPROVE_OPTION) {
            this.weightFilename = OpenDialog.getFilename();
            this.weightTextField.setText(weightFilename);
        }
    }//GEN-LAST:event_weightButtonActionPerformed

    private void execRanking_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_execRanking_jButtonActionPerformed
        // TODO add your handling code here:

        Process proc = null;
        Scanner in = null;
        try {
            String fDir = SystemPropertiesManager.getInstance().getProperty("FEATURE.DIR");
            String normalize = "notNormalize";
            if (normalizejCheckBox.isSelected()){
                normalize = "normal";
            }
            System.out.println(fDir + "\\programa-classificador.exe " +
                    "\""+this.trainingTextField.getText() +"\"" + " " +
                    "\""+this.weightTextField.getText()+"\"" + " " +
                    "\""+this.classesTextField.getText()+"\"" + " " +
                    normalize);
            proc = new ProcessBuilder(fDir + "\\programa-classificador.exe ",
                    "\""+this.trainingTextField.getText()+"\"",
                    "\""+this.weightTextField.getText()+"\"",
                    "\""+this.classesTextField.getText()+"\"",
                    normalize).start();


            in = new Scanner(new BufferedInputStream(proc.getInputStream()));

            while (in.hasNextLine() && !in.nextLine().equals("##########")) {
            }
            this.sampleLine.clear();
            this.classification.clear();
            if (in.hasNextLine()) {
                int lN; // line/sample number
                double cl; // class from classifier
                String line = in.nextLine();
                while (in.hasNextLine() && !line.equals("##########")) {
                   // System.out.println(line);
                    lN = Integer.valueOf(line.substring(0, line.lastIndexOf('-')).trim());
                    this.sampleLine.add(lN);
                    cl = Double.valueOf(line.substring(line.lastIndexOf('-') + 1, line.length()).trim());
                    this.classification.add(cl);
                    line = in.nextLine();
                }
            }
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (proc != null) {
                proc.destroy();
            }

            if (in != null) {
                in.close();
            }            

            if (this.sampleLine.size() > 0) {
                initModels();
                this.classificationResultjTable.setModel(this.classificationResultTableModel);
                String[] row = new String[2];
                for (int i = 0; i < this.sampleLine.size(); i++) {
                    row[0] = String.valueOf(this.sampleLine.get(i));
                    row[1] = String.valueOf(this.classification.get(i));
                    classificationResultTableModel.addRow(row);
                }
            }
        }
    }//GEN-LAST:event_execRanking_jButtonActionPerformed

    private void trainingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trainingButtonActionPerformed
        int result = OpenDialog.showOpenDialog(new DATAFilter(), this);

        if (result == JFileChooser.APPROVE_OPTION) {
            this.trainingFilename = OpenDialog.getFilename();
            this.trainingTextField.setText(this.trainingFilename);
        }
    }//GEN-LAST:event_trainingButtonActionPerformed

    private void namesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namesButtonActionPerformed
        // TODO add your handling code here:
        int result = OpenDialog.showOpenDialog(new NAMESFilter(), this);

        if (result == JFileChooser.APPROVE_OPTION) {
            this.namesFile = OpenDialog.getFilename();
            this.namesTextField.setText(this.namesFile);
        }
}//GEN-LAST:event_namesButtonActionPerformed

    public void saveScalar(String filename, Vector<String> names, Vector<Double> classes){
        BufferedWriter out = null;

        //SAVE THE POINTS FILE
        try {
            out = new BufferedWriter(new FileWriter(filename));

            //Writting the header
            out.write("SCALARS:nnClassifier\n");
            for (int i = 0; i < names.size(); i++) {
                out.write(names.get(i).trim() + ":" + classes.get(i));
                out.write("\n");
            }

        } catch (java.io.IOException e) {
            //throw new java.io.IOException("Problems written \"" + this.filename + "\"!");
            e.printStackTrace();
        } finally {
            //fechar o arquivo
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (java.io.IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
    private void generateScalarjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateScalarjButtonActionPerformed
       /* int result = SaveDialog.showSaveDialog(new SCALARFilter(), this);

        if (result == JFileChooser.APPROVE_OPTION) {
            String filename = SaveDialog.getFilename();

            if (!filename.toLowerCase().endsWith(".scalar")) {
                filename = filename.concat(".scalar");
            }
            if (namesFile.equals("")) {
                int resultN = OpenDialog.showOpenDialog(new NAMESFilter(), this);

                if (resultN == JFileChooser.APPROVE_OPTION) {
                    this.namesFile = OpenDialog.getFilename();
                    this.namesTextField.setText(this.namesFile);
                }
            }
            if (!namesFile.equals("")) {
                try {
                    Vector<String> names = Util.readNames(namesFile);
                    saveScalar(filename, names, this.classification);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }            
        }*/
    }//GEN-LAST:event_generateScalarjButtonActionPerformed

    private void classesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classesButtonActionPerformed
        int result = OpenDialog.showOpenDialog(new CLASSESFilter(), this);

        if (result == JFileChooser.APPROVE_OPTION) {
            this.classesFilename = OpenDialog.getFilename();
            this.classesTextField.setText(this.classesFilename);
        }
        
}//GEN-LAST:event_classesButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new NeuralNetworkClassifierView().setVisible(true);
            }
        });
    }
    String trainingFilename = "";
    String weightFilename = "";
    String namesFile = "";
    String classesFilename = "";
    Vector<Double> classification = new Vector<Double>();
    Vector<Integer> sampleLine = new Vector<Integer>();
    DefaultTableModel classificationResultTableModel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton classesButton;
    private javax.swing.JTextField classesTextField;
    private javax.swing.JScrollPane classificationResultjScrollPane;
    private javax.swing.JTable classificationResultjTable;
    private javax.swing.JPanel classifier_jPanel;
    private javax.swing.JButton execRanking_jButton;
    private javax.swing.JButton generateScalarjButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton namesButton;
    private javax.swing.JTextField namesTextField;
    private javax.swing.JCheckBox normalizejCheckBox;
    private javax.swing.JPanel scalarGenerationjPanel;
    private javax.swing.JButton trainingButton;
    private javax.swing.JTextField trainingTextField;
    private javax.swing.JButton weightButton;
    private javax.swing.JTextField weightTextField;
    // End of variables declaration//GEN-END:variables
}