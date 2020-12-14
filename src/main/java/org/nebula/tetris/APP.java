package org.nebula.tetris;

import org.nebula.tetris.controller.MainController;
import org.nebula.tetris.model.configuration.Configuration;

import javax.swing.*;

public class APP {
    protected static MainController controller;

    public static void main(String[] args) {
        if (checkHelpFlag(args)) {
            JOptionPane.showMessageDialog(null, "Usage: jd-gui [option] [input-file] ...\n\nOption:\n -h Show this help message and exit", Constants.APP_NAME, JOptionPane.INFORMATION_MESSAGE);
        } else {
            controller = new MainController();
            controller.show();
        }
    }


    protected static boolean checkHelpFlag(String[] args) {
        if (args != null) {
            for (String arg : args) {
                if ("-h".equals(arg)) {
                    return true;
                }
            }
        }
        return false;
    }
}
