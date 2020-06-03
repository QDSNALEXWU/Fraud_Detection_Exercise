package app;

import app.Transaction;
import app.FileReader;
import app.FradDetector;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;

public class Main {
  public static void main(String[] args) {
    if (args.length != 2) {
		System.err.println("Please provide a price threshold argument and a filename");
		System.exit(1);
	}

	/* parse price threshold */
	double priceThreshold;
	try {
		priceThreshold = Double.parseDouble(args[0]);
	} catch (NumberFormatException e) {
		System.err.println("Invalid price threshold argument, expect double");
		return;
	}

	List<Transaction> transactions = FileReader.readTransanctionsFromCSV(args[1]);
	
	findFrauds(transactions, priceThreshold);
  }

  public static void findFrauds(List<Transaction> transactions, double threshold ) {
  	
  	HashSet<String> fraudSet = new HashSet();
  	Map<String, FradDetector> detectors = new HashMap<String, FradDetector>();

  	for (Transaction t : transactions) {
  		String cardNum = t.getCardNumber();
  		
	  	/*Step over if a card has been detectd fraudulent*/
	  	if(fraudSet.contains(cardNum)) {
  			continue;
  		}

  		if(detectors.get(cardNum) == null ) {
  			detectors.put(cardNum, new FradDetector(t));
  		} else {
  			FradDetector detector = detectors.get(cardNum);
  			detector.addTransaction(t);
  			detectors.put(cardNum, detector);
  		}

  		if(detectors.get(cardNum).isFraudulent(threshold)) {
  			fraudSet.add(cardNum);
  		}

  	}

  	if(fraudSet.isEmpty()) {
  		System.out.println("No Fraud Detected");
  	} else {
  		/* print out the fradulent card numbers */
  		fraudSet.forEach(System.out::println);
  	}
  }

}