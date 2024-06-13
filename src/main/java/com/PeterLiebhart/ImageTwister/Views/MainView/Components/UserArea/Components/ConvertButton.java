package com.PeterLiebhart.ImageTwister.Views.MainView.Components.UserArea.Components;

import com.PeterLiebhart.ImageTwister.Service.ImageConverter;
import com.vaadin.flow.component.button.Button;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class ConvertButton {

    private final ImageConverter imageConverter;

    public ConvertButton(ImageConverter imageConverter) {
        this.imageConverter = imageConverter;
    }

    public Button get(HttpSession httpSession) {

        Button button = new Button("Convert your image");
        button.addClickListener(event -> {
            imageConverter.convert(httpSession);
        });

        return button;
    }
}
