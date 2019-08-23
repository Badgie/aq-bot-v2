package util;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.tesseract.*;
import org.bytedeco.javacpp.lept.PIX;
//import org.bytedeco.leptonica.global.lept;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Tesseract utility class
 */
public class TessUtil {

    public String getImageText(BufferedImage target) throws IOException {
        TessBaseAPI tess = new TessBaseAPI();
        tess.Init(Util.getUsrDir() + "/tessdata/", "eng");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(target, "png", baos);
        byte[] bytes = baos.toByteArray();

        PIX img = lept.pixReadMemPng(bytes, bytes.length);

        tess.SetImage(img);

        tess.SetSourceResolution(70);

        BytePointer outText = tess.GetUTF8Text();
        String result = outText.getString();

        tess.End();
        outText.deallocate();
        img.destroy();

        return result;
    }
}
