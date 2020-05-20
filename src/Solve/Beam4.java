package Solve;
import java.util.*;

//Children class of Beam that Override all the efforts and displacement methods
public class Beam4 extends Beam implements deltaC3{
	
	//fields
	private double Nmax;//maximal axial effort
	private double Tmax;//maximal shear effort
	private double Mmax;//maximal bending moment
	private Section Lasection4;//section of the beam4
	private double E;//young modulus
	
	public Beam4(double length, Section section4, double h, double a, double l, double alpha, double q1, double q2, double q3, int N) {
		super(length,section4,h,a,l,alpha,q1,q2,q3,N);
			Nmax = 0;
			Tmax = 0;
			Mmax = 0;
			E = 210e9;
			Lasection4 = section4;
	}
	
	//method that calculates Nmax
	@Override
	public double calculateNmax(double XA, double YA, double XB, double YB) {
		Nmax = Math.abs(-YB);
		return Nmax;
	}
	
	//method that calculates Tmax
	@Override
	public double calculateTmax(double XA, double YA, double XB, double YB) {
		double [] T4 = new double [N];
		for(int i = 0; i < N; i++) {
			T4[i] = Math.abs(q3*x[i]+XB);
		}
		Tmax = Arrays.stream(T4).max().orElse(-1);
		return Tmax;
	}
	
	//method that calculate Mmax
	@Override
	public double calculateMmax(double XA, double YA, double XB, double YB) {
		double [] M4 = new double[N];
		for(int i = 0; i < N; i++) {
			M4[i] = Math.abs(-x[i]*x[i]*q3+h/2*q3*x[i]-x[i]*XB);
		}
		Mmax = Arrays.stream(M4).max().orElse(-1);
		return Mmax;
	}
	
	//method that calculates horizontal displacement of C3 (contribution of this beam)
	@Override
	public double deltaXC3(double XA, double YA, double XB, double YB) {
		double Iz = Lasection4.getIz();
		double deltaXC3 = 1/(E*Iz)*(-Math.pow(length,4)*q3/12+h/12*q3*Math.pow(length,3)-Math.pow(length,3)/6*XB);
		return deltaXC3;
	}
	
	//method that calculates vertical displacement of C3 (contribution of this beam)
	@Override
	public double deltaYC3(double XA, double YA, double XB, double YB) {
		double A = Lasection4.getA();
		double P4= length*section.getG();
		double deltaYC3 = -length*(P4+YB)/(A*E);
		return deltaYC3;
	}
}
