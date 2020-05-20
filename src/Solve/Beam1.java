package Solve;
import java.util.*;

//Children class of Beam that Override all the efforts and displacement methods 
public class Beam1 extends Beam implements deltaC1 {
	
	//fields
	private double Nmax;//maximum axial effort
	private double Tmax;//maximum shear effort
	private double Mmax;//maximum bending moment
	private double E;//young modulus of steel
	private Section Lasection1;//section of the beam
	
	public Beam1(double length, Section section1, double h, double a, double l, double alpha, double q1, double q2, double q3, int N) {
		super(length,section1,h,a,l,alpha,q1,q2,q3,N);
		Nmax = 0;
		Tmax = 0;
		Mmax = 0;
		Lasection1 = section1;
		E = 210e9;
	}
	
	//method that calculates Nmax
	@Override
	public double calculateNmax(double XA, double YA, double XB, double YB) {
		Nmax = Math.abs(-YA);
		return Nmax;
	}
	
	//method that calculates Tmax
	@Override
	public double calculateTmax(double XA, double YA, double XB, double YB) {
		double [] T1 = new double [N];
		for(int i = 0; i < N;i++) {
			T1[i] = Math.abs(q2*x[i]+XA);
		}
		Tmax = Arrays.stream(T1).max().orElse(-1);
		return Tmax;
	}
	
	//method that calculates Mmax
	@Override
	public double calculateMmax(double XA, double YA, double XB, double YB) {
		double [] M1 = new double [N];
		for(int i = 0; i < N; i++) {
			M1[i] = Math.abs(-q2*x[i]*x[i]+(h/2)*q2*x[i]-x[i]*XA);
		}
		Mmax = Arrays.stream(M1).max().orElse(-1);
		return Mmax;
	}
	
	//method that calculates horizontal displacement of C1 (contribution of this beam)
	@Override
	public double deltaXC1(double XA, double YA, double XB, double YB) {
		double Iz = Lasection1.getIz();
		double deltaXC1 = (-q2*Math.pow(length,4)/12+Math.pow(length, 3)/6*(h/2*q2-XA))/(E*Iz);
		return deltaXC1;
	}
	
	//method that calculates vertical displacement of C1 (contribution of this beam)
	@Override
	public double deltaYC1(double XA, double YA, double XB, double YB) {
		double A = Lasection1.getA();
		double P1 = length*section.getG();
		double deltaYC1 = (-P1-YA)*length/(E*A);
		return deltaYC1;
	}
}
