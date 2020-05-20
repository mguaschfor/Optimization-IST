package Optimization;
import java.util.Random;

//this is the class that will control the evolution of the population of the chromosomes (simulated annealing algorithm)
public class PopulationAnnealed {
	
	//fields
	private Chromosome[] Chromosomes; //tab of chromosomes. It's the current population
	private Chromosome[] SelectedPop; //intermediary tab of chromosomes. In this tab enters the selected chromosomes that will suffer operations
	private double fittest; //best chromosome of the population
	private double worst; //worst chromosome of the population
	private int NumberOfChromosomes; //size N of the population
	private double T; // Temperature of the fictive system
	private static int Ngen=0; //Iterator that counts the number of generations
	
	//Constructor of the class. It creates a random population
	public PopulationAnnealed(int NumberOfChromosomes, int K, double T, double length1, double length2, double length3, double length4, double alpha, double l, double h, double a, double q1, double q2, double q3) {
		this.NumberOfChromosomes = NumberOfChromosomes;
		this.T = T;
		Chromosomes = new Chromosome[NumberOfChromosomes];
		for(int i = 0; i < NumberOfChromosomes; i++) {
			Chromosomes[i] = new Chromosome(K,length1,length2,length3,length4,alpha,l,h,a,q1,q2,q3);
		}
	}
	
	//Perform the Metropolis algorithm to keep or no the neighbor chromosome
	public void Metropolis() {
		SelectedPop = new Chromosome[NumberOfChromosomes];
		for(int i = 0; i < NumberOfChromosomes; i++) {
			Chromosome potential = this.Neighbor(i);
			double Zc = Chromosomes[i].calculateFitness();
			double Zn = potential.calculateFitness();
			if(Zn < Zc) {
				SelectedPop[i] = (Chromosome) potential.clone();
			}
			else {
				double Pacceptance = Math.exp((Zc-Zn)/T);
				double Aleatoire = Math.random();
				if(Aleatoire <= Pacceptance) {
					SelectedPop[i] = (Chromosome) potential.clone();
				}
				else {
					SelectedPop[i] = (Chromosome) Chromosomes[i].clone();
				}
			}
		}
	}
	
	//generate a random neighbor chromosome (with a close random modification of one gene)
	public Chromosome Neighbor(int i) {
		Chromosome neighbor = (Chromosome) Chromosomes[i].clone();
		int GeneMuted_pos = (int) Math.round(3.49*Math.random());
		//random variable which determine which new section to choose in the selected family
		Random random = new Random();
		//random variable which determine which family of sections to choose
		double Aleatoire = Math.random();
		//random variable that choose if we take a bigger or a smaller section
		double randomSign = Math.random();
		if(Aleatoire < 0.16) {
			if(randomSign <= 0.5) {
				if(this.getPop()[i].getCodes()[GeneMuted_pos]%100 <= 15){ 
					neighbor.getCodes()[GeneMuted_pos] = 100+this.getPop()[i].getCodes()[GeneMuted_pos]%100+random.nextInt(3);
				}
				else if(this.getPop()[i].getCodes()[GeneMuted_pos]%100 == 16) {
					neighbor.getCodes()[GeneMuted_pos] = 100+this.getPop()[i].getCodes()[GeneMuted_pos]%100+1;
				}
			}
			else {
				if(this.getPop()[i].getCodes()[GeneMuted_pos]%100 >= 2) {
					neighbor.getCodes()[GeneMuted_pos] = 100+this.getPop()[i].getCodes()[GeneMuted_pos]%100-random.nextInt(3);
				}
				else if(this.getPop()[i].getCodes()[GeneMuted_pos]%100 == 1) {
					neighbor.getCodes()[GeneMuted_pos] = 100+this.getPop()[i].getCodes()[GeneMuted_pos]%100-1;
				}
			}
		}
		else if(Aleatoire >= 0.16 && Aleatoire < 0.33){
			if(randomSign <= 0.5) {
				if(this.getPop()[i].getCodes()[GeneMuted_pos]%100 <= 16) {
					neighbor.getCodes()[GeneMuted_pos] = 200+this.getPop()[i].getCodes()[GeneMuted_pos]%100+random.nextInt(3);
				}
				else if(this.getPop()[i].getCodes()[GeneMuted_pos]%100 == 17) {
					neighbor.getCodes()[GeneMuted_pos] = 200+this.getPop()[i].getCodes()[GeneMuted_pos]%100+1;
				}
			}
			else {
				if(this.getPop()[i].getCodes()[GeneMuted_pos]%100 >= 2 ) {
					neighbor.getCodes()[GeneMuted_pos] = 200+this.getPop()[i].getCodes()[GeneMuted_pos]%100-random.nextInt(3);
				}
				else if(this.getPop()[i].getCodes()[GeneMuted_pos]%100 == 1) {
					neighbor.getCodes()[GeneMuted_pos] = 200+this.getPop()[i].getCodes()[GeneMuted_pos]%100-1;
				}
			}				
		}
		else if(Aleatoire >= 0.33 && Aleatoire < 0.50){
			if(randomSign <= 0.5) {
				if(this.getPop()[i].getCodes()[GeneMuted_pos]%100 <= 16) {
					neighbor.getCodes()[GeneMuted_pos] = 400+this.getPop()[i].getCodes()[GeneMuted_pos]%100+random.nextInt(3);
				}
				else if(this.getPop()[i].getCodes()[GeneMuted_pos]%100 == 17) {
					neighbor.getCodes()[GeneMuted_pos] = 400+this.getPop()[i].getCodes()[GeneMuted_pos]%100+1;
				}
			}
			else {
				if(this.getPop()[i].getCodes()[GeneMuted_pos]%100 >= 2 ) {
					neighbor.getCodes()[GeneMuted_pos] = 400+this.getPop()[i].getCodes()[GeneMuted_pos]%100-random.nextInt(3);
				}
				else if(this.getPop()[i].getCodes()[GeneMuted_pos]%100 == 1) {
					neighbor.getCodes()[GeneMuted_pos] = 400+this.getPop()[i].getCodes()[GeneMuted_pos]%100-1;
				}
			}				
		}
		else if(Aleatoire >= 0.50 && Aleatoire < 0.66){
			if(randomSign <= 0.5) {
				if(this.getPop()[i].getCodes()[GeneMuted_pos]%100 <= 16) {
					neighbor.getCodes()[GeneMuted_pos] = 500+this.getPop()[i].getCodes()[GeneMuted_pos]%100+random.nextInt(3);
				}
				else if(this.getPop()[i].getCodes()[GeneMuted_pos]%100 == 17) {
					neighbor.getCodes()[GeneMuted_pos] = 500+this.getPop()[i].getCodes()[GeneMuted_pos]%100+1;
				}
			}
			else {
				if(this.getPop()[i].getCodes()[GeneMuted_pos]%100 >= 2 ) {
					neighbor.getCodes()[GeneMuted_pos] = 500+this.getPop()[i].getCodes()[GeneMuted_pos]%100-random.nextInt(3);
				}
				else if(this.getPop()[i].getCodes()[GeneMuted_pos]%100 == 1) {
					neighbor.getCodes()[GeneMuted_pos] = 500+this.getPop()[i].getCodes()[GeneMuted_pos]%100-1;
				}
			}				
		}
		else if(Aleatoire >= 0.66 && Aleatoire < 0.83){
			if(randomSign <= 0.5) {
				if(this.getPop()[i].getCodes()[GeneMuted_pos]%100 <= 16) {
					neighbor.getCodes()[GeneMuted_pos] = 600+this.getPop()[i].getCodes()[GeneMuted_pos]%100+random.nextInt(3);
				}
				else if(this.getPop()[i].getCodes()[GeneMuted_pos]%100 == 17) {
					neighbor.getCodes()[GeneMuted_pos] = 600+this.getPop()[i].getCodes()[GeneMuted_pos]%100+1;
				}
			}
			else {
				if(this.getPop()[i].getCodes()[GeneMuted_pos]%100 >= 2 ) {
					neighbor.getCodes()[GeneMuted_pos] = 600+this.getPop()[i].getCodes()[GeneMuted_pos]%100-random.nextInt(3);
				}
				else if(this.getPop()[i].getCodes()[GeneMuted_pos]%100 == 1) {
					neighbor.getCodes()[GeneMuted_pos] = 600+this.getPop()[i].getCodes()[GeneMuted_pos]%100-1;
				}
			}				
		}
		else {
			if(randomSign <= 0.5) {
				if(this.getPop()[i].getCodes()[GeneMuted_pos]%100 <= 18) {
					neighbor.getCodes()[GeneMuted_pos] = 300+this.getPop()[i].getCodes()[GeneMuted_pos]%100+random.nextInt(3);
				}
				else if(this.getPop()[i].getCodes()[GeneMuted_pos]%100 == 19) {
					neighbor.getCodes()[GeneMuted_pos] = 300+this.getPop()[i].getCodes()[GeneMuted_pos]%100+1;
				}
			}
			else {
				if(this.getPop()[i].getCodes()[GeneMuted_pos]%100 >= 2) {
					neighbor.getCodes()[GeneMuted_pos] = 300+this.getPop()[i].getCodes()[GeneMuted_pos]%100-random.nextInt(3);
				}
				else if(this.getPop()[i].getCodes()[GeneMuted_pos]%100 == 1) {
					neighbor.getCodes()[GeneMuted_pos] = 300+this.getPop()[i].getCodes()[GeneMuted_pos]%100-1;
				}
			}
		}
		return neighbor;
	}
	
	//method that erase the previous generations and decrease the temperature
	public void NextGeneration() {
		for(int i = 0; i < NumberOfChromosomes; i++) {
			Chromosomes[i] = (Chromosome) SelectedPop[i].clone();
		}
		Ngen++;
		//Linear model (-1 degree at each iteration)
		T = T-1;
	}
	
	//all the getters
	public Chromosome getWorst() {
		int indexMax = 0;
		double [] fitness = new double [NumberOfChromosomes];
		double fitnessMax = 0;
		for(int i = 0; i < NumberOfChromosomes; i++) {
			fitness [i] = Chromosomes[i].calculateFitness();
			if(fitnessMax < fitness[i]) {
				fitnessMax = fitness[i];
				indexMax = i;
			}
		}
		worst = fitnessMax;
		return Chromosomes[indexMax];
	}
	public Chromosome getFittest() {
		int indexMin = 0;
		double [] fitness = new double [NumberOfChromosomes];
		double fitnessMin = 5e21;
		for(int i = 0; i < NumberOfChromosomes; i++) {
			fitness [i] = Chromosomes[i].calculateFitness();
			if(fitness[i] < fitnessMin) {
				fitnessMin = fitness[i];
				indexMin = i;
			}
		}
		fittest = fitnessMin;
		return Chromosomes[indexMin];
	}
	public Chromosome[] getSelectedPop() {
		return SelectedPop;
	}
	public Chromosome[] getPop() {
		return Chromosomes;
	}
	public Chromosome getChromosome(int index) {
		return Chromosomes[index];
	}
	public double getFittestValue() {
		return fittest;
	}
	public double getWorstValue() {
		return worst;
	}
	public int getIteration() {
		return Ngen;
	}
	public double getTemperature() {
		return T;
	}
	public static void clearTheIteration() {
		Ngen = 1;
	}
}
