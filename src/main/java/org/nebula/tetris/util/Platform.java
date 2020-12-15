package org.nebula.tetris.util;

public enum Platform {
    Windows, MacOSX, Linux, Other;

    public final static Platform OS = getOS();

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
        return OS == Linux;
    }

    public static boolean isWindows() {
        return OS == Windows;
    }

    public static boolean isMacOSX() {
        return OS == MacOSX;
    }
}
