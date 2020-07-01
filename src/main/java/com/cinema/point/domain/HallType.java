package com.cinema.point.domain;

public enum HallType {
    _2D("2D"),
    _3D("3D"),
    _4D("4D"),
    _IMAX("IMAX");

    private final String type;

    HallType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
