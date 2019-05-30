package testIO;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import InputOutput.LivestreamSource;


/**
 * This JUnit test is for testing if the class LivestreamSource can convey a frame as a matrix from a selected device. 
 * Before running this test, a prior test with the "TestLivestreamSourceConnection" is necessary, which reveals 
 * if the connection works.
 * @author sahin
 *
 */
class TestLivestreamSourceTransmission {
	LivestreamSource livestream;
	
	
	/**
	 * This method is called before each test. It creates an object of the class LivestreamSource and establishes a connection to the selected device.
	 * Here the first device is selected in the constructor.
	 */
	@BeforeEach
	void open() {
		livestream = new LivestreamSource(0);
		livestream.openConnection();
	}
	
	/**
	 * This method is testing if a matrix is returned after the transmission.
	 */
	@Test
	void TestGetNextMat() {
		assertNotNull(livestream.getNextMat());
	}
	
	
	/**
	 * This method is called after each test. It is for closing the connection.
	 */
	@AfterEach
	void close() {
		livestream.closeConnection();
	}

}
