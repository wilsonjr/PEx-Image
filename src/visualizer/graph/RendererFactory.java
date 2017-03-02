/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizer.graph;

/**
 *
 * @author wilson
 */
public class RendererFactory {
    
    public static Renderer get(String className) {
        String name = "visualizer.graph."+className;
        
        try {
            Object classInstantiation = Class.forName(name).newInstance();
            return (Renderer) classInstantiation;
        } catch( ClassNotFoundException | InstantiationException | IllegalAccessException e ) {
            throw new RuntimeException(e);
        }
    }
    
}
