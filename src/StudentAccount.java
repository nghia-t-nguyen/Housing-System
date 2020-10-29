import java.util.ArrayList;
import java.util.Scanner;

public class StudentAccount extends Account{
    private String studentID;
    private ArrayList<Review> renterReviews;
    private ArrayList<Listing> bookmarks;
    private ArrayList<Listing> favoriteProperties;

    StudentAccount(String username, String password, String firstName, String lastName,
                   String studentID) {
        super(username, password, firstName, lastName);
        this.studentID = studentID;
        renterReviews = new ArrayList<Review>();
        bookmarks = new ArrayList<Listing>();
        favoriteProperties = new ArrayList<Listing>();
    }

    public void addBookmark(Listing listing) {
        bookmarks.add(listing);
    }

    public void addFavorite(Listing listing) {
        favoriteProperties.add(listing);
    }

    public void addRenterReview(Review review) {
        renterReviews.add(review);
    }

    public void writeListingReview(Listing listing, int rating, String text) {
        Scanner key = new Scanner(System.in);
        System.out.println("Write listing review");
        String Lreview = key.nextLine();
        System.out.println("Leave a rating from 1-5");
        int LRating = key.nextInt();
        while(LRating<1||LRating>5)
        {
            System.out.println("Invalid rating - out of range, try again. Ratings range from 1-5");
            int NewLRating = key.nextInt();
            NewLRating = LRating;
        }
        text = Lreview;
        LRating = rating;
      
        //I need to figure out how to add to a new listing each time it's called upon, I'm not sure this'll work but we'll see
    }

    /**
     public void writeListingReview(Listing listing, int rating, String text) {
     Review lreview = new Review(listing, rating, text);
     }
     **/

    public void writeHostReview(HostAccount host, int rating, String text) {
        Scanner key = new Scanner(System.in);
        System.out.println("Write host review");
        String Hreview = key.nextLine();
        System.out.println("Leave a rating from 1-5");
        int HRating = key.nextInt();
        while(HRating<1||HRating>5)
        {
            System.out.println("Invalid rating - out of range, try again. Ratings range from 1-5");
            int NewHRating = key.nextInt();
            NewHRating = HRating;
        }
        text = Hreview;
        HRating = rating;
       
    }
/**
    public void writeHostReview(HostAccount host, int rating, String text) {
        Review hreview = new Review(host, rating, text);
    }
**/
    public ArrayList<Review> getReviews() {
        return renterReviews;
    }

    public ArrayList<Listing> getBookmarks() {
        return bookmarks;
    }

    public ArrayList<Listing> getFavorites() {
        return favoriteProperties;
    }
}
