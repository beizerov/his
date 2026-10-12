package com.beizerov.his.patientmanagementservice.repository.impl;

import com.beizerov.his.patientmanagementservice.mapper.PatientMapper;
import com.beizerov.his.patientmanagementservice.model.Patient;
import com.beizerov.his.patientmanagementservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of the PatientRepository interface using MyBatis.
 */
@Repository
@RequiredArgsConstructor
public class PatientRepositoryImpl implements PatientRepository {

    private final PatientMapper patientMapper;

    @Override
    public List<Patient> findAll() {
        return patientMapper.findAll();
    }
}
