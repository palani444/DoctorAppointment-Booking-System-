package com.example.doctorappointment.controller;

import com.example.doctorappointment.entity.Slot;
import com.example.doctorappointment.service.SlotService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/slots")
public class SlotController {

    private final SlotService service;

    public SlotController(SlotService service) {
        this.service = service;
    }

    @GetMapping
    public List<Slot> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Slot getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Slot create(@RequestBody Slot slot) {
        return service.save(slot);
    }

    @PutMapping("/{id}")
    public Slot update(@PathVariable Long id, @RequestBody Slot slot) {
        return service.update(id, slot);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
