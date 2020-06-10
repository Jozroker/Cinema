package com.cinema.point.errors;

public class ResoursNotFoundException extends RuntimeException {

    public ResoursNotFoundException(String resourceType, Long id) {
        super(resourceType + " with id " + id + " not found");
    }
}
