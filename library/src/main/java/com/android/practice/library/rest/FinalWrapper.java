package com.android.practice.library.rest;

public class FinalWrapper<T> {

    private final T value;

    public FinalWrapper(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
