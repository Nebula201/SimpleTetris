package org.nebula.tetris.util.exception;

public abstract class ExceptionUtil {
    public static boolean printStackTrace(Throwable throwable) {
        throwable.printStackTrace();
        return true;
    }
}