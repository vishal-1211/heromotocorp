package com.heromotocorp.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.heromotocorp.entity.Ticket;

@Repository
public interface TicketRepo extends CrudRepository<Ticket, Integer> {

	@Query("SELECT t FROM Ticket t WHERE t.status = 'EMPTY'")
	public Set<Ticket> getAvailableTickets();

}
