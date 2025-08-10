package com.beizerov.his.patientmanagementservice.mapper;

import com.beizerov.his.patientmanagementservice.model.Patient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PatientMapper {

    @Select("SELECT id, first_name, last_name, email, phone_number, address FROM patient")
    List<Patient> findAll();
}
