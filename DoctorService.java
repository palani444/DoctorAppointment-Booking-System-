package com.example.doctorappointment.service;

import com.example.doctorappointment.entity.Doctor;
import com.example.doctorappointment.repository.DoctorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository repository;

    public DoctorService(DoctorRepository repository) {
        this.repository = repository;
    }

    public List<Doctor> getAll() {
        return repository.findAll();
    }

    public Doctor getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public Doctor save(Doctor doctor) {
        return repository.save(doctor);
    }

    public Doctor update(Long id, Doctor doctor) {
        Doctor existing = getById(id);
        existing.setName(doctor.getName());
        existing.setSpecialization(doctor.getSpecialization());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.delete(getById(id));
    }
}
