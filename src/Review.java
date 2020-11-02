public class Review {
	protected String text;
	protected String writer;
	protected int rating;
	
/**
* Class review with instance variables text, writer, and rating (all protected)
*/

	public Review(String writer, int rating, String text) {
		this.writer = writer;
		this.rating = rating;
		this.text = text;
	}

/**
* Constructor for Class Review
*/
	
	public String getWriter() {
		return this.writer;
	}

/**
* getWriter returns string this.writer
*/
	
	public int getRating() {
		return this.rating;
	}
	
/**
* getRating returns string this.rating
*/

	public String getComment() {
		return this.text;
	}
	
/*
* getComment returns string this.text
*/

	public String toString() {
		return "Writer: " + getWriter() + "\nRating: " + getRating() + "\nReview: " + text;
	}

/**
* toString returns a string line containing getWriter(), getRating, and text
*/

}
