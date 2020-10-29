import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 * Writes data to the json file
 * @author Rhylen 'Jennifer' Nguyen
 *
 */
public class DataWriter {
	@SuppressWarnings("unchecked")
	public static void saveListings() {
		Server server = Server.getInstance();
		ArrayList<Listing> listings = server.getAllListings();
		JSONArray jsonListings = new JSONArray();
		
		for (int i=0; i< listings.size(); ++i) {
			jsonListings.add(getListingJSON(listings.get(i)));
		}
		
		try (FileWriter file = new FileWriter("src/listingtest.json")) {
			file.write(jsonListings.toJSONString());
			file.flush();
		} catch (IOException e) {
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void saveAccounts() {
		Server server = Server.getInstance();
		ArrayList<Account> accounts = new ArrayList<Account>();
		for (Account account : server.getAllAccounts()) {
			accounts.add(account);
		}
		JSONArray jsonAccounts = new JSONArray();
		
		for (int i=0; i< accounts.size(); ++i) {
			jsonAccounts.add(getAccountJSON(accounts.get(i)));
		}
		
		try (FileWriter file = new FileWriter("src/accounttest.json")) {
			file.write(jsonAccounts.toJSONString());
			file.flush();
		} catch (IOException e) {
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject getListingJSON(Listing listing) {
		JSONObject listingDetails = new JSONObject();
		listingDetails.put("name", listing.getName());
		listingDetails.put("address", listing.getAddress());
		listingDetails.put("description", listing.getDescription());
		listingDetails.put("rent", listing.getRent());
		listingDetails.put("bedrooms", listing.getBedrooms());
		JSONArray reviewJSON = new JSONArray();
		for (Review review : listing.getClassReviews()) {
			reviewJSON.add(getReviewJSON(review));
		}
		listingDetails.put("bathrooms", listing.getBathrooms());
		listingDetails.put("reviews", reviewJSON);
		listingDetails.put("rented", listing.isRented());
		
		return listingDetails;
	}
	@SuppressWarnings("unchecked")
	public static JSONObject getAccountJSON(Account account) {
		JSONObject accountDetails = new JSONObject();
		accountDetails.put("username", account.getUsername());
		accountDetails.put("firstName", account.getFirstName());
		accountDetails.put("lastName", account.getLastName());
		accountDetails.put("hashedPassword", account.getPassword());
		
		JSONArray messageBoxJSON = new JSONArray();
		for (String message : account.getMessageBox().getMessages()) {
			messageBoxJSON.add(message);
		}
	
		accountDetails.put("messagebox", messageBoxJSON);
		
			
		if (account instanceof StudentAccount) {
			StudentAccount student = (StudentAccount) account;
			accountDetails.put("type", "student");
			accountDetails.put("studentID", student.getStudentID());
			
			JSONArray reviewJSON = new JSONArray();s
			for (Review review : student.getRenterReviews()) {
				reviewJSON.add(getReviewJSON(review));
			}
			
			accountDetails.put("renterReviews", reviewJSON);
		}
		return accountDetails;	
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject getReviewJSON(Review review) {
		JSONObject reviewJSON = new JSONObject();
		reviewJSON.put("text", review.getComment());
		reviewJSON.put("writer", review.getWriter());
		reviewJSON.put("rating", review.getRating());
		return reviewJSON;
	}
	
}
