package UI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

//Window of the genetic algorithm
public class gaWindow extends JFrame{
	
	private JLabel NbGenerations;
	private JLabel Pcross;
	private JLabel Pmut;
	private JLabel fitness;
	private JLabel sol;
	private JLabel time;
	private JLabel populationSize;
	private JLabel Kvalue;
	private JLabel progress;
	
	private JTextField NbGenerationsField;
	private JTextField PcrossField;
	private JTextField PmutField;
	private JTextField fitnessArea;
	private JTextField solArea;
	private JTextField timeArea;
	private JTextField populationField;
	private JTextField kvalueField;
	
	private JProgressBar bar;
	
	private JButton start;
	
	private JPanel fields;
	private JPanel fields2;
	
	private JPanel mainPanel;
	
	private XYSeries series;
	private XYSeriesCollection dataset;
	private JFreeChart chart;
	
	public gaWindow(JavaAlgoGen theFen) {
		super("Genetic Algorithm");
		setSize(1000,700);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setLocationRelativeTo(null);
        
        NbGenerations = new JLabel("Generations: ");
        Pcross = new JLabel("Pc: ");
        Pmut = new JLabel("Pm: ");
        fitness = new JLabel("Best fitness: ");
        sol = new JLabel("Optimal solution: ");
        time = new JLabel("Execution time: ");
        populationSize = new JLabel("Population size: ");
        Kvalue = new JLabel("K: ");
        progress = new JLabel("Progression: ");
        
        NbGenerationsField = new JTextField(3);
	    PcrossField = new JTextField(3);
	    PmutField = new JTextField(3);
	    fitnessArea = new JTextField(6);
		solArea = new JTextField(17);
		timeArea = new JTextField(5);
		populationField = new JTextField(3);
		kvalueField  = new JTextField(3);
		bar = new JProgressBar();
		bar.setStringPainted(true);
		bar.setValue(0);
		
		start = new JButton("Start");
		
		NbGenerationsField.setMaximumSize(new Dimension(60,20));
	    PcrossField.setMaximumSize(new Dimension(60,20));
	    PmutField.setMaximumSize(new Dimension(60,20));
	    fitnessArea.setMaximumSize(new Dimension(60,20));
	    solArea.setSize(new Dimension(500,20));
	    timeArea.setMaximumSize(new Dimension(20,10));
	    
	    series = new XYSeries("Fitness of the best solution of No generation");
	    dataset = new XYSeriesCollection(series);
	    chart = ChartFactory.createXYLineChart("Fitness VS Generations", "No Generation", "Fitness of the best solution of No generation", dataset);
		
        fields = new JPanel();
        fields.setLayout(new FlowLayout());
        fields.add(populationSize);
        fields.add(populationField);
        fields.add(NbGenerations);
        fields.add(NbGenerationsField);
        fields.add(Pcross);
        fields.add(PcrossField);
        fields.add(Pmut);
        fields.add(PmutField);
        fields.add(Kvalue);
        fields.add(kvalueField);
        fields.add(start);
        fields.add(progress);
        fields.add(bar);
        
        fields2 = new JPanel();
        fields2.setLayout(new FlowLayout());
        fields2.add(fitness);
        fields2.add(fitnessArea);
        fields2.add(sol);
        fields2.add(solArea);
        fields2.add(time);
        fields2.add(timeArea);
     
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(fields2,BorderLayout.SOUTH);
        mainPanel.add(fields,BorderLayout.NORTH);
        mainPanel.add(new ChartPanel(chart), BorderLayout.CENTER);
        
        EcouteurStart doGA = new EcouteurStart(this, theFen, bar);
		start.addActionListener(doGA);
        
        this.setContentPane(mainPanel);
	    setVisible(true);
	}
	
	public int getPopulation() {
		return Integer.parseInt(populationField.getText());
	}
	public int getGenerations() {
		return Integer.parseInt(NbGenerationsField.getText());
	}
	public double getPcross() {
		return Double.parseDouble(PcrossField.getText());
	}
	public double getPmut() {
		return Double.parseDouble(PmutField.getText());
	}
	public int getK() {
		return Integer.parseInt(kvalueField.getText());
	}
	public void setFitness(String str) {
		fitnessArea.setText(str);
	}
	public void setSol(String str) {
		solArea.setText(str);
	}
	public void setTime(String str) {
		timeArea.setText(str);
	}
	public void setChart(XYSeriesCollection dataset) {
		this.dataset = dataset;
		chart.getXYPlot().setDataset(dataset);
	}
}
