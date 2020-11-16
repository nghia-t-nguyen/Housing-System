import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HostAccountTest {
	private ArrayList<Listing> listing;
	private HostAccount hostAccount;
		
	@BeforeEach
		public void setup() {
			 hostAccount = new HostAccount("mLee", "123456".hashCode(), "Minho" , "Lee");
			 listing = new ArrayList<Listing>();
		}
		
		@AfterEach
		public void tearDown() {
			hostAccount = null;
			listing.clear();
		}

		@Test
		public void testWriteRenterReview() {
			StudentAccount subject = new StudentAccount("jYang", 0, "Jeongin", "Yang", "0801");
			Review review = new Review("Minho Lee", 0, "Hello");
			hostAccount.writeRenterReview(subject, 0, "Hello"); 
		
			assertEquals(review, subject.getAccountReviews().get(0));
		}
	
		@Test
		public void testGetProfile() {
			hostAccount.addProperty(new Listing(hostAccount,"Academic Towers", "123 School LN", 1200));
			String expected = "****Host****\nUsername: @mLee\nName: Minho Lee\nAverage Rating: No ratings\n-Academic Towers : 123 School LN";
			assertEquals(expected, hostAccount.getProfile());
		}
		
}

