package com.beizerov.his.patientmanagementservice;

import com.beizerov.his.patientmanagementservice.model.Patient;
import com.beizerov.his.patientmanagementservice.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("integration")
class PatientManagementServiceApplicationIntegrationTests {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testFindAllReturnsPatients() {
        List<Patient> patients = patientRepository.findAll();
        assertThat(patients).isNotNull();
        assertThat(patients).isNotEmpty();

        Patient firstPatient = patients.getFirst();
        assertThat(firstPatient.getId()).isNotNull();
        assertThat(firstPatient.getFirstName()).isNotEmpty();
        assertThat(firstPatient.getFirstName()).isEqualTo("John");
    }
}
