package com.sismics.util;

import org.junit.Assert;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

public class TestImageUtil {

    @Test
    public void computeGravatarTest() {
        Assert.assertEquals("0bc83cb571cd1c50ba6f3e8a78ef1346", ImageUtil.computeGravatar("MyEmailAddress@example.com "));
    }

    @Test
    public void computeGravatarNullTest() {
        Assert.assertNull(ImageUtil.computeGravatar(null));
    }

    @Test
    public void computeGravatarCaseInsensitiveTest() {
        String email1 = "Test@Example.COM";
        String email2 = "test@example.com";
        Assert.assertEquals(ImageUtil.computeGravatar(email1), ImageUtil.computeGravatar(email2));
    }

    @Test
    public void writeJpegTest() throws Exception {
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageUtil.writeJpeg(image, outputStream);
        Assert.assertTrue(outputStream.size() > 0);
    }

    @Test
    public void writeJpegWithAlphaTest() throws Exception {
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageUtil.writeJpeg(image, outputStream);
        Assert.assertTrue(outputStream.size() > 0);
    }

    @Test
    public void isBlackBinaryImageTest() {
        BufferedImage binaryImage = new BufferedImage(10, 10, BufferedImage.TYPE_BYTE_BINARY);
        boolean result = ImageUtil.isBlack(binaryImage, 5, 5);
        Assert.assertTrue(result);
    }

    @Test
    public void isBlackRGBWhitePixelTest() {
        BufferedImage rgbImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
        rgbImage.setRGB(5, 5, 0xFFFFFF);
        boolean result = ImageUtil.isBlack(rgbImage, 5, 5);
        Assert.assertFalse(result);
    }

    @Test
    public void isBlackOutOfBoundsTest() {
        BufferedImage image = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
        boolean result = ImageUtil.isBlack(image, -1, -1);
        Assert.assertFalse(result);
    }
}
