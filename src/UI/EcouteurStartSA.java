package UI;
import Optimization.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.Timer;
import javax.swing.JProgressBar;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

//action of the button start of the simulated annealing algorithm's window
public class EcouteurStartSA implements ActionListener{
	private saWindow window;
	private JavaAlgoGen fen;
	private JProgressBar bar;
	private int size;
	private double T;
	private int K;
	private double alpha;
	private double length1; 
	private double length2;
	private double length3; 
	private double length4;
	private double l; 
	private double h; 
	private double a; 
	private double q1; 
	private double q2;
	private double q3;
	private XYSeriesCollection dataset;
	
	public EcouteurStartSA(saWindow window, JavaAlgoGen fen, JProgressBar bar) {
		this.window = window;
		this.fen = fen;
		this.bar = bar;
	}
	
	public void actionPerformed(ActionEvent ae){
		
	    alpha = Math.PI*fen.getAlpha()/180;
		length1 = fen.getlength1();
		length2 = fen.getlength2();
		length3 = fen.getlength3();
		length4 = fen.getlength4();
		l = length2*Math.cos(alpha) + length3*Math.cos(alpha);
		h = length1;
		a = length2*Math.sin(alpha);
		q1 = fen.getq1();
		q2 = fen.getq2();
		q3 = fen.getq3();
		
		size = window.getPopulation();
		T = window.getTemperature();
		K = window.getK();
		
		PopulationAnnealed.clearTheIteration();
	    
		PopulationAnnealed population = new PopulationAnnealed(size, K, T-1, length1, length2, length3, length4, alpha, l, h, a, q1, q2, q3);
		long startTime = System.nanoTime();
		Chromosome Fittest;
		XYSeries series = new XYSeries("Fitness vs generations");
		int i = 0;
		//here is the assembled body of the simulated annealing. It is performed with all the parameters chosen through the UI
		while(population.getTemperature() != 0 ) {
			population.Metropolis();
			population.NextGeneration();
			Fittest = population.getFittest();
			series.add(i,population.getFittestValue()); 
			i++;
		}
		bar.setValue(100);
		long endTime   = System.nanoTime();
	    dataset = new XYSeriesCollection();
	    dataset.addSeries(series);
	    window.setChart(dataset);
			
		Chromosome theFittest = population.getFittest();
		Chromosome theWorst = population.getWorst();
		double fittestvalue = population.getFittestValue();
		double worstvalue = population.getWorstValue();
		DecimalFormat df = new DecimalFormat("###.###");
		window.setFitness(df.format(fittestvalue));
		window.setSol(" "+theFittest.toString2());
		window.setTime(df.format(((endTime - startTime)*Math.pow(10, -9)))+" s");
		
		System.out.println("Time : "+((endTime - startTime)*Math.pow(10, -9))+" seconds");
		System.out.println("fittestvalue = "+fittestvalue);
		System.out.println("worstvalue = "+worstvalue);
		System.out.println("fittest = " +theFittest.toString());
		System.out.println("worst = " +theWorst.toString());
		System.out.println("Iteration : "+population.getIteration());
	}
	
	public XYSeriesCollection getDataSet() {
		return dataset;
	}
	

}

