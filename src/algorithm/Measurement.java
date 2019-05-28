package algorithm;

public class Measurement extends Calculation {

	public double conversion(){
		// Pixelanzahl * 2,54[cm/i] / Auflösung [dpi] = Breite [cm] 
		double converted = berechnen()*2.54;
		return converted;
	}
	
}
