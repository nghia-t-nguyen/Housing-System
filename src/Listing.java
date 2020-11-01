import java.util.ArrayList;

public class Listing {
	private HostAccount host;
	private String name;
	private String address;
	private String description;
	private double rent;
	private int bedrooms;
	private int bathrooms;
	private ArrayList<String> filters = new ArrayList<>();
	private ArrayList<Review> reviews = new ArrayList<>();
	private boolean rented;
	
	public Listing(HostAccount host, String name, String address, double rent) {
		host.addProperty(this);
		this.name = name;
		this.address = address;
		this.rent = rent;
		this.rented = false;
	}
	
	public Listing(String name, String address, double rent, boolean rented) {
		this.name = name;
		this.address = address;
		this.rent = rent;
		this.rented = rented;
	}
		
	public void addDescription(String text) {
		this.description = text;
	}
	
	public void addBedrooms(int number) {
		this.bedrooms = number;
	}
	
	public void addBathrooms(int number) {
		this.bathrooms = number;
	}
	
	public void addFilter(String filter) {
		filters.add(filter);
	}
	
	public void addReview(Review review) {
		reviews.add(review);
	}
	
	public ArrayList<String> getReviews() {
		ArrayList<String> reviewsList = new ArrayList<>();
		for(Review review : reviews ) {
			reviewsList.add(review.toString());
		}
		return reviewsList;
	}
	public ArrayList<Review> getClassReviews() {
		ArrayList<Review> reviewsList = new ArrayList<>();
		for(Review review : reviews ) {
			reviewsList.add(review);
		}
		return reviewsList;
	}
	
	public ArrayList<String> getFilters() {
		return filters;
	}
	
	public double getAverageRating() {
	  double sum = 0.0;
	  for (Review review : reviews) {
	    sum += review.getRating();
	  }
	  return sum/reviews.size();
	}
	
	public double getRent() {
		return this.rent;
	}
	
	public int getBedrooms() {
		return this.bedrooms;
	}
	
	public int getBathrooms() {
		return this.bathrooms;
	}
	
	public void changeRented() {
		if(rented == true) {
			rented = false;
		}else {
			rented = true;
		}
	}
	
	public HostAccount getHost() {
		return this.host;
	}
	
	public String getAddress() {
	  return address;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public boolean isRented() {
		return rented;
	}

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
