package com.heromotocorp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "ticket")
public class Ticket {

	private Integer ticketId;

	private String category;

	private double price;

	private String status;

	private User user;

	public Ticket() {
	}

	public Ticket(String category, double price, String status) {
		this.category = category;
		this.price = price;
		this.status = status;
	}

	@Id
	@Column(length = 5, name = "ticket_id", nullable = false, updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	@Column(length = 10, name = "category", nullable = false)
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "price", nullable = false)
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(length = 10, name = "status", nullable = false)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	@JsonBackReference
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
