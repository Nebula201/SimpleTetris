package org.nebula.tetris.model.configuration;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Configuration {
    protected Point mainWindowLocation;
    protected Dimension mainWindowSize;
    protected boolean mainWindowMaximize;

    protected String lookAndFeel;

    protected Map<String, String> preferences = new HashMap<>();

    public Point getMainWindowLocation() {
        return mainWindowLocation;
    }

    public Dimension getMainWindowSize() {
        return mainWindowSize;
    }

    public boolean isMainWindowMaximize() {
        return mainWindowMaximize;
    }

    public String getLookAndFeel() {
        return lookAndFeel;
    }

    public Map<String, String> getPreferences() {
        return preferences;
    }

    public void setLookAndFeel(String lookAndFeel) {
        this.lookAndFeel = lookAndFeel;
    }

    public void setPreferences(Map<String, String> preferences) {
        this.preferences = preferences;
    }


    public void setMainWindowLocation(Point mainWindowLocation) {
        this.mainWindowLocation = mainWindowLocation;
    }

    public void setMainWindowSize(Dimension mainWindowSize) {
        this.mainWindowSize = mainWindowSize;
    }

    public void setMainWindowMaximize(boolean mainWindowMaximize) {
        this.mainWindowMaximize = mainWindowMaximize;
    }
}
