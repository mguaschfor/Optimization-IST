package UI;
import Solve.*;
import java.awt.event.*;
import java.text.DecimalFormat;

//This class implement the action when we click on the button calculate
public class EcouteurCalculate  implements ActionListener {
		
	private JavaAlgoGen fen;
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
	private int N;
	private double XA;
	private double YA;
	private double XB;
	private double YB;
	private Section section1;
	private Section section2;
	private Section section3;
	private Section section4;
	private double P1;
	private double P2;
	private double P3;
	private double P4;
	
	public EcouteurCalculate(JavaAlgoGen fen) {
		this.fen =fen;
	}
		
	public void actionPerformed(ActionEvent ae){
		section1 = (Section) fen.getList1().getSelectedItem();
		section2 = (Section) fen.getList2().getSelectedItem();
		section3 = (Section) fen.getList3().getSelectedItem();
		section4 = (Section) fen.getList4().getSelectedItem();
		alpha = Math.PI*fen.getAlpha()/180;
		length1 = fen.getlength1();
		length2 = fen.getlength2();
		length3 = fen.getlength3();
		length4 = fen.getlength4();
		P1 = 9.81*length1*section1.getG();
		P2 = 9.81*length2*section2.getG();
		P3 = 9.81*length3*section3.getG();
		P4 = 9.81*length4*section4.getG();
		l = length2*Math.cos(alpha) + length3*Math.cos(alpha);
		h = length1;
		a = length2*Math.sin(alpha);
		q1 = fen.getq1();
		q2 = fen.getq2();
		q3 = fen.getq3();
		N = fen.getdx();
		
		XA = -l*l*q1/(8*Math.cos(alpha)*(a+h))+h*h*(q2+q3)/(4*(a+h))+q3*(h*(a+h/2)/(a+h)-h)-l*(-P3-P2)/(8*(a+h))-q2*h;
		YA = -q1*l/(2*Math.cos(alpha))-Math.pow(h,2)*(q2+q3)/(2*l)+3*P2/4+P3/4+P1;
		XB = Math.pow(l,2)*q1/(8*Math.cos(alpha)*(a+h))-Math.pow(h,2)*(q2+q3)/(4*(a+h))-q3*(h*(a+h/2)/(a+h))-P3*l/(8*(a+h))-P2*l/(8*(a+h));
		YB = -q1*l/(2*Math.cos(alpha))+Math.pow(h,2)*(q2+q3)/(2*l)+P2/4+3*P3/4+P4;
		
		Beam1 beam1 = new Beam1(length1,section1,h,a,l,alpha,q1,q2,q3,N);
		Beam2 beam2 = new Beam2(length2,section2,h,a,l,alpha,q1,q2,q3,N,beam1);
		Beam4 beam4 = new Beam4(length4,section4,h,a,l,alpha,q1,q2,q3,N);
		Beam3 beam3 = new Beam3(length3,section3,h,a,l,alpha,q1,q2,q3,N,beam4);
		
		double N1 = Math.round(beam1.calculateNmax(XA,YA,XB,YB)*100)/100;
		double T1 = Math.round(beam1.calculateTmax(XA,YA,XB,YB)*100)/100;
		double M1 = Math.round(beam1.calculateMmax(XA,YA,XB,YB)*100)/100;
		double N2 = Math.round(beam2.calculateNmax(XA,YA,XB,YB)*100)/100;
		double T2 = Math.round(beam2.calculateTmax(XA,YA,XB,YB)*100)/100;
		double M2 = Math.round(beam2.calculateMmax(XA,YA,XB,YB)*100)/100;
		double N3 = Math.round(beam3.calculateNmax(XA,YA,XB,YB)*100)/100;
		double T3 = Math.round(beam3.calculateTmax(XA,YA,XB,YB)*100)/100;
		double M3 = Math.round(beam3.calculateMmax(XA,YA,XB,YB)*100)/100;
		double N4 = Math.round(beam4.calculateNmax(XA,YA,XB,YB)*100)/100;
		double T4 = Math.round(beam4.calculateTmax(XA,YA,XB,YB)*100)/100;
		double M4 = Math.round(beam4.calculateMmax(XA,YA,XB,YB)*100)/100;
		double deltaXC1 = 100*(beam1.deltaXC1(XA,YA,XB,YB) + beam2.deltaXC1(XA,YA,XB,YB));
		double deltaYC1 = 100*(beam1.deltaYC1(XA,YA,XB,YB) + beam2.deltaYC1(XA,YA,XB,YB));
		double deltaYC2 = 100*(beam2.deltaYC2(XA,YA,XB,YB) + beam3.deltaYC2(XA,YA,XB,YB));
		double deltaXC2 = 100*(beam2.deltaXC2(XA,YA,XB,YB) + beam3.deltaXC2(XA,YA,XB,YB));
		double deltaXC3 = 100*(beam3.deltaXC3(XA,YA,XB,YB) + beam4.deltaXC3(XA,YA,XB,YB));
		double deltaYC3 = 100*(beam3.deltaYC3(XA,YA,XB,YB) + beam4.deltaYC3(XA,YA,XB,YB));
		double deltaXC = deltaXC1 + deltaXC2 + deltaXC3;
		
		String efforts = "failed";
		if(beam1.Efforts(N1, M1) == false) {
			efforts = efforts+" 1";
		}
		if(beam2.Efforts(N2, M2) == false) {
			efforts = efforts+" 2";
		}
		if(beam3.Efforts(N3, M3) == false) {
			efforts = efforts+" 3";
		}
		if(beam4.Efforts(N4, M4) == false) {
			efforts = efforts+" 4";
		}
		if (beam1.Efforts(N1,M1) && beam2.Efforts(N2,M2) && beam3.Efforts(N3,M3) && beam4.Efforts(N4,M4) == true) {
			efforts = "Passed";
		}	
		System.out.println("Critera 1 ");
		System.out.println(beam1.performanceEfforts(N1, M1));
		System.out.println(beam2.performanceEfforts(N2, M2));
		System.out.println(beam3.performanceEfforts(N3, M3));
		System.out.println(beam4.performanceEfforts(N4, M4));
		
		String buckling = "failed";
		if(beam1.buckling(N1, M1) == false) {
			buckling = buckling+" 1";
		}
		if(beam2.buckling(N2, M2) == false) {
			buckling = buckling+" 2";
		}
		if(beam3.buckling(N3, M3) == false) {
			buckling = buckling+" 3";
		}
		if(beam4.buckling(N4, M4) == false) {
			buckling = buckling+" 4";
		}
		if (beam1.buckling(N1,M1) && beam2.buckling(N2,M2) && beam3.buckling(N3,M3) && beam4.buckling(N4,M4) == true) {
			buckling = "Passed";
		}	
		System.out.println("Critera 2 ");
		System.out.println(beam1.performanceBuckling(N1, M1));
		System.out.println(beam2.performanceBuckling(N2, M2));
		System.out.println(beam3.performanceBuckling(N3, M3));
		System.out.println(beam4.performanceBuckling(N4, M4));
		
		String horizontalCriteria = "failed";
		if (beam1.displacement(deltaXC1) == false) {
			horizontalCriteria = horizontalCriteria+" 1";
		}
		if (beam2.displacement(deltaXC) == false) {
			horizontalCriteria = horizontalCriteria+" 2";
		}
		if (beam3.displacement(deltaXC) == false) {
			horizontalCriteria = horizontalCriteria+" 3";
		}
		if (beam4.displacement(deltaXC3) == false) {
			horizontalCriteria = horizontalCriteria+" 4";
		}
		if (beam1.displacement(deltaXC1) && beam2.displacement(deltaXC) && beam3.displacement(deltaXC) && beam4.displacement(deltaXC3) == true) {
			horizontalCriteria = "Passed";
		}
		System.out.println("Criteria 3 ");
		System.out.println(beam1.performanceDisplacement(deltaXC1));
		System.out.println(beam2.performanceDisplacement(deltaXC));
		System.out.println(beam3.performanceDisplacement(deltaXC));
		System.out.println(beam4.performanceDisplacement(deltaXC3));
		
		String verticalCriteria = "failed";
		if (beam1.displacement(deltaYC1) == false) {
			verticalCriteria = verticalCriteria+" 1";
		}
		if (beam2.displacement(deltaYC2) == false) {
			verticalCriteria = verticalCriteria+" 2";
		}
		if (beam3.displacement(deltaYC2) == false) {
			verticalCriteria = verticalCriteria+" 3";
		}
		if (beam4.displacement(deltaYC3) == false) {
			verticalCriteria = verticalCriteria+" 4";
		}
		if (beam1.displacement(deltaYC1) && beam2.displacement(deltaYC2) && beam3.displacement(deltaYC2) && beam4.displacement(deltaYC3) == true) {
			verticalCriteria = "Passed";
		}
		System.out.println("Criteria 4 ");
		System.out.println(beam1.performanceDisplacement(deltaYC1));
		System.out.println(beam2.performanceDisplacement(deltaYC2));
		System.out.println(beam3.performanceDisplacement(deltaYC2));
		System.out.println(beam4.performanceDisplacement(deltaYC3));
		
		fen.setN1(Double.toString(N1)+" N");
		fen.setT1(Double.toString(T1)+" N");
		fen.setM1(Double.toString(M1)+" N.m");
		fen.setN2(Double.toString(N2)+" N");
		fen.setT2(Double.toString(T2)+" N");
		fen.setM2(Double.toString(M2)+" N.m");
		fen.setN3(Double.toString(N3)+" N");
		fen.setT3(Double.toString(T3)+" N");
		fen.setM3(Double.toString(M3)+" N.m");
		fen.setN4(Double.toString(N4)+" N");
		fen.setT4(Double.toString(T4)+" N");
		fen.setM4(Double.toString(M4)+" N.m");
		DecimalFormat df = new DecimalFormat("###.###");
		fen.setDeltaX1(df.format(deltaXC1)+" cm");
		fen.setDeltaX2(df.format(deltaXC)+" cm");
		fen.setDeltaX3(df.format(deltaXC3)+" cm");
		fen.setDeltaY1(df.format(deltaYC1)+" cm");
		fen.setDeltaY2(df.format(deltaYC2)+" cm");
		fen.setDeltaY3(df.format(deltaYC3)+" cm");
		fen.setCritera1(efforts);
		fen.setCritera2(buckling);
		fen.setCritera3(horizontalCriteria);
		fen.setCritera4(verticalCriteria);
	}
}
