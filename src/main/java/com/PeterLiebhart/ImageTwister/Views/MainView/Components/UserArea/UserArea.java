package com.PeterLiebhart.ImageTwister.Views.MainView.Components.UserArea;

import com.PeterLiebhart.ImageTwister.Views.MainView.Components.UserArea.Components.ConvertButton;
import com.PeterLiebhart.ImageTwister.Views.MainView.Components.UserArea.Components.FormatRadio;
import com.PeterLiebhart.ImageTwister.Views.MainView.Components.UserArea.Components.UploadForm;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class UserArea {

    private final ConvertButton convertButton;
    private final FormatRadio formatRadio;
    private final UploadForm uploadForm;

    public UserArea(ConvertButton convertButton, FormatRadio formatRadio, UploadForm uploadForm) {

        this.convertButton = convertButton;
        this.formatRadio = formatRadio;
        this.uploadForm = uploadForm;

    }

    public VerticalLayout get(HttpSession httpSession) {

        VerticalLayout layout = new VerticalLayout();

        layout.setWidth("50%");
        layout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        layout.add(
                new H1("Convert your image"),
                formatRadio.get(httpSession),
                uploadForm.get(httpSession),
                convertButton.get(httpSession)
        );

        return layout;
    }

}
