package Optimization;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

// this is the class that will control the evolution of the population of the chromosomes (genetic algorithm)
public class Population {
	
	//fields
	private Chromosome[] Chromosomes; //tab of chromosomes. It's the current population
	private Chromosome[] SelectedPop; //intermediary tab of chromosomes. In this tab enters the selected chromosomes that will suffer operations
	private double fittest; //best chromosome of the population
	private double worst; //worst chromosome of the population
	private int NumberOfChromosomes; //size N of the population
	private static int Ngen=1; //Iterator that counts the number of generations

	//Constructor of the class. It creates a random population
	public Population(int NumberOfChromosomes, int K, double length1, double length2, double length3, double length4, double alpha, double l, double h, double a, double q1, double q2, double q3) {
		this.NumberOfChromosomes = NumberOfChromosomes;
		Chromosomes = new Chromosome[NumberOfChromosomes];
		for(int i = 0; i < NumberOfChromosomes; i++) {
			Chromosomes[i] = new Chromosome(K,length1,length2,length3,length4,alpha,l,h,a,q1,q2,q3);
		}
	}
	
	//method that fulfills the Selectedpop tab under the N/2 elitism selection procedure
	public void Elitism() {
		//We need a 2D tab to keep in the first column the fitness of all chromosomes and in the second, their positions
		double[][] temp = new double[NumberOfChromosomes][2];
		//we need the first best part of the population, and their clones in sorted2
		Chromosome [] sorted = new Chromosome[NumberOfChromosomes];
		Chromosome [] sorted2 = new Chromosome[NumberOfChromosomes];
		SelectedPop = new Chromosome[NumberOfChromosomes];
		for(int i = 0; i < NumberOfChromosomes; i++) {
			temp[i][0] = Chromosomes[i].calculateFitness();
			temp[i][1] = i;
			}
		//Sorting from lowest to highest fitness
		Arrays.sort(temp,Comparator.comparingDouble(o -> o[0]));
		for(int i = 0; i < NumberOfChromosomes; i++) {
			sorted[i] = Chromosomes[(int)temp[i][1]];
			sorted2[i] = (Chromosome) sorted[i].clone();
		}
		Chromosomes = sorted; //the population is sorted
		//creating the selected pop with N/2 best chromosomes and their clones (N even number)
		if(NumberOfChromosomes%2 == 0) {
			for(int i = 0; i < NumberOfChromosomes/2; i++) {
				SelectedPop[i] = sorted[i];
				SelectedPop[i+NumberOfChromosomes/2] = sorted2[i];
			}
		}
		//same (N odd number)
		else {
			for(int i = 0; i < (NumberOfChromosomes+1)/2; i++) {
				SelectedPop[i] = sorted[i];
			}
			for(int i = 0; i < (NumberOfChromosomes-1)/2; i++) {
				SelectedPop[i+(NumberOfChromosomes+1)/2] = sorted2[i];
			}
		}
	}	
	
	//method that performs the crossover with a probability P to each cloned chromosome 
	public void Crossover(double P) {
		Random random = new Random();
		//N is even
		if(NumberOfChromosomes%2 == 0) {
			//indexP1 is the index of the first parent and indexP2, of the second parent
			for(int indexP1 = 0 ; indexP1 < NumberOfChromosomes/2; indexP1++) {
				int indexP2 = NumberOfChromosomes/2 + random.nextInt(NumberOfChromosomes/2);
				//generating a random number between 0 and 1 to perform the probability test of the crossover
				double Ps = Math.random();
				if(Ps <= P) {
					int GeneCrossed = (int) Math.round(3.49*Math.random());
					int tempCode = SelectedPop[indexP2].getCode(GeneCrossed);
					Chromosome temp1 = SelectedPop[indexP1+NumberOfChromosomes/2];
					Chromosome temp2 = SelectedPop[indexP2];
					temp2.getCodes()[GeneCrossed] = temp1.getCodes()[GeneCrossed];
					temp1.getCodes()[GeneCrossed] = tempCode;
					SelectedPop[indexP1+NumberOfChromosomes/2] = temp1;
					SelectedPop[indexP2] = temp2;
				}
			}
		}
		//same with N odd
		else {
			for(int indexP1 = 0; indexP1 < (NumberOfChromosomes+1)/2; indexP1++) {
				int indexP2 = (NumberOfChromosomes+1)/2 + random.nextInt((NumberOfChromosomes-1)/2);
				double Ps = Math.random();
				if(Ps <= P) {
					int GeneCrossed = (int) Math.round(3.49*Math.random());
					int tempCode = SelectedPop[indexP2].getCode(GeneCrossed);
					Chromosome temp1 = SelectedPop[indexP1+NumberOfChromosomes/2];
					Chromosome temp2 = SelectedPop[indexP2];
					temp2.getCodes()[GeneCrossed] = temp1.getCodes()[GeneCrossed];
					temp1.getCodes()[GeneCrossed] = tempCode;
				}
			}
		}
	}
	
	//method that performs a random mutation with Probability Pmu to each cloned chromosome
	public void Mutation(double Pmu) {
		// N even
		if(NumberOfChromosomes%2 == 0) {
			for(int i = 0; i < NumberOfChromosomes/2; i++) {
				double P = Math.random();
				if(P <= Pmu) {
					//Selecting randomly which gene to mutate
					int GeneMuted_pos = (int) Math.round(3.49*Math.random());
					//random variable that will choose which new section between the family selected randomly
					Random random = new Random();
					//random variable between 0 and 1 that choose which is the new family of the section
					double Aleatoire = Math.random();
					if(Aleatoire < 0.16) {
						this.getSelectedPop()[i+NumberOfChromosomes/2].getCodes()[GeneMuted_pos] = 100+random.nextInt(18);
					}
					else if(Aleatoire >= 0.16 && Aleatoire < 0.33){
						this.getSelectedPop()[i+NumberOfChromosomes/2].getCodes()[GeneMuted_pos] = 200+random.nextInt(19);
					}
					else if(Aleatoire >= 0.33 && Aleatoire < 0.50){
						this.getSelectedPop()[i+NumberOfChromosomes/2].getCodes()[GeneMuted_pos] = 400+random.nextInt(19);
					}
					else if(Aleatoire >= 0.50 && Aleatoire < 0.66){
						this.getSelectedPop()[i+NumberOfChromosomes/2].getCodes()[GeneMuted_pos] = 500+random.nextInt(19);
					}
					else if(Aleatoire >= 0.66 && Aleatoire < 0.83){
						this.getSelectedPop()[i+NumberOfChromosomes/2].getCodes()[GeneMuted_pos] = 600+random.nextInt(19);
					}
					else {
						this.getSelectedPop()[i+NumberOfChromosomes/2].getCodes()[GeneMuted_pos] = 300+random.nextInt(21);
					}
				}
			}
		}
		//same with N odd
		else {
			for(int i = 0; i < (NumberOfChromosomes-1)/2; i++) {
				double P = Math.random();
				if(P <= Pmu) {
					int GeneMuted_pos = (int) Math.round(3.49*Math.random());
					Random random = new Random();
					double Aleatoire = Math.random();
					if(Aleatoire < 0.16) {
						this.getSelectedPop()[i+NumberOfChromosomes/2].getCodes()[GeneMuted_pos] = 100+random.nextInt(18);
					}
					else if(Aleatoire >= 0.16 && Aleatoire < 0.33){
						this.getSelectedPop()[i+NumberOfChromosomes/2].getCodes()[GeneMuted_pos] = 200+random.nextInt(19);
					}
					else if(Aleatoire >= 0.33 && Aleatoire < 0.50){
						this.getSelectedPop()[i+NumberOfChromosomes/2].getCodes()[GeneMuted_pos] = 400+random.nextInt(19);
					}
					else if(Aleatoire >= 0.50 && Aleatoire < 0.66){
						this.getSelectedPop()[i+NumberOfChromosomes/2].getCodes()[GeneMuted_pos] = 500+random.nextInt(19);
					}
					else if(Aleatoire >= 0.66 && Aleatoire < 0.83){
						this.getSelectedPop()[i+NumberOfChromosomes/2].getCodes()[GeneMuted_pos] = 600+random.nextInt(19);
					}
					else {
						this.getSelectedPop()[i+NumberOfChromosomes/2].getCodes()[GeneMuted_pos] = 300+random.nextInt(21);
					}
				}
			}
		}
	}
	
	//Method that erase the previous generation and count the iteration
	public void NextGeneration() {
		for(int i = 0; i < NumberOfChromosomes; i++) {
			Chromosomes[i] = (Chromosome) SelectedPop[i].clone();
		}
		Ngen++;
	}
	
	//method that gets the worst chromosome
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
	//method that gets the best chromosome
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
	//all getters and setters
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
	public static void clearTheIteration() {
		Ngen = 1;
	}
}
