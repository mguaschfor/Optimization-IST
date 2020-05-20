package Solve;
import java.util.ArrayList;

//this class allows to create the sections of the beams
public class Section{
	
	//fields
	private String name;//name of the section
	private double G;//linear weight of the section
	private double A;//area of the section
	private double Iz;//inertia moment 
	private double Wel;//elastic bending modulus
	private double Wpl;//plastic bending modulus
	private int classe;//class of the section
	private double tf;//geometric characteristic 
	private double b;//geometric characteristic
	private static int code;//code associated with the section (allows to decode)
	private static ArrayList<ArrayList<Object>> memory = new ArrayList<>();//a 2D tab that will stock all the characteristics of the sections. Essential to decode
	private static ArrayList<Object> temp;//a 1D tab that contains the characteristic of one section. Each of these tabs are added to memory
	private static Integer number = 0;// Iterator that count how many sections are created
	
	//first constructor. Allows to keep in memory all the sections instantiated
	public Section(String name, double G, double A, double Iz, double Wel, double Wpl, int classe, double tf, double b, int code, boolean countOn){
		Section.code = code;
		this.name = name;
		this.G = G;
		this.A = A;
		this.Iz = Iz;
		this.Wel = Wel;
		this.Wpl = Wpl;
		this.classe = classe;
		this.tf = tf;
		this.b = b;
		
		if(countOn = true) {
			Integer codeX = code;
			Double GX = G;
			Double AX = A;
			Double IzX = Iz;
			Double WelX = Wel;
			Double WplX = Wpl;
			Integer classeX = classe;
			Double tfX = tf;
			Double bX = b;
			temp = new ArrayList<Object>();
			temp.add(codeX);
			temp.add(name);
			temp.add(GX);
			temp.add(AX);
			temp.add(IzX);
			temp.add(WelX);
			temp.add(WplX);
			temp.add(classeX);
			temp.add(tfX);
			temp.add(bX);
			memory.add(temp);
			number++;
		}
	}
	
	//second constructor. it does not keep in memory all the sections instantiated
	public Section(String name, double G, double A, double Iz, double Wel, double Wpl, int classe, double tf, double b, int code){
		Section.code = code;
		this.name = name;
		this.G = G;
		this.A = A;
		this.Iz = Iz;
		this.Wel = Wel;
		this.Wpl = Wpl;
		this.classe = classe;
		this.tf = tf;
		this.b = b;
	}
	
	//method that allows to access the corresponding section to the code (gene) from the memory tab. 
	public static Section decode(int code) {
		int i = 0;
		Section test = new Section();
		while(i < number) {
			if((int)memory.get(i).get(0) == code){
			test = new Section((String)memory.get(i).get(1),(double)memory.get(i).get(2),(double)memory.get(i).get(3),(double)memory.get(i).get(4),(double)memory.get(i).get(5),(double)memory.get(i).get(6),(int)memory.get(i).get(7),(double)memory.get(i).get(8),(double)memory.get(i).get(9),(int)memory.get(i).get(0));
			}
			i++;
		}
		return test;
	}
	
	//all getters and setters here
	public Section() {
		super();
	}
	@Override
	public String toString() {
		return name;
	}
	public double getG() {
		return G;
	}
	public double getA() {
		return A;
	}
	public double getIz() {
		return Iz;
	}
	public double getWel() {
		return Wel;
	}
	public double getWpl() {
		return Wpl;
	}
	public int getClasse() {
		return classe;
	}
	public double getTf() {
		return tf;
	}
	public double getB() {
		return b;
	}
	public static int getCode() {
		return code;
	}
}
