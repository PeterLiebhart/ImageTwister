package com.PeterLiebhart.ImageTwister.Views.MainView.Components.UserArea.Components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.server.StreamResource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public class DownloadButton {

    public Anchor get(HttpSession httpSession) {

        StreamResource resource = getStreamResource(httpSession);

        Anchor downloadLink = new Anchor(resource, "");
        downloadLink.getElement().setAttribute("download", true);

        Button downloadButton = new Button("Download File");
        downloadLink.add(downloadButton);
        return downloadLink;
    }

    //Only gets called at first open. Needs to be called every time format changes.
    private StreamResource getStreamResource(HttpSession httpSession) {

        return new StreamResource(httpSession.getAttribute("original-file-name-without-extension") + "." + httpSession.getAttribute("desired-format"), () -> new ByteArrayInputStream((byte[]) httpSession.getAttribute("converted-image")));

    }
}
