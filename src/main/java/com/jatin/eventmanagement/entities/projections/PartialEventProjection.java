package com.jatin.eventmanagement.entities.projections;

import org.springframework.data.rest.core.config.Projection;

import com.jatin.eventmanagement.entities.Event;
import java.time.ZonedDateTime;

@Projection(name="partial", types= {Event.class})
public interface PartialEventProjection {

	String getName();
	ZonedDateTime getStartTime();
	ZonedDateTime getEndTime();
	
}
