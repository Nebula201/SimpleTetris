package org.nebula.tetris.view;

import org.nebula.tetris.Constants;
import org.nebula.tetris.model.configuration.Configuration;
import org.nebula.tetris.model.game.Shape;
import org.nebula.tetris.model.game.Tetromino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Collections;

import static org.nebula.tetris.util.swing.SwingUtil.*;

public class MainView {
    private JFrame mainFrame;

    public MainView(
            ActionListener aboutActionListener) {
        GameView gameView = new GameView();
        invokeLater(() -> {
            mainFrame = new JFrame("俄罗斯方块");
            mainFrame.add(gameView);
            gameView.add(new Tetromino(Shape.O));
            mainFrame.setIconImages(Collections.singletonList(getImage("/org/nebula/tetris/images/icon_64.png")));
            mainFrame.setMinimumSize(new Dimension(Constants.MINIMAL_WIDTH, Constants.MINIMAL_HEIGHT));
            mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            Action aboutAction = newAction("关于", true, "关于此游戏", aboutActionListener);

            // 菜单
            JMenuBar menuBar = new JMenuBar();
            JMenu menu = new JMenu("游戏");
            menuBar.add(menu);
//            menu.add(openAction).setAccelerator(KeyStroke.getKeyStroke('N', menuShortcutKeyMask));
            menu = new JMenu("帮助");
            menuBar.add(menu);
            menu.add(aboutAction).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));

            mainFrame.setJMenuBar(menuBar);
        });
    }

    public void show(Point location, Dimension size, boolean maximize) {
        invokeLater(() -> {
            // Set position, resize and show
            mainFrame.setLocation(location);
            mainFrame.setSize(size);
            mainFrame.setExtendedState(maximize ? JFrame.MAXIMIZED_BOTH : 0);
            mainFrame.setVisible(true);
        });
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }
}
