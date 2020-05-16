package com.pad.coctelapp.util;

/** Represents amounts for ingredients
 */
public class Amount {
    private String desc;

    public Amount(String description) {
        this.desc = description;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
