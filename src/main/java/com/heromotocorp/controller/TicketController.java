package com.heromotocorp.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.heromotocorp.entity.Ticket;
import com.heromotocorp.entity.User;
import com.heromotocorp.service.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@PostMapping("/populate")
	public ResponseEntity<HttpStatus> populateTickets() {
		ticketService.populateTickets();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/populate/users")
	public ResponseEntity<List<User>> populateUsers() {
		return ResponseEntity.ok(ticketService.populateUsers());
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<Set<Ticket>> getTicketsByUserId(@PathVariable Integer userId) {
		return ResponseEntity.ok(ticketService.getTicketsByUserId(userId));
	}

	@GetMapping("/available")
	public ResponseEntity<Set<Ticket>> getTicketsByUserId() {
		return ResponseEntity.ok(ticketService.getAvailableTickets());
	}

	@PostMapping("/book")
	public ResponseEntity<?> bookTickets(@RequestParam Integer userId, @RequestBody Set<Integer> ticketIds) {
		ticketService.bookTickets(userId, ticketIds);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
