import java.util.ArrayList;

/**
 * The HostAccount class extends the Account class and is able to make use of
 * it's methods. The class also imports the ArrayList package so that this class
 * may make use of ArrayLists. The HostAccount allows for the creation of
 * HostAccount objects which have special properties that the default Account
 * object does not. The method also has two private ArrayLists of Listing
 * objects and Review objects that will be used throughout the class. 
 * Created by Team QUEBEC
 */
public class HostAccount extends Account {
	private ArrayList<Listing> ownedProperties;

	/**
	 * The first constructor takes in Strings and calls super and uses these Strings
	 * in the default Account constructor. It also declares the private ArrayList
	 * variable. 
	 * Created by Team QUEBEC
	 */
	public HostAccount(String username, String password, String firstName, String lastName) {
		super(username, password, firstName, lastName);
		ownedProperties = new ArrayList<Listing>();
	}

	/**
	 * The second constructor takes in Strings and an int and calls super and uses
	 * these variables in the default Account constructor. It also declares the
	 * private ArrayList variable. 
	 * Created by Team QUEBEC
	 */
	public HostAccount(String username, int hashedPassword, String firstName, String lastName) {
		super(username, hashedPassword, firstName, lastName);
		ownedProperties = new ArrayList<Listing>();
	}

	/**
	 * The getOwnedProperties method returns the ownedProperties ArrayList. 
	 * Created by Team QUEBEC
	 */
	public ArrayList<Listing> getOwnedProperties() {
		return ownedProperties;
	}

	/**
	 * The writeRenterReview method creates a new Review object using the variables
	 * the method takes in, it then adds that Review to the accountReviews ArrayList
	 * in the Account class using the addAccountReview method. 
	 * Created by Team QUEBEC
	 */
	public void writeRenterReview(StudentAccount subject, int rating, String text) {
		subject.addAccountReview(new Review((this.firstName + " " +this.lastName), rating, text));
	}

	/**
	 * The addProperty method takes in a Listing object and adds it to the
	 * ownedProperties ArrayList. 
	 * Created by Team QUEBEC
	 */
	public void addProperty(Listing listing) {
		ownedProperties.add(listing);
	}
	
	public String getProfile() {
	  String ret = super.getProfile();
	  return ret;
	}
}
