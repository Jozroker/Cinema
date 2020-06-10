package com.cinema.point.errors;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceType, Long id) {
        super(resourceType + " with id " + id + " not found");
    }

    public ResourceNotFoundException(String resourceType, String name) {
        super(resourceType + " with name " + name + " not found");
    }
}
