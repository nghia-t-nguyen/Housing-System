import java.io.FileReader;
import java.util.ArrayList;
import java.util.TreeSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;;

/**
 * Loads the data from the JSON files
 * @author Rhylen 'Jennifer' Nguyen
 *
 */
public class DataLoader {
	public static Server server = Server.getInstance();
	
	public static TreeSet<Account> loadAccounts() {
		TreeSet<Account> accounts = new TreeSet<Account>();
		
		try {
			FileReader reader = new FileReader("src/accounts.json");
			JSONArray accountsJSON = (JSONArray)new JSONParser().parse(reader);
			JSONArray studentaccountsJSON = (JSONArray)accountsJSON.get(0);
			JSONArray hostaccountsJSON = (JSONArray)accountsJSON.get(1);
			
			for(int i=0; i < studentaccountsJSON.size(); i++) {
				JSONObject studentaccountJSON = (JSONObject)studentaccountsJSON.get(i);
				String username = (String)studentaccountJSON.get("username");
				int hashedPassword = (int)studentaccountJSON.get("hashedPassword");
				String firstName = (String)studentaccountJSON.get("firstName");
				String lastName = (String)studentaccountJSON.get("lastName");
				
			}
			
			for(int i=0; i < hostaccountsJSON.size(); i++) {
				JSONObject hostaccountJSON = (JSONObject)hostaccountsJSON.get(i);
			}
					
			return accounts;
		} catch (Exception e) {
			
		}
		return null;
		
	}
	
	public static ArrayList<Listing> loadListings() {
		ArrayList<Listing> listings = new ArrayList<Listing>();
		
		try {
			FileReader reader = new FileReader("src/listings.json");
			JSONArray listingsJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < listingsJSON.size(); i++) {
				JSONObject listingJSON = (JSONObject)listingsJSON.get(i);
				String name = (String)listingJSON.get("name");
				String address = (String)listingJSON.get("address");
				String description = (String)listingJSON.get("description");
				
				int rent = (int)listingJSON.get("rent");
				int bedrooms= ((Long)listingJSON.get("bedrooms")).intValue();
				int bathrooms= (int)listingJSON.get("bathrooms");
				boolean rented = (boolean)listingJSON.get("rented");				
				JSONArray reviewsJSON = (JSONArray)listingJSON.get("reviews");
		
				
				listings.add(new Listing(name, address, rent, rented));
				listings.get(i).addDecription(description);
				String f[] =(String[])listingJSON.get("filters");
				for (String filter :f) {
					listings.get(i).addFilter(filter);
				}
				for ( int j=0; j <reviewsJSON.size(); j++) {
					JSONObject review = (JSONObject)reviewsJSON.get(j); 
					String text = (String)review.get("text");
					String writer = (String)review.get("writer");
					int rating = (int)review.get("rating");
					listings.get(i).addReview(new Review(server.getAccount(writer), rating, text));
				}
				listings.get(i).addBathrooms(bathrooms);
				listings.get(i).addBedrooms(bedrooms);
			}
			
			return listings;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
