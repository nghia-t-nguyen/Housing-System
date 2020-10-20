
public class Review {
	protected String text;
	protected Account writer;
	protected int rating;
	
	public Review(Account writer, int rating, String text) {
		this.writer = writer;
		this.rating = rating;
		this.text = text;
	}
	
	public Account getWriter() {
		return this.writer;
	}
	
	public int getRating() {
		return this.getRating();
	}
	
	public String getComment() {
		return this.text;
	}
	
	public String toString() {
		return "";
	}

}
