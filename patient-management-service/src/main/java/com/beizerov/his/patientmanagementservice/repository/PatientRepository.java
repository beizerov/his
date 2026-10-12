package com.beizerov.his.patientmanagementservice.repository;

import com.beizerov.his.patientmanagementservice.model.Patient;

import java.util.List;

/**
 * Repository interface for managing Patient entities.
 */
public interface PatientRepository {

    List<Patient> findAll();
}
