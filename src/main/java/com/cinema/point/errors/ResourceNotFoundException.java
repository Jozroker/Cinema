package com.cinema.point.errors;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceType, Long id) {
        super(resourceType + " with id " + id + " not found");
    }
}
