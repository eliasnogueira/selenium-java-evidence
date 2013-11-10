package com.eliasnogueira.selenium.evidence;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;


/**
 * Bean to store evidence message and image in a BASE64Decoder
 *
 * @author Elias Nogueira <elias.nogueira@gmai.com>
 */
public final class SeleniumEvidence {

    /**
     * Evidence message
     */
    private String message;

    /**
     * String returned by Selenium
     */
    private String imageString;

    /**
     * Image to use in evidence report
     */
    private BufferedImage image;

    /**
     * Constructor to create a new instance of the evidence data
     * @param message
     * @param imageString
     * @throws Exception
     */
    public SeleniumEvidence(String message, String imageString) throws Exception {
        setMessage(message);
        setImageString(imageString);
    }

    /**
     * Get the evidence message
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the evidence message
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Get the image
     * @return the image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Set the image
     * @param image the sel_image to set
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * Get the image in BASE64Decoder
     * @return the imageString
     */
    public String getImageStringg() {
        return imageString;
    }

    /**
     * Creates a ImageIO based on string coming to Selenium
     * @param sel_image_string the sel_image_string to set
     * @throws IOException if occurs any problem with the directory
     * @throws Exception if occurs any other problem with the code
     */
    public void setImageString(String imageString) throws IOException, Exception {
        this.imageString = imageString;
        setImage(ImageIO.read(new ByteArrayInputStream(toImage(imageString))));
    }

    /**
     * Transform the string returned in BASE64Decoder
     * @param string string returned by Selenium
     * @return an instance of BASE64Decoder
     * @throws Exception if the string is malformed or null
     */
    public static byte[] toImage(String string) throws Exception {
        return Base64.decodeBase64(string);
    }
}
