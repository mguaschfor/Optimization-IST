package Optimization;
import Solve.*;
import java.util.Random;

//class chromosome that is used in both algorithm. It allows to encode the 4 beams into 4 genes represented by a code
public class Chromosome implements Cloneable{
	
	//fields
	private int[] codes;//tab that will contains the 4 codes of the 4 genes
	private Beam[] dataBeam = new Beam[4];//tab that will keep in memory the 4 beams of the 4 genes after being decoded. Overridden each time the genes are decoded
	private static int NumberOfGenes = 4;//size of the tab codes
	private double fitness;//the fitness in the case of the genetic algorithm, the fictive energy in the case of the simulated annealing. Same function will be used in both cases
	private int K;//penalty coefficient
	//geometry characteristics of the frame
	private double length1;
	private double length2;
	private double length3;
	private double length4;
	private double alpha;
	private double l;
	private double h;
	private double a;
	//loads
	private double q1;
	private double q2;
	private double q3;
	
	//first constructor. Use to generate a random chromosome
	public Chromosome(int K, double length1, double length2, double length3, double length4, double alpha, double l, double h, double a, double q1, double q2, double q3) {
		codes = new int [NumberOfGenes];
		Random random = new Random();
		for(int i = 0; i < NumberOfGenes; i++) {
			double Aleatoire = Math.random();
			if(Aleatoire < 0.16) {
				codes[i]=100+random.nextInt(18);
			}
			else if(Aleatoire >= 0.16 && Aleatoire < 0.33){
				codes[i]=200+random.nextInt(19);
			}
			else if(Aleatoire >= 0.33 && Aleatoire < 0.50){
				codes[i]=400+random.nextInt(19);
			}
			else if(Aleatoire >= 0.50 && Aleatoire < 0.66){
				codes[i]=500+random.nextInt(19);
			}
			else if(Aleatoire >= 0.66 && Aleatoire < 0.83){
				codes[i]=600+random.nextInt(19);
			}
			else {
				codes[i]=300+random.nextInt(21);
			}
		}
		this.K = K;
		this.length1 = length1;
		this.length2 = length2;
		this.length3 = length3;
		this.length4 = length4;
		this.alpha = alpha;
		this.l = l;
		this.h = h;
		this.a = a;
		this.q1 = q1;
		this.q2 = q2;
		this.q3 = q3;
	}
	//second constructor. Used to generate a precise chromosome with the known genes passed in parameters of the method during the creation of clones
	public Chromosome(int K, double length1, double length2, double length3, double length4, double alpha, double l, double h, double a, double q1, double q2, double q3, int code1, int code2, int code3, int code4) {
		codes = new int [NumberOfGenes];
		codes[0] = code1;
		codes[1] = code2;
		codes[2] = code3;
		codes[3] = code4;
		this.K = K;
		this.length1 = length1;
		this.length2 = length2;
		this.length3 = length3;
		this.length4 = length4;
		this.alpha = alpha;
		this.l = l;
		this.h = h;
		this.a = a;
		this.q1 = q1;
		this.q2 = q2;
		this.q3 = q3;
	}
	//method that calculates the fitness(fictive energy) according the the formula presented in the report
	public double calculateFitness() {
		fitness = 0;
		double Crit = 0;
		for(int i = 0; i < NumberOfGenes; i++) {
			double g = 0;
			for(int j = 1; j < 5; j++) {
				Crit = this.calculateCriteria(j,i);
				if(Crit > 1) {
					g = g + Crit-1;
				}
			}
			fitness = fitness + this.getWeight(i)*(1+K*g);
		}
		return fitness;
	}
	//method that calculate the criteria passed in the parameters at the selected gene position 
	public double calculateCriteria(int Criteria, int Gene_pos) {
		double CriteriaToReturn = 0;
		//decoding the genes in order to update dataBeam after potential operations on the precedent generation
		this.dataBeam[0] = new Beam1(length1,this.decodeGene(0),h,a,l,alpha,q1,q2,q3,3);
		this.dataBeam[1] = new Beam2(length2,this.decodeGene(1),h,a,l,alpha,q1,q2,q3,3,(Beam1)dataBeam[0]);
		this.dataBeam[3] = new Beam4(length4,this.decodeGene(3),h,a,l,alpha,q1,q2,q3,3);
		this.dataBeam[2] = new Beam3(length3,this.decodeGene(2),h,a,l,alpha,q1,q2,q3,3,(Beam4)dataBeam[3]);
		//calculating everything needed to check the criteria
		//weights of the beams
		double P1 = 9.81*length1*this.decodeGene(0).getG();
		double P2 = 9.81*length2*this.decodeGene(1).getG();
		double P3 = 9.81*length3*this.decodeGene(2).getG();
		double P4 = 9.81*length4*this.decodeGene(3).getG();
		//support reactions
		double XA = -l*l*q1/(8*Math.cos(alpha)*(a+h))+h*h*(q2+q3)/(4*(a+h))+q3*(h*(a+h/2)/(a+h)-h)-l*(-P3-P2)/(8*(a+h))-q2*h;
		double YA = -q1*l/(2*Math.cos(alpha))-Math.pow(h,2)*(q2+q3)/(2*l)+3*P2/4+P3/4+P1;
		double XB = Math.pow(l,2)*q1/(8*Math.cos(alpha)*(a+h))-Math.pow(h,2)*(q2+q3)/(4*(a+h))-q3*(h*(a+h/2)/(a+h))-P3*l/(8*(a+h))-P2*l/(8*(a+h));
		double YB = -q1*l/(2*Math.cos(alpha))+Math.pow(h,2)*(q2+q3)/(2*l)+P2/4+3*P3/4+P4;
		//maximal efforts needed
		double Ni = dataBeam[Gene_pos].calculateNmax(XA,YA,XB,YB);
		double Mi = dataBeam[Gene_pos].calculateMmax(XA,YA,XB,YB);
		//displacements
		double deltaXC1 = 100*(((Beam1)dataBeam[0]).deltaXC1(XA,YA,XB,YB) + ((Beam2)dataBeam[1]).deltaXC1(XA,YA,XB,YB));
		double deltaYC1 = 100*(((Beam1)dataBeam[0]).deltaYC1(XA,YA,XB,YB) + ((Beam2)dataBeam[1]).deltaYC1(XA,YA,XB,YB));
		double deltaYC2 = 100*(((Beam2)dataBeam[1]).deltaYC2(XA,YA,XB,YB) + ((Beam3)dataBeam[2]).deltaYC2(XA,YA,XB,YB));
		double deltaXC2 = 100*(((Beam2)dataBeam[1]).deltaXC2(XA,YA,XB,YB) + ((Beam3)dataBeam[2]).deltaXC2(XA,YA,XB,YB));
		double deltaXC3 = 100*(((Beam3)dataBeam[2]).deltaXC3(XA,YA,XB,YB) + ((Beam4)dataBeam[3]).deltaXC3(XA,YA,XB,YB));
		double deltaYC3 = 100*(((Beam3)dataBeam[2]).deltaYC3(XA,YA,XB,YB) + ((Beam4)dataBeam[3]).deltaYC3(XA,YA,XB,YB));
		double deltaXC = deltaXC1 + deltaXC2 + deltaXC3;
		//Choosing and calculating the criteria chosen from parameter of the method to the selected gene
		if(Criteria == 1){
			CriteriaToReturn = dataBeam[Gene_pos].performanceEfforts(Ni,Mi);
		}
		else if(Criteria == 2){
			CriteriaToReturn = dataBeam[Gene_pos].performanceBuckling(Ni,Mi);
		}
		else if(Criteria == 3){
			if(Gene_pos == 0) {
				CriteriaToReturn = dataBeam[0].performanceDisplacement(deltaXC1);
			}
			else if(Gene_pos == 1) {
				CriteriaToReturn = dataBeam[1].performanceDisplacement(deltaXC);
			}
			else if(Gene_pos == 2) {
				CriteriaToReturn = dataBeam[2].performanceDisplacement(deltaXC);
			}
			else if(Gene_pos == 3) {
				CriteriaToReturn = dataBeam[3].performanceDisplacement(deltaXC3);
			}
		}
		else {
			if(Gene_pos == 0) {
				CriteriaToReturn = dataBeam[0].performanceDisplacement(deltaYC1);
			}
			else if(Gene_pos == 1) {
				CriteriaToReturn = dataBeam[1].performanceDisplacement(deltaYC2);
			}
			else if(Gene_pos == 2) {
				CriteriaToReturn = dataBeam[2].performanceDisplacement(deltaYC2);
			}
			else if(Gene_pos == 3) {
				CriteriaToReturn = dataBeam[3].performanceDisplacement(deltaYC3);
			}
		}
		return CriteriaToReturn;
	}
	//return a string of the chromosome (encoded)
	@Override
	public String toString() {
		String chro = "|";
		for(int i = 0 ; i < NumberOfGenes; i++) {
			chro = chro + Integer.toString(this.getCode(i))+"|";
		}
	return chro;
	}
	//return a string of the chromosome (decoded)
	public String toString2() {
		String chro = "|";
		for(int i = 0 ; i < NumberOfGenes; i++) {
			chro = chro + Section.decode(this.getCode(i)).toString()+"|";
		}
	return chro;
	}
	//Allows to clone a chromosome (if operations affect the clone, it will not affect the original)
	@Override
	public Object clone() {
	    Chromosome Clone = null;
	    try {
	    	Clone = (Chromosome) super.clone();
	    	Clone = new Chromosome(this.K, this.length1, this.length2, this.length3, this.length4, this.alpha, this.l, this.h, this.a, this.q1, this.q2, this.q3, this.getCode(0), this.getCode(1), this.getCode(2), this.getCode(3));
	    } catch(CloneNotSupportedException cnse) {
	      	cnse.printStackTrace(System.err);
	    }
	    return Clone;
	}
	//Method that allow to decode the selected genes 
	public Section decodeGene(int index) {
		return Section.decode(codes[index]);
	}
	//getters and setters
	public int getCode(int i) {
		return codes[i];
	}
	public int [] getCodes() {
		return codes;
	}
	public void setCode(int index, int value) {
		codes[index] = value;
	}
	public double getWeight(int index) {
		return 7800*dataBeam[index].getL()*Section.decode(codes[index]).getA();
	}
}
