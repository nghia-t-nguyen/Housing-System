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
	public static ArrayList<Listing> loadListings() {
		ArrayList<Listing> listings = new ArrayList<Listing>();
		
		try {
			FileReader reader = new FileReader("src/listings.json");
			JSONParser parser = new JSONParser();
			JSONArray listingsJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < listingsJSON.size(); i++) {
				JSONObject listingJSON = (JSONObject)listingsJSON.get(i);
				String host = (String)listingJSON.get("host");
				String name = (String)listingJSON.get("name");
				String address = (String)listingJSON.get("address");
				String description = (String)listingJSON.get("description");
				int rent = (int)listingJSON.get("rent");
				int bedrooms= (int)listingJSON.get("bedrooms");
				int bathrooms= (int)listingJSON.get("bathrooms");
				ArrayList<String> filters = new ArrayList<String>();
				ArrayList<Review> reviews = new ArrayList<Review>();
				boolean rented = (boolean)listingJSON.get("rented");
				
				listings.add(new Listing(name, address));
				listings.get(i).addDecription(description);
			}
			
			return listings;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static TreeSet<Account> loadAccounts() {
		TreeSet<Account> accounts = new TreeSet<Account>();
		return accounts;
	}
}
