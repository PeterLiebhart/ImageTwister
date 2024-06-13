package com.PeterLiebhart.ImageTwister.Views.MainView;

import com.PeterLiebhart.ImageTwister.Views.MainView.Components.LogoImage;
import com.PeterLiebhart.ImageTwister.Views.MainView.Components.UserArea.UserArea;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import jakarta.servlet.http.HttpSession;


@Route(value = "home")
public class MainView extends HorizontalLayout {

    private final UserArea userArea;
    private final LogoImage logoImage;

    public MainView(LogoImage logoImage, HttpSession httpSession, UserArea userAreaLayout) {

        this.userArea = userAreaLayout;
        this.logoImage = logoImage;

        createView(httpSession);
    }

    private void createView(HttpSession httpSession) {

        setSizeFull();
        setDefaultVerticalComponentAlignment(Alignment.CENTER);
        getStyle().set("gap", "3vh");
        add(userArea.get(httpSession), logoImage.get());
    }
}
