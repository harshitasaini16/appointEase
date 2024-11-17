package com.example.demo.service;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Slot;
import com.example.demo.entity.User;
import com.example.demo.entity.Verification;
import com.example.demo.repository.VerificationRepository;
import com.example.demo.service.interfaces.AppointmentService;
import com.example.demo.service.interfaces.SlotService;
import com.example.demo.service.interfaces.VerificationService;
@Service
public class VerificationServiceImp implements VerificationService{

	private VerificationRepository verificationRepository;
	private AppointmentService appointmentService;
    private JavaMailSender mailSender;
    
    private SlotService slotService;

    @Autowired
	public VerificationServiceImp(JavaMailSender mailSender,VerificationRepository verificationRepository, AppointmentService appointmentService) {
		this.mailSender = mailSender;
		this.verificationRepository = verificationRepository;
		this.appointmentService = appointmentService;
	}

    private String generateVerificationCode() {
        Random random = new Random();
        int code = 1000 + random.nextInt(9000); 
        return String.valueOf(code);
    }
	
	@Override
	public ResponseEntity<Void> sendEmailVerificationCode(String email) {
		try {
            String code = generateVerificationCode();
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Your Verification Code");
            message.setText("Your verification code is: " + code);
            mailSender.send(message);
            Optional<Verification> verficationUser = this.verificationRepository.findById(email);
            if(!verficationUser.isPresent()) {
            	Verification user = new Verification(email, code);
            	this.verificationRepository.save(user);
            } else {
            	verficationUser.get().setCode(code);
            	this.verificationRepository.save(verficationUser.get());
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {

            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

	@Override
	public ResponseEntity<Void> verifyEmail(String email, String code) {
		// TODO Auto-generated method stub
		Optional<Verification> verficationUser = this.verificationRepository.findById(email);
		if(verficationUser.isPresent() && verficationUser.get().getCode().equals(code)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Void> sendConfirmationMail(Long appointmentId, String email) {
		// TODO Auto-generated method stub
        ResponseEntity<Appointment> appointment = this.appointmentService.getAppointment(appointmentId);
        if(appointment.getStatusCode() == HttpStatus.OK) {
        	Appointment appointmentBody = appointment.getBody();
        	SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Your Appointment is Confirmed");
            Slot slot = appointmentBody.getSlot();
            User user = appointmentBody.getUser();
            String text = "Hi, your appointment with email: "+user.getEmailId() +" is confirmend for "+
            				slot.getSlotStartTime().format(DateTimeFormatter.ISO_DATE_TIME)+" - "+
            				slot.getSlotEndTime().format(DateTimeFormatter.ISO_DATE_TIME);
            message.setText(text);
        	mailSender.send(message);
        	return ResponseEntity.ok().build();
        }
		return ResponseEntity.notFound().build();
	}

}
