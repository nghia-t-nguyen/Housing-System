import java.io.FileReader;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;;

/**
 * Loads the data from the JSON files
 * @author Rhylen 'Jennifer' Nguyen
 *
 */
public class DataLoader {
	public static ArrayList<Listing> loadListing() {
		ArrayList<Listing> listings = new ArrayList<Listing>();
		return listings;
	}
	
	public static ArrayList<Account> loadAccounts() {
		ArrayList<Account> accounts = new ArrayList<Account>();
		return accounts;
	}
}
