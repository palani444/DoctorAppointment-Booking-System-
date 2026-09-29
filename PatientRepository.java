package com.example.doctorappointment.repository;

import com.example.doctorappointment.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
