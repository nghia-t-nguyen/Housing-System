import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
		
		try (FileWriter file = new FileWriter("src/listings.json")) {
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
		
		try (FileWriter file = new FileWriter("src/accounts.json")) {
			file.write(jsonAccounts.toJSONString());
			file.flush();
		} catch (IOException e) {
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject getListingJSON(Listing listing) {
		JSONObject listingDetails = new JSONObject();
		listingDetails.put("host", listing.getHost().getUsername());
		listingDetails.put("name", listing.getName());
		listingDetails.put("address", listing.getAddress());
		listingDetails.put("description", listing.getDescription());
		listingDetails.put("rent", listing.getRent());
		listingDetails.put("bedrooms", listing.getBedrooms());
		
		JSONArray reviewJSON = new JSONArray();
		for (Review review : listing.getReviews()) {
			reviewJSON.add(getReviewJSON(review));
		}
		//System.out.println(reviewJSON+"\n");
		
		JSONArray filterJSON = new JSONArray();
		for (String filter : listing.getFilters()) {
			filterJSON.add(filter);
		}
		
		listingDetails.put("bathrooms", listing.getBathrooms());
		listingDetails.put("reviews", reviewJSON);
		listingDetails.put("filters", filterJSON);
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
		
		JSONArray reviewJSON = new JSONArray();
		//System.out.println("SHDJIHSIDHI"+ account.getAccountReviews());
		for (Review review : account.getAccountReviews()) {
				reviewJSON.add(getReviewJSON(review));
			}
		//System.out.println(reviewJSON+"\n");
		accountDetails.put("accountReviews", reviewJSON);
			
		if (account instanceof StudentAccount) {
			StudentAccount student = (StudentAccount) account;
			accountDetails.put("type", "student");
			accountDetails.put("studentID", student.getStudentID());
					
			JSONArray bookmarkJSON = new JSONArray();
			for (Listing bookmark : student.getBookmarks()) {
				bookmarkJSON.add(getSimpleListingJSON(bookmark));
			}
			
			JSONArray favoriteJSON = new JSONArray();
			for (Listing favorite : student.getFavorites()) {
				favoriteJSON.add(getSimpleListingJSON(favorite));
			}
			
			accountDetails.put("bookmarks", bookmarkJSON);
			accountDetails.put("favorites", favoriteJSON);			
		} 
		
		if (account instanceof HostAccount) {
			HostAccount host = (HostAccount) account;
			accountDetails.put("type", "host");
			
			JSONArray ownedJSON = new JSONArray();
			for (Listing listing : host.getOwnedProperties()) {
				ownedJSON.add(getSimpleListingJSON(listing));
			}
			accountDetails.put("ownedProperties", ownedJSON);
			
		}
		//System.out.println(accountDetails);
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
	
	@SuppressWarnings("unchecked")
	public static JSONObject getSimpleListingJSON(Listing listing) {
		JSONObject simpleListingJSON = new JSONObject();
		simpleListingJSON.put("name", listing.getName());
		simpleListingJSON.put("address", listing.getAddress());
		return simpleListingJSON;
	}
	
}
