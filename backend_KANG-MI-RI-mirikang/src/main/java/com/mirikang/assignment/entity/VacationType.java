package com.mirikang.assignment.entity;


public enum VacationType {
    VACATION("연차"),
    HALF_VACATION("반차"),
    QUARTER_VACATION("반반차");

    private String title;

    VacationType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
