package com.PeterLiebhart.ImageTwister.Service;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ImageConverter {

    public void convert(HttpSession session) {

        byte[] imageBytes = (byte[]) session.getAttribute("original-image");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {

            BufferedImage inputImage = ImageIO.read(inputStream);
            ImageIO.write(inputImage, (String) session.getAttribute("desired-format"), outputStream);

            // Convert ByteArrayOutputStream to byte array
            byte[] outputImageBytes = outputStream.toByteArray();

            // Override the original image in the session with the converted one
            session.setAttribute("converted-image", outputImageBytes);

            System.out.println("Image converted successfully");

        } catch (IOException e) {
            System.out.println("Image conversion failed");
            e.printStackTrace();
        }
    }
}
