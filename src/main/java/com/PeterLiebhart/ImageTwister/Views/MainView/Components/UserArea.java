package com.PeterLiebhart.ImageTwister.Views.MainView.Components;

import com.PeterLiebhart.ImageTwister.Service.ImageConverter;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.spring.annotation.UIScope;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@UIScope
@Component
public class UserArea {

    private final ImageConverter imageConverter;
    private final VerticalLayout layout;
    private Anchor downloadButton;
    private Upload uploadForm;

    @Autowired
    public UserArea(ImageConverter imageConverter) {
        this.imageConverter = imageConverter;
        this.layout = new VerticalLayout();
    }

    public VerticalLayout get(HttpSession httpSession) {

        layout.setWidth("50%");
        layout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        layout.removeAll(); // Clear previous components
        layout.add(
                new H1("Convert your image"),
                getFormatRadio(httpSession),
                getUploadForm(httpSession),
                getConvertButton(httpSession),
                getDownloadButton(httpSession)
        );

        return layout;
    }

    public Button getConvertButton(HttpSession httpSession) {
        Button button = new Button("Convert your image");
        button.addClickListener(event -> imageConverter.convert(httpSession));
        return button;
    }

    public Anchor getDownloadButton(HttpSession httpSession) {
        StreamResource resource = new StreamResource(
                "converted-image" + "." + httpSession.getAttribute("desired-format"),
                () -> new ByteArrayInputStream((byte[]) httpSession.getAttribute("converted-image"))
        );

        Anchor downloadLink = new Anchor(resource, "");
        downloadLink.getElement().setAttribute("download", true);
        Button downloadButton = new Button("Download File");
        downloadLink.add(downloadButton);
        this.downloadButton = downloadLink; // Store reference to the download button
        return downloadLink;
    }

    public RadioButtonGroup<String> getFormatRadio(HttpSession httpSession) {
        RadioButtonGroup<String> formatRadio = new RadioButtonGroup<>();
        formatRadio.setLabel("Target format");
        formatRadio.setItems("TIFF", "PNG", "BMP", "JPEG");

        formatRadio.addValueChangeListener(event -> {
            httpSession.setAttribute("desired-format", event.getValue().toLowerCase());
            layout.remove(downloadButton); // Remove the existing download button
            downloadButton = getDownloadButton(httpSession); // Recreate the download button
            layout.add(downloadButton); // Add the new download button
        });

        return formatRadio;
    }

    public Upload getUploadForm(HttpSession httpSession) {
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

            httpSession.setAttribute("original-image", imageByteArray);
        });
        upload.setAcceptedFileTypes("image/jpeg", "image/png", "image/jpg", "image/jpeg", "image/bmp", "image/tiff");
        return upload;
    }

}
