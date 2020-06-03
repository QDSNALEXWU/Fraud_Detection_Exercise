package app;

import java.time.LocalDateTime;

public class Transaction {

	private String cardNumber;

	private LocalDateTime timestamp;

	private double amount;

	public Transaction(String cardNumber, LocalDateTime timestamp, double amount) {
		this.cardNumber = cardNumber;
		this.timestamp = timestamp;
		this.amount = amount;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	@Override
    public String toString() {
    	return "Transaction [cardNumber=" + cardNumber + ", amount=" + amount + ", time=" + timestamp + "]";
    }

}