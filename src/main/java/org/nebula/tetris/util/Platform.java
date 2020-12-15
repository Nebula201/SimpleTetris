package org.nebula.tetris.util;

public enum Platform {
    Windows, MacOSX, Linux, Other;

    public final static Platform os = getOS();

    private static Platform getOS() {
//        System.out.println("System.getProperty(\"os.name\").toLowerCase()");
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("windows")) {
            return Windows;
        } else if (os.contains("mac os")) {
            return MacOSX;
        } else if (os.contains("linux")) {
            return Linux;
        } else {
            return Other;
        }
    }

    public static boolean isLinux() {
        return os == Linux;
    }

    public static boolean isWindows() {
        return os == Windows;
    }

    public static boolean isMacOSX() {
        return os == MacOSX;
    }
}
