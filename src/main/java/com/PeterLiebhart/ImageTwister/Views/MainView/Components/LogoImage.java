package com.PeterLiebhart.ImageTwister.Views.MainView.Components;

import com.vaadin.flow.component.html.Image;
import org.springframework.stereotype.Service;

@Service
public class LogoImage {

    public Image get(){
        Image logoImage = new Image("logo.png", "logo");
        logoImage.setWidth("50%");
        return logoImage;
    }
}
