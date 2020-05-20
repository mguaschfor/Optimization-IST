package UI;
import java.awt.event.*;
import java.awt.event.ActionListener;

//this class implement the action of the button optimize. Open the windows of the selected algorithms
public class EcouteurOptimize implements ActionListener{
	private JavaAlgoGen fen;

	public EcouteurOptimize(JavaAlgoGen fen) {
		this.fen =fen;
	}
		
	public void actionPerformed(ActionEvent ae){
		if(fen.isGaSelected()) {
			gaWindow GA = new gaWindow(fen);
		}
		if(fen.isSaSelected()) {
			saWindow SA = new saWindow(fen); 
		}
	}
}
