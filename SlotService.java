package com.example.doctorappointment.service;

import com.example.doctorappointment.entity.Slot;
import com.example.doctorappointment.repository.SlotRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SlotService {

    private final SlotRepository repository;

    public SlotService(SlotRepository repository) {
        this.repository = repository;
    }

    public List<Slot> getAll() {
        return repository.findAll();
    }

    public Slot getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Slot not found"));
    }

    public Slot save(Slot slot) {
        return repository.save(slot);
    }

    public Slot update(Long id, Slot slot) {
        Slot existing = getById(id);
        existing.setDoctorId(slot.getDoctorId());
        existing.setDateTime(slot.getDateTime());
        existing.setAvailable(slot.isAvailable());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.delete(getById(id));
    }
}
