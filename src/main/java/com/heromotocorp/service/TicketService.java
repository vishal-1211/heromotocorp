package com.heromotocorp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.heromotocorp.constants.Category;
import com.heromotocorp.constants.Status;
import com.heromotocorp.entity.Ticket;
import com.heromotocorp.repo.TicketRepo;
import com.heromotocorp.repo.UserRepo;

@Service
public class TicketService {

	@Value("${tickets.total}")
	private int totalTickets;

	@Value("${tickets.premium}")
	private int premiumTickets;

	@Value("${tickets.basic}")
	private int basicTickets;

	@Value("${tickets.lower}")
	private int lowerTickets;

	@Autowired
	private TicketRepo ticketRepo;

	@Autowired
	private UserRepo userRepo;

	public void populateTickets() {
		List<Ticket> totalTickets = new ArrayList<>();
		totalTickets.addAll(populateTickets(premiumTickets, Category.PREMIUM));
		totalTickets.addAll(populateTickets(basicTickets, Category.BASIC));
		totalTickets.addAll(populateTickets(lowerTickets, Category.LOWER));

		ticketRepo.saveAll(totalTickets);
	}

	private List<Ticket> populateTickets(int threshold, Category category) {
		List<Ticket> tickets = new ArrayList<>();
		for (int i = 0; i < threshold; i++) {
			tickets.add(new Ticket(category.name(), category.getPrice(), Status.EMPTY.name()));
		}

		return tickets;
	}

}
