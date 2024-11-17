package com.example.demo.service.interfaces;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.http.ResponseEntity;
import com.example.demo.entity.Slot;

public interface SlotService {
	public ResponseEntity<List<Slot>> getSlot();
	
	public ResponseEntity<Slot> getSlot(Long id);
	
	public ResponseEntity<Void> deleteSlot(Long id);
	
	public ResponseEntity<Slot> updateSlot(Long id,Slot slot);
	
	public ResponseEntity<List<Slot>> getSlotByDateTime(LocalDateTime dateTime);
	
	public ResponseEntity<Void> vacantSlot(Slot slot);
	
	public ResponseEntity<Void> fillSlot(Long id);
}
