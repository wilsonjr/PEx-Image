/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizer.view;

import java.util.HashMap;

/**
 *
 * @author wilson
 */
public class SingletonRegistry {
    private static HashMap<String, Object> map = new HashMap();
    
    protected SingletonRegistry() {}
    
    public synchronized static Object getInstance(String className) {
        Object classInstantiation = map.get(className);
        
        if( classInstantiation == null ) {
            
            try {
                classInstantiation = Class.forName(className).newInstance();
                map.put(className, classInstantiation);
            } catch( ClassNotFoundException | InstantiationException | IllegalAccessException e ) {
                throw new RuntimeException(e);
            }
        }
        
        return classInstantiation;
    }
}
