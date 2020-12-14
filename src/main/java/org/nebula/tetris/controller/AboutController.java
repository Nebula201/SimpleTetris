package org.nebula.tetris.controller;

import org.nebula.tetris.view.AboutView;

import javax.swing.*;

public class AboutController {
    protected AboutView aboutView;

    public AboutController(JFrame mainFrame) {
        // 创建界面
        aboutView = new AboutView(mainFrame);
    }

    public void show() {
        // 显示
        aboutView.show();
    }
}
