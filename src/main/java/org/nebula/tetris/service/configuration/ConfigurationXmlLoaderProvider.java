package org.nebula.tetris.service.configuration;

import org.nebula.tetris.Constants;
import org.nebula.tetris.model.configuration.Configuration;
import org.nebula.tetris.service.platform.PlatformService;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ConfigurationXmlLoaderProvider implements ConfigurationLoader {
    protected static final File FILE = getConfigFile();

    protected static File getConfigFile() {
        String configFilePath = System.getProperty(Constants.CONFIG_FILENAME);

        if (configFilePath != null) {
            File configFile = new File(configFilePath);
            if (configFile.exists()) {
                System.out.println(configFile.getPath());
                return configFile;
            }
        }

        if (PlatformService.getInstance().isLinux()) {
            String xdgConfigHome = System.getenv("XDG_CONFIG_HOME");
            if (xdgConfigHome != null) {
                File xdgConfigHomeFile = new File(xdgConfigHome);
                if (xdgConfigHomeFile.exists()) {
                    return new File(xdgConfigHomeFile, Constants.CONFIG_FILENAME);
                }
            }

            File userConfigFile = new File(System.getProperty("user.home"), ".config");
            if (userConfigFile.exists()) {
                return new File(userConfigFile, Constants.CONFIG_FILENAME);
            }
        } else if (PlatformService.getInstance().isWindows()) {
            String roamingConfigHome = System.getenv("APPDATA");
            if (roamingConfigHome != null) {
                File roamingConfigHomeFile = new File(roamingConfigHome);
                if (roamingConfigHomeFile.exists()) {
                    return new File(roamingConfigHomeFile, Constants.CONFIG_FILENAME);
                }
            }
        }

        return new File(Constants.CONFIG_FILENAME);
    }

    @Override
    public Configuration load() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int w = Math.min(screenSize.width, Constants.DEFAULT_WIDTH);
        int h = Math.min(screenSize.height, Constants.DEFAULT_HEIGHT);
        int x = (screenSize.width - w) / 2;
        int y = (screenSize.height - h) / 2;

        Configuration config = new Configuration();
        config.setMainWindowLocation(new Point(x, y));
        config.setMainWindowSize(new Dimension(w, h));
        config.setMainWindowMaximize(false);

        String defaultLaf = System.getProperty("swing.defaultlaf");

        config.setLookAndFeel((defaultLaf != null) ? defaultLaf : UIManager.getSystemLookAndFeelClassName());
        return config;
    }

    @Override
    public void save(Configuration configuration) {

    }
}
