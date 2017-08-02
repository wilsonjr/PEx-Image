/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizer.projection.representative;

import br.com.methods.utils.Util;
import br.com.methods.utils.Vect;
import br.com.representative.RepresentativeFinder;
import br.com.representative.RepresentativeRegistry;
import br.com.representative.analysis.AnalysisController;
import br.com.representative.clustering.FarPointsMedoidApproach;
import br.com.representative.clustering.affinitypropagation.AffinityPropagation;
import br.com.representative.clustering.furs.FURS;
import br.com.representative.clustering.partitioning.KMeans;
import br.com.representative.clustering.partitioning.KMedoid;
import br.com.representative.dictionaryrepresentation.DS3;
import br.com.representative.lowrank.CSM;
import br.com.representative.lowrank.KSvd;
import br.com.representative.metric.GNAT;
import br.com.representative.metric.SSS;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author wilson
 */
public class Analysis {
    
    
    private double[][] similarity;
    private List<List<Double>> attrs;
    private Point2D.Double[] points;
    private List<Vect> elems;
    private boolean dataset;
    
    public Analysis(double[][] similarity, List<List<Double>> attrs, Point2D.Double[] points, List<Vect> elems, 
                    boolean dataset) {
        this.similarity = similarity;
        this.attrs = attrs;
        this.points = points;
        this.elems = elems;
        this.dataset = dataset;
    }
    
    public void execute() {
        
        double maxDistance = -1.0;
        double[][] distances = new double[points.length][points.length];
        for( int i = 0; i < distances.length; ++i )
            for( int j = i; j < distances.length; ++j ) {
                distances[i][j] = Util.euclideanDistance(points[i].x, points[i].y, points[j].x, points[j].y);
                distances[j][i] = distances[i][j];
                maxDistance = Math.max(maxDistance, distances[i][j]);
            }
        // tests for CBR-ILP-IR dataset
        if( dataset ) {
            
            RepresentativeFinder sss = (RepresentativeFinder) RepresentativeRegistry.getInstance(SSS.class, elems, 0.134, maxDistance); // verificar se é isso msm
            RepresentativeFinder gnat = (RepresentativeFinder) RepresentativeRegistry.getInstance(GNAT.class, elems, 19);
            RepresentativeFinder kmeans = (RepresentativeFinder) RepresentativeRegistry.getInstance(KMeans.class, elems, new FarPointsMedoidApproach(), (int)(elems.size()*0.0331));
            RepresentativeFinder kmedoid = (RepresentativeFinder) RepresentativeRegistry.getInstance(KMedoid.class, elems, new FarPointsMedoidApproach(), (int)(elems.size()*0.0331));
            RepresentativeFinder csm = (RepresentativeFinder) RepresentativeRegistry.getInstance(CSM.class, attrs, (int)(attrs.size()*0.0331), attrs.size());
            RepresentativeFinder ksvd = (RepresentativeFinder) RepresentativeRegistry.getInstance(KSvd.class, attrs, (int)(attrs.size()*0.0331));
            RepresentativeFinder ds3 = (RepresentativeFinder) RepresentativeRegistry.getInstance(DS3.class, distances, 0.17, 17, 21);
            RepresentativeFinder ap = (RepresentativeFinder) RepresentativeRegistry.getInstance(AffinityPropagation.class, elems, 17, 21);
            RepresentativeFinder furs = (RepresentativeFinder) RepresentativeRegistry.getInstance(FURS.class, elems, (int)(elems.size()*0.0331), 15, 0.2f, 15.0f/(float)points.length);


            List<RepresentativeFinder> techniques = Arrays.asList(sss, gnat, kmeans, kmedoid, csm, ksvd, ds3, ap, furs);

            techniques.forEach((v) -> {

                    System.out.println("Technique: "+v.toString());

                    v.execute();
                    int[] indexes = v.getRepresentatives();

                    Point2D.Double[] points = new Point2D.Double[indexes.length];
                    for( int i = 0; i < indexes.length; ++i )  
                            points[i] = new Point2D.Double(points[indexes[i]].x, points[indexes[i]].y);

                    AnalysisController.execute(indexes, similarity, points);

                    System.out.println("\n");

            });
            
        } else { // tests for ImageCorel dataset
            
            RepresentativeFinder sss = (RepresentativeFinder) RepresentativeRegistry.getInstance(SSS.class, elems, 0.131, maxDistance); // verificar se é isso msm
            RepresentativeFinder gnat = (RepresentativeFinder) RepresentativeRegistry.getInstance(GNAT.class, elems, 23);
            RepresentativeFinder kmeans = (RepresentativeFinder) RepresentativeRegistry.getInstance(KMeans.class, elems, new FarPointsMedoidApproach(), (int)(elems.size()*0.023));
            RepresentativeFinder kmedoid = (RepresentativeFinder) RepresentativeRegistry.getInstance(KMedoid.class, elems, new FarPointsMedoidApproach(), (int)(elems.size()*0.023));
            RepresentativeFinder csm = (RepresentativeFinder) RepresentativeRegistry.getInstance(CSM.class, attrs, (int)(attrs.size()*0.023), attrs.size());
            RepresentativeFinder ksvd = (RepresentativeFinder) RepresentativeRegistry.getInstance(KSvd.class, attrs, (int)(attrs.size()*0.023));
            RepresentativeFinder ds3 = (RepresentativeFinder) RepresentativeRegistry.getInstance(DS3.class, distances, 0.03, 20, 25);
            RepresentativeFinder ap = (RepresentativeFinder) RepresentativeRegistry.getInstance(AffinityPropagation.class, elems, 20, 25);
            RepresentativeFinder furs = (RepresentativeFinder) RepresentativeRegistry.getInstance(FURS.class, elems, (int)(elems.size()*0.023), 15, 0.2f, 15.0f/(float)points.length);


            List<RepresentativeFinder> techniques = Arrays.asList(sss, gnat, kmeans, kmedoid, csm, ksvd, ds3, ap, furs);

            techniques.forEach((v) -> {

                    System.out.println("Technique: "+v.toString());

                    v.execute();
                    int[] indexes = v.getRepresentatives();

                    Point2D.Double[] points = new Point2D.Double[indexes.length];
                    for( int i = 0; i < indexes.length; ++i )  
                            points[i] = new Point2D.Double(points[indexes[i]].x, points[indexes[i]].y);

                    AnalysisController.execute(indexes, similarity, points);

                    System.out.println("\n");

            });
            
            
        }
        
    }
    
    
    
    
    
}
