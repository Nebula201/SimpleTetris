package org.nebula.tetris.service.platform;

public class PlatformService {
    protected static final PlatformService PLATFORM_SERVICE = new PlatformService();

    private static OS os;

    protected PlatformService() {
        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("windows")) {
            os = OS.WINDOWS;
        } else if (osName.contains("mac os")) {
            os = OS.MAC;
        } else {
            os = OS.LINUX;
        }
    }

    public static PlatformService getInstance() {
        return PLATFORM_SERVICE;
    }

    public OS getOs() {
        return os;
    }

    public boolean isLinux() {
        return os == OS.LINUX;
    }

    public boolean isWindows() {
        return os == OS.WINDOWS;
    }

    public boolean isMac() {
        return os == OS.MAC;
    }

    enum OS {
        WINDOWS,
        MAC,
        LINUX
    }
}
