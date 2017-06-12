package com.seleniumsimplified.enums;

public enum OperativeSystem {
    Windows("win"),
    MacOS("mac");

    private String OS;

    OperativeSystem(String OS) {
        this.OS = OS;
    }

    public String toString() {
        return OS;
    }
}
