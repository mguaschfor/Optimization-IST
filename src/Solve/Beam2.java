package Solve;
import java.util.*;

//Children class of Beam that Override all the efforts and displacement methods
public class Beam2 extends Beam implements deltaC1, deltaC2{
	
	//fields
	private double Nmax;//maximal axial effort
	private double Tmax;//maximal shear effort
	private double Mmax;//maximal bending moment
	private double b;//geometric characteristic 
	private Section Lasection2;//section of the beal2
	private Beam1 beam1;//beam 1 is imported because we need its weight
	private double E;//young modulus

	public Beam2(double length, Section section2, double h, double a, double l, double alpha, double q1, double q2, double q3, int N, Beam1 beam1) {
		super(length,section2,h,a,l,alpha,q1,q2,q3,N);
		Nmax = 0;
		Tmax = 0;
		Mmax = 0;
		Lasection2 = section2;
		this.beam1 = beam1;
		E = 210e9;
		b = l/(2*Math.cos(alpha));
	}
	
	//method that calculates Nmax
	@Override
	public double calculateNmax(double XA, double YA, double XB, double YB) {
		double P1 = -(beam1.length*beam1.getSection().getG())*9.81;
		double P2 = -(length*Lasection2.getG())*9.81;
		double [] N2 = new double [N];
		for(int i = 0; i < N; i++) {
			if(x[i] <= length/2) {
				N2[i] = Math.abs(-Math.cos(alpha)*(XA+q2*h)-Math.sin(alpha)*(YA+P1+q1*x[i]));
			}
			else {
				N2[i] = Math.abs(-Math.cos(alpha)*(XA+q2*h)-Math.sin(alpha)*(YA+P1+P2+q1*x[i]));
			}
		}
		Nmax = Arrays.stream(N2).max().orElse(-1);
		return Nmax;
	}
	
	//method that calculates Tmax
	@Override	
	public double calculateTmax(double XA, double YA, double XB, double YB) {
		double P1 = -(beam1.length*beam1.getSection().getG())*9.81;
		double P2 = -(length*Lasection2.getG())*9.81;
		double [] T2 = new double [N];
		for(int i = 0; i < N; i++) {
			if(x[i] <= length/2) {
				T2[i] = Math.abs(Math.sin(alpha)*(XA+q2*h)-Math.cos(alpha)*(YA+P1+q1*x[i]));
			}
			else {
				T2[i] = Math.abs(Math.sin(alpha)*(XA+q2*h)-Math.cos(alpha)*(YA+P1+P2+q1*x[i]));
			}
		}
		Tmax = Arrays.stream(T2).max().orElse(-1);
		return Tmax;
	}
	
	//method that calculates Mmax
	@Override
	public double calculateMmax(double XA, double YA, double XB, double YB) {
		double P1 = -(beam1.length*beam1.getSection().getG())*9.81;
		double P2 = -(length*Lasection2.getG())*9.81;
		double [] M2 = new double [N];
		for(int i = 0; i < N; i++) {
			if(x[i] <= length/2) {
				M2[i] = Math.abs(x[i]*Math.cos(alpha)*YA-(x[i]*Math.sin(alpha)+h)*XA-(x[i]*Math.sin(alpha)+h/2)*q2*h+x[i]*Math.cos(alpha)*P1+x[i]/2*q1*x[i]);
			}
			else {
				M2[i] = Math.abs(x[i]*Math.cos(alpha)*YA-(x[i]*Math.sin(alpha)+h)*XA-(x[i]*Math.sin(alpha)+h/2)*q2*h+x[i]*Math.cos(alpha)*P1+x[i]/2*q1*x[i]-(b/2*Math.cos(alpha)-x[i]*Math.cos(alpha))*P2);
			}
		}
		Mmax = Arrays.stream(M2).max().orElse(-1);
		return Mmax;
	}
	
	//method that calculates horizontal displacement of C1 (contribution of this beam)
	@Override
	public double deltaXC1(double XA, double YA, double XB, double YB) {
		double P1 = -(beam1.length*beam1.getSection().getG())*9.81;
		double A = Lasection2.getA();
		double deltaXC1 = length*((-Math.cos(alpha)*(XA+q2*h)-Math.sin(alpha)*(YA+P1))/(A*E));
		return deltaXC1;
	}
	
	//method that calculates vertical displacement of C1 (contribution of this beam). No contribution (approximation)
	@Override
	public double deltaYC1(double XA, double YA, double XB, double YB) {
		return 0;
	}
	
	//method that calculates horizontal displacement of C2 (contribution of this beam)
	@Override
	public double deltaXC2(double XA, double YA, double XB, double YB) {
		double P1 = -(beam1.length*beam1.getSection().getG())*9.81;
		double P2 = -(length*Lasection2.getG())*9.81;
		double A = Lasection2.getA();
		double deltaXC2 = length*((-Math.cos(alpha)*(XA+q2*h)-Math.sin(alpha)*(YA+P1+P2+q1*length))/(A*E));
		return deltaXC2;
	}
	
	//method that calculates vertical displacement of C1 (contribution of this beam)
	@Override
	public double deltaYC2(double XA, double YA, double XB, double YB) {
		double Iz = Lasection2.getIz();
		double Mmax = this.calculateMmax(XA, YA, XB, YB);
		double deltaYC2 = 1/(384*E*Iz)*(5*q1*Math.pow(length,4)-48*Mmax*length*length);
		return 2*deltaYC2;
	}
}



