package app;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import app.Transaction;


public class FileReader {

	/**
	 * read transanctions from a csv file
	 * @param  fileName file to read
	 * @return transaction list
	 */
  	public static List<Transaction> readTransanctionsFromCSV(String fileName) {
  		
  		List<Transaction> transactions = new ArrayList<Transaction>();
  		Path pathToFile = Paths.get(fileName);

  		try (BufferedReader br = Files.newBufferedReader(pathToFile,StandardCharsets.US_ASCII)) {
  			String line = br.readLine();
  			// read until end of file
  			while (line != null) {
  				String[] attributes = line.split(",");
  				Transaction transaction = createTransaction(attributes);
  				transactions.add(transaction);
  				line = br.readLine();
  			}
  		} catch (IOException ioe) {
        	ioe.printStackTrace();
    	}

    	return transactions;
  	}

  	/**
  	 * parse data from metadata
  	 * @param  row 
  	 * @return transaction
  	 */
  	private static Transaction createTransaction(String[] row) {
  		
  		String cardNumber = row[0];
  		LocalDateTime time = LocalDateTime.now();
  		Double amount = 0.0;

  		try {
  			time = LocalDateTime.parse(row[1].replaceAll("\\s",""), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
  		} catch (DateTimeParseException e) {
  			System.err.println("Mailformed date time : " + row[1]);
  			System.exit(1);
  		}
  		
  		try {
  			amount = Double.parseDouble(row[2]);
  		} catch (NumberFormatException e) {
  			System.err.println("Mailformed amount : " + row[2]);
  			System.exit(1);
  		}
  		
  		return new Transaction(cardNumber, time, amount);
  	}

}
