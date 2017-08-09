package com.android.practice.library;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by android.
 */

public class NotificationID {
    private final static AtomicInteger c = new AtomicInteger(0);

    public static int getID() {
        return c.incrementAndGet();
    }
}
