import java.util.ArrayList;

public class StudentAccount extends Account{
  private String studentID;
  private ArrayList<Listing> bookmarks;
  private ArrayList<Listing> favoriteProperties;

/**
* StudentAccount class extending Account with instance variables studentID, bookmarks, and favorite properties. 
* Imports java.util.ArrayList to make use of ArrayLists
*/

  public String getStudentID() {
	return studentID;
}

/**
* getStudentID returns the string studentID when called upon
*/

public ArrayList<Listing> getFavoriteProperties() {
	return favoriteProperties;
}

/**
* getFavoriteProperties returns favoriteProperties in an ArrayList of type Listing when called upon
*/

StudentAccount(String username, String password, String firstName, String lastName,
      String studentID) {
    super(username, password, firstName, lastName);
    this.studentID = studentID;
    bookmarks = new ArrayList<Listing>();
    favoriteProperties = new ArrayList<Listing>();
  }

/**
* Constructor for StudentAccount with a super calling back to Account and its respective instance variables
*/
  
  StudentAccount(String username, int hashedPassword, String firstName, String lastName,
	      String studentID) {
	    super(username, hashedPassword, firstName, lastName);
	    this.studentID = studentID;
	    bookmarks = new ArrayList<Listing>();
	    favoriteProperties = new ArrayList<Listing>();
	  }
  
  public void addBookmark(Listing listing) {
	  bookmarks.add(listing);
  }
  
/**
* addBookmark adds listing type Listing for bookmarks
*/

  public void addFavorite(Listing listing) {
	  favoriteProperties.add(listing);
  }

/**
* addFavorite adds listing type Listing for Favorites
*/

  public void writeListingReview(Listing listing, int rating, String text) {
	  listing.addReview(new Review((this.firstName+" "+this.lastName), rating, text));
  }
 
/**
* writeListingReview adds review to listing type Listing with the reviewer's name, rating, and review text
*/
 
  public void writeHostReview(HostAccount host, int rating, String text) {
	  host.addAccountReview(new Review((this.firstName+" "+this.lastName), rating, text));
  }
  
/**
* writeHostReview adds review to host type HostAccount with the reviewer's name, rating, and review text
*/  

  public ArrayList<Listing> getBookmarks() {
    return bookmarks;
  }
  
/**
* getBookmarks returns bookmarks in an ArrayList of type Listing when called upon
*/

  public ArrayList<Listing> getFavorites() {
    return favoriteProperties;
  }
  
  public String getProfile() {
    String ret = "****Student****\n";
    ret += super.getProfile();
    return ret;
  }
}

/**
* getFavorites returns favoriteProperties in an ArrayList of type Listing when called upon
*/