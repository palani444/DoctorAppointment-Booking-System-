package com.example.doctorappointment.service;

import com.example.doctorappointment.entity.Patient;
import com.example.doctorappointment.repository.PatientRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientService {

    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public List<Patient> getAll() {
        return repository.findAll();
    }

    public Patient getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public Patient save(Patient patient) {
        return repository.save(patient);
    }

    public Patient update(Long id, Patient patient) {
        Patient existing = getById(id);
        existing.setName(patient.getName());
        existing.setPhone(patient.getPhone());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.delete(getById(id));
    }
}
