package com.PeterLiebhart.ImageTwister.Views;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.AppShellSettings;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;

@Theme(variant = Material.DARK)
public class AppShellConfig implements AppShellConfigurator {

    @Override
    public void configurePage(AppShellSettings settings) {

        settings.setPageTitle("Image Twister");

        AppShellConfigurator.super.configurePage(settings);
    }
}
