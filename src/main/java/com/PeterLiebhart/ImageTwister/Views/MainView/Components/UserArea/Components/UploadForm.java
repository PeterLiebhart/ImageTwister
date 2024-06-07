package com.PeterLiebhart.ImageTwister.Views.MainView.Components.UserArea.Components;

import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UploadForm {

    public Upload get(HttpSession httpSession) {

        MemoryBuffer buffer = new MemoryBuffer();

        Upload upload = new Upload(buffer);

        upload.addSucceededListener(event -> {
            String fileName = event.getFileName();
            byte[] imageByteArray;

            try {
                imageByteArray = buffer.getInputStream().readAllBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            httpSession.setAttribute("original-file-name-without-extension", fileName.split("\\.")[0]);
            httpSession.setAttribute("original-image", imageByteArray);
            httpSession.setAttribute("original-file-name", fileName);
            System.out.println(httpSession.getAttribute("original-file-name"));
            System.out.println(httpSession.getAttribute("original-image"));
        });
        upload.setAcceptedFileTypes("image/jpeg", "image/png", "image/jpg", "image/jpeg");
        return upload;
    }
}
