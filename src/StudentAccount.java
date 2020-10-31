import java.util.ArrayList;

public class StudentAccount extends Account{
  private String studentID;
  private ArrayList<Listing> bookmarks;
  private ArrayList<Listing> favoriteProperties;

  public String getStudentID() {
	return studentID;
}


public ArrayList<Listing> getFavoriteProperties() {
	return favoriteProperties;
}

StudentAccount(String username, String password, String firstName, String lastName,
      String studentID) {
    super(username, password, firstName, lastName);
    this.studentID = studentID;
    bookmarks = new ArrayList<Listing>();
    favoriteProperties = new ArrayList<Listing>();
  }
  
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
  
  public void addFavorite(Listing listing) {
	  favoriteProperties.add(listing);
  }
  
  public void writeListingReview(Listing listing, int rating, String text) {
	  listing.addReview(new Review((this.firstName+" "+this.lastName), rating, text));
  }
  
  public void writeHostReview(HostAccount host, int rating, String text) {
	  host.addAccountReview(new Review((this.firstName+" "+this.lastName), rating, text));
  }
  
  
  public ArrayList<Listing> getBookmarks() {
    return bookmarks;
  }
  
  public ArrayList<Listing> getFavorites() {
    return favoriteProperties;
  }
}
