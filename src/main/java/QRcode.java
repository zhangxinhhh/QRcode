import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class QRcode {

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    public static String GetString(){
        Scanner scan = new Scanner(System.in);
        System.out.println("输入字符串:");
        String text = scan.nextLine();
        return text;
    }

    public static String GetPath(){
        File root = new File(".");

        try{
            String rootDir=root.getCanonicalPath();
            System.out.println("当前工程所在文件夹："+rootDir);
            return rootDir;
        }

        catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }



    public static void buildQuickMark(String content, String path, String filename) throws Exception {
        try {
            BitMatrix byteMatrix = new MultiFormatWriter().encode(new String(content.getBytes(), "iso-8859-1"),
                    BarcodeFormat.QR_CODE, 200, 200);
            String format = "png";

            File file = new File(path+"\\"+filename+"."+format);
            BufferedImage image = toBufferedImage(byteMatrix);

            if (!ImageIO.write(image, format, file)) {
                throw new IOException("Could not write an image of format " + format + " to " + file);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

}
