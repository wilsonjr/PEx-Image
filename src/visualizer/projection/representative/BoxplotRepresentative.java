/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizer.projection.representative;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngine;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import visualizer.matrix.Matrix;
import visualizer.view.color.ColorScale;
import visualizer.view.color.RainbowScale;



/**
 *
 * @author wilson
 */
public class BoxplotRepresentative implements RepresentativeGenerator {
    private List<BoxplotDataGenerator> boxplotDataGenerator;
    private final int NUM_COLS = 2;
    private boolean compareWithProjection = true;
    private Image generatedImage = null;
    private String toSave;
    
    
    public BoxplotRepresentative(List<BoxplotDataGenerator> boxplotDataGenerator, boolean compareWithProjection) {
       this.boxplotDataGenerator = boxplotDataGenerator;
       this.compareWithProjection = compareWithProjection;
       this.generatedImage = null;
    }
    
    private BoxAndWhiskerCategoryDataset createDataSet(BoxplotDataGenerator generator,
            Matrix selected, Matrix projection) {        
        try {
            DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();    
                
            String filename = generator.toString()+".csv";
            File file = new File(filename);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("Type,Value\n");
            
            switch (generator.analysisType()) {
                case BOTH:
                    {
                        float[] vGlobal = generator.generateGlobalAnalysis(selected, projection);
                        float[] vLocal = generator.generateLocalAnalysis(selected);
                        addValues(vLocal, generator.toString(), "Local", dataset);
                        addValues(vGlobal, generator.toString(), "Global", dataset);
                        for( int i = 0; i < vGlobal.length; ++i ) {
                            writer.write("Global,"+vGlobal[i]+"\n");
                        }       
                        for( int i = 0; i < vLocal.length; ++i ) {
                            writer.write("Local,"+vLocal[i]+"\n");
                        }       
                        break;
                    }
                case LOCAL:
                    {
                        float[] vLocal = generator.generateLocalAnalysis(selected);
                        addValues(vLocal, generator.toString(), "Local", dataset);
                        for( int i = 0; i < vLocal.length; ++i ) {
                            writer.write("Local,"+vLocal[i]+"\n");
                        }
                        break;
                    }
                case GLOBAL:                 
                    {
                        float[] vGlobal = generator.generateGlobalAnalysis(selected, projection);
                        addValues(vGlobal, generator.toString(), "Global", dataset);
                        for( int i = 0; i < vGlobal.length; ++i ) {
                            writer.write("Global,"+vGlobal[i]+"\n");
                        } 
                        break;
                    }
                default:
                    break;
            }
            
            if( compareWithProjection ) {
                float[] cProjection = generator.generateGlobalAnalysis(projection, projection);
                addValues(cProjection, generator.toString(), "Proj.", dataset);
                
                for( int i = 0; i < cProjection.length; ++i ) {
                    writer.write("Projection,"+cProjection[i]+"\n");
                }
            }
            
            writer.flush();
            writer.close();
            filename = file.getAbsolutePath();
            filename = filename.replace("\\", "\\\\");
            
            executeScript(filename, generator.toString());
            
            return dataset;
        } catch( IOException e ) {
            throw new RuntimeException(e);
        }        
    } 

    private void addValues(float[] v, String rowKey, String colKey, DefaultBoxAndWhiskerCategoryDataset dataset) {
        List<Double> list =  new ArrayList<>();
        for( int j = 0; j < v.length; ++j )
            list.add((double)v[j]);
        dataset.add(list, rowKey, colKey);
    }
    
    public JPanel getSmallMultiples(Matrix selected, Matrix projection) {
        
        int numero_linhas = numeroLinhas();        
        SmallMultiplesPanel smallMultiples = new SmallMultiplesPanel(numero_linhas, NUM_COLS);
        ColorScale scn = new RainbowScale();        
        toSave = boxplotDataGenerator.size()+"\n";
        
        for( int i = 0; i < boxplotDataGenerator.size(); ++i ) {
            BoxAndWhiskerCategoryDataset dataset = createDataSet(boxplotDataGenerator.get(i), selected, projection);
            
            toSave = prepareDataContent(i, dataset, toSave);
            
            CategoryAxis xAxis = new CategoryAxis("");
            NumberAxis yAxis = new NumberAxis("");
            BoxAndWhiskerRenderer renderer = new CustomBoxAndWhiskerRenderer();
            
            defineBoxplotStyle(renderer, scn, i, yAxis, xAxis);
            
            CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);
            plot.setRangeGridlinesVisible(true);            
            float[] dash = {5.0f};
            plot.setRangeGridlineStroke(new BasicStroke(1.5f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        5.0f,  dash, 0.0f));
            JFreeChart chart = new JFreeChart(plot);
            ChartPanel panel = new ChartPanel(chart);  
            //panel.setMouseWheelEnabled(true);
            panel.setMouseZoomable(true);
        
            smallMultiples.add(panel);
        }
                
        
        return smallMultiples;
    }

    private int numeroLinhas() {
        return (boxplotDataGenerator.size() % 2 == 0 ? 
                    boxplotDataGenerator.size() : 
                    boxplotDataGenerator.size()+1
               ) / NUM_COLS;
    }

    private void defineBoxplotStyle(BoxAndWhiskerRenderer renderer, ColorScale scn, int i, NumberAxis yAxis, CategoryAxis xAxis) {
        Font legendFont = new Font("SansSerif", Font.PLAIN, 16);
        renderer.setLegendTextFont(0, legendFont);
        renderer.setLegendTextFont(1, legendFont);
        
        renderer.setFillBox(true);
        renderer.setUseOutlinePaintForWhiskers(true);
        renderer.setMedianVisible(true);
        renderer.setMeanVisible(true);
        
        Color c = scn.getColor((i+1)*(60/255.f));
        renderer.setSeriesOutlineStroke(0, new BasicStroke(1.0f));
        renderer.setSeriesPaint(0, new Color(c.getRed(), c.getGreen(), c.getBlue(), 200));
        renderer.setMaximumBarWidth(0.20);
        renderer.setWhiskerWidth(1.0);
        
        ((CustomBoxAndWhiskerRenderer)renderer).setOutlierVisible(true);
        
        yAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 15));
        xAxis.setTickLabelFont(new Font("SansSerif", Font.BOLD|Font.ITALIC, 15));
    }

    private String prepareDataContent(int i, BoxAndWhiskerCategoryDataset dataset, String toSave) {
        String thisMetric = boxplotDataGenerator.get(i).toString()+" "+dataset.getColumnCount()+"\n";
        for( int j = 0; j < dataset.getColumnCount(); ++j ) {
            thisMetric += dataset.getColumnKey(j)+" ";
            thisMetric += dataset.getMedianValue(0, j)+" ";
            thisMetric += dataset.getMeanValue(0, j)+" ";
            thisMetric += dataset.getMinRegularValue(0, j)+" ";
            thisMetric += dataset.getMaxRegularValue(0, j)+" ";
            thisMetric += dataset.getQ1Value(0, j)+" ";
            thisMetric += dataset.getQ3Value(0, j)+" ";
            
            thisMetric += dataset.getOutliers(0, j).size();            
            for( int k = 0; k < dataset.getOutliers(0, j).size(); ++k ) 
                thisMetric += (" "+dataset.getOutliers(0, j).get(k));            
            
            thisMetric += "\n";

        }
        toSave += thisMetric;            
        
        return toSave;
    }
    
    
    @Override
    public JPanel generateRepresentative(Matrix selected, Matrix projection) {
        SmallMultiplesPanel panel = ((SmallMultiplesPanel)getSmallMultiples(selected, projection));
        
        generatedImage = panel.getImage();
        
        return panel;        
    }

    public void geraImagem(Matrix mCluster, Matrix projection, String string) {
        
        ((SmallMultiplesPanel)getSmallMultiples(mCluster, projection)).saveImage(string);
    }

    public void save(File file) {
        
        if( file == null )
            return;
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            try( BufferedWriter bw = new BufferedWriter(fw) ) {
                
                /**
                 * File structure:
                 * N -- number of metrics
                 * Metric Name(i) M -- Number of analysis
                 * {global|local} median mean min max Q1 Q3 K o1, o2, ..., oK
                 * [{global|local} median mean min max Q1 Q3 K o1, o2, ..., oK ] -- if M == 2
                 * ...
                 * Metric Name(N) M -- Number of analysis
                 * {global|local} median mean min max Q1 Q3 K o1, o2, ..., oK
                 * [{global|local} median mean min max Q1 Q3 K o1, o2, ..., oK ] -- if M == 2
                 */
                System.out.println("escrevendo,...");
                bw.write(toSave);                
            }
        } catch (IOException ex) {
            Logger.getLogger(BoxplotRepresentative.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Image representativeAsImage() {
        return generatedImage;
    }
    
    private void executeScript(String filename, String title) {
        
        try {
            
            RConnection connection = new RConnection();
            String script = createScript(filename, title);
            System.out.println(script);
            REXP exp = connection.eval(script);             
            
        } catch (RserveException ex) {
            Logger.getLogger(BoxplotRepresentative.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private String createScript(String filename, String title) {
        
        String script = "require(Cairo);" +
                        "require(ggplot2);" +
                        "Cairo(file=\""+filename.replace("csv", "png")+"\", type=\"png\", units=\"in\", width=9.5, height=5, pointsize=12, dpi=1200);" +
                        "datasetStress <- read.csv(\""+filename+"\", header=TRUE, encoding=\"UTF-8\");" +
                        "print(ggplot(datasetStress, aes(x=Type, y=Value, fill=Type)) +" +
                                "geom_boxplot(fill='#AA1233', alpha=0.6) +" +
                                "scale_y_continuous(name = \"Value\") +"+
                                "scale_x_discrete(name = \"Type\") +"+
                                "ggtitle(\""+title+"\") +"+
                                "theme_classic() +"+
                                "theme(plot.title = element_text(size = 14, family = \"Tahoma\", face = \"bold\"), "+
                                        "text = element_text(size = 12, family = \"Tahoma\"), "+
                                        "axis.title = element_text(size = 14, face=\"bold\"), "+
                                        "axis.text.x = element_text(size = 13, face=\"bold\"), "+
                                        "axis.text.y = element_text(size = 13, face=\"bold\"), "+
                                        "legend.title = element_text(size=14), "+
                                        "legend.text = element_text(size=14), "+
                                        "legend.position = \"right\") + "+
                                "scale_fill_brewer(palette = \"Accent\") + "+
                                "labs(fill=\"Type\"));"+
                        "dev.off();";
        
        return script;
    }
    
    
    public class SmallMultiplesPanel extends JPanel {
        
        public SmallMultiplesPanel(int rows, int cols) {
            super(new GridLayout(rows, cols));
            setSize(320, 290);
        }
        
        public Image getImage() {
            layoutRecursively(this);
            BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            paint(image.getGraphics());
            
            return image;
        }
        
        public void saveImage(String nome) {
            layoutRecursively(this);
            BufferedImage bi = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
            paint(bi.getGraphics());
            File file = new File(nome);
            try {
                ImageIO.write(bi, "png", file);                
            } catch( IOException e ) {
                throw new RuntimeException(e);
            }
        }       
        
        private void layoutRecursively(Component component) {
            component.doLayout();
            if (component instanceof Container) {
                Container container = (Container) component;
                for (int i = 0; i < container.getComponentCount(); i++) {
                    layoutRecursively(container.getComponent(i));
                }
            }
        }
    }
    
}
