package org.nebula.tetris.util.swing;

import org.nebula.tetris.util.exception.ExceptionUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public abstract class SwingUtil {

    public static void installGtkPopupBugWorkaround() {
        // Get current look-and-feel implementation class
        LookAndFeel laf = UIManager.getLookAndFeel();
        Class<?> lafClass = laf.getClass();

        // Do nothing when not using the problematic LaF
        if (!lafClass.getName().equals("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")) return;

        // We do reflection from here on. Failure is silently ignored. The
        // workaround is simply not installed when something goes wrong here
        try {
            // Access the GTK style factory
            Field field = lafClass.getDeclaredField("styleFactory");
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            Object styleFactory = field.get(laf);
            field.setAccessible(accessible);

            // Fix the horizontal and vertical thickness of popup menu style
            Object style = getGtkStyle(styleFactory, new JPopupMenu(), "POPUP_MENU");
            fixGtkThickness(style, "yThickness");
            fixGtkThickness(style, "xThickness");

            // Fix the vertical thickness of the popup menu separator style
            style = getGtkStyle(styleFactory, new JSeparator(), "POPUP_MENU_SEPARATOR");
            fixGtkThickness(style, "yThickness");
        } catch (Exception e) {
            // Silently ignored. Workaround can't be applied.
            assert ExceptionUtil.printStackTrace(e);
        }
    }

    private static void fixGtkThickness(Object style, String fieldName) throws Exception {
        Field field = style.getClass().getDeclaredField(fieldName);
        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        field.setInt(style, Math.max(1, field.getInt(style)));
        field.setAccessible(accessible);
    }

    private static Object getGtkStyle(Object styleFactory, JComponent component, String regionName) throws Exception {
        // Create the region object
        Class<?> regionClass = Class.forName("javax.swing.plaf.synth.Region");
        Field field = regionClass.getField(regionName);
        Object region = field.get(regionClass);

        // Get and return the style
        Class<?> styleFactoryClass = styleFactory.getClass();
        Method method = styleFactoryClass.getMethod("getStyle", JComponent.class, regionClass);
        boolean accessible = method.isAccessible();
        method.setAccessible(true);
        Object style = method.invoke(styleFactory, component, region);
        method.setAccessible(accessible);
        return style;
    }

    public static void invokeLater(Runnable runnable) {
        if (SwingUtilities.isEventDispatchThread()) {
            runnable.run();
        } else {
            SwingUtilities.invokeLater(runnable);
        }
    }

    public static Image getImage(String iconPath) {
        return Toolkit.getDefaultToolkit().getImage(SwingUtil.class.getResource(iconPath));
    }

    public static ImageIcon newImageIcon(String iconPath) {
        return new ImageIcon(getImage(iconPath));
    }

    public static Action newAction(String name, boolean enable, ActionListener listener) {
        Action action = new AbstractAction(name) {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                listener.actionPerformed(actionEvent);
            }
        };
        action.setEnabled(enable);
        return action;
    }

    public static Action newAction(String name, ImageIcon icon, boolean enable, ActionListener listener) {
        Action action = newAction(name, enable, listener);
        action.putValue(Action.SMALL_ICON, icon);
        return action;
    }

    public static Action newAction(ImageIcon icon, boolean enable, ActionListener listener) {
        Action action = newAction(null, icon, enable, listener);
        action.putValue(Action.SMALL_ICON, icon);
        return action;
    }

    public static Action newAction(String name, ImageIcon icon, boolean enable, String shortDescription, ActionListener listener) {
        Action action = newAction(name, icon, enable, listener);
        action.putValue(Action.SHORT_DESCRIPTION, shortDescription);
        return action;
    }

    public static Action newAction(String name, boolean enable, String shortDescription, ActionListener listener) {
        Action action = newAction(name, enable, listener);
        action.putValue(Action.SHORT_DESCRIPTION, shortDescription);
        return action;
    }
}
