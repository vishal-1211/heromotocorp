package com.heromotocorp.constants;

public enum Category {

	PREMIUM(10000), BASIC(5000), LOWER(1000);

	private Category(double price) {
		this.price = price;
	}

	private double price;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
