/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sm.acz.imagen;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import sm.image.BufferedImageOpAdapter;

/**
 *
 * @author alejandro
 */
public class RojoOp extends BufferedImageOpAdapter {

    private int umbral;

    public RojoOp(int umbral) {
        this.umbral = umbral;
    }

    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        if (src == null) {
            throw new NullPointerException("src image is null");
        }
        if (dest == null) {
            dest = createCompatibleDestImage(src, null);
        }
        WritableRaster srcRaster = src.getRaster();
        WritableRaster destRaster = dest.getRaster();
        int[] pixelComp = new int[srcRaster.getNumBands()];
        int[] pixelCompDest = new int[srcRaster.getNumBands()];

        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                srcRaster.getPixel(x, y, pixelComp);
                //Por hacer: efecto resaltar rojo
                int R = pixelComp[0];
                int G = pixelComp[1];
                int B = pixelComp[2];
                if(R-G-B>=umbral){
                    for(int i=0; i<pixelComp.length; i++){
                        pixelCompDest[i]=pixelComp[i];
                    }
                }
                else{
                    int media = (R+G+B)/3;
                    for(int i=0; i<pixelComp.length; i++){
                        pixelCompDest[i]=media;
                    }
                }
                destRaster.setPixel(x, y, pixelCompDest);
            }

        }
        return dest;
    }

}
