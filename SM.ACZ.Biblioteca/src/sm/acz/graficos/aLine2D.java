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
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 *
 * @author alejandro
 */
public class aLine2D extends aShape {

    protected Line2D.Float linea;
    
    /**
     * Constructor del objeto línea.
     * 
     * @param p1
     * @param p2
     */
    public aLine2D(Point2D p1, Point2D p2){
        super();
        linea = new Line2D.Float(p1, p2);
    }
    
    public aLine2D(Line2D.Float linea){
        this.linea=linea;
    }
    
    
    /**
     * 
     * Devuelve si la posición de la línea esta cerca.
     * 
     * @param p
     * @return distancia de linea
     */
    public boolean isNear(Point2D p){
        if(linea.getP1().equals(linea.getP2())) return linea.getP1().distance(p)<=2.0; //p1=p2
        return linea.ptLineDist(p)<=2.0; // p1!=p2
    }
    
    /**
     * 
     * @param p1
     * @param p2 
     */
    public void setLine(Point2D p1,Point2D p2){
        this.linea.setLine(p1,p2);
    }
    
    /**
     * 
     *  Devuelve el punto inicial de la línea.
     * 
     * @return Localización de la línea por puntos
     */
    
    public Line2D getLocation() {
        return this.linea;
    }
    
    
    @Override
    public Rectangle getBounds() {
        return linea.getBounds(); 
    }
    
    /**
     * 
     * Colocar la línea
     * 
     * @param pos 
     */
    @Override
    public void setLocation(Point2D pos){
        double dx=pos.getX()-linea.getX1();
        double dy=pos.getY()-linea.getY1();
        Point2D newp2 = new Point2D.Double(linea.getX2()+dx,linea.getY2()+dy);
        this.setLine(pos,newp2);
    }
        
    /**
     * 
     * @param punto
     * @return 
     */
    @Override
    public boolean contains(Point2D punto) {
       return isNear(punto);
    }
    
    /**
     * Dibuja una línea.
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
            g2d.fill(linea);
        }
        
        if(isEditar_objeto()){
            dibujarCuadroEdicion(g2d);
        }
        
        
        g2d.draw(linea);
    }

    
}
