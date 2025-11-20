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
import java.awt.geom.QuadCurve2D;

/**
 *
 * @author alejandro
 */
public class aCurva2D extends aShape {
    
    protected QuadCurve2D curva;
    
    /**
     * Constructor del objeto curva.
     *
     */
    public aCurva2D(){
        super();
        curva = new QuadCurve2D.Float();
    }
    
    public aCurva2D(QuadCurve2D.Float curva){
        this.curva=curva;
    }
    
    
    /**
     * Devuelve punto donde esta localizada la curva.
     * 
     * @return Punto
     */
    public QuadCurve2D getLocation() {
        return this.curva;
    }

    /**
     * Para modificar el punto de control de la linea.
     * 
     * @param ini
     * @param control
     * @param fin 
     */
    public void setControl(Point2D ini,Point2D control,Point2D fin){
        this.curva.setCurve(ini, control, fin);
    }
    
    
    @Override
    public Rectangle getBounds() {
        return curva.getBounds(); 
    }

    
    /**
     * 
     * @param pos 
     */
    @Override
    public void setLocation(Point2D pos) {
        double dx = pos.getX() - curva.getX1();
        double dy = pos.getY() - curva.getY1();
        
        Point2D newP2 = new Point2D.Double(curva.getX2() + dx, curva.getY2() + dy);  
        Point2D newControl = new Point2D.Double(curva.getCtrlX() + dx, curva.getCtrlY() + dy);  
        
        curva.setCurve(pos, newControl, newP2);
    }

    
    /**
     * 
     * @return 
     */
    @Override
    public boolean contains(Point2D punto) {
        if(curva==null){
            return false;
        }
        return curva.contains(punto);
    }
    
    
    
    /**
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
            g2d.fill(curva);
        }
        
        if(isEditar_objeto()){
            dibujarCuadroEdicion(g2d);
        }
        
        
        g2d.draw(curva);
    }

    
}
