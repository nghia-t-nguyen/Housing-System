import java.util.ArrayList;
import java.lang.Math;

public class Listing {

	private String host;
	private String name;
	private String address;
	private String description;
	private double rent;
	private int bedrooms;
	private int bathrooms;
	private ArrayList<String> filters;
	private ArrayList<Review> reviews;
	private boolean rented;

/**
* Class Listing with instance variables host, name, address, description, rent, bedrooms, bathrooms, filters, reviews, and rented.
* Imoprts ArrayList to make use of its methods
*/

	public Listing(HostAccount host, String name, String address, double rent) {
		host.addProperty(this);
		this.host = host.getUsername();
		this.name = name;
		this.address = address;
		this.rent = rent;
		this.rented = false;
		this.filters = new ArrayList<String>();
		this.reviews = new ArrayList<Review>();
	}

/**
* Constructor for Listing class
*/

	public Listing(String host, String name, String address, double rent, boolean rented) {
		this.host = host;
		this.name = name;
		this.address = address;
		this.rent = rent;
		this.rented = rented;
	    this.filters = new ArrayList<String>();
	    this.reviews = new ArrayList<Review>();
	}
/**
* addDescription adds String text to this.description
*/

	public void addDescription(String text) {
		this.description = text;
	}


/**
* addBedrooms adds int number to this.bedrooms
*/

	public void addBedrooms(int number) {
		this.bedrooms = number;
	}

/**
* addBathrooms adds int number to this.bathrooms
*/
	public void addBathrooms(int number) {
		this.bathrooms = number;
	}

/**
* addFilter adds String filter to this.filter
*/
	public void addFilter(String filter) {
		filters.add(filter.toLowerCase());
	}


/**
* addReview adds Review review to this.review
*/
	public void addReview(Review review) {
		reviews.add(review);
	}



	public ArrayList<Review> getReviews() {
		return reviews;
	}
/*

* getReviews adds reviews to reviewList and returns reviewList in ArrayList type String


	public ArrayList<Review> getClassReviews() {
		ArrayList<Review> reviewsList = new ArrayList<>();
		for(Review review : reviews ) {
			reviewsList.add(review);
		}
		return reviewsList;
	}
*/
/**
* getClassReviews adds reviews to reviewList and returns reviewList in ArrayList type String
*/

	public ArrayList<String> getFilters() {
		return filters;
	}

/**
* addReview adds Review review to this.review
*/

	public double getAverageRating() {
	  double sum = 0.0;
	  for (Review review : reviews) {
	    sum += review.getRating();
	  }
	  double average =  sum/reviews.size();
	  average = Math.round(average * 100); //rounds to two decimal places
	  return average/100.0;
	}
/**
* getAverageRating obtains the average rating, returning sum/reviews.size()
*/
	public double getRent() {
		return this.rent;
	}

/**
* getRent returns double rent
*/

	public int getBedrooms() {
		return this.bedrooms;
	}

/**
* getBedrooms returns int bedrooms
*/

	public int getBathrooms() {
		return this.bathrooms;
	}

/**
* getBathrooms returns int bathrooms
*/

	public void changeRented() {
		if(rented == true) {
			rented = false;
		}else {
			rented = true;
		}
	}

/**
* changeRented changes the rented status to true or false
*/

	public HostAccount getHost() {
		return (HostAccount) Server.getInstance().getAccount(host);
	}
/**
* getHost() returns HostAccount host
*/
	public String getAddress() {
	  return address;
	}

/**
* getAddress returns String address
*/

	public String getName() {
		return name;
	}

/**
* getName returns String name
*/

	public String getDescription() {
		return description;
	}
/**
* getDescription returns String description
*/
	public boolean isRented() {
		return rented;
	}

/**
* isRented returns rented value
*/

	public String toString() {
	  String ret = name;
	  ret += "\nAddress: " + address;
	  ret += "\nHost: " + host;
	  ret += "\nAverage Rating: ";
	  ret += (reviews.size() == 0) ? "No ratings" : getAverageRating();
	  ret += "\nDescription: ";
      ret += (description == null) ? "n/a" : description;
      ret += "\nRented: ";
      ret += (rented) ? "Yes" : "No";
	  ret += "\nRent: " + rent;
	  ret += "\nBedrooms: ";
	  ret += (bedrooms == 0) ? "n/a" : bedrooms;
	  ret += "\nBathrooms: ";
	  ret += (bathrooms == 0) ? "n/a" : bedrooms;
	  ret += "\nFilters:";
	  for (String filter : filters) {
	    ret += " #" + filter;
	  }

	  return ret;
	}

}
/**
* toString returns a string with a description of the listing calling upon the attributes from the rest of this class' methods
*/
