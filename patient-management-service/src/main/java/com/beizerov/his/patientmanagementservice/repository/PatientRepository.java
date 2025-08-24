package com.beizerov.his.patientmanagementservice.repository;

import com.beizerov.his.patientmanagementservice.model.Patient;

import java.util.List;

public interface PatientRepository {

    List<Patient> findAll();
}
