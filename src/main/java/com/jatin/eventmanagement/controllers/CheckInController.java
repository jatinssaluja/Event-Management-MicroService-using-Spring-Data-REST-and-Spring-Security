package com.jatin.eventmanagement.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jatin.eventmanagement.controllers.exceptions.AlreadyCheckedInException;
import com.jatin.eventmanagement.entities.Participant;
import com.jatin.eventmanagement.repos.ParticipantRepository;

@RepositoryRestController
@RequestMapping("/participants")
public class CheckInController {
	
	@Autowired
	private ParticipantRepository participantRepository;
	
	@PostMapping("/checkin/{id}")
	public ResponseEntity<PersistentEntityResource> checkIn(@PathVariable Long id, PersistentEntityResourceAssembler pers) {
		
		Participant participant = participantRepository.findById(id).orElseThrow(()->new ResourceNotFoundException());
		
		if(participant.getCheckedIn())
		{
			throw new AlreadyCheckedInException();
		} else {
			
			participant.setCheckedIn(true);
			participantRepository.save(participant);
		}
		
		
		return ResponseEntity.ok(pers.toResource(participant));
	}

}
