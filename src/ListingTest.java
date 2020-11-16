import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListingTest {
    private Listing listing;
    private HostAccount host;

    @BeforeEach
    public void setup(){
        Listing.getInstance().getListing().clear();
        DataWriter.saveListings();
    }

    @AfterEach
    public void tearDown(){
        Listing.getInstance().getListing().clear();
        DataWriter.saveListings();
    }

    @Test
   public void testNullDescription()
    {
        String isCreated = listing.getDescription();
        isCreated = "";
    }

    @Test
    public void testNullBathrooms()
    {
        int isCreated = listing.getBathrooms();
        isCreated = 0;
    }

    @Test
    public void testNullBedrooms()
    {
        int isCreated = listing.getBedrooms();
        isCreated = 0;
    }

    @Test
    public void testNullBathrooms()
    {
        int isCreated = listing.getBathrooms();
        isCreated = 0;
    }

    @Test
    public void testNullRent()
    {
        double isCreated = listing.getRent();
        isCreated = 0.0;
    }

    @Test
    public void testNullName()
    {
        String isCreated = listing.getName();
        isCreated = "";
    }

    @Test
    public void testNullClassReviews()
    {
        ArrayList<Review> isCreated = listing.getClassReviews();
    }
}

