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
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author alejandro
 */
public class aRectangle2D extends aShape{
    
    protected Rectangle2D rectangulo;
    
    /**
     * Constructor del objeto rectángulo.
     * 
     */
    public aRectangle2D(){
        //super();
        rectangulo = new Rectangle2D.Float();
 
    }
    
    public aRectangle2D(Rectangle2D.Float rectangulo){
        this.rectangulo=rectangulo;
    }
    

    /**
     * 
     * Devuelve el objeto rectángulo.
     * 
     * @return rectangulo
     */
    public Rectangle2D getRectangulo() {
        return rectangulo;
    }

    
    /**
     * Devuelve el rectangulo.
     * 
     * @return rectangulo
     */
    public Rectangle2D getLocation(){
        return this.rectangulo;
    }
    
    /**
     * Introduce un valor al objeto rectangulo
     * @param rectangulo 
     */
    public void setRectangulo(Rectangle2D rectangulo) {
        this.rectangulo = rectangulo;
    }
    
    
    @Override
    public Rectangle getBounds() {
        return rectangulo.getBounds(); 
    }
    
    /**
     * 
     * @param pos 
     */
    @Override
    public void setLocation(Point2D pos) {
        double width = rectangulo.getWidth();
        double height = rectangulo.getHeight();

        Point2D p1 = new Point2D.Double(pos.getX() - rectangulo.getWidth() / 2, pos.getY() - height / 2);
        Point2D p2 = new Point2D.Double(pos.getX() + rectangulo.getWidth() / 2, pos.getY() + height / 2);

        rectangulo.setFrameFromDiagonal(p1, p2);
    }

        
    /**
     * 
     * @param punto
     * @return 
     */
    @Override
    public boolean contains(Point2D punto) {
        if(rectangulo==null){
            return false;
        }
        
        return rectangulo.contains(punto);
    }
    
    /**
     * Dibuja un rectangulo.
     * Usa una variable g2d de gráficos para dibujar una elipse
     * @param g2d 
     */
    @Override
    public void draw(Graphics2D g2d){
        g2d.setPaint(getColor());
        g2d.setStroke(getTrazo());
        g2d.setComposite(getTransparencia());
        g2d.setRenderingHints(getAlisado());
        if(isRelleno()){
            g2d.fill(rectangulo);
        }
        
        if(isEditar_objeto()){
            dibujarCuadroEdicion(g2d);
           
            

        }
        
        
        g2d.draw(rectangulo);
    }

    
}
