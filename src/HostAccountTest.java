import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.AfterEach;
import org.junit.BeforeEach;
import org.junit.jupiter.api.Test;

public class HostAccountTest {
	private ArrayList<Listing> listing = new ArrayList<Listing>;
		
		@BeforeEach
		public static void setup() {
			HostAccount hostAccount = new HostAccount(null, 0, null, null);
			ArrayList<Listing> listing = new ArrayList<Listing>;
		}
		
		@AfterEach
		public static void tearDown() {
			listing.clear();
		}

		@Test
		public void testWriteRenterReview() {
			StudentAccount subject = new StudentAccount(null, 0, null, null, null);
			subject.addAccountReview(new Review((null, 0, null)));
		}
		
		@Test
		public void testAddProperty() {
			listing.add(null);
		}
		
}