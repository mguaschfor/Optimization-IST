package Solve;
import java.util.*;

//Children class of Beam that Override all the efforts and displacement methods
public class Beam3 extends Beam implements deltaC2, deltaC3 {
	
	//fields
	private double Nmax;//maximal axial effort
	private double Tmax;//maximal shear effort
	private double Mmax;//maximal bending effort
	private double b;//geometric characteristic
	private Section Lasection3;//section of the beam3
	private Beam4 beam4;//beam 4 is imported because we need its weight
	private double E;//young modulus
		
	public Beam3(double length, Section section3, double h, double a, double l, double alpha, double q1, double q2, double q3, int N, Beam4 beam4) {
		super(length,section3,h,a,l,alpha,q1,q2,q3,N);
		Nmax = 0;
		Tmax = 0;
		Mmax = 0;
		b = l/(2*Math.cos(alpha));
		E = 210e9;
		Lasection3 = section3;
		this.beam4 = beam4;
	}
	
	//method that calculates Nmax
	@Override
	public double calculateNmax(double XA, double YA, double XB, double YB) {
		double P4 = -(beam4.length*beam4.getSection().getG())*9.81;
		double P3 = -(length*Lasection3.getG())*9.81;
		double [] N3 = new double [N];
		for(int i = 0; i < N; i++) {
			if(x[i] < length/2) {
				N3[i] = Math.abs(Math.cos(alpha)*(XB+q3*h)-Math.sin(alpha)*(YB+q1*x[i]+P4));
			}
			else {
				N3[i] = Math.abs(Math.cos(alpha)*(XB+q3*h)-Math.sin(alpha)*(YB+q1*x[i]+P3+P4));
			}
		}
		Nmax = Arrays.stream(N3).max().orElse(-1);
		return Nmax;
	}
	
	//method that calculates Tmax
	@Override
	public double calculateTmax(double XA, double YA, double XB, double YB) {
		double P4 = -(beam4.length*beam4.getSection().getG())*9.81;
		double P3 = -(length*Lasection3.getG())*9.81;
		double [] T3 = new double [N];
		for(int i = 0; i < N; i++) {
			if(x[i] < length/2) {
				T3[i] = Math.abs(Math.cos(alpha)*(YB+q1*x[i]+P4)+Math.sin(alpha)*(XB+q3*h));
			}
			else {
				T3[i] = Math.abs(Math.cos(alpha)*(YB+q1*x[i]+P3+P4)+Math.sin(alpha)*(XB+q3*h));
			}
		}
		Tmax = Arrays.stream(T3).max().orElse(-1);
		return Tmax;
	}
	
	//method that calculates Mmax
	@Override
	public double calculateMmax(double XA, double YA, double XB, double YB) {
		double P4 = -(beam4.length*beam4.getSection().getG())*9.81;
		double P3 = -(length*Lasection3.getG())*9.81;
		double [] M3 = new double[N];
		for(int i = 0; i < N; i++) {
			if(x[i]<length/2) {
				M3[i] = Math.abs(-x[i]*Math.cos(alpha)*YB-(x[i]*Math.sin(alpha)+h)*XB-(x[i]*Math.sin(alpha)+h/2)*q3*h-x[i]*Math.cos(alpha)*P4-x[i]/2*Math.cos(alpha)*q1*x[i]);
			}
			else {
				M3[i] = Math.abs(-x[i]*Math.cos(alpha)*YB-(x[i]*Math.sin(alpha)+h)*XB-(x[i]*Math.sin(alpha)+h/2)*q3*h-x[i]*Math.cos(alpha)*P4-x[i]/2*Math.cos(alpha)*q1*x[i]-(-b/2*Math.cos(alpha)+x[i]*Math.cos(alpha))*P3);	
			}
		}
		Mmax = Arrays.stream(M3).max().orElse(-1);
		return Mmax;
	}
	
	//method that calculates horizontal displacement of C2 (contribution of this beam)
	@Override
	public double deltaXC2(double XA, double YA, double XB, double YB) {
		double P4 = -(beam4.length*beam4.getSection().getG())*9.81;
		double P3 = -(length*Lasection3.getG())*9.81;
		double A = Lasection3.getA();
		double deltaXC2 = length*(Math.cos(alpha)*(XB+q3*h)-Math.sin(alpha)*YB+P3+q1*length+P4)/(A*E);
		return deltaXC2;
	}
	
	//method that calculates horizontal displacement of C3 (contribution of this beam)
	@Override
	public double deltaXC3(double XA, double YA, double XB, double YB) {
		double P4 = -(beam4.length*beam4.getSection().getG())*9.81;
		double A = Lasection3.getA();
		double deltaXC3 = length*(Math.cos(alpha)*(XB+q3*h)-Math.sin(alpha)*YB+P4)/(A*E);
		return deltaXC3;
	}
	
	//method that calculates vertical displacement of C3 (contribution of this beam)
	@Override
	public double deltaYC3(double XA, double YA, double XB, double YB) {
		return 0;
	}
	
	//method that calculates vertical displacement of C2 (contribution of this beam)
	@Override
	public double deltaYC2(double XA, double YA, double XB, double YB) {
		double Iz = Lasection3.getIz();
		double deltaYC2 = 1/(384*E*Iz)*(5*q1*Math.pow(length,4)-48*Mmax*length*length);
		return 2*deltaYC2;
	}
}
