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

/**
 *
 * @author alejandro
 */
public abstract class aShape /*implements Shape*/ {
 
    private Color color;
    private int grosor;
    private RenderingHints alisado;
    private Composite transparencia;
    private boolean editar = false;
    private boolean relleno = false;
    private Stroke trazo = new BasicStroke();
    
    private boolean alisar = false;
    private boolean transparente = false;
    private boolean editar_objeto = false;
    private boolean fijada = false;
      
    
    /**
     * Para añadir el color con el que se va a dibujar.
     * 
     * @param color 
     */
    public void setColor(Color color){
       this.color = color;
    }
    /**
     * Devuelve el color de la figura dibujada.
     * 
     * @return El color de la figura
     */
    public Color getColor(){
        return this.color;
    }
    
    /**
     * Para añadir el grosor con el que se va a dibujar.
     * @param grosor 
     */
    public void setGrosor(int grosor){
        this.grosor = grosor;
    }
    
    /**
     * Devuelve la cantidad de grosor con la que se esta dibujando.
     * @return 
     */
    protected int getGrosor(){
        return this.grosor;
    }

    /**
     * Devuelve el tono de alisado.
     * @return alisado
     */
    public RenderingHints getAlisado() {
        return alisado;
    }

    
    /**
     * Introduce el tono de alisado.
     * @param alisado 
     */
    public void setAlisado(RenderingHints alisado) {
        this.alisado = alisado;
    }

    
    /**
     * Devuelve el tono de transparencia.
     * 
     * @return transparencia
     */
    
    public Composite getTransparencia() {
        return transparencia;
    }

    /**
     * Introduce el tono de transparencia.
     * 
     * @param transparencia 
     */
    public void setTransparencia(Composite transparencia) {
        this.transparencia = transparencia;
    }

    /**
     * Comprueba si se esta editando o no.
     * @return editar
     */
    public boolean isEditar() {
        return editar;
    }
    
    /**
     * Introduce si se esta editando o no.
     * 
     * @param editar 
     */
    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    /**
     * Comprueba si esta relleno o no.
     * @return 
     */
    public boolean isRelleno() {
        return relleno;
    }

    /**
     * Introduce si se quiere relleno o no.
     * @param relleno 
     */
    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }

    /**
     *
     * @return
     */
    public Stroke getTrazo() {
        return trazo;
    }

    /**
     *
     * @param grosor 
     */
    public void setTrazo(int grosor) {
        this.trazo = new BasicStroke(grosor);
    }

    /**
     *
     * @return
     */
    public boolean isAlisar() {
        return alisar;
    }

    /**
     *
     * @param alisar
     */
    public void setAlisar(boolean alisar) {
        this.alisar = alisar;
    }

    /**
     *
     * @return
     */
    public boolean isTransparente() {
        return transparente;
    }

    /**
     *
     * @param transparente
     */
    public void setTransparente(boolean transparente) {
        this.transparente = transparente;
    }

    /**
     * 
     * Devuelve si se está editando el objeto o no.
     * 
     * @return editar_objeto
     */
    public boolean isEditar_objeto() {
        return editar_objeto;
    }

    
    /**
     * Edita el valor del modo de edición.
     * 
     * @param editar_objeto 
     */
    public void setEditar_objeto(boolean editar_objeto) {
        this.editar_objeto = editar_objeto;
    }

    /**
     * Indica si la figura esta fijada o no
     * @return fijada
     */
    public boolean isFijada() {
        return fijada;
    }

    /**
     * Devuelve si se va a fijar o no.
     * @param fijada 
     */
    public void setFijada(boolean fijada) {
        this.fijada = fijada;
    }
    
    
    public void dibujarCuadroEdicion(Graphics2D g2d) {
        Color oldColor = g2d.getColor();
        Stroke oldStroke = g2d.getStroke();

        Rectangle bounds = getBounds(); 

        g2d.setColor(Color.RED);
        float dash[] = {5.0f};
        g2d.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
        g2d.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);

        int size = 6;
        g2d.fillRect(bounds.x - size / 2, bounds.y - size / 2, size, size);
        g2d.fillRect(bounds.x + bounds.width - size / 2, bounds.y - size / 2, size, size);
        g2d.fillRect(bounds.x - size / 2, bounds.y + bounds.height - size / 2, size, size);
        g2d.fillRect(bounds.x + bounds.width - size / 2, bounds.y + bounds.height - size / 2, size, size);

        g2d.setColor(oldColor);
        g2d.setStroke(oldStroke);
    }


    /**
     * Devuevle el método getBounds en las diferentes clases hijas
     * 
     * @return bounds
     */
    public abstract Rectangle getBounds();

    
    
    /**
     * Devuelve la localización de la figura
     * 
     * @param pos
     */
    public abstract void setLocation(Point2D pos);
    
    /**
     * Devuelve si esta contenido o no
     * 
     * @param punto
     * @return 
     */
    public abstract boolean contains(Point2D punto);
    
    /**
     * El método para dibujar cualquier figura.
     * 
     * @param g2d
     */
    public abstract void draw(Graphics2D g2d);
    
    
}
