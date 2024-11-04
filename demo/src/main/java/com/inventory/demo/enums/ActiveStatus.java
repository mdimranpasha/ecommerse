package com.inventory.demo.enums;

public enum ActiveStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private final String value;

    ActiveStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }



}


