package app;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import app.Transaction;
import java.util.List;
import java.util.ArrayList;

public class FradDetector {

	private Double sum;

	private List<Transaction> transactions = new ArrayList<Transaction>();

	private final long windowLength = 24;

	public FradDetector(Transaction t) {
		this.sum = t.getAmount();
		this.transactions.add(t);
	}

	public void addTransaction(Transaction newTransaction) {
		/* pop all the transaction that happens before the given time window */
		Transaction cur = this.transactions.get(0);
		while (true) {
			long hoursDiff = ChronoUnit.HOURS.between(cur.getTimestamp(), newTransaction.getTimestamp());
			if(hoursDiff > this.windowLength) {
				this.transactions.remove(0);
				this.sum -= cur.getAmount();
				if(this.transactions.isEmpty()) {
					break;
				} else {
					cur = this.transactions.get(0);
				}
			} else {
				break;
			}
		}
		/* add new transaction */
		this.sum += newTransaction.getAmount();
		this.transactions.add(newTransaction);
	}

	public Boolean isFraudulent(double threshold) {
		return this.sum > threshold;
	}

}