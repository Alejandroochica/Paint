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
import sm.image.BufferedImageOpAdapter;

/**
 *
 * @author alejandro
 */
public class OscurecerOp extends BufferedImageOpAdapter{
    float factor;
    
    public OscurecerOp(float factor){
        this.factor = factor;
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
        
        for(int x=0; x<srcRaster.getWidth(); x++){
            for(int y=0; y<srcRaster.getHeight(); y++){
                srcRaster.getPixel(x,y,pixelComp);
               
                for(int i=0; i<3; i++){
                    pixelCompDest[i]=(int) (pixelComp[i]*factor);
                }
                
                
                destRaster.setPixel(x, y, pixelCompDest);
            }
        }
        
        return dest;
    }
}
