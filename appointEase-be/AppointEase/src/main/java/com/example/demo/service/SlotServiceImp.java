package com.example.demo.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Slot;
import com.example.demo.repository.SlotRepository;
import com.example.demo.service.interfaces.SlotService;

@Service
public class SlotServiceImp implements SlotService {

    private final SlotRepository slotRepository;

    @Autowired
    public SlotServiceImp(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    @Override
    public ResponseEntity<List<Slot>> getSlot() {
        List<Slot> slots = slotRepository.findAll();
        if (!slots.isEmpty()) {
            return ResponseEntity.ok(slots);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @Override
    public ResponseEntity<Slot> getSlot(Long id) {
        Optional<Slot> slot = slotRepository.findById(id);
        return slot.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @Override
    public ResponseEntity<Void> deleteSlot(Long id) {
        Optional<Slot> slot = slotRepository.findById(id);
        if (slot.isPresent()) {
            slotRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    public ResponseEntity<Slot> updateSlot(Long id, Slot updatedSlot) {
        Optional<Slot> slot = slotRepository.findById(id);
        if (slot.isPresent()) {
            Slot existingSlot = slot.get();
            
            existingSlot.setSlotStartTime(updatedSlot.getSlotStartTime());
            existingSlot.setSlotEndTime(updatedSlot.getSlotEndTime());

            Slot savedSlot = slotRepository.save(existingSlot);
            return ResponseEntity.ok(savedSlot);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @Override
    public ResponseEntity<List<Slot>> getSlotByDateTime(LocalDateTime dateTime) {
        List<Slot> nearestSlots = new ArrayList<>();
        int slotsNeeded = 5;
        LocalDateTime afterTime = adjustToValidTime(dateTime.plusHours(1));

        while (nearestSlots.size() < slotsNeeded) {
            if (!slotExistsOrIsFilled(afterTime)) {
                Slot newSlot = createOrFetchSlot(afterTime);
                nearestSlots.add(newSlot);
            }
            afterTime = adjustToValidTime(afterTime.plusHours(1));
        }
        
        nearestSlots.sort(Comparator.comparing(Slot::getSlotStartTime));

        return ResponseEntity.ok(nearestSlots);
    }

    private boolean slotExistsOrIsFilled(LocalDateTime slotStartTime) {
        Optional<Slot> slot = slotRepository.findBySlotStartTime(slotStartTime);
        return slot.isPresent() && slot.get().getIsFilled();
    }

    private Slot createOrFetchSlot(LocalDateTime slotStartTime) {
        Optional<Slot> existingSlot = slotRepository.findBySlotStartTime(slotStartTime);
        if (existingSlot.isPresent()) {
            return existingSlot.get();
        } else {
            Slot newSlot = new Slot(slotStartTime, slotStartTime.plusHours(1));
            slotRepository.save(newSlot);
            return newSlot;
        }
    }

    private LocalDateTime adjustToValidTime(LocalDateTime dateTime) {
        if (dateTime.getMinute() > 0 || dateTime.getSecond() > 0) {
            dateTime = dateTime.plusHours(1).withMinute(0).withSecond(0);
        } else {
            dateTime = dateTime.withMinute(0).withSecond(0);
        }

        if (dateTime.getHour() < 12) {
            dateTime = dateTime.withHour(12);
        } else if (dateTime.getHour() >= 19) {
            dateTime = dateTime.plusDays(1).withHour(12).withMinute(0);
        }

        while (isWeekend(dateTime)) {
            dateTime = dateTime.plusDays(1).withHour(12).withMinute(0);
        }

        return dateTime;
    }

    private boolean isWeekend(LocalDateTime dateTime) {
        return dateTime.getDayOfWeek() == DayOfWeek.SATURDAY || dateTime.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    @Override
    public ResponseEntity<Void> vacantSlot(Slot slot) {
        slot.setIsFilled(false);
        slotRepository.save(slot);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> fillSlot(Long id) {
        Optional<Slot> slot = slotRepository.findById(id);
        if (slot.isPresent()) {
            Slot currentSlot = slot.get();
            currentSlot.setIsFilled(true);
            slotRepository.save(currentSlot);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
