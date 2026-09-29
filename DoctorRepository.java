package com.example.doctorappointment.repository;

import com.example.doctorappointment.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
