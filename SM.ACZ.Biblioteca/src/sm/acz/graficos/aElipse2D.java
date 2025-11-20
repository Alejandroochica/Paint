/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sm.acz.graficos;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;


/**
 *
 * @author alejandro
 */
public class aElipse2D extends aShape {

    protected Ellipse2D elipse; 
    
    /**
     * Constructor del objeto elipse.
     * 
     */
    public aElipse2D(){
        super();
        elipse = new Ellipse2D.Float();
    }
    
    public aElipse2D(Ellipse2D.Float elipse){
        this.elipse = elipse;
    }
    
    
    /**
     * 
     * Devuelve el centro de la elipse.
     * 
     * @return Centro de la elipse
     */
    public Ellipse2D getLocation(){
        
        return this.elipse;
        
    }
    
    @Override
    public Rectangle getBounds() {
        return elipse.getBounds(); 
    }
    
    /**
     * 
     * Coloca la elipse.
     * 
     * @param pos 
     */
    @Override
    public void setLocation(Point2D pos) {
        double width = elipse.getWidth();
        double height = elipse.getHeight();

        
        Point2D p1 = new Point2D.Double(pos.getX() - width / 2, pos.getY() - height / 2);
        Point2D p2 = new Point2D.Double(pos.getX() + width / 2, pos.getY() + height / 2);

        elipse.setFrameFromDiagonal(p1, p2);
    }
    
    /**
     * 
     * @param punto
     * @return 
     */
    @Override
    public boolean contains(Point2D punto) {
        if(elipse==null){
            return false;
        }
        
        return elipse.contains(punto);
    }
   
    /**
     * Dibuja una elipse.
     * Usa una variable g2d de gráficos para dibujar una elipse
     * 
     * @param g2d 
     */
    @Override
    public void draw(Graphics2D g2d){
        g2d.setPaint(getColor());
        g2d.setStroke(getTrazo());
        g2d.setComposite(getTransparencia());
        g2d.setRenderingHints(getAlisado());
        if(isRelleno()){
            g2d.fill(elipse);
        }
        
        if(isEditar_objeto()){
            dibujarCuadroEdicion(g2d);
        }
        
        
        g2d.draw(elipse);
    }
}
