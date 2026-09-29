package com.example.doctorappointment.service;

import com.example.doctorappointment.entity.Appointment;
import com.example.doctorappointment.entity.Slot;
import com.example.doctorappointment.repository.AppointmentRepository;
import com.example.doctorappointment.repository.DoctorRepository;
import com.example.doctorappointment.repository.PatientRepository;
import com.example.doctorappointment.repository.SlotRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final SlotRepository slotRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              DoctorRepository doctorRepository,
                              PatientRepository patientRepository,
                              SlotRepository slotRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.slotRepository = slotRepository;
    }

    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    public Appointment getById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public Appointment book(Appointment appointment) {
        doctorRepository.findById(appointment.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        patientRepository.findById(appointment.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Slot slot = slotRepository.findById(appointment.getSlotId())
                .orElseThrow(() -> new RuntimeException("Slot not found"));
        if (!slot.isAvailable()) {
            throw new RuntimeException("Slot is already booked");
        }

        slot.setAvailable(false);
        slotRepository.save(slot);
        appointment.setAppointmentTime(slot.getDateTime());
        return appointmentRepository.save(appointment);
    }

    public void cancel(Long id) {
        Appointment appointment = getById(id);
        Slot slot = slotRepository.findById(appointment.getSlotId()).orElse(null);
        if (slot != null) {
            slot.setAvailable(true);
            slotRepository.save(slot);
        }
        appointmentRepository.delete(appointment);
    }
}
