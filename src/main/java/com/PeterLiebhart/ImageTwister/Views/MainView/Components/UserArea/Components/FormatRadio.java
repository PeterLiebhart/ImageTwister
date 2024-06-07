package com.PeterLiebhart.ImageTwister.Views.MainView.Components.UserArea.Components;

import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class FormatRadio {

    public RadioButtonGroup<String> get(HttpSession httpSession) {

        RadioButtonGroup<String> formatRadio = new RadioButtonGroup<>();

        formatRadio.setLabel("Target format");
        formatRadio.setItems("PDF", "PNG", "BMP", "JPEG");

        formatRadio.addValueChangeListener(event -> {
            httpSession.setAttribute("desired-format", event.getValue().toLowerCase());
        });

        return formatRadio;
    }

}
