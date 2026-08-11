package com.beizerov.his.patientmanagementservice;

import com.beizerov.his.patientmanagementservice.model.Patient;
import com.beizerov.his.patientmanagementservice.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
@ActiveProfiles("integration")
class PatientManagementServiceApplicationIntegrationTests {

    @Autowired
    private PatientRepository patientRepository;

    @Container
    static final PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:17");

    @DynamicPropertySource
    static void configureDatasource(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.datasource.driver-class-name", postgres::getDriverClassName);
    }

    @Test
    public void testFindAllReturnsPatients() {
        List<Patient> patients = patientRepository.findAll();
        assertThat(patients).isNotNull();
        assertThat(patients).isNotEmpty();

        Patient firstPatient = patients.getFirst();
        assertThat(firstPatient.id()).isNotNull();
        assertThat(firstPatient.firstName()).isNotEmpty();
        assertThat(firstPatient.firstName()).isEqualTo("John");
    }
}
