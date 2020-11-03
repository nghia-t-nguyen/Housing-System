import java.io.FileReader;
import java.util.ArrayList;
import java.util.TreeSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
			JSONArray listingsJSON = (JSONArray)new JSONParser().parse(reader);

			for(int i=0; i < listingsJSON.size(); ++i) {
				JSONObject listingJSON = (JSONObject)listingsJSON.get(i);
				String name = (String)listingJSON.get("name");
				String address = (String)listingJSON.get("address");
				String description = (String)listingJSON.get("description");

				double rent = ((Long)listingJSON.get("rent")).doubleValue();
				int bedrooms= ((Long)listingJSON.get("bedrooms")).intValue();
				int bathrooms= ((Long)listingJSON.get("bathrooms")).intValue();
				boolean rented = (boolean)listingJSON.get("rented");
				JSONArray reviewsJSON = (JSONArray)listingJSON.get("reviews");


				listings.add(new Listing(name, address, rent, rented));
				listings.get(i).addDescription(description);
				JSONArray filters =(JSONArray)listingJSON.get("filters");
				for (int j = 0; j < filters.size(); ++j) {
					listings.get(i).addFilter((String)filters.get(j));
				}
				for ( int j=0; j <reviewsJSON.size(); ++j) {
					JSONObject review = (JSONObject)reviewsJSON.get(j);
					String text = (String)review.get("text");
					String writer = (String)review.get("writer");
					int rating = ((Long)review.get("rating")).intValue();
					listings.get(i).addReview(new Review(writer, rating, text));
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

	public static Server server = Server.getInstance();

	public static TreeSet<Account> loadAccounts() {
		TreeSet<Account> accounts = new TreeSet<Account>();

		try {
			FileReader reader = new FileReader("src/accounts.json");
			JSONArray accountsJSON = (JSONArray)new JSONParser().parse(reader);

			for (int i=0; i< accountsJSON.size(); ++i) {
				JSONObject accountJSON = (JSONObject)accountsJSON.get(i);
				String firstName = (String)accountJSON.get("firstName");
				//System.out.println(firstName);
				String lastName = (String)accountJSON.get("lastName");
				JSONArray reviewsJSON = (JSONArray)accountJSON.get("accountReviews");
				int hashedPassword = ((Long)accountJSON.get("hashedPassword")).intValue();
				String type = (String)accountJSON.get("type");
				String username = (String)accountJSON.get("username");
				

				if (type.equals("student")) {
					String studentID = (String)accountJSON.get("studentID");

					StudentAccount student = new StudentAccount(username, hashedPassword, firstName, lastName, studentID);
					//System.out.println(student.getUsername());
					JSONArray messageBoxJSON = (JSONArray)accountJSON.get("messagebox");

					for (int j = 0; j< messageBoxJSON.size(); ++j) {
						student.getMessageBox().addMessage((String)messageBoxJSON.get(j));
					}

					//Dont know if these 2 are working properly
					JSONArray bookmarksJSON = (JSONArray)accountJSON.get("bookmarks");
					for (int j= 0; j< bookmarksJSON.size();++j) {
						student.addBookmark(server.getListing((String)bookmarksJSON.get(j)));
						//System.out.println(student.getBookmarks().get(j).getAddress());
					}

					JSONArray favoritesJSON = (JSONArray)accountJSON.get("favorites");
					for (int j= 0; j< favoritesJSON.size();++j) {
						student.addFavorite(server.getListing((String)favoritesJSON.get(j)));
					}

					for ( int j=0; j <reviewsJSON.size(); ++j) {
						JSONObject review = (JSONObject)reviewsJSON.get(j);
						String text = (String)review.get("text");
						String writer = (String)review.get("writer");
						int rating = ((Long)review.get("rating")).intValue();
						student.addAccountReview(new Review(writer, rating, text));
					}
					accounts.add(student);
				}
				else if (type.equals("host")){
					HostAccount host = new HostAccount(username, hashedPassword, firstName, lastName);
					//System.out.println(host.getUsername());
					JSONArray messageBoxJSON = (JSONArray)accountJSON.get("messagebox");

					for (int j = 0; j< messageBoxJSON.size(); ++j) {
						//host.getMessageBox().addMessage((String)messageBoxJSON.get(j));
					}
					for ( int j=0; j <reviewsJSON.size(); ++j) {
						JSONObject review = (JSONObject)reviewsJSON.get(j);
						String text = (String)review.get("text");
						String writer = (String)review.get("writer");
						int rating = ((Long)review.get("rating")).intValue();
						host.addAccountReview(new Review(writer, rating, text));
					}

					JSONArray propertyJSON = (JSONArray)accountJSON.get("ownedProperties");
					for (int j= 0; j<propertyJSON.size();++j) {
						Listing listing = server.getListing((String)propertyJSON.get(j));
						host.addProperty(listing);
						listing.addHost(host);
					}
					accounts.add(host);

				}
			}
			
			return accounts;
		} catch (Exception e) {

		}
		return accounts;
	}

}
