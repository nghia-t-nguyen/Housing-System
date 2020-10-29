public class Review {
	protected String text;
	protected String writer;
	protected int rating;
	
	public Review(String writer, int rating, String text) {
		this.writer = writer;
		this.rating = rating;
		this.text = text;
	}
	
	public String getWriter() {
		return this.writer;
	}
	
	public int getRating() {
		return this.getRating();
	}
	
	public String getComment() {
		return this.text;
	}
	
	public String toString() {
		return "Writer: " + getWriter() + "\nRating: " + getRating() + "\nReview: " + text;
	}

}
