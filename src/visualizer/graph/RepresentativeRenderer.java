/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizer.graph;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author wilson
 */
interface RepresentativeRenderer {
    void render(Graphics g, Representative rep);
    boolean inside(Point p, Representative rep);
    Rectangle bounds(Representative rep);
}
