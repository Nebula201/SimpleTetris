package org.nebula.tetris.view;

import org.nebula.tetris.util.exception.ExceptionUtil;
import org.nebula.tetris.util.swing.SwingUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class AboutView {
    protected JDialog aboutDialog;
    protected JButton aboutOkButton;

    public AboutView(JFrame mainFrame) {
        SwingUtil.invokeLater(() -> {
            aboutDialog = new JDialog(mainFrame, "关于俄罗斯方块", false);
            aboutDialog.setResizable(false);

            JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            panel.setLayout(new BorderLayout());
            aboutDialog.add(panel);

            Box vBox = Box.createVerticalBox();
            panel.add(vBox, BorderLayout.NORTH);
            JPanel subPanel = new JPanel();
            vBox.add(subPanel);
            subPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            subPanel.setBackground(Color.WHITE);
            subPanel.setLayout(new BorderLayout());
            JLabel logo = new JLabel(new ImageIcon(SwingUtil.getImage("/org/nebula/tetris/images/icon_64.png")));
            logo.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            subPanel.add(logo, BorderLayout.WEST);
            Box subVBox = Box.createVerticalBox();
            subVBox.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 15));
            subPanel.add(subVBox, BorderLayout.EAST);
            Box hBox = Box.createHorizontalBox();
            subVBox.add(hBox);
            JLabel mainLabel = new JLabel("俄罗斯方块");
            mainLabel.setFont(UIManager.getFont("Label.font").deriveFont(Font.BOLD, 14));
            hBox.add(mainLabel);
            hBox.add(Box.createHorizontalGlue());
            hBox = Box.createHorizontalBox();
            subVBox.add(hBox);
            JPanel subSubPanel = new JPanel();
            hBox.add(subSubPanel);
            subSubPanel.setLayout(new GridLayout(2, 2));
            subSubPanel.setOpaque(false);
            subSubPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));

            String version = "0.0.1";
            String author = "Nebula W";

            try {
                Enumeration<URL> enumeration = AboutView.class.getClassLoader().getResources("META-INF/MANIFEST.MF");

                while (enumeration.hasMoreElements()) {
                    try (InputStream is = enumeration.nextElement().openStream()) {
                        Attributes attributes = new Manifest(is).getMainAttributes();
                        String attribute = attributes.getValue("JD-GUI-Version");

                        if (attribute != null) {
                            version = attribute;
                        }

                        attribute = attributes.getValue("JD-Core-Version");

                        if (attribute != null) {
                            author = attribute;
                        }
                    }
                }
            } catch (IOException e) {
                assert ExceptionUtil.printStackTrace(e);
            }

            subSubPanel.add(new JLabel("版本 "));
            subSubPanel.add(new JLabel(version));
            subSubPanel.add(new JLabel("作者"));
            subSubPanel.add(new JLabel(author));

            hBox.add(Box.createHorizontalGlue());

            hBox = Box.createHorizontalBox();
            hBox.add(new JLabel("Copyright © 2020  成都信息工程大学"));
            hBox.add(Box.createHorizontalGlue());
            subVBox.add(hBox);

            vBox.add(Box.createVerticalStrut(10));
            hBox = Box.createHorizontalBox();
            panel.add(hBox, BorderLayout.SOUTH);
            hBox.add(Box.createHorizontalGlue());
            aboutOkButton = new JButton("关闭");
            Action aboutOkActionListener = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    aboutDialog.setVisible(false);
                }
            };
            aboutOkButton.addActionListener(aboutOkActionListener);
            hBox.add(aboutOkButton);
            hBox.add(Box.createHorizontalGlue());

            // Last setup
            JRootPane rootPane = aboutDialog.getRootPane();
            rootPane.setDefaultButton(aboutOkButton);
            rootPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "AboutView.ok");
            rootPane.getActionMap().put("AboutView.ok", aboutOkActionListener);

            // Prepare to display
            aboutDialog.pack();
        });
    }

    public void show() {
        SwingUtil.invokeLater(() -> {
            aboutDialog.setLocationRelativeTo(aboutDialog.getParent());
            aboutDialog.setVisible(true);
            aboutOkButton.requestFocus();
        });
    }
}
