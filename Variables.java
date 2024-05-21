import java.awt.*;
import java.io.*;

public class Variables {
    public static Color lightBlue = new Color(23, 209, 249);
    public static Color darkBlue = new Color(7, 55, 108);
    public static Font boldFont, mediumFont;

    static {
        try {
            // Load the TTF file
            Font poppinsBold = Font.createFont(Font.TRUETYPE_FONT, new File("Poppins/Poppins-Bold.ttf"));
            Font poppinsMedium = Font.createFont(Font.TRUETYPE_FONT, new File("Poppins/Poppins-Medium.ttf"));

            // Register the font with the graphics environment
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(poppinsBold);
            ge.registerFont(poppinsMedium);

            // Create font instances with different sizes
            boldFont = poppinsBold.deriveFont(56f);
            mediumFont = poppinsMedium.deriveFont(19f);

        } catch (IOException | FontFormatException e) {
        }
    }
}
