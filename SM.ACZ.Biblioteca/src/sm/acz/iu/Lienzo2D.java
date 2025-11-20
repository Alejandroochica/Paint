/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package sm.acz.iu;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import sm.acz.graficos.aCurva2D;
import sm.acz.graficos.aElipse2D;
import sm.acz.graficos.aLine2D;
import sm.acz.graficos.aRectangle2D;
import sm.acz.graficos.aShape;
import sm.acz.iu.Herramientas_dibujo;
import static sm.acz.iu.Herramientas_dibujo.CURVA;
import static sm.acz.iu.Herramientas_dibujo.ELIPSE;
import static sm.acz.iu.Herramientas_dibujo.LINEA;
import static sm.acz.iu.Herramientas_dibujo.RECTANGULO;
import sm.acz.iu.Lienzo2D;

/**
 *
 * @author alejandro
 */
public class Lienzo2D extends javax.swing.JPanel {

    private Herramientas_dibujo figura_select=Herramientas_dibujo.LINEA;;
    private ArrayList<aShape> listaFiguras = new ArrayList();
    private Point2D punto_presionado;
    private Point2D punto_final;
    private Point2D punto_control;
    private aShape forma;
    private boolean editar = false;
    private boolean relleno = false;
    private boolean alisar = false;
    private boolean transparencia = false;
    private int grosor;
    private Color color = Color.BLACK;
    public int paso = 0;
    private BufferedImage image;
    private boolean fijar = false;
    private boolean eliminar = false;
    private File sonidoFijar, sonidoEliminar;
    private String estado;
    
    
    
    /**
     * Creates new form Lienzo2D
     */
    public Lienzo2D() {
        initComponents();
        
    }
    
    /**
     *
     * Dibuja las figuras que haya en el array de listaFiguras.
     * 
     * @param g
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        
        if(image!=null)
            g2d.drawImage(image,0,0,this);
            
        
        for(aShape s: listaFiguras){
            if(!isEditar()){
                s.setEditar_objeto(false);
            }
            
            s.draw(g2d);
        }
        
    }

    /**
     * Devuelve la figura seleccionada de las herramientas posibles dadas
     * @return 
     */
    public Herramientas_dibujo getFigura_select() {
        return figura_select;
    }

    /**
     * Introduce la figura que se quiere dibujar.
     * @param figura_select 
     */
    public void setFigura_select(Herramientas_dibujo figura_select) {
        this.figura_select = figura_select;
    }
    
    /**
     * Comprueba si ha seleccionado o no una figura.
     * 
     * @param p
     * @return 
     */
    private aShape figuraSeleccionada(Point2D p){
        for(aShape s:listaFiguras)
        if(s.contains(p)) return s;
            return null;
    }

    /**
     * Devuelve si se esta editando o no.
     * @return editar
     */
    public boolean isEditar() {
        return this.editar;
    }

    /**
     * Introduce si se va a editar o no.
     * @param editar 
     */
    public void setEditar(boolean editar) {
        this.editar=editar;
    }

    /**
     * Devuelve si esta modo relleno o no.
     * @return relleno
     */
    public boolean isRelleno() {
        return this.relleno;
    }

    /**
     * Introduce si se va realizar relleno o no.
     * @param relleno 
     */
    public void setRelleno(boolean relleno) {
        this.relleno=relleno;
    }

    /**
     * Devuelve si esta en modo alisado o no.
     * @return alisar
     */
    public boolean isAlisar() {
        return this.alisar;
    }

    /**
     * Introduce si se esta utilizando el modo alisar o no.
     * @param alisar 
     */
    public void setAlisar(boolean alisar) {
        this.alisar=alisar;
    }

    /**
     * Devuelve si esta en modo transparencia o no.
     * @return transparencia
     */
    public boolean isTransparencia() {
        return this.transparencia;
    }

    /**
     * Introduce si se va a usar el modo transparencia o no.
     * @param transparencia 
     */
    public void setTransparencia(boolean transparencia) {
        this.transparencia=transparencia;
    }
    
    /**
     * Introduce el color de la figura dibujada
     * @param color 
     */
    public void setColor(Color color){
        this.color=color;
    }
    
    public Color getColor(){
        return this.color;
    }
    
    public int getGrosor(){
        return this.grosor;
    }
    
    public void setGrosor(int grosor){
        this.grosor = grosor;
    }

    /**
     * 
     * @return 
     */
    public BufferedImage getImage() {
        return image;
    }
    
    public BufferedImage getPaintedImage() {
      BufferedImage imgout = new BufferedImage(image.getWidth(),
                                               image.getHeight(),
                                               image.getType());

      Graphics2D g2dImagen = imgout.createGraphics();
      //g2dImagen.drawImage(image, 0, 0, this);

      if (image != null) g2dImagen.drawImage(image, 0, 0, this);
      
      for (aShape s : listaFiguras) {
          s.draw(g2dImagen); 
      }
      
      return imgout;
    }

    
    /**
     * 
     * @param image 
     */
    public void setImage(BufferedImage image) {
        this.image = image;
        if(image!=null) {
            setPreferredSize(new Dimension(image.getWidth(),image.getHeight()));
        }
    }

    /**
     * Comprueba si se ha presionado la opción de fijar
     * @return fijar
     */
    public boolean isFijar() {
        return fijar;
    }

    /**
     * Introduce si se va a fijar la figura o no.
     * @param fijar 
     */
    public void setFijar(boolean fijar) {
        this.fijar = fijar;
    }

    /**
     * Comprueba si se ha presionado la ocpión de eliminar
     * @return eliminar
     */
    public boolean isEliminar() {
        return eliminar;
    }

    /**
     * Establece si se va a elminar o no
     * @param eliminar 
     */
    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }

    /**
     * Devuelve la forma usada.
     * @return forma
     */
    public aShape getForma() {
        return forma;
    }

    /**
     * Introduce una nueva forma.
     * @param forma 
     */
    public void setForma(aShape forma) {
        this.forma = forma;
    }

    
    /**
     * Se encarga de restablecer el grupo de botones para que no coincidan botones a la vez.
     * 
     */
    public void reestablecer(){
        this.setEliminar(false);
        this.setFijar(false);
        this.setEditar(false);
        this.setFigura_select(Herramientas_dibujo.LINEA);
    }

    /**
     * Añadir el sonido de fijar
     * @param sonidoFijar 
     */
    public void setSonidoFijar(File sonidoFijar) {
        this.sonidoFijar = sonidoFijar;
    }

    /**
     * Añadir el sonido de eliminar
     * @param sonidoEliminar 
     */
    public void setSonidoEliminar(File sonidoEliminar) {
        this.sonidoEliminar = sonidoEliminar;
    }
    /**
     * Para reproducir los sonidos de cualquier archivo
     * @param archivoSonido 
     */
    public void reproducirSonido(File archivoSonido) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(archivoSonido);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error al reproducir sonido: " + e.getMessage());
        }
    }

    /**
     * Obtener el texto de la barra de estado.
     * @return estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Introducir el estado en la barra de estado.
     * @param estado 
     */
    /*public void setEstado(JLabel estado) {
        this.estado = estado;
    }*/
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        
        if(isEditar()){
            
            for (aShape f : listaFiguras) {
                f.setEditar_objeto(false);
            }
            
            forma = figuraSeleccionada(evt.getPoint());
            
            if (isEditar() && forma != null) {
                forma.setEditar_objeto(true);

            }
            else{
                forma=null;
            }
            
        }
        else if(!isEditar()){
                
                switch(figura_select){
                    
                    case LINEA:
                        punto_presionado = evt.getPoint();
                        Line2D linea = new Line2D.Float(punto_presionado, evt.getPoint());
                        forma = new aLine2D((Line2D.Float) linea);
                       
                    break;
                    case RECTANGULO:
                        Rectangle2D rectangulo = new Rectangle2D.Float();
                        forma = new aRectangle2D((Rectangle2D.Float) rectangulo);
                        punto_presionado = evt.getPoint();
                    break;
                    case ELIPSE:
                        Ellipse2D elipse = new Ellipse2D.Float();
                        forma = new aElipse2D((Ellipse2D.Float) elipse);
                        punto_presionado = evt.getPoint();
                    break;
                    case CURVA:
                        if(paso==0){
                            QuadCurve2D curva = new QuadCurve2D.Float();
                            forma = new aCurva2D((QuadCurve2D.Float) curva);
                            punto_presionado=evt.getPoint();
                        }
                    break;
                }
                if(forma!=null){
                    forma.setEditar_objeto(false); 
                    forma.setColor(getColor());
                    forma.setTrazo(getGrosor());
                    forma.setRelleno(isRelleno());
                    if (isTransparencia()) {
                        forma.setTransparencia(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                    } else {
                        forma.setTransparencia(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
                    }
                    if (isAlisar()) {
                        forma.setAlisado(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
                    } else {
                        forma.setAlisado(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF));
                    }

                    
                    listaFiguras.add(forma);
                }
                
                
                
        }
        
        this.repaint();
        
        
        
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
        if(!isEditar()){
            if(forma!=null){
                switch(figura_select){
                case LINEA:
                    
                    ((aLine2D)forma).getLocation().setLine(((aLine2D)forma).getLocation().getP1(), evt.getPoint());
                break;

                case RECTANGULO:
                     ((aRectangle2D)forma).getLocation().setFrameFromDiagonal(punto_presionado, evt.getPoint());
                     
                    //rectangulo.setFrameFromDiagonal(punto_presionado, evt.getPoint());
                break;

                case ELIPSE:
                    ((aElipse2D)forma).getLocation().setFrameFromDiagonal(punto_presionado,evt.getPoint());
                    //elipse.setFrameFromDiagonal(punto_presionado, evt.getPoint());
                break;
                
                case CURVA:
                    if(paso==0){
                        //punto_presionado=evt.getPoint();
                        //((aCurva2D)forma).getLocation().setCurve(punto_presionado, punto_presionado, punto_final);
                        ((aCurva2D)forma).setControl(punto_presionado, punto_presionado, evt.getPoint());                        
                    }
                    else if(paso==1){
                        ((aCurva2D)forma).setControl(punto_presionado, evt.getPoint(), punto_final);
                        //((aCurva2D)forma).getLocation().setCurve(punto_presionado, evt.getPoint(), punto_final);
                        
                    }
            }
            
                    
                    
            }
        }
        else{
            if(forma != null){
                if (forma!=null && forma instanceof aRectangle2D)
                    ((aRectangle2D)forma).setLocation(evt.getPoint());
                if(forma!=null && forma instanceof aLine2D)
                    ((aLine2D)forma).setLocation(evt.getPoint());
                if(forma!=null && forma instanceof aElipse2D)
                    ((aElipse2D)forma).setLocation(evt.getPoint());
                if(forma!=null && forma instanceof aCurva2D)
                    ((aCurva2D)forma).setLocation(evt.getPoint());
            }
            
        }
        
        this.repaint();
    }//GEN-LAST:event_formMouseDragged

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
        if(figura_select==Herramientas_dibujo.CURVA){
            if(paso==0){
                punto_final=evt.getPoint();
                paso=1;
            }
            else if(paso==1){
                paso=0;
               
            }
        }
    }//GEN-LAST:event_formMouseReleased

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        // TODO add your handling code here:
        aShape figura = figuraSeleccionada(evt.getPoint());
       
        if(isFijar()){
                       
            if(figura != null){
                
                figura.setFijada(true);
                
                if(image!=null){
                    Graphics2D g2d = image.createGraphics();
                    figura.draw(g2d);
                }
                
                listaFiguras.remove(figura);
                this.reproducirSonido(sonidoFijar);
            }
            
        }
        if(isEliminar()){
            
            if(figura!=null){
                listaFiguras.remove(figura);
                this.reproducirSonido(sonidoEliminar);
            }
          
        }
        
        BufferedImage img = this.getImage();
        if(img != null){
            int x = evt.getX();
            int y = evt.getY();
            if(x>=0 && y>=0 && x<img.getWidth() && y<img.getHeight()){
                Color color = new Color(img.getRGB(x, y),true);
                estado = "RGB: (" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ")";
            }
        }

        this.repaint();
    }//GEN-LAST:event_formMouseMoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
