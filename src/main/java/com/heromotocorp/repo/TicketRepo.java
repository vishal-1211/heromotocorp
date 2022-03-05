package com.heromotocorp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.heromotocorp.entity.Ticket;

@Repository
public interface TicketRepo extends CrudRepository<Ticket, Integer> {

}
