package org.nebula.tetris.controller;

import org.nebula.tetris.util.swing.SwingUtil;
import org.nebula.tetris.view.MainView;

import javax.swing.*;
import java.awt.*;

public class MainController {
    //    private Configuration configuration;
    private MainView mainView;

    private AboutController aboutController;

    public MainController() {
        mainView = new MainView(e -> onAbout());
    }

    protected void onAbout() {
        aboutController.show();
    }

    public void show() {
        SwingUtil.invokeLater(() -> {
            mainView.show(new Point(300, 300), new Dimension(600, 800), false);
            JFrame mainFrame = mainView.getMainFrame();
            aboutController = new AboutController(mainFrame);
        });
    }
}
