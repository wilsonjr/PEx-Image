/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizer.view;

import br.com.methods.overlap.hexboard.HexBoardExecutor;
import br.com.methods.overlap.incboard.IncBoardExecutor;
import br.com.methods.overlap.incboard.PointItem;
import br.com.methods.overlap.prism.PRISM;
import br.com.methods.overlap.projsnippet.ProjSnippet;
import br.com.methods.overlap.rwordle.RWordleC;
import br.com.methods.overlap.rwordle.RWordleL;
import br.com.methods.overlap.vpsc.VPSC;
import br.com.methods.utils.OverlapRect;
import br.com.methods.utils.Util;
import java.awt.Polygon;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import visualizer.graph.Graph;
import visualizer.graph.Vertex;
import visualizer.matrix.Matrix;
import visualizer.matrix.MatrixFactory;
import visualizer.projection.distance.Dissimilarity;
import visualizer.projection.distance.DissimilarityFactory;
import visualizer.projection.distance.DissimilarityType;
import visualizer.projection.distance.DistanceMatrix;
import visualizer.util.ChangeRetangulo;
import visualizer.util.KNN;
import visualizer.util.Pair;

/**
 *
 * @author wilson
 */
public class RemoveOverlapView extends javax.swing.JFrame {
    private Graph graph;
    private Viewer gv;
    private ButtonGroup group;
    private int resultadoVPSC = 0;
    /**
     * Creates new form RemoveOverlapView
     */
    public RemoveOverlapView() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        resultadoVPSC = 0;
                 
        graph = null;        
        group = new ButtonGroup();
        group.add(rWordleCJRadioButton);
        group.add(rWordleLJRadioButton);
        group.add(vpscJRadioButton);
        group.add(prismJRadioButton);
        group.add(projSnippetJRadioButton);
        group.add(incBoardJRadioButton);
        group.add(hexBoardJRadioButton);
        
        rWordleCJRadioButton.setSelected(true);
    }
    
    public void setViewer(Viewer gv) {
        this.graph = gv.getGraph();
        this.gv = gv;
    }
    
    private boolean temSobreposicao(double x, double y, ArrayList<ChangeRetangulo> R, int id, int rectsize) {
        double x1 = x, x2 = x+rectsize;
        double y1 = y, y2 = y+rectsize;
        
        for( int i = 0; i < R.size(); ++i ) {
          // if(  R.get(i).third.getUX() == 0.0 && R.get(i).third.getUY() == 0.0  )
          //      continue;
            
            
            OverlapRect r = R.get(i).third.getUX() == 0.0 && R.get(i).third.getUY() == 0.0 ?
                          R.get(i).second : R.get(i).third;
            //Retangulo r = R.get(i).third;
            
            if( r.getId() == id )
                continue;
            
            if( x2 > r.getUX() && x1 < r.getUX()+rectsize && y2 > r.getUY() && y1 < r.getUY()+rectsize )
                return true;
        }
        
        return false;
    }
    
    private OverlapRect[] findPosition(ArrayList<ChangeRetangulo> R) {
        OverlapRect[] rs = new OverlapRect[R.size()];
        
//        
//        Collections.sort(R, (ChangeRetangulo o1, ChangeRetangulo o2)-> {
//            double media1 = 0.0, var1=0.0, media2=0.0, var2=0.0;
//            for( int i = 0; i < knn[o1.third.getId()].length; ++i )
//                media1 += knn[o1.third.getId()][i].value;
//            media1 = media1/knn[o1.third.getId()].length;
//            for( int i = 0; i < knn[o1.third.getId()].length; ++i )
//                var1 += Math.pow(knn[o1.third.getId()][i].value-media1, 2);
//            var1 = var1/(knn[o1.third.getId()].length);
//            
//                    
//            for( int i = 0; i < knn[o2.third.getId()].length; ++i )
//                media2 += knn[o2.third.getId()][i].value;
//            media2 = media2/knn[o2.third.getId()].length;
//            for( int i = 0; i < knn[o2.third.getId()].length; ++i )
//                var2 += Math.pow(knn[o2.third.getId()][i].value-media2, 2);
//            var2 = var2/(knn[o2.third.getId()].length);
//            
//            
//            
//            double max1 = Double.MIN_VALUE, max2 = Double.MIN_VALUE;
//            for( int i = 0; i < knn[o1.third.getId()].length; ++i )
//                max1 = Math.max(max1, knn[o1.third.getId()][i].value);
//            for( int i = 0; i < knn[o2.third.getId()].length; ++i )
//                max2 = Math.max(max2, knn[o2.third.getId()][i].value);
//            
//            if( max1 < max2 )
//                return -1;
//            else if( max1 > max2 )
//                return 1;
//            
////            if( var1 < var2 )
////                return -1;
////            else if( var1 > var2 )
////                return 1;
//            
////            if( media1 < media2 )
////                return -1;
////            else if( media1 > media2 )
////                return 1;
//            return 0;
//        });
        
        
        Collections.sort(R, (ChangeRetangulo o1, ChangeRetangulo o2)-> {
            double d1 = Math.sqrt(Math.pow(o1.first.getUX()-o1.second.getUX(), 2)
                                        +
                        Math.pow(o1.first.getUY()-o1.second.getUY(), 2));
            double d2 = Math.sqrt(Math.pow(o2.first.getUX()-o2.second.getUX(), 2)
                                   +
                        Math.pow(o2.first.getUY()-o2.second.getUY(), 2));
            if( d1 < d2 )
                return -1;
            else if( d1 > d2 )
                return 1;

            return 0;
        });

        for( int j = 0; j < R.size(); ++j ) {
            ChangeRetangulo r = R.get(j);
            
            double FACTOR = 0.0001;
            double i = 1-FACTOR;
            double x = 0, y = 0;

            for( ; i >= 0.0; i -= FACTOR ) {
                x = (1.0-i)*r.second.getUX() + i*r.first.getUX();
                y = (1.0-i)*r.second.getUY() + i*r.first.getUY();                
                if( !temSobreposicao(x, y, R, r.third.getId(), (int)r.first.getWidth()) )  
                    break;
            }
            i += FACTOR;
                        
           // System.out.println("i: "+i+", distancia: "+d);
            x = (1.0-i)*r.second.getUX() + i*r.first.getUX();
            y = (1.0-i)*r.second.getUY() + i*r.first.getUY();

           // System.out.println("Position: (x:"+x+", y:"+y+") - (x:"+r.second.getUX()+", y:"+r.second.getUY()+")");
            r.third.setUX(x);
            r.third.setUY(y);
            rs[r.third.getId()] = r.third;
        }
        
        
        return rs;
    }
    
    private double FACTOR = 0.00001;
    private void reduceKNN(int k, int id, Pair[][] knn, ArrayList<ChangeRetangulo> R, double i) {
        
        
        if( R.get(k).third.getUX() != 0.0 || R.get(k).third.getUY() != 0.0 )
            return;
        
        
        //  double i = 1-FACTOR;
        double x = 0, y = 0;

        for( ; i >= 0; i -= FACTOR ) {
            x = (1.0-i)*R.get(k).second.getUX() + i*R.get(k).first.getUX();
            y = (1.0-i)*R.get(k).second.getUY() + i*R.get(k).first.getUY();                
            if( !temSobreposicao(x, y, R, R.get(k).third.getId(), (int)R.get(k).first.getWidth()) )  
                break;
        }
        i += FACTOR;        
        
       // System.out.println("i: "+i+", distancia: "+d);
        x = (1.0-i)*R.get(k).second.getUX() + i*R.get(k).first.getUX();
        y = (1.0-i)*R.get(k).second.getUY() + i*R.get(k).first.getUY();

       // System.out.println("Position: (x:"+x+", y:"+y+") - (x:"+r.second.getUX()+", y:"+r.second.getUY()+")");
        R.get(k).third.setUX(x);
        R.get(k).third.setUY(y);
        
        // faz para os vizinhos até parar...
        for( int j = 0; j < knn[id].length; ++j ) {
            int K = getIDElement(R, knn[id][j].index);
            
            reduceKNN(K, knn[id][j].index, knn, R, i);
        }
    }
    
    
    
    
    private void reduceKNN2(int k, int id, Pair[][] knn, ArrayList<ChangeRetangulo> R, double i) {
        
        if( R.get(k).third.getUX() != 0.0 || R.get(k).third.getUY() != 0.0 )
                return;
        
        
        double x = 0, y = 0;
        System.out.println("Olha o i: "+i);
        for( ; i >= 0; i -= FACTOR ) {
            x = (1.0-i)*R.get(k).second.getUX() + i*R.get(k).first.getUX();
            y = (1.0-i)*R.get(k).second.getUY() + i*R.get(k).first.getUY();                
            if( !temSobreposicao(x, y, R, R.get(k).third.getId(), (int)R.get(k).first.getWidth()) ) { 
                break;
            }
        }
        i += FACTOR;        

       // System.out.println("i: "+i+", distancia: "+d);
        x = (1.0-i)*R.get(k).second.getUX() + i*R.get(k).first.getUX();
        y = (1.0-i)*R.get(k).second.getUY() + i*R.get(k).first.getUY();

       // System.out.println("Position: (x:"+x+", y:"+y+") - (x:"+r.second.getUX()+", y:"+r.second.getUY()+")");
        R.get(k).third.setUX(x);
        R.get(k).third.setUY(y);
        
        
        Queue<ChangeRetangulo> q = new LinkedList<>();
        q.add(R.get(k));
        
        
        while( !q.isEmpty() ) {
            ChangeRetangulo u = q.remove();
            
            int ID = u.first.getId();
            
            //if( u.third.getUX() != 0.0 || u.third.getUY() != 0.0 )
           //     continue;


            //  double i = 1-FACTOR;
            
            

            // faz para os vizinhos até parar...
            for( int j = 0; j < knn[ID].length; ++j ) {
                int K = getIDElement(R, knn[ID][j].index);

             //   reduceKNN(K, knn[ID][j].index, knn, R, i);
                if( u.third.getUX() != 0.0 || u.third.getUY() != 0.0 )
                    continue;                
                System.out.println("Olha o i: "+i);
                for( ; i >= 0; i -= FACTOR ) {
                    x = (1.0-i)*R.get(K).second.getUX() + i*R.get(K).first.getUX();
                    y = (1.0-i)*R.get(K).second.getUY() + i*R.get(K).first.getUY();                
                    if( !temSobreposicao(x, y, R, R.get(K).third.getId(), (int)R.get(K).first.getWidth()) ) { 
                        break;
                    }
                }
                i += FACTOR;        

               // System.out.println("i: "+i+", distancia: "+d);
                x = (1.0-i)*R.get(K).second.getUX() + i*R.get(K).first.getUX();
                y = (1.0-i)*R.get(K).second.getUY() + i*R.get(K).first.getUY();

               // System.out.println("Position: (x:"+x+", y:"+y+") - (x:"+r.second.getUX()+", y:"+r.second.getUY()+")");
                R.get(K).third.setUX(x);
                R.get(K).third.setUY(y);
                
                
                
                q.add(R.get(K));
                
                
            }
            
        }
        
        
        
        
        
    }
    private void BubbleSort( Pair [ ] num )
{
     int j;
     boolean flag = true;   // set flag to true to begin first pass
     Pair temp;   //holding variable

     while ( flag )
     {
            flag= false;    //set flag to false awaiting a possible swap
            for( j=0;  j < num.length -1;  j++ )
            {
                double d1 = Math.sqrt(Math.pow(num[j].r.first.getUX()-num[j].r.second.getUX(), 2)
                                        +
                        Math.pow(num[j].r.first.getUY()-num[j].r.second.getUY(), 2));
                double d2 = Math.sqrt(Math.pow(num[j+1].r.first.getUX()-num[j+1].r.second.getUX(), 2)
                                       +
                        Math.pow(num[j+1].r.first.getUY()-num[j+1].r.second.getUY(), 2));
                
                
                
                   if ( d1 < d2 )   // change to > for ascending sort
                   {
                           temp = num[ j ];                //swap elements
                           num[ j ] = num[ j+1 ];
                           num[ j+1 ] = temp;
                          flag = true;              //shows a swap occurred  
                  } 
            } 
      } 
} 
    
    private OverlapRect[] findPosition(ArrayList<ChangeRetangulo> R, Pair[][] knn) {
        OverlapRect[] rs = new OverlapRect[R.size()];
        Collections.sort(R, (ChangeRetangulo o1, ChangeRetangulo o2)-> {
            double media1 = 0.0, var1=0.0, media2=0.0, var2=0.0;
            for( int i = 0; i < knn[o1.third.getId()].length; ++i )
                media1 += knn[o1.third.getId()][i].value;
            media1 = media1/knn[o1.third.getId()].length;
            for( int i = 0; i < knn[o1.third.getId()].length; ++i )
                var1 += Math.pow(knn[o1.third.getId()][i].value-media1, 2);
            var1 = var1/(knn[o1.third.getId()].length);
            
                    
            for( int i = 0; i < knn[o2.third.getId()].length; ++i )
                media2 += knn[o2.third.getId()][i].value;
            media2 = media2/knn[o2.third.getId()].length;
            for( int i = 0; i < knn[o2.third.getId()].length; ++i )
                var2 += Math.pow(knn[o2.third.getId()][i].value-media2, 2);
            var2 = var2/(knn[o2.third.getId()].length);
            
            
            
//            double max1 = Double.MIN_VALUE, max2 = Double.MIN_VALUE;
//            for( int i = 0; i < knn[o1.third.getId()].length; ++i )
//                max1 = Math.max(max1, knn[o1.third.getId()][i].value);
//            for( int i = 0; i < knn[o2.third.getId()].length; ++i )
//                max2 = Math.max(max2, knn[o2.third.getId()][i].value);
//            
//            if( max1 < max2 )
//                return -1;
//            else if( max1 > max2 )
//                return 1;
//            
            
            
            if( media1 < media2 )
                return -1;
            else if( media1 > media2 )
                return 1;
            return 0;
        });
        
        Collections.sort(R, (ChangeRetangulo o1, ChangeRetangulo o2)-> {
            double d1 = Math.sqrt(Math.pow(o1.first.getUX()-o1.second.getUX(), 2)
                                        +
                        Math.pow(o1.first.getUY()-o1.second.getUY(), 2));
            double d2 = Math.sqrt(Math.pow(o2.first.getUX()-o2.second.getUX(), 2)
                                   +
                        Math.pow(o2.first.getUY()-o2.second.getUY(), 2));
            if( d1 < d2 )
                return -1;
            else if( d1 > d2 )
                return 1;

            return 0;
        });
//        
        
        
        for( int j = 0; j < R.size(); ++j ) {
            reduceKNN2(j, R.get(j).first.getId(), knn, R, 1.0-FACTOR);            
            //rs[R.get(j).first.getId()] = R.get(j).third;
        }
        
        for( int j = 0; j < R.size(); ++j ) {
            rs[R.get(j).first.getId()] = R.get(j).third;
        }
        
//        
        
        
//        reduceKNN(idMinDist, knn, R);
////        
//        for( int j = 0; j < knn.length; ++j ) {
//            reduceKNN(j, knn, R);            
//            rs[j] = R.get(j).third;
//        }       
        
        return rs;
    }
    
    
    
    
    
    private ArrayList<OverlapRect> formRectangles() {
        ArrayList<Vertex> vertices = graph.getVertex();
        ArrayList<OverlapRect> retangulos = new ArrayList<>();        
        
        // forma os retângulos de acordo com a visualização 
        for( int i = 0; i < vertices.size(); ++i ) {
            if( vertices.get(i).isDrawAsImages() ) {
                int w = vertices.get(i).getImage().getWidth(null);
                int h = vertices.get(i).getImage().getHeight(null);
                int x = ((int) vertices.get(i).getX()) - w/2;
                int y = ((int) vertices.get(i).getY()) - h/2;
                if( Vertex.isDrawClassOnImage() ) {
                    x -= 2;
                    y -= 2;
                    w += 3;
                    h += 3;
                }
                retangulos.add(new OverlapRect(x, y, 20, 20));
            } else {
                
                int x = ((int) vertices.get(i).getX()) - vertices.get(i).getRay();
                int y = ((int) vertices.get(i).getY()) - vertices.get(i).getRay();
                int raio = vertices.get(i).getRay()*2;
                retangulos.add(new OverlapRect(x, y, 20, 20));
            }
            
        }
        
        return retangulos; 
    }
    
        /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        iniciarJButton = new javax.swing.JButton();
        rWordleCJRadioButton = new javax.swing.JRadioButton();
        rWordleLJRadioButton = new javax.swing.JRadioButton();
        vpscJRadioButton = new javax.swing.JRadioButton();
        prismJRadioButton = new javax.swing.JRadioButton();
        projSnippetJRadioButton = new javax.swing.JRadioButton();
        incBoardJRadioButton = new javax.swing.JRadioButton();
        hexBoardJRadioButton = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        anguloJTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        recentralizarJRadioButton = new javax.swing.JRadioButton();
        matrizEsparsaJCheckBox = new javax.swing.JCheckBox();
        reduceSpaceCheckBox = new javax.swing.JCheckBox();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        iniciarJButton.setText("Iniciar");
        iniciarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarJButtonActionPerformed(evt);
            }
        });

        rWordleCJRadioButton.setText("RWordle-C");

        rWordleLJRadioButton.setText("RWordle-L");

        vpscJRadioButton.setText("VPSC");

        prismJRadioButton.setText("PRISM");

        projSnippetJRadioButton.setText("ProjSnippet");

        incBoardJRadioButton.setText("IncBoard");

        hexBoardJRadioButton.setText("HexBoard");

        jLabel1.setText("Atenção: As técnicas IncBoard e HexBoard mudarão a projeção.");

        anguloJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anguloJTextFieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Ângulo:");

        recentralizarJRadioButton.setText("Recentralizar");

        matrizEsparsaJCheckBox.setText("Usar estrutura Yale");

        reduceSpaceCheckBox.setText("Reduzir Espaços");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(vpscJRadioButton)
                    .addComponent(rWordleCJRadioButton)
                    .addComponent(projSnippetJRadioButton)
                    .addComponent(incBoardJRadioButton)
                    .addComponent(hexBoardJRadioButton)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(iniciarJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rWordleLJRadioButton)
                            .addComponent(prismJRadioButton))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(matrizEsparsaJCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(reduceSpaceCheckBox)
                                .addGap(36, 36, 36))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(anguloJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(recentralizarJRadioButton))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rWordleCJRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rWordleLJRadioButton)
                    .addComponent(anguloJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(recentralizarJRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vpscJRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prismJRadioButton)
                    .addComponent(matrizEsparsaJCheckBox)
                    .addComponent(reduceSpaceCheckBox))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(projSnippetJRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(incBoardJRadioButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hexBoardJRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(iniciarJButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iniciarJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarJButtonActionPerformed
        Map<OverlapRect, OverlapRect> reprojected = null;
        ArrayList<OverlapRect> projectedValues = null;
        ArrayList<OverlapRect> rects = formRectangles();
        if( rWordleCJRadioButton.isSelected() ) {
            
            double[] center0 = Util.getCenter(rects);
            RWordleC rwordlec = new RWordleC();
            reprojected = rwordlec.applyAndShowTime(rects);
            projectedValues = Util.getProjectedValues(reprojected);
            double[] center1 = Util.getCenter(projectedValues);
            
            double ammountX = center0[0]-center1[0];
            double ammountY = center0[1]-center1[1];
            Util.translate(projectedValues, ammountX, ammountY);
            Util.normalize(projectedValues);
            
        } else if( rWordleLJRadioButton.isSelected() ) {
            double alpha;
            try {
                alpha = Double.parseDouble(anguloJTextField.getText());
            } catch( NumberFormatException e ) {
                JOptionPane.showMessageDialog(this, "Insira um valor correto para 'alpha'.", "Parâmetro incorreto", WIDTH);
                return;
            }

            double[] center0 = Util.getCenter(rects);
            RWordleL rwordlel = new RWordleL(alpha, recentralizarJRadioButton.isSelected());
            reprojected = rwordlel.applyAndShowTime(rects);
            projectedValues = Util.getProjectedValues(reprojected);
            double[] center1 = Util.getCenter(projectedValues);

            double ammountX = center0[0]-center1[0];
            double ammountY = center0[1]-center1[1];
            Util.translate(projectedValues, ammountX, ammountY);
            Util.normalize(projectedValues);
            
        } else if( vpscJRadioButton.isSelected() ) {
            
            double[] center0 = Util.getCenter(rects);
            VPSC vpsc = new VPSC();
            reprojected = vpsc.applyAndShowTime(rects);
            projectedValues = Util.getProjectedValues(reprojected);
            double[] center1 = Util.getCenter(projectedValues);


            double ammountX = center0[0]-center1[0];
            double ammountY = center0[1]-center1[1];
            Util.translate(projectedValues, ammountX, ammountY);
            Util.normalize(projectedValues);
            
        } else if( prismJRadioButton.isSelected() ) {
            
            double[] center0 = Util.getCenter(rects);
            int algo = matrizEsparsaJCheckBox.isSelected() ? 1 : 0;
            PRISM prism = new PRISM(algo);
            reprojected = prism.applyAndShowTime(rects);
            projectedValues = Util.getProjectedValues(reprojected);
            double[] center1 = Util.getCenter(projectedValues);
            
            
            double ammountX = center0[0]-center1[0];
            double ammountY = center0[1]-center1[1];
            Util.translate(projectedValues, ammountX, ammountY);
            Util.normalize(projectedValues);
            
            
        } else if( projSnippetJRadioButton.isSelected() ) {
            
            double[] center0 = Util.getCenter(rects);

            String alpha_value = JOptionPane.showInputDialog("Por favor, insira o valor para 'alpha':");
            String k_value = JOptionPane.showInputDialog("Por favor, insira o valor de 'k':");
            
            ProjSnippet projsnippet = new ProjSnippet(Double.parseDouble(alpha_value), Integer.parseInt(k_value)+1);
            reprojected = projsnippet.applyAndShowTime(rects);
            if( reprojected != null ) {
                projectedValues = Util.getProjectedValues(reprojected);
                double[] center1 = Util.getCenter(projectedValues);

                double ammountX = center0[0]-center1[0];
                double ammountY = center0[1]-center1[1];
                Util.translate(projectedValues, ammountX, ammountY);
                Util.normalize(projectedValues);
                
            } else
                JOptionPane.showMessageDialog(this, "Houve um problema ao aplicar o método Projsnippet.");
            
        } else if( incBoardJRadioButton.isSelected() ) {
            
            try {
                File file = new File(graph.getProjectionData().getSourceFile());

                ArrayList<PointItem> items = new ArrayList<>();
                Scanner scn = new Scanner(file);
                for( int i = 0; i < 2; ++i )
                    if( scn.hasNext() )
                        scn.nextLine();
                
                int dimensoes = Integer.parseInt(scn.nextLine());
                scn.nextLine();
                
                System.out.println("Quantidade de dimensões: "+dimensoes);
                
                String regex = "\\S+:\\S+";
                Pattern pattern = Pattern.compile(regex);
            
                int id = 0;
                while( scn.hasNext() ) {
                    String[] linha = scn.nextLine().split(";");
                    double[] dims = new double[dimensoes];
                    for( int i = 0; i < dims.length; ++i )
                        dims[i] = 0;
                        
                    for( int i = 1, j = 0; i < linha.length-1; ++i, ++j ) {
                        Matcher matcher = pattern.matcher(linha[i]);
                        if( matcher.find() )
                            dims[j] = Double.parseDouble(linha[i].split(":")[1]);
                        else
                            dims[j] = Double.parseDouble(linha[i]);
                    }
                    
                    items.add(new PointItem(dims, String.valueOf(id), id, 0));
                    id++;
                }
                System.out.println("Quantidade de elementos: "+items.size());
                IncBoardExecutor executor = new IncBoardExecutor();                
                long startTime = System.currentTimeMillis();
                executor.apply(items);
                long endTime = System.currentTimeMillis();
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "IncBoard time: {0}s", (endTime-startTime)/1000.0);
                
                int ymin = Math.abs(executor.getMinRow());
                int xmin = Math.abs(executor.getMinCol());
                projectedValues = new ArrayList<>();
                int w = (int) rects.get(0).getWidth();
                int h = (int) rects.get(0).getHeight();
                int offset = 50;
                for( PointItem d: executor.getItems() ) {
                    OverlapRect r = new OverlapRect(w*(d.getCol()+xmin) + offset, h*(d.getRow()+ymin) + offset, w, h);
                    r.setId(d.getId());
                    projectedValues.add(r);
                }
                
            } catch( IOException e ) {
                
            }
        } else if( hexBoardJRadioButton.isSelected() ) {
            
            try {
                File file = new File(graph.getProjectionData().getSourceFile());       
                
                ArrayList<PointItem> items = new ArrayList<>();
                Scanner scn = new Scanner(file);
                for( int i = 0; i < 2; ++i )
                    if( scn.hasNext() )
                        scn.nextLine();
                
                int dimensoes = Integer.parseInt(scn.nextLine());
                scn.nextLine();
                
                System.out.println("Quantidade de dimensões: "+dimensoes);
                
                String regex = "\\S+:\\S+";
                Pattern pattern = Pattern.compile(regex);
                
                int id = 0;
                while( scn.hasNext() ) {
                    String[] linha = scn.nextLine().split(";");
                    double[] dims = new double[dimensoes];
                    for( int i = 0; i < dims.length; ++i )
                        dims[i] = 0;
                        
                    for( int i = 1, j = 0; i < linha.length-1; ++i, ++j ) {
                        Matcher matcher = pattern.matcher(linha[i]);
                        if( matcher.find() )
                            dims[j] = Double.parseDouble(linha[i].split(":")[1]);
                        else
                            dims[j] = Double.parseDouble(linha[i]);
                        
                    }
                              
                    items.add(new PointItem(dims, String.valueOf(id), id, 0));
                    ++id;
                }

                HexBoardExecutor executor = new HexBoardExecutor();
                long startTime = System.currentTimeMillis();
                executor.apply(items);
                long endTime = System.currentTimeMillis();
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "HexBoard time: {0}s", (endTime-startTime)/1000.0);

                int zMin = executor.getMinRow()-executor.getMinCol();
                
                int minDist = Integer.MAX_VALUE;
                PointItem q = null;
                int zMIN = Integer.MAX_VALUE;
                for( PointItem d: executor.getItems() ) {
                    int z = d.getRow()-d.getCol();
                    if( z < zMIN )
                        zMIN = z;
                    int x;
                    if( zMin > z )
                        x = executor.getMinRow()+((Math.abs(zMin)+Math.abs(z))/2);
                    else
                        x = executor.getMinRow()-((Math.abs(zMin)+Math.abs(z))/2);
                    int dist = d.getCol()-x;
                    if( dist < minDist ) {
                        minDist = dist;
                        q = d;
                    }                    
                }
                
                int w = (int) rects.get(0).getWidth();
                int xmin = 30;
                int a = (int) Math.sqrt(w*w - Math.pow(w/2, 2));
                
                int offset = 100;
                
                projectedValues = new ArrayList<>();
                ((ProjectionViewer)gv).setBoard(true);
                        
                for( PointItem d: executor.getItems() ) {
                    int z = d.getRow() - d.getCol();
                    
                    // até hoje não sei como consegui converter aquele padrão maluco em coordenadas (x,y)
                    int centerHexY = (3*w/2) * (z + Math.abs(zMIN)) + w;
                    int distancia = (Math.abs(q.getRow() - d.getRow()) + Math.abs(q.getCol()-d.getCol())) * a + xmin;

                    OverlapRect r = new OverlapRect(distancia - (w/2) + offset, centerHexY - (w/2) + offset, w, w);
                    r.setId(d.getId());
                    projectedValues.add(r);
                    
                    int x = distancia+offset;
                    int y = centerHexY+offset;
                    
                    Polygon poly = new Polygon();
                    poly.addPoint(x, y - w);
                    poly.addPoint(x + a, y - w/2);
                    poly.addPoint(x + a, y + w/2);
                    poly.addPoint(x, y + w);
                    poly.addPoint(x - a, y + w/2);
                    poly.addPoint(x - a, y - w/2);
                    
                    ((ProjectionViewer)gv).addPolygonBoard(poly);
                }
            } catch( IOException e ) {
                
            }
        }
        
        if( projectedValues != null ) {        
            ArrayList<Vertex> vertices = graph.getVertex();
            for( int i = 0; i < vertices.size(); ++i ) {
                System.out.println(projectedValues.get(i).getId()+": "+projectedValues.get(i).x+"; "+projectedValues.get(i).y);
                vertices.get(projectedValues.get(i).getId()).setX((float) projectedValues.get(i).getCenterX());
                vertices.get(projectedValues.get(i).getId()).setY((float) projectedValues.get(i).getCenterY());
            }
            gv.updateImage();
        }
        dispose();
    }//GEN-LAST:event_iniciarJButtonActionPerformed

    private void anguloJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anguloJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_anguloJTextFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RemoveOverlapView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RemoveOverlapView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RemoveOverlapView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RemoveOverlapView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new RemoveOverlapView().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField anguloJTextField;
    private javax.swing.JRadioButton hexBoardJRadioButton;
    private javax.swing.JRadioButton incBoardJRadioButton;
    private javax.swing.JButton iniciarJButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JCheckBox matrizEsparsaJCheckBox;
    private javax.swing.JRadioButton prismJRadioButton;
    private javax.swing.JRadioButton projSnippetJRadioButton;
    private javax.swing.JRadioButton rWordleCJRadioButton;
    private javax.swing.JRadioButton rWordleLJRadioButton;
    private javax.swing.JRadioButton recentralizarJRadioButton;
    private javax.swing.JCheckBox reduceSpaceCheckBox;
    private javax.swing.JRadioButton vpscJRadioButton;
    // End of variables declaration//GEN-END:variables

    private int getIDElement(ArrayList<ChangeRetangulo> R, int index) {
        
        for( int i = 0; i < R.size(); ++i )
            if( R.get(i).third.getId() == index ) {
                return i;
            }
        return -1;
    }
}
