package com.wsl.shoppingkill.common.exception;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author wsl
 */
public final class Exceptions {
    private Exceptions() {
    }

    public static RuntimeException unchecked(Exception e) {
        return e instanceof RuntimeException ? (RuntimeException) e : new RuntimeException(e.getMessage(), e);
    }

    public static String getStackTraceAsString(Throwable exception) {
        StringWriter sw;
        PrintWriter pw = null;

        String var3;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            exception.printStackTrace(pw);
            var3 = sw.toString();
        } finally {
            if (pw != null) {
                pw.close();
            }

        }

        return var3;
    }

    @SafeVarargs
    public static boolean isCausedBy(Exception ex, Class<? extends Exception>... causeExceptionClasses) {
        for (Throwable cause = ex.getCause(); cause != null; cause = cause.getCause()) {
            Class[] var3 = causeExceptionClasses;
            int var4 = causeExceptionClasses.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                Class<? extends Exception> causeClass = var3[var5];
                if (causeClass.isInstance(cause)) {
                    return true;
                }
            }
        }

        return false;
    }
}
