package testIO;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import InputOutput.FilestreamSource;

class JUTestFileStreamSourceConnection {

	/**
	 * This method tests instantiating the class FilestreamSource.
	 */
	@Test
	void testObject() {
		FilestreamSource filestream = new FilestreamSource("US_video.avi"); 
		assertTrue(filestream!=null);
	}
	
	
	/**
	 * This method tests if the connection to the device can be established.
	 * The connection can only work if the instantiating was successful.
	 */
	@Test
	void testOpenConnection() {
		FilestreamSource filestream = new FilestreamSource("US_video.avi");
		assertTrue(filestream.openConnection());
	}
	
	/**
	 * This method tests the closing of an existing connection. 
	 * Prior to that it is important to ensure that opening the connection is working.
	 */
	@Test
	void testCloseConnection() {
		FilestreamSource filestream = new FilestreamSource("US_video.avi");
		filestream.openConnection();
		assertTrue(filestream.closeConnection());
	}
}

