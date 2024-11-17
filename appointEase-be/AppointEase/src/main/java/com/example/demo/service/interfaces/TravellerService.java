package com.example.demo.service.interfaces;
import java.util.List;
import org.springframework.http.ResponseEntity;
import com.example.demo.entity.Traveller;

public interface TravellerService {
	public ResponseEntity<Long> addTraveller(List<Traveller> travellers, Long appointmentId);
	
	public ResponseEntity<List<Traveller>> getTraveller();
	
	public ResponseEntity<Traveller> getTraveller(Long id);
	
	public ResponseEntity<Void> deleteTraveller(Long id);
	
	public ResponseEntity<Traveller> updateTraveller(Long id, Traveller traveller); 
	
	public ResponseEntity<List<Traveller>> getTravellerByAppointmentId(Long id);
	
	public ResponseEntity<Void> deleteAllTravellers(List<Long> deletedTravellers);
}
