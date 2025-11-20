/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sm.acz.imagen;

import java.awt.Color;
import static java.awt.Color.HSBtoRGB;
import static java.awt.Color.RGBtoHSB;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import static java.lang.Math.abs;
import sm.image.BufferedImageOpAdapter;

/**
 *
 * @author alejandro
 */
public class TonoColorOp extends BufferedImageOpAdapter {
    private int umbral;
    private Color C;
    private int desplazamiento;
    
    public TonoColorOp(int umbral, Color C, int desplazamiento){
        this.umbral=umbral;
        this.C=C;
        this.desplazamiento=desplazamiento;
    }
    
    @Override
    public BufferedImage filter(BufferedImage src,BufferedImage dest){
        if(src == null){
            throw new NullPointerException("src image is null");
        }
        if(dest == null){
            dest = createCompatibleDestImage(src, null);
        }
        WritableRaster srcRaster = src.getRaster();
        WritableRaster destRaster = dest.getRaster();
        int[] pixelComp = new int[srcRaster.getNumBands()];
        int[] pixelCompDest = new int[srcRaster.getNumBands()];
        
        //Sirve para el cambio de RGB a HSB y de HSB a RGB
        int cambio = 360;
        
        //Array para guardar los valores de hsb
        float[] hsbvals_colordado = new float[3];
        float[] hsbvals_c = new float[3];
        
        //Color C en HSB
        hsbvals_c = RGBtoHSB(C.getRed(),C.getGreen(),C.getBlue(),hsbvals_c);
        
        //Adaptamos los valores entre 0,360
        float H1=cambio*hsbvals_c[0];
        
        
        for(int x=0; x<srcRaster.getWidth(); x++){
            for(int y=0; y<srcRaster.getHeight(); y++){
                srcRaster.getPixel(x,y,pixelComp);
               
                //Convertimos a HSB los componentes RGB del pixel
                RGBtoHSB(pixelComp[0],pixelComp[1],pixelComp[2],hsbvals_colordado);
                
                //Adaptamos los valores entre 0-360
                float H2 = cambio*hsbvals_colordado[0];
                
                if(umbral>getDistancia(H1,H2)){
                    H2=(H2+desplazamiento)%360;
                }
                
                //Adaptamos los valores entre 0-1 para restablecer el pixel a rgb
                H2=H2/cambio;
                
                //Convertimos a RGB los componentes HSB del pixel
                int colorRGB=HSBtoRGB(H2,hsbvals_colordado[1],hsbvals_colordado[2]);
                
                //Añadimos al pixel destino los colores calculados
                pixelCompDest[0] = (colorRGB >> 16) & 0xFF;
                pixelCompDest[1] = (colorRGB >> 8) & 0xFF;
                pixelCompDest[2] = colorRGB & 0xFF;
                 
                
                destRaster.setPixel(x, y, pixelCompDest);
            }
        }
        
        return dest;
    }
    
    public float getDistancia(float H1, float H2){
        
        float distancia = 0;
        
        if(abs(H1-H2)<=180){
            distancia=abs(H1-H2);
        }
        else{
            distancia=360-abs(H1-H2);
        }
       
        return distancia;
    }
}
