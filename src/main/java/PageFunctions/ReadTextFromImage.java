package PageFunctions;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ReadTextFromImage {
    public static void main(String[] args) {
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:/Program Files/Tesseract-OCR/tessdata");


        tesseract.setLanguage("eng");

        File imageFile = new File("PNG.png");

        try {
            // Read the image file
            BufferedImage image = ImageIO.read(imageFile);

            // Optionally, enhance the image for better OCR results
            image = ImageHelper.convertImageToGrayscale(image);
            image = ImageHelper.getScaledInstance(image, image.getWidth() * 2, image.getHeight() * 2);

            // Perform OCR on the image
            String text = tesseract.doOCR(image);

            // Print the extracted text
            System.out.println("Extracted text: " + text);
        } catch (IOException e) {
            System.err.println("Error reading the image file: " + e.getMessage());
        } catch (TesseractException e) {
            System.err.println("Error performing OCR: " + e.getMessage());
        }
    }
}
