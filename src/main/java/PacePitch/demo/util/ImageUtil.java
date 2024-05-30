package PacePitch.demo.util;

import org.jcodec.common.model.Picture;

import java.awt.image.BufferedImage;

public class ImageUtil {

    public static BufferedImage toBufferedImage(Picture picture) {
        BufferedImage bufferedImage = new BufferedImage(picture.getWidth(), picture.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        byte[] data = picture.getPlaneData(0);
        byte[] data1 = picture.getPlaneData(1);
        byte[] data2 = picture.getPlaneData(2);
        int offset = 0;
        for (int y = 0; y < picture.getHeight(); y++) {
            for (int x = 0; x < picture.getWidth(); x++) {
                int r = data[offset];
                int g = data1[offset];
                int b = data2[offset];
                int rgb = (r << 16) | (g << 8) | b;
                bufferedImage.setRGB(x, y, rgb);
                offset++;
            }
        }
        return bufferedImage;
    }
}
