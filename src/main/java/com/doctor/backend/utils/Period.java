package com.doctor.backend.utils;

public enum Period {
    HOUR("hour"),
    DAY("day"),
    WEEK("week");
    private String periodValue;

    Period(String period) {
        this.periodValue = period;
    }

    public String getUrl() {
        return periodValue;
    }
}
