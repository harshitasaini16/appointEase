package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Traveller;
@Repository
public interface TravellerRepository extends JpaRepository<Traveller, Long>{
	List<Traveller> findByAppointmentId(Long appointmentId);
}
