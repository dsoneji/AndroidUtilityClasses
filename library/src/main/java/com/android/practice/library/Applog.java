package com.android.practice.library;

import android.util.Log;

public class Applog {
    public static boolean DEBUG = true;

    public static void e(String tag, String message) {
        if (DEBUG) {
            try {
                Log.e(tag, "-->" + message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void e(String tag, String message, Exception e) {
        if (DEBUG) {
            try {
                Log.e(tag, "-->" + message, e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void w(String tag, String message) {
        if (DEBUG) {
            try {
                Log.w(tag, "-->" + message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void d(String tag, String message) {
        if (DEBUG) {
            try {
                Log.d(tag, "-->" + message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void i(String tag, String message) {
        if (DEBUG) {
            try {
                Log.i(tag, "-->" + message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void wtf(String tag, String message) {
        if (DEBUG) {
            try {
                Log.wtf(tag, "-->" + message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void wtf(String tag, String message, Exception e) {
        if (DEBUG) {
            try {
                Log.wtf(tag, "-->" + message, e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void e(String tag, int value) {
        try {
            Applog.e(tag, String.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void e(String tag, float value) {
        try {
            Applog.e(tag, String.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void e(String tag, double value) {
        try {
            Applog.e(tag, String.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void e(String tag, boolean value) {
        try {
            Applog.e(tag, String.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void w(String tag, int value) {
        try {
            Applog.w(tag, String.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void w(String tag, float value) {
        try {
            Applog.w(tag, String.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void w(String tag, double value) {
        try {
            Applog.w(tag, String.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void w(String tag, boolean value) {
        try {
            Applog.w(tag, String.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void d(String tag, int value) {
        try {
            Applog.d(tag, String.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void d(String tag, float value) {
        try {
            Applog.d(tag, String.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void d(String tag, double value) {
        Applog.d(tag, String.valueOf(value));
    }

    public static void d(String tag, boolean value) {
        try {
            Applog.d(tag, String.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void i(String tag, int value) {
        try {
            Applog.i(tag, String.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void i(String tag, float value) {
        try {
            Applog.i(tag, String.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void i(String tag, double value) {
        try {
            Applog.i(tag, String.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void i(String tag, boolean value) {
        try {
            Applog.i(tag, String.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void wtf(String tag, int value) {
        try {
            Applog.wtf(tag, String.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void wtf(String tag, float value) {
        try {
            Applog.wtf(tag, String.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void wtf(String tag, double value) {
        try {
            Applog.wtf(tag, String.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void wtf(String tag, boolean value) {
        try {
            Applog.wtf(tag, String.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void enableLogging() {
        DEBUG = true;
    }
}
