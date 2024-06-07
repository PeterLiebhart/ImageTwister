package com.PeterLiebhart.ImageTwister.Views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.Route;

import java.io.InputStream;

@Route("home")
public class MainView extends VerticalLayout {

    public MainView() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();

        RadioButtonGroup<String> formatRadio = getFormatRadio();
        Upload upload = getUploadForm();
        Button downloadButton = getDownloadButton();

        verticalLayout.add(new H1("Image Twister"), formatRadio, upload, downloadButton);
        verticalLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        verticalLayout.getStyle().set("gap", "4vh");

        add(verticalLayout);
    }

    private RadioButtonGroup<String> getFormatRadio() {
        RadioButtonGroup<String> formatRadio = new RadioButtonGroup<>();
        formatRadio.setLabel("Target format");
        formatRadio.setItems("PDF", "TIFF", "BMP", "JPEG");
        return formatRadio;
    }

    private Upload getUploadForm() {
        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        Upload upload = new Upload(buffer);

        upload.addSucceededListener(event -> {
            String fileName = event.getFileName();
            InputStream inputStream = buffer.getInputStream(fileName);

            // Do something with the file data
            // processFile(inputStream, fileName);
        });
        return upload;
    }

    private Button getDownloadButton() {
        Button button = new Button("Download converted image");

        return button;
    }
}
