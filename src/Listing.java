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
	
	public Listing(HostAccount host, String name, String address, double rent, boolean rented) {
		this.host = host;
		this.name = name;
		this.address = address;
	}
		
	public void addDecription(String text) {
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
	
	public ArrayList<String> getFilters() {
		return filters;
	}
	
	public double getAverageRating() {
		return 0.0;
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
	
	public String toString() {
		return "";
	}
	
	public String getAddress() {
	  return address;
	}

}
