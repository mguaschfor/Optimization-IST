package Solve;

//This class is a common class to all the beams. It gives fields and methods that are shared.
//Since it is abstract, it cannot be called. Only the children can be instantiate
public abstract class Beam {
	
	//fields of the class 
	protected double length; //length of the beam
	protected Section section; //section of the beam
	protected double h; //height of the frame
	protected double a; //vertical distance between C1(C3) and C2 
	protected double l; //length of the frame (distance AB)
	protected double alpha; // angle alpha that appears sketch
	protected double q1; //load q1
	protected double q2; //load q2
	protected double q3; //load q3
	protected double [] x; // tab that will discretized x-value of the beam (local orientation)
	protected int N; // the number of point of the discretization
	protected double dx; // the space between two elements discretized
	
	//Constructor of the class
	public Beam(double length, Section section, double h, double a, double l, double alpha, double q1, double q2, double q3, int N) {
		this.length = length;
		this.section = section;
		this.h = h;
		this.a = a;
		this.l = l;
		this.alpha = alpha;
		this.q1 = q1;
		this.q2 = q2;
		this.q3 = q3;
		this.N =N;
		x = new double[N];
		dx = length/(N-1);
		x[0]=0;
		for(int i = 0; i < N-1; i++) {
			x[i+1]=x[i]+dx;
		}
	}
	
	//Methods that will allow to calculate maximum efforts Nmax, Tmax and Mmax. Overridden in the children class
	public abstract double calculateNmax(double XA, double YA, double XB, double YB);
	public abstract double calculateTmax(double XA, double YA, double XB, double YB);
	public abstract double calculateMmax(double XA, double YA, double XB, double YB);
	
	//Return true if the criterion 1 passed (constraints)
	public boolean Efforts(double N, double M) {
		double NR = section.getA()*235e6;
		double MR12 = section.getWpl()*235e6;
		double Aw = section.getA()-2*section.getB()*section.getTf();
		double aa = Math.min(Aw/section.getA(), 0.5);
		boolean result = false;
		double test = 0;
		if(section.getClasse() == 1 || section.getClasse() == 2) {
			if(N > Math.min(0.25*NR,0.5*Aw*235e-6)){
				double MR12b = MR12*((1-N/NR)/(1-0.5*aa));
				test = N/NR + M/MR12b;
			}
			else {
				test = N/NR + M/MR12;
			}
			if(test <= 1) {
				result = true;
			}
		}
		if (section.getClasse() == 3){
			test = N/section.getA() + M/section.getWel();
			if(test <= 235e6) {
				result = true;
			}
		}
		return result;
	}
	
	//return the value of the criterion 1 (<1 if passed, >1 if not)
	public double performanceEfforts(double N, double M) {
		double NR = section.getA()*235e6;
		double MR12 = section.getWpl()*235e6;
		double Aw = section.getA()-2*section.getB()*section.getTf();
		double aa = Math.min(Aw/section.getA(), 0.5);
		double test = 0;
		if(section.getClasse() == 1 || section.getClasse() == 2) {
			if(N > Math.min(0.25*NR,0.5*Aw*235e-6)){
				double MR12b = MR12*((1-N/NR)/(1-0.5*aa));
				test = N/NR + M/MR12b;
			}
			else{
				test = N/NR + M/MR12;
			}
		}
		if (section.getClasse() == 3){
			test = N/(235e6*section.getA()) + M/(235e6*section.getWel());
		}
		return test;
	}
	
	//return true if the second criterion passed (buckling)
	public boolean buckling(double N, double M) {
		double NR = section.getA()*235e6;
		double MR12 = section.getWpl()*235e6;
		double MR3 = section.getWel()*235e6;
		double ii = Math.sqrt(section.getIz()/section.getA());
		double lambda = this.length/ii;
		double lambda1 = Math.PI*Math.sqrt(210e9/235e6);
		double lambdaM = lambda/lambda1;
		double phi = 0.5*(1+0.34*(lambdaM-0.2)+Math.pow(lambdaM, 2));
		double X = 1/(phi+Math.sqrt(Math.pow(phi,2)-Math.pow(lambdaM, 2)));
		double muz = -lambdaM*1.4+(section.getWpl()-section.getWel())/section.getWel();
		double kz = 1-(muz*N)/(X*section.getA()*235e6);
		boolean result = false;
		if(section.getClasse() == 1 || section.getClasse() == 2) {
			double test = N/(X*(NR/1.1)) + kz*M/(MR12/1.1);
			if(test <= 1) {
				result = true;
			}
		}
		else if (section.getClasse() == 3){
			double test = N/(X*(NR/1.1)) + kz*M/(MR3/1.1);
			if(test <= 1) {
				result = true;
			}
		}
		return result;
	}
	
	//return the value of the value of the second criterion (<1 if passed, >1 if not)
	public double performanceBuckling(double N,double M) {
		double NR = section.getA()*235e6;
		double MR12 = section.getWpl()*235e6;
		double MR3 = section.getWel()*235e6;
		double ii = Math.sqrt(section.getIz()/section.getA());
		double lambda = this.length/ii;
		double lambda1 = Math.PI*Math.sqrt(210e9/235e6);
		double lambdaM = lambda/lambda1;
		double phi = 0.5*(1+0.34*(lambdaM-0.2)+Math.pow(lambdaM, 2));
		double X = 1/(phi+Math.sqrt(Math.pow(phi,2)-Math.pow(lambdaM, 2)));
		double muz = -lambdaM*1.4+(section.getWpl()-section.getWel())/section.getWel();
		double test = 0;
		double kz = 1-(muz*N)/(X*section.getA()*235e6);
		if(section.getClasse() == 1 || section.getClasse() == 2) {
			test = N/(X*(NR/1.1)) + kz*M/(MR12/1.1);
		}
		else if (section.getClasse() == 3){
			test = N/(X*(NR/1.1)) + kz*M/(MR3/1.1);
		}
		return test;
	}
	
	//return true if the displacement criterion passed. Used for vertical and horizontal displacement
	public boolean displacement(double y) {
		if (Math.abs(y/100) <= length/500) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// return the value of the displacement criterion. (<1 if passed, >1 if not)
	public double performanceDisplacement(double y) {
		return Math.abs(y/100)/(length/500);
	}
	
	// all the methods that allows to access and set the protected fields
	public void setSection(Section toSet) {
		section = toSet;
	}
	public double getLength() {
		return length;
	}
	public Section getSection() {
		return section;
	}
	public double getH() {
		return h;
	}
	public double getA() {
		return a;
	}
	public double getL() {
		return l;
	}
	public double getAlpha() {
		return alpha;
	}
	public double getQ1() {
		return q1;
	}
	public double getQ2() {
		return q2;
	}
	public double getQ3() {
		return q3;
	}
	public double getN() {
		return N;
	}
	public double getDx() {
		return dx;
	}
}
