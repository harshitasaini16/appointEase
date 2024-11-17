package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="slot")
public class Slot {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="slot_start_time")
    private LocalDateTime slotStartTime;
    
    @Column(name="slot_end_time")
    private LocalDateTime slotEndTime;

    @Column(name="is_filled")
    private boolean isFilled;

    public Slot() {
        // Default constructor for JPA
    }

    public Slot(LocalDateTime slotStartTime, LocalDateTime slotEndTime) {
        this.slotStartTime = slotStartTime;
        this.slotEndTime = slotEndTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getSlotStartTime() {
        return slotStartTime;
    }

    public void setSlotStartTime(LocalDateTime slotTime) {
        this.slotStartTime = slotTime;
    }
    
    public LocalDateTime getSlotEndTime() {
        return slotEndTime;
    }

    public void setSlotEndTime(LocalDateTime slotTime) {
        this.slotEndTime = slotTime;
    }

	public boolean getIsFilled() {
		return isFilled;
	}

	public void setIsFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}

	@Override
	public String toString() {
		return "Slot [id=" + id + ", slotStartTime=" + slotStartTime + ", slotEndTime=" + slotEndTime + ", isFilled="
				+ isFilled + "]";
	}
    
}
