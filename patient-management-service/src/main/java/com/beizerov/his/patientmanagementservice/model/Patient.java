package com.beizerov.his.patientmanagementservice.model;

import java.util.UUID;

public record Patient (
    UUID id,
    String firstName,
    String lastName,
    String email,
    String phoneNumber,
    String address
){
    @Override
    public String toString() {
        return "Patient{id=%s}".formatted(id);
    }
}
