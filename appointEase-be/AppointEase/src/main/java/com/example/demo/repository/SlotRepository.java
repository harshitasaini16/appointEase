package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Slot;
import com.example.demo.entity.Traveller;
@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {
    
    @Query("SELECT s FROM Slot s WHERE s.slotStartTime >= :date AND s.isFilled IS false ORDER BY s.slotStartTime ASC")
    List<Slot> findNearestSlots(@Param("date") LocalDateTime date);
    
    @Query("SELECT s.slotStartTime FROM Slot s WHERE s.slotStartTime >= :date")
    List<LocalDateTime> findExistingStartTimes(@Param("date") LocalDateTime date);

	Optional<Slot>findBySlotStartTime(LocalDateTime slotStartTime);
}
