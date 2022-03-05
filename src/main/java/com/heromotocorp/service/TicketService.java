package com.heromotocorp.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.heromotocorp.constants.Category;
import com.heromotocorp.constants.Status;
import com.heromotocorp.entity.Ticket;
import com.heromotocorp.entity.User;
import com.heromotocorp.exception.TicketException;
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

	/**
	 * Populate users
	 */
	public List<User> populateUsers() {
		List<User> users = new ArrayList<>();
		String[] randomNames = { "Ramesh", "Suresh", "Gopal", "Satish", "Nivedita", "Ali", "Aksa" };
		for (String name : randomNames) {
			users.add(new User(name));
		}

		return (List<User>) userRepo.saveAll(users);
	}

	/**
	 * Populate the tickets initially
	 */
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

	/**
	 * Book tickets for user
	 * 
	 * @param userId
	 * @param ticketIds
	 */
	public void bookTickets(Integer userId, Set<Integer> ticketIds) {
		Optional<User> user = userRepo.findById(userId);
		List<Ticket> tickets = (List<Ticket>) ticketRepo.findAllById(ticketIds);

		validateBooking(ticketIds, user, tickets.stream().collect(Collectors.toSet()));

		User matchedUser = user.get();
		tickets.forEach(data -> {
			data.setStatus(Status.BOOKED.name());
			data.setUser(matchedUser);
		});
		matchedUser.setTickets(tickets.stream().collect(Collectors.toSet()));

		userRepo.save(matchedUser);
	}

	private void validateBooking(Set<Integer> ticketIds, Optional<User> user, Set<Ticket> tickets)
			throws TicketException {
		if (!user.isPresent()) {
			throw new TicketException("Invalid user id");
		}

		if (ticketIds.isEmpty() || ticketIds.size() != tickets.size()) {
			throw new TicketException("Invalid tickets requested");
		}

		if (tickets.stream().anyMatch(data -> data.getStatus().equals(Status.BOOKED.name()))) {
			throw new TicketException("Ticket(s) are already booked");
		}
	}

	/**
	 * Get tickets by user id
	 * 
	 * @param userId
	 * @return
	 */
	public Set<Ticket> getTicketsByUserId(Integer userId) {
		Optional<User> user = userRepo.findById(userId);
		return user.isPresent() ? user.get().getTickets() : new HashSet<>();
	}

	/**
	 * Get available tickets
	 * 
	 * @return
	 */
	public Set<Ticket> getAvailableTickets() {
		return ticketRepo.getAvailableTickets();
	}
}
