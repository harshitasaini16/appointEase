package com.example.demo.entity;

import com.example.demo.enums.AppointmentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="appointment")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@Column(name="status")
	private AppointmentStatus status;
	
	@Column(name="is_payment_done")
	private boolean isPaymentDone;
	
    @OneToOne
    @JoinColumn(name = "slot_id", referencedColumnName = "id")
    private Slot slot;
	
	public Appointment() {
		this.status = AppointmentStatus.Initiated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isPaymentDone() {
		return isPaymentDone;
	}

	public void setPaymentDone(boolean isPaymentDone) {
		this.isPaymentDone = isPaymentDone;
	}
	
	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", user=" + user + ", status=" + status + ", isPaymentDone=" + isPaymentDone
				+ ", slot=" + slot + "]";
	}
	
}
