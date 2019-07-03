package InputOutput;

import java.util.ArrayList;

import org.medcare.igtl.messages.ImageMessage;
import org.medcare.igtl.network.GenericIGTLinkClient;
import org.medcare.igtl.network.IOpenIgtPacketListener;
import org.medcare.igtl.util.Status;

import com.neuronrobotics.sdk.addons.kinematics.math.TransformNR;
import com.neuronrobotics.sdk.common.Log;

import Jama.Matrix;

/**
 * This class is for a connection based on OpenIGTLink. As a prerequisite MITK v2016.11 has to run in the background. 
 * The connection is established by using the online stream. 
 * @author team3
 *
 */
public class OpenIGTConnection implements IOpenIgtPacketListener {
	private GenericIGTLinkClient client;
	private ImageMessage imgMsg;
	private String name;
	private byte[] imgData;
	
	
	/**
	 * An IP-address and a port number is needed for the connection. Here both are set through the constructor. 
	 * @param ip
	 * @param port
	 */
	public OpenIGTConnection(String ip, int port) {

		try {
			client = new GenericIGTLinkClient(ip, port);

			client.addIOpenIgtOnPacket(this);

		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

	@Override
	public void onRxImage(String name, ImageMessage image) {
		setImageMessage(image);
		
		try {
			image.UnpackBody();
			
			setImageDataByte(image.getImageData());
		} catch (Exception e) {

			e.printStackTrace();
		}
		this.name = name;

	}

	public ImageMessage getImageMessage() {
		return imgMsg;
	}

	public void setImageMessage(ImageMessage img) {

		imgMsg = img;
	}
	
	public void setImageDataByte(byte[] imgData) {
		this.imgData = imgData;
	}
	
	public byte[] getImageDataByte() {

		return imgData;
	}

	public boolean stop() {
		client.stopClient();
		return client.isInterrupted();
	}
	
	public boolean isConnected() {
		return client.isConnected();
	}
	
	
	/**
	 * The methods below are not used during a connection over MITK
	 */
	@Override
	public void onRxTransform(String name, TransformNR t) {
	

	}

	@Override
	public TransformNR getTxTransform(String name) {
		
		return null;
	}

	@Override
	public Status onGetStatus(String name) {
	
		return null;
	}

	@Override
	public void onRxString(String name, String body) {
		
	}

	@Override
	public String onTxString(String name) {
		
		return null;
	}

	@Override
	public void onRxDataArray(String name, Matrix data) {
		
	}

	@Override
	public double[] onTxDataArray(String name) {

		return null;
	}

	@Override
	public void onTxNDArray(String name) {
	

	}

	@Override
	public void onRxNDArray(String name, float[] data) {
		

	}

}
