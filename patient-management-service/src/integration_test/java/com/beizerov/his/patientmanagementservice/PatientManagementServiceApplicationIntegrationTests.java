package com.beizerov.his.patientmanagementservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = PatientManagementServiceApplication.class)
@ActiveProfiles("integration")
class PatientManagementServiceApplicationIntegrationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() {

    }

    @Test
    public void testDatabaseInitialization() {
        // Perform a query to check if the database is correctly initialized
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM patient", Integer.class);

        assertNotNull(count);
        assertEquals(3, count);

        // Optionally, add more assertions to verify the database state
    }

}
