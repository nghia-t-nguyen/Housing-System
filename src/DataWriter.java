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
	
	public static void saveAccounts() {
		
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject getListingJSON(Listing listing) {
		JSONObject listingDetails = new JSONObject();
		listingDetails.put("name", listing.getName());
		listingDetails.put("address", listing.getAddress());
		listingDetails.put("description", listing.getDescription());
		listingDetails.put("rent", listing.getRent());
		listingDetails.put("bedrooms", listing.getBedrooms());
		listingDetails.put("bathrooms", listing.getBathrooms());
		listingDetails.put("reviews", listing.getReviews());
		listingDetails.put("rented", listing.isRented());
		
		return listingDetails;
	}
	public static JSONObject getAccountJSON(Account account) {
		return null;	
	}
	
	
}
