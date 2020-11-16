import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountTest {
	Account host;
	Account student;
	
	@BeforeEach
	void setUp() throws Exception {
		host = new HostAccount("mLee", "123456".hashCode(), "Minho" , "Lee");
		student = new StudentAccount("fLee", "123456".hashCode(), "Felix", "Lee", "SKZ0325");
	}

	@AfterEach
	void tearDown() throws Exception {
		host = null;
		student = null;
	}

	@Test
	void testEqualsAccount() {
		Account expected = new HostAccount("mLee", "123456".hashCode(), "Minho" , "Lee");
		assert(expected.equals(host));
		assert(!expected.equals(student));
	}
	
	@Test
	void testGetAverageRating() {
		host.addAccountReview(new Review(null, 5, ""));
     	host.addAccountReview(new Review(null, 2, ""));
     	host.addAccountReview(new Review(null, 3, ""));
     	host.addAccountReview(new Review(null, 2, ""));
     	host.addAccountReview(new Review(null, 3, ""));
     	
     	student.addAccountReview(new Review(null, 5, ""));
     	student.addAccountReview(new Review(null, 2, ""));
     	student.addAccountReview(new Review(null, 3, ""));
     	student.addAccountReview(new Review(null, 2, ""));
     	student.addAccountReview(new Review(null, 3, ""));
     	
     	double expected = 3;
     	
     	assertEquals(expected, host.getAverageRating());
     	assertEquals(expected, student.getAverageRating());
	}
	
	@Test
	void testGetProfile() {
		String expected1 = "Username: @mLee\nName: Minho Lee\nAverage Rating: No ratings";
		String expected2 = "Username: @fLee\nName: Felix Lee\nAverage Rating: No ratings";
		
		assertEquals(expected1,host.getProfile());
		assertEquals(expected2,student.getProfile());
	}

}
