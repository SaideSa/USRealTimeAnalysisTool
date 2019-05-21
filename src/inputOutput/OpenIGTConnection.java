package InputOutput;

import org.medcare.igtl.messages.ImageMessage;
import org.medcare.igtl.network.GenericIGTLinkClient;
import org.medcare.igtl.network.IOpenIgtPacketListener;
import org.medcare.igtl.util.Status;

import com.neuronrobotics.sdk.addons.kinematics.math.TransformNR;
import com.neuronrobotics.sdk.common.Log;

import Jama.Matrix;

public class OpenIGTConnection implements IOpenIgtPacketListener {
	private GenericIGTLinkClient client;
	private ImageMessage imgMsg;
	private String name;

	public OpenIGTConnection(String ip, int port) {

		try {
			// Log.enableDebugPrint(false);
			// Log.enableSystemPrint(false);
			//
			// Log.debug("Starting client");
			client = new GenericIGTLinkClient(ip, port);

			client.addIOpenIgtOnPacket(this);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onRxImage(String name, ImageMessage image) {
		imgMsg = image;
		this.name = name;

		// System.out.println("8");
	}

	public ImageMessage getImageMessage() {
		return imgMsg;
	}

	public boolean stop() {
		client.stopClient();
		return client.isInterrupted();
	}
	
	public boolean isConnected() {
		return client.isConnected();
	}
	
	
	//The methods below are not used during a connection with MITK?
	@Override
	public void onRxTransform(String name, TransformNR t) {
		// TODO Auto-generated method stub
		// System.out.println("1");

	}

	@Override
	public TransformNR getTxTransform(String name) {
		// TODO Auto-generated method stub
		// System.out.println("2");
		return null;
	}

	@Override
	public Status onGetStatus(String name) {
		// TODO Auto-generated method stub
		// System.out.println("3");
		return null;
	}

	@Override
	public void onRxString(String name, String body) {
		// TODO Auto-generated method stub
		// System.out.println("4");

	}

	@Override
	public String onTxString(String name) {
		// TODO Auto-generated method stub
		// System.out.println("5");
		return null;
	}

	@Override
	public void onRxDataArray(String name, Matrix data) {
		// TODO Auto-generated method stub
		// System.out.println("6");

	}

	@Override
	public double[] onTxDataArray(String name) {
		// TODO Auto-generated method stub
		// System.out.println("7");
		return null;
	}

	@Override
	public void onTxNDArray(String name) {
		// TODO Auto-generated method stub
		// System.out.println("9");

	}

	@Override
	public void onRxNDArray(String name, float[] data) {
		// TODO Auto-generated method stub
		// System.out.println("10");

	}

}
