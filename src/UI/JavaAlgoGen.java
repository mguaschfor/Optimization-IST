package UI;
import Solve.*;
import javax.swing.*;
import java.awt.*;

//This class is the main window of the software (the first one that is open after execution)
public class JavaAlgoGen extends JFrame {

	//All the widgets of the geometry's section 
	//Labels are text indications
	private JLabel geoGeometry;
	private JLabel geoAlpha;
	private JLabel geoSection1;
	private JLabel geoLength1;
	private JLabel geoSection2;
	private JLabel geoLength2;
	private JLabel geoSection3;
	private JLabel geoLength3;
	private JLabel geoSection4;
	private JLabel geoLength4;
	private JLabel beam1;
	private JLabel beam2;
	private JLabel beam3;
	private JLabel beam4;
	
	//TextFields are fields where you can write
	private JTextField geoFieldAlpha;
	private JTextField geoFieldLength1; 
	private JTextField geoFieldLength2; 
	private JTextField geoFieldLength3; 
	private JTextField geoFieldLength4; 
	
	//All the widgets of the Loads's section
	private JLabel loLoads;
	private JLabel loq1;
	private JLabel loq2;
	private JLabel loq3;
	private JTextField loFieldq1;
	private JTextField loFieldq2;
	private JTextField loFieldq3;
	
	//All the widgets of the precision's section. this section is implemented in order to set the value of dx
	private JLabel prePrecision;
	private JLabel predx;
	private JTextField preFielddx;
	
	//All the widgets of the Results section 
	private JLabel reResults;
	private JLabel reN1;
	private JLabel reT1;
	private JLabel reM1;
	private JLabel reN2;
	private JLabel reT2;
	private JLabel reM2;
	private JLabel reN3;
	private JLabel reT3;
	private JLabel reM3;
	private JLabel reN4;
	private JLabel reT4;
	private JLabel reM4;
	private JLabel reBeam1;
	private JLabel reBeam2;
	private JLabel reBeam3;
	private JLabel reBeam4;
	private JLabel reDeltaX1;
	private JLabel reDeltaX2;
	private JLabel reDeltaX3;
	private JLabel reDeltaY1;
	private JLabel reDeltaY2;
	private JLabel reDeltaY3;
	private JLabel reCritera1;
	private JLabel reCritera2;
	private JLabel reCritera3;
	private JLabel reCritera4;
	private JTextArea reTextN1;
	private JTextArea reTextT1;
	private JTextArea reTextM1;
	private JTextArea reTextN2;
	private JTextArea reTextT2;
	private JTextArea reTextM2;
	private JTextArea reTextN3;
	private JTextArea reTextT3;
	private JTextArea reTextM3;
	private JTextArea reTextN4;
	private JTextArea reTextT4;
	private JTextArea reTextM4;
	private JTextArea reTextDeltaX1;
	private JTextArea reTextDeltaX2;
	private JTextArea reTextDeltaX3;
	private JTextArea reTextDeltaY1;
	private JTextArea reTextDeltaY2;
	private JTextArea reTextDeltaY3;
	private JTextArea reTextCritera1;
	private JTextArea reTextCritera2;
	private JTextArea reTextCritera3;
	private JTextArea reTextCritera4;
	
	//The list of sections
	private JComboBox list1;
	private JComboBox list2;
	private JComboBox list3;
	private JComboBox list4;
	
	//The two buttons that will allow to calculate or to optimize the structure
	private JButton btnCalculate;
	private JButton btnOptimize; 
	
	//The two check-boxes that allow to choose which optimization algorithm to perform
	private JCheckBox checkGa;
	private JCheckBox checkSa;
	
	//The sub-areas where the widgets will be placed, fieldset
	private JPanel geoArea;
	private JPanel alphaArea;
	private JPanel beam1Area;
	private JPanel beam2Area;
	private JPanel beam3Area;
	private JPanel beam4Area; 
	private JPanel loadsTitleArea;
	private JPanel loadsArea;
	private JPanel precisionTitleArea;
	private JPanel precisionArea;
	
	//The sub-areas where the widgets will be placed, fieldresult
	private JPanel resultsArea;
	private JPanel result1;
	private JPanel result2;
	private JPanel result3;
	private JPanel result4;
	private JPanel deltaXArea;
	private JPanel deltaYArea;
	private JPanel criteraArea12;
	private JPanel criteraArea34;
	private JPanel btnfield;
	
	//The field-areas where the sub-areas will be placed 
	private JPanel fieldset;
	private JPanel fieldresult;
	
	private JPanel southMainContainer;
	
	//The main container
	private JPanel mainContainer;
	
	//Constructor of the window. We are setting everything here and it is meant to create the main window
	public JavaAlgoGen() {
		super("Optimization Project");
		setSize(1150,900);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setLocationRelativeTo(null);
	    //We put in memory all the sections possible for the 4 beams. This step is important in order to decode the genes and access the mechanical characteristics of the sections
    	//This is the benchmark database
        Section IPE80 = new Section("IPE80",6.0,7.64e-4,80.14e-8,20.03e-6,23.22e-6,1,5.2e-3,46e-3,100,true);
        Section IPE100 = new Section("IPE100",8.1,10.3e-4,171e-8,34.20e-6,39.41e-6,1,5.7e-3,55e-3,101,true);
        Section IPE120 = new Section("IPE120",10.4,13.2e-4,317.8e-8,52.96e-6,60.73e-6,1,6.3e-3,64e-3,102,true);
		Section IPE140 = new Section("IPE140",12.9,16.4e-4,541.2e-8,77.32e-6,88.34e-6,1,6.9e-3,73e-3,103,true);
		Section IPE160 = new Section("IPE160",15.8,20.1e-4,869.3e-8,108.7e-6,123.9e-6,1,7.4e-3,82e-3,104,true);
		Section IPE180 = new Section("IPE180",18.8,23.9e-4,1317e-8,143.6e-6,166.4e-6,1,8e-3,91e-3,105,true);
        Section IPE200 = new Section("IPE200",22.4,28.5e-4,1943e-8,194.3e-6,220.6e-6,1,8.5e-3,100e-3,106,true);
        Section IPE220 = new Section("IPE220",26.2,33.4e-4,2772e-8,252e-6,285.4e-6,1,9.2e-3,110e-3,107,true);
	    Section IPE240 = new Section("IPE240",30.7,39.1e-4,3892e-8,324.3e-6,366.6e-6,2,9.8e-3,120-3,108,true);
	    Section IPE270 = new Section("IPE270",36.1,45.9e-4,5790e-8,428.9e-6,484e-6,2,10.2e-3,135e-3,109,true);
        Section IPE300 = new Section("IPE300",42.2,53.8e-4,8356e-8,557.1e-6,628.4e-6,2,10.7e-3,150e-3,110,true);
        Section IPE330 = new Section("IPE330",49.1,62.6e-4,11770e-8,713.1e-6,804.3e-6,3,11.5e-3,160e-3,111,true);
	    Section IPE360 = new Section("IPE360",57.1,72.7e-4,16270e-8,903.6e-6,1019e-6,3,12.7e-3,170e-3,112,true);
	    Section IPE400 = new Section("IPE400",66.3,84.5e-4,23130e-8,1156e-6,1307e-6,3,13.5e-3,180e-3,113,true);
	    Section IPE450 = new Section("IPE450",77.6,98.8e-4,33740e-8,1500e-6,1702e-6,3,14.6e-3,190e-3,114,true);
	    Section IPE500 = new Section("IPE500",91.8,116e-4,48200e-8,1928e-6,2194e-6,3,16e-3,200e-3,115,true);
	    Section IPE550 = new Section("IPE550",106,134e-4,67120e-8,2441e-6,2787e-6,3,17.2e-3,210e-3,116,true);
	    Section IPE600 = new Section("IPE600",122,156e-4,92080e-8,3069e-6,3512e-6,3,19e-3,220e-3,117,true);
	    Section HEA100 = new Section("HEA100",16.7,21.2e-4,349.2e-8,72.76e-6,83.01e-6,1,8e-3,100e-3,200,true);
	    Section HEA120 = new Section("HEA120",19.9,25.3e-4,606.2e-8,106.3e-6,199.5e-6,1,8e-3,120e-3,201,true);
	    Section HEA140 = new Section("HEA140",24.7,31.4e-4,1033e-8,155.4e-6,173.5e-6,1,8.5e-3,140e-3,202,true);
	    Section HEA160 = new Section("HEA160",30.4,38.8e-4,1673e-8,220.1e-6,245.1e-6,1,9e-3,160e-3,203,true);
	    Section HEA180 = new Section("HEA180",35.5,45.3e-4,2510e-8,293.6e-6,324.9e-6,1,9.5e-3,180e-3,204,true);
	    Section HEA200 = new Section("HEA200",42.3,53.8e-4,3692e-8,388.6e-6,429.5e-6,1,10e-3,200e-3,205,true);
	    Section HEA220 = new Section("HEA220",50.5,64.3e-4,5410e-8,515.2e-6,568.5e-6,1,11e-3,220e-3,206,true);
	    Section HEA240 = new Section("HEA240",60.3,76.8e-4,7763e-8,675.1e-6,744.6e-6,1,12e-3,240e-3,207,true);
	    Section HEA260 = new Section("HEA260",68.2,86.8e-4,10450e-8,836.4e-6,919.8e-6,2,12.5e-3,260e-3,208,true);
	    Section HEA280 = new Section("HEA280",76.4,97.3e-4,13670e-8,1013e-6,1112e-6,2,13e-3,280e-3,209,true);
	    Section HEA300 = new Section("HEA300",88.3,112.5e-4,18260e-8,1260e-6,1383e-6,2,14e-3,300e-3,210,true);
	    Section HEA320 = new Section("HEA320",97.6,124.4e-4,22930e-8,1479e-6,1628e-6,1,15.5e-3,300e-3,211,true);
	    Section HEA340 = new Section("HEA340",105,133.5e-4,27690e-8,1678e-6,1850e-6,1,16.5e-3,300e-3,212,true);
	    Section HEA360 = new Section("HEA360",112,142.8e-4,33090e-8,1891e-6,2088e-6,1,17.5e-3,300e-3,213,true);
	    Section HEA400 = new Section("HEA400",125,159e-4,45070e-8,2311e-6,2562e-6,1,19e-3,300e-3,214,true);
	    Section HEA450 = new Section("HEA450",140,178e-4,63720e-8,2896e-6,3216e-6,1,21e-3,300e-3,215,true);
	    Section HEA500 = new Section("HEA500",155,197.5e-4,86970e-8,3550e-6,3949e-6,1,23e-3,300e-3,216,true);
	    Section HEA550 = new Section("HEA550",166,211.8e-4,111900e-8,4146e-6,4622e-6,1,24e-3,300e-3,217,true);
	    Section HEA600 = new Section("HEA600",178,266.5e-4,141200e-8,4787e-6,5350e-6,1,27e-3,300e-3,218,true);
	    Section IPN80 = new Section("IPN80",5.94,7.57e-4,77.8e-8,19.5e-6,22.8e-6,1,5.9e-3,42e-3,300,true);
	    Section IPN100 = new Section("IPN100",8.34,10.6e-4,171e-8,34.2e-6,39.8e-6,1,6.8e-3,50e-3,301,true);
	    Section IPN120 = new Section("IPN120",11.1,14.2e-4,328e-8,54.7e-6,63.6e-6,1,7.7e-3,58e-3,302,true);
	    Section IPN140 = new Section("IPN140",14.3,18.2e-4,573e-8,81.9e-6,95.4e-6,1,8.6e-3,66e-3,303,true);
	    Section IPN160 = new Section("IPN160",17.9,22.8e-4,935e-8,117e-6,136e-6,1,9.5e-3,74e-3,304,true);
	    Section IPN180 = new Section("IPN180",21.9,27.9e-4,1450e-8,161e-6,187e-6,1,10.4e-3,82e-3,305,true);
	    Section IPN200 = new Section("IPN200",26.2,33.4e-4,2140e-8,214e-6,250e-6,1,11.3e-3,90e-3,306,true);
	    Section IPN220 = new Section("IPN220",31.1,39.5e-4,3060e-8,278e-6,324e-6,1,12.2e-3,98e-3,307,true);
	    Section IPN240 = new Section("IPN240",36.2,46.1e-4,4250e-8,354e-6,412e-6,1,13.1e-3,106e-3,308,true);
	    Section IPN260 = new Section("IPN260",41.9,53.3e-4,5740e-8,442e-6,514e-6,1,14.1e-3,113e-3,309,true);
	    Section IPN280 = new Section("IPN280",47.9,61e-4,7590e-8,542e-6,632e-6,1,15.2e-3,119e-3,310,true);
	    Section IPN300 = new Section("IPN300",54.2,69e-4,9800e-8,653e-6,762e-6,1,16.2e-3,125e-3,311,true);
	    Section IPN320 = new Section("IPN320",61,77.7e-4,12510e-8,782e-6,914e-6,1,17.3e-3,131e-3,312,true);
	    Section IPN340 = new Section("IPN340",68,86.7e-4,15700e-8,923e-6,1080e-6,1,18.3e-3,137e-3,313,true);
	    Section IPN360 = new Section("IPN360",76.1,97e-4,19610e-8,1090e-6,1276e-6,1,19.5e-3,143e-3,314,true);
	    Section IPN380 = new Section("IPN380",84,107e-4,24010e-8,1260e-6,1482e-6,1,20.5e-3,149e-3,315,true);
	    Section IPN400 = new Section("IPN400",92.4,118e-4,29210e-8,1460e-6,1714e-6,1,21.6e-3,155e-3,316,true);
	    Section IPN450 = new Section("IPN450",115,147e-4,45850e-8,2040e-6,2400e-6,1,24.3e-3,170e-3,317,true);
	    Section IPN500 = new Section("IPN500",141,179e-4,68740e-8,2750e-6,3240e-6,1,27e-3,185e-3,318,true);
	    Section IPN550 = new Section("IPN550",166,212e-4,99180e-8,3610e-6,4240e-6,1,30e-3,200e-3,319,true);
	    Section IPN600 = new Section("IPN600",199,254e-4,139000e-8,4630e-6,5452e-6,1,32.4e-3,215e-3,320,true);
	    Section HEB100 = new Section("HEB100",20.4,26.0e-4,450e-8,90e-6,104.2e-6,1,10e-3,100e-3,400,true);
	    Section HEB120 = new Section("HEB120",26.7,34.0e-4,864e-8,144e-6,165.2e-6,1,11e-3,120e-3,401,true);
	    Section HEB140 = new Section("HEB140",33.7,43.0,1509e-8,216e-6,245.4e-6,1,12e-3,140e-3,402,true);
	    Section HEB160 = new Section("HEB160",42.6,54.3e-4,2492e-8,312e-6,354e-6,1,13e-3,160e-3,403,true);
	    Section HEB180 = new Section("HEB180",51.2,65.3e-4,3831e-8,426e-6,481.4e-6,1,14e-3,180e-3,404,true);
	    Section HEB200 = new Section("HEB200",61.3,78.1e-4,5696e-8,570e-6,642.5e-6,1,15e-3,200e-3,405,true);
	    Section HEB220 = new Section("HEB220",71.5,91.0e-4,8091e-8,736e-6,827.0e-6,1,16e-3,220e-3,406,true);
	    Section HEB240 = new Section("HEB240",83.2,106.0e-4,11260e-8,938e-6,1053e-6,1,17e-3,240e-3,407,true);
	    Section HEB260 = new Section("HEB260",93.3,118.4e-4,14920e-8,1148e-6,1283e-6,1,17.5e-3,260e-3,408,true);
	    Section HEB280 = new Section("HEB280",103,131.4e-4,19270e-8,1376e-6,1534e-6,1,18e-3,280e-3,409,true);
	    Section HEB300 = new Section("HEB300",117,149.1e-4,25170e-8,1678e-6,1869e-6,1,19e-3,300e-3,410,true);
	    Section HEB320 = new Section("HEB320",127,161.3e-4,30820e-8,1926e-6,2149e-6,1,20.5e-3,300e-3,411,true);
	    Section HEB340 = new Section("HEB340",134,170.9e-4,36660e-8,2156e-6,2408e-6,1,21.5e-3,300e-3,412,true);
	    Section HEB360 = new Section("HEB360",142,180.6e-4,43190e-8,2400e-6,2683e-6,1,22.5e-3,300e-3,413,true);
	    Section HEB400 = new Section("HEB400",155,197.8e-4,57680e-8,2884e-6,3232e-6,1,24e-3,300e-3,414,true);
	    Section HEB450 = new Section("HEB450",171,218.0e-4,79890e-8,3551e-6,3982e-6,1,26e-3,300e-3,415,true);
	    Section HEB500 = new Section("HEB500",187,238.6e-4,107200e-8,4287e-6,4815e-6,1,28e-3,300e-3,416,true);
	    Section HEB550 = new Section("HEB550",199,254.1e-4,136700e-8,4971e-6,5591e-6,1,29e-3,300e-3,417,true);
	    Section HEB600 = new Section("HEB600",212,270.0e-4,171000e-8,5701e-6,6425e-6,1,30e-3,300e-3,418,true);
	    Section HEAA100 = new Section("HEAA100",12.2,15.6e-4,237e-8,52e-6,58.36e-6,1,5.5e-3,100e-3,500,true);
	    Section HEAA120 = new Section("HEAA120",14.6,18.6e-4,413e-8,76e-6,84.12e-6,1,5.5e-3,120e-3,501,true);
	    Section HEAA140 = new Section("HEAA140",18.1,23.0e-4,720e-8,112e-6,123.8e-6,1,6e-3,140e-3,502,true);
	    Section HEAA160 = new Section("HEAA160",23.8,30.4e-4,1283e-8,173e-6,190.4e-6,1,7e-3,160e-3,503,true);
	    Section HEAA180 = new Section("HEAA180",28.7,36.5e-4,1967e-8,236e-6,258.2e-6,1,7.5e-3,180e-3,504,true);
	    Section HEAA200 = new Section("HEAA200",34.6,44.1e-4,2944e-8,317e-6,347.1e-6,1,8e-3,200e-3,505,true);
	    Section HEAA220 = new Section("HEAA220",40.4,51.5e-4,4170e-8,407e-6,445.5e-6,1,8.5e-3,220e-3,506,true);
	    Section HEAA240 = new Section("HEAA240",47.4,60.4e-4,5835e-8,521e-6,570.6e-6,1,9e-3,240e-3,507,true);
	    Section HEAA260 = new Section("HEAA260",54.1,69.0e-4,7981e-8,654e-6,714.5e-6,1,9.5e-3,260e-3,508,true);
	    Section HEAA280 = new Section("HEAA280",61.2,78.0e-4,10560e-8,800e-6,873e-6,1,10e-3,280e-3,509,true);
	    Section HEAA300 = new Section("HEAA300",69.8,88.9e-4,13800e-8,976e-6,1065e-6,1,10.5e-3,300e-3,510,true);
	    Section HEAA320 = new Section("HEAA320",74.2,94.6e-4,16450e-8,1093e-6,1196e-6,1,11e-3,300e-3,511,true);
	    Section HEAA340 = new Section("HEAA340",78.9,100.5e-4,19550e-8,1222e-6,1341e-6,1,11.5e-3,300e-3,512,true);
	    Section HEAA360 = new Section("HEAA360",83.7,106.6e-4,23040e-8,1359e-6,1495e-6,1,12e-3,300e-3,513,true);
	    Section HEAA400 = new Section("HEAA400",92.4,117.7e-4,31250e-8,1654e-6,1824e-6,1,13e-3,300e-3,514,true);
	    Section HEAA450 = new Section("HEAA450",99.7,127.1e-4,41890e-8,1971e-6,2183e-6,1,13.5e-3,300e-3,515,true);
	    Section HEAA500 = new Section("HEAA500",107,136.9e-4,54640e-8,2315e-6,2576e-6,1,14e-3,300e-3,516,true);
	    Section HEAA550 = new Section("HEAA550",120,152.8e-4,72870e-8,2792e-6,3128e-6,1,15e-3,300e-3,517,true);
	    Section HEAA600 = new Section("HEAA600",129,164.1e-4,91900e-8,3218e-6,3623e-6,1,15.5e-3,300e-3,518,true);
	    Section HEM100 = new Section("HEM100",41.8,53.2e-4,1143e-8,190e-6,235.8e-6,1,20e-3,106e-3,600,true);
	    Section HEM120 = new Section("HEM120",52.1,66.4e-4,2018e-8,288e-6,350.6e-6,1,21e-3,126e-3,601,true);
	    Section HEM140 = new Section("HEM140",63.2,80.6e-4,3291e-8,411e-6,493.8e-6,1,22e-3,146e-3,602,true);
	    Section HEM160 = new Section("HEM160",76.2,97.1e-4,5098e-8,567e-6,674.6e-6,1,23e-3,166e-3,603,true);
	    Section HEM180 = new Section("HEM180",88.9,113.3e-4,7483e-8,748e-6,883.4e-6,1,24e-3,186e-3,604,true);
	    Section HEM200 = new Section("HEM200",103,131.3e-4,10640e-8,967e-6,1135e-6,1,25e-3,206e-3,605,true);
	    Section HEM220 = new Section("HEM220",117,149.4e-4,14600e-8,1217e-6,1419e-6,1,26e-3,226e-3,606,true);
	    Section HEM240 = new Section("HEM240",157,199.6e-4,24290e-8,1799e-6,2117e-6,1,32e-3,248e-3,607,true);
	    Section HEM260 = new Section("HEM260",172,219.6e-4,31310e-8,2159e-6,2524e-6,1,32.5e-3,268e-3,608,true);
	    Section HEM280 = new Section("HEM280",189,240.2e-4,39550e-8,2551e-6,2966e-6,1,33e-3,288e-3,609,true);
	    Section HEM300 = new Section("HEM300",238,303.1e-4,59200e-8,3482e-6,4078e-6,1,39e-3,310e-3,610,true);
	    Section HEM320 = new Section("HEM320",245,312.0e-4,68130e-8,3796e-6,4435e-6,1,40e-3,309e-3,611,true);
	    Section HEM340 = new Section("HEM340",248,315.8e-4,76370e-8,4052e-6,4718e-6,1,40e-3,309e-3,612,true);
	    Section HEM360 = new Section("HEM360",250,318.8e-4,84870e-8,4297e-6,4989e-6,1,40e-3,308e-3,613,true);
	    Section HEM400 = new Section("HEM400",256,325.8e-4,104100e-8,4820e-6,5571e-6,1,40e-3,307e-3,614,true);
	    Section HEM450 = new Section("HEM450",263,335.4e-4,131500e-8,5501e-6,6331e-6,1,40e-3,307e-3,615,true);
	    Section HEM500 = new Section("HEM500",270,344.3e-4,161900e-8,6180e-6,7094e-6,1,40e-3,306e-3,616,true);
	    Section HEM550 = new Section("HEM550",278,354.4e-4,198000e-8,6923e-6,7933e-6,1,40e-3,306e-6,617,true);
	    Section HEM600 = new Section("HEM600",285,363.7e-4,237400e-8,7660e-6,8772e-6,1,40e-3,305e-3,618,true);
	    // we put everything in a tab in order to create the scrolling widgets
	    Object [] elements = new Object[] {IPE80, IPE100, IPE120, IPE140, IPE160, IPE180, IPE200, IPE220, IPE240, IPE270, IPE300, IPE330, IPE360, IPE400, IPE450, IPE500, IPE550, IPE600, HEA100, HEA120, HEA140, HEA160, HEA180, HEA200, HEA220, HEA240, HEA260, HEA280, HEA300, HEA320, HEA340, HEA360, HEA400, HEA450, HEA500, HEA550, HEA600,IPN80 ,IPN100 , IPN120, IPN140, IPN160, IPN180, IPN200, IPN220, IPN240, IPN260, IPN280, IPN300, IPN320, IPN340, IPN360, IPN380, IPN400, IPN450, IPN500, IPN550, IPN600, HEB100, HEB120, HEB140, HEB160, HEB180, HEB200, HEB220, HEB240, HEB260, HEB280, HEB300, HEB320, HEB340, HEB360, HEB400, HEB450, HEB500, HEB550, HEB600,HEAA100, HEAA120, HEAA140, HEAA160, HEAA180, HEAA200, HEAA220, HEAA240, HEAA260, HEAA280, HEAA300, HEAA320, HEAA340, HEAA360, HEAA400, HEAA450, HEAA500, HEAA550, HEAA600,HEM100, HEM120, HEM140, HEM160, HEM180, HEM200, HEM220, HEM240, HEM260, HEM280, HEM300, HEM320, HEM340, HEM360, HEM400, HEM450, HEM500, HEM550, HEM600};
        list1 = new JComboBox(elements);
        list2 = new JComboBox(elements);
        list3 = new JComboBox(elements);
        list4 = new JComboBox(elements);
        
        //All the widgets are initialized here except the areas
        geoGeometry = new JLabel("Geometry:");
	    geoAlpha = new JLabel("Alpha: ");
	    beam1 = new JLabel("Beam 1 --> ");
	    beam2 = new JLabel("Beam 2 --> ");
	    beam3 = new JLabel("Beam 3 --> ");
	    beam4 = new JLabel("Beam 4 --> ");
	    geoSection1 = new JLabel("Section:");
	    geoLength1 = new JLabel("Length (m):");
	    geoSection2 = new JLabel("Section:");
	    geoLength2 = new JLabel("Length (m):");
	    geoSection3 = new JLabel("Section:");
	    geoLength3 = new JLabel("Length (m):");
	    geoSection4 = new JLabel("Section:");
	    geoLength4 = new JLabel("Length (m):");
	    geoFieldAlpha = new JTextField(4);
	    geoFieldLength1 = new JTextField(4);
	    geoFieldLength2 = new JTextField(4);
	    geoFieldLength3 = new JTextField(4);
	    geoFieldLength4 = new JTextField(4);
	    
	    loLoads = new JLabel("Loads (kN/m) :");
	    loq1 = new JLabel("q1:");
	    loq2 = new JLabel("q2:");
	    loq3 = new JLabel("q3:");
	    loFieldq1 = new JTextField(5);
	    loFieldq2 = new JTextField(5);
	    loFieldq3 = new JTextField(5);
	    
	    prePrecision = new JLabel("Precision:");
	    predx = new JLabel("N:");
	    preFielddx = new JTextField(4);
	    
		reResults = new JLabel("Results:");
		reN1 = new JLabel("N1max:");
		reT1 = new JLabel("T1max:");
		reM1 = new JLabel("M1max:");
		reN2 = new JLabel("N2max:");
		reT2 = new JLabel("T2max:");
		reM2 = new JLabel("M2max:");
		reN3 = new JLabel("N3max:");
		reT3 = new JLabel("T3max:");
		reM3 = new JLabel("M3max:");
		reN4 = new JLabel("N4max:");
		reT4 = new JLabel("T4max:");
		reM4 = new JLabel("M4max:");
		reBeam1 = new JLabel("beam 1--> ");
		reBeam2 = new JLabel("beam 2--> ");
		reBeam3 = new JLabel("beam 3--> ");
		reBeam4 = new JLabel("beam 4--> ");
		reDeltaX1 = new JLabel(" u1:");
		reDeltaX2 = new JLabel(" u2:");
		reDeltaX3 = new JLabel(" u3:");
		reDeltaY1 = new JLabel(" v1:");
		reDeltaY2 = new JLabel(" v2:");
		reDeltaY3 = new JLabel(" v3:");
	    reCritera1 = new JLabel("Criterion 1:");
		reCritera2 = new JLabel("Criterion 2:");
		reCritera3 = new JLabel("Criterion 3:");
		reCritera4 = new JLabel("Criterion 4:");
		reTextN1 = new JTextArea(5,5);
		reTextT1 = new JTextArea(5,5);
		reTextM1 = new JTextArea(5,5);
		reTextN2 = new JTextArea(5,5);
		reTextT2 = new JTextArea(5,5);
		reTextM2 = new JTextArea(5,5);
		reTextN3 = new JTextArea(5,5);
		reTextT3 = new JTextArea(5,5);
		reTextM3 = new JTextArea(5,5);
		reTextN4 = new JTextArea(5,5);
		reTextT4 = new JTextArea(5,5);
		reTextM4 = new JTextArea(5,5);
		reTextDeltaX1 = new JTextArea(5,5);
		reTextDeltaX2 = new JTextArea(5,5);
		reTextDeltaX3 = new JTextArea(5,5);
		reTextDeltaY1 = new JTextArea(5,5);
		reTextDeltaY2 = new JTextArea(5,5);
		reTextDeltaY3 = new JTextArea(5,5);
		reTextCritera1 = new JTextArea(5,5);
		reTextCritera2 = new JTextArea(5,5);
		reTextCritera3 = new JTextArea(5,5);
		reTextCritera4 = new JTextArea(5,5);
	    
	    btnCalculate = new JButton("Calculate");
	    btnOptimize = new JButton("Optimize:");
	    
	    checkGa = new JCheckBox("Genetic Algorithm");
	    checkSa = new JCheckBox("Simulated Annealing");
	    
	    //Geometry label area 
	    geoArea = new JPanel();
	    geoArea.setLayout(new BoxLayout(geoArea, BoxLayout.X_AXIS));
	    geoArea.add(geoGeometry);
	    geoArea.add(Box.createRigidArea(new Dimension(480,0)));
	    
	    //Alpha settings area
	    geoFieldAlpha.setMaximumSize(new Dimension(60,20));
        alphaArea = new JPanel();
        alphaArea.setLayout(new BoxLayout(alphaArea, BoxLayout.X_AXIS));
        alphaArea.add(geoAlpha);
        alphaArea.add(geoFieldAlpha);
        alphaArea.add(Box.createRigidArea(new Dimension(365,0)));
        
        //First beam settings area
        list1.setMaximumSize(new Dimension(85,20));
        geoFieldLength1.setMaximumSize(new Dimension(60,20));
        beam1Area = new JPanel();
        beam1Area.setLayout(new BoxLayout(beam1Area, BoxLayout.X_AXIS));
        beam1Area.add(beam1);
        beam1Area.add(Box.createRigidArea(new Dimension(10,0)));
        beam1Area.add(geoSection1);
        beam1Area.add(Box.createRigidArea(new Dimension(10,0)));
        beam1Area.add(list1);
        beam1Area.add(Box.createRigidArea(new Dimension(10,0)));
        beam1Area.add(geoLength1);
        beam1Area.add(Box.createRigidArea(new Dimension(10,0)));
        beam1Area.add(geoFieldLength1);
        beam1Area.add(Box.createRigidArea(new Dimension(150,0)));
        
        //Second beam settings area
        list2.setMaximumSize(new Dimension(85,20));
        geoFieldLength2.setMaximumSize(new Dimension(60,20));
        beam2Area = new JPanel();
        beam2Area.setLayout(new BoxLayout(beam2Area, BoxLayout.X_AXIS));
        beam2Area.add(beam2);
        beam2Area.add(Box.createRigidArea(new Dimension(10,0)));
        beam2Area.add(geoSection2);
        beam2Area.add(Box.createRigidArea(new Dimension(10,0)));
        beam2Area.add(list2);
        beam2Area.add(Box.createRigidArea(new Dimension(10,0)));
        beam2Area.add(geoLength2);
        beam2Area.add(Box.createRigidArea(new Dimension(10,0)));
        beam2Area.add(geoFieldLength2);
        beam2Area.add(Box.createRigidArea(new Dimension(150,0)));
        
        //Third beam settings area
        list3.setMaximumSize(new Dimension(85,20));
        geoFieldLength3.setMaximumSize(new Dimension(60,20));
        beam3Area = new JPanel();
        beam3Area.setLayout(new BoxLayout(beam3Area, BoxLayout.X_AXIS));
        beam3Area.add(beam3);
        beam3Area.add(Box.createRigidArea(new Dimension(10,0)));
        beam3Area.add(geoSection3);
        beam3Area.add(Box.createRigidArea(new Dimension(10,0)));
        beam3Area.add(list3);
        beam3Area.add(Box.createRigidArea(new Dimension(10,0)));
        beam3Area.add(geoLength3);
        beam3Area.add(Box.createRigidArea(new Dimension(10,0)));
        beam3Area.add(geoFieldLength3);
        beam3Area.add(Box.createRigidArea(new Dimension(150,0)));
   
        //fourth beam settings area
        list4.setMaximumSize(new Dimension(85,20));
        geoFieldLength4.setMaximumSize(new Dimension(60,20));
        beam4Area = new JPanel();
        beam4Area.setLayout(new BoxLayout(beam4Area, BoxLayout.X_AXIS));
        beam4Area.add(beam4);
        beam4Area.add(Box.createRigidArea(new Dimension(10,0)));
        beam4Area.add(geoSection4);
        beam4Area.add(Box.createRigidArea(new Dimension(10,0)));
        beam4Area.add(list4);
        beam4Area.add(Box.createRigidArea(new Dimension(10,0)));
        beam4Area.add(geoLength4);
        beam4Area.add(Box.createRigidArea(new Dimension(10,0)));
        beam4Area.add(geoFieldLength4);
        beam4Area.add(Box.createRigidArea(new Dimension(150,0)));
      
        //Loads Title settings area
        loadsTitleArea = new JPanel();
        loadsTitleArea.setLayout(new BoxLayout(loadsTitleArea, BoxLayout.X_AXIS));
        loadsTitleArea.add(loLoads);
        loadsTitleArea.add(Box.createRigidArea(new Dimension(460,0)));
        
        //Loads settings area
        loFieldq1.setMaximumSize(new Dimension(60,20));
        loFieldq2.setMaximumSize(new Dimension(60,20));
        loFieldq3.setMaximumSize(new Dimension(60,20));
        loadsArea = new JPanel();
        loadsArea.setLayout(new BoxLayout(loadsArea, BoxLayout.X_AXIS));
        loadsArea.add(loq1);
        loadsArea.add(Box.createRigidArea(new Dimension(10,0)));
        loadsArea.add(loFieldq1);
        loadsArea.add(Box.createRigidArea(new Dimension(10,0)));
        loadsArea.add(loq2);
        loadsArea.add(Box.createRigidArea(new Dimension(10,0)));
        loadsArea.add(loFieldq2);
        loadsArea.add(Box.createRigidArea(new Dimension(10,0)));
        loadsArea.add(loq3);
        loadsArea.add(Box.createRigidArea(new Dimension(10,0)));
        loadsArea.add(loFieldq3);
        loadsArea.add(Box.createRigidArea(new Dimension(160,0)));
        
        //Precision Title settings area
        precisionTitleArea = new JPanel();
        precisionTitleArea.setLayout(new BoxLayout(precisionTitleArea, BoxLayout.X_AXIS));
        precisionTitleArea.add(prePrecision);
        precisionTitleArea.add(Box.createRigidArea(new Dimension(480,0)));
        
        //Precision settings area
        preFielddx.setMaximumSize(new Dimension(60,20));
        precisionArea = new JPanel();
        precisionArea.setLayout(new BoxLayout(precisionArea, BoxLayout.X_AXIS));
        precisionArea.add(predx);
        precisionArea.add(Box.createRigidArea(new Dimension(10,0)));
        precisionArea.add(preFielddx);
        precisionArea.add(Box.createRigidArea(new Dimension(345,0)));
        
        //Results label area
        resultsArea = new JPanel();
	    resultsArea.setLayout(new BoxLayout(resultsArea, BoxLayout.X_AXIS));
	    resultsArea.add(reResults);
	    resultsArea.add(Box.createRigidArea(new Dimension(480,0)));
	    
	    //Results of the first beam area
	    reTextN1.setMaximumSize(new Dimension(60,20));
        reTextT1.setMaximumSize(new Dimension(60,20));
        reTextM1.setMaximumSize(new Dimension(60,20));
	    result1 = new JPanel();
	    result1.setLayout(new BoxLayout(result1, BoxLayout.X_AXIS));
	    result1.add(reBeam1);
	    result1.add(Box.createRigidArea(new Dimension(10,0)));
	    result1.add(reN1);
	    result1.add(Box.createRigidArea(new Dimension(10,0)));
	    result1.add(reTextN1);
	    result1.add(Box.createRigidArea(new Dimension(10,0)));
	    result1.add(reT1);
	    result1.add(Box.createRigidArea(new Dimension(10,0)));
	    result1.add(reTextT1);
	    result1.add(Box.createRigidArea(new Dimension(10,0)));
	    result1.add(reM1);
	    result1.add(Box.createRigidArea(new Dimension(10,0)));
	    result1.add(reTextM1);
	    result1.add(Box.createRigidArea(new Dimension(30,0)));
	    
	    //Results of the second beam area
	    reTextN2.setMaximumSize(new Dimension(60,20));
        reTextT2.setMaximumSize(new Dimension(60,20));
        reTextM2.setMaximumSize(new Dimension(60,20));
	    result2 = new JPanel();
	    result2.setLayout(new BoxLayout(result2, BoxLayout.X_AXIS));
	    result2.add(reBeam2);
	    result2.add(Box.createRigidArea(new Dimension(10,0)));
	    result2.add(reN2);
	    result2.add(Box.createRigidArea(new Dimension(10,0)));
	    result2.add(reTextN2);
	    result2.add(Box.createRigidArea(new Dimension(10,0)));
	    result2.add(reT2);
	    result2.add(Box.createRigidArea(new Dimension(10,0)));
	    result2.add(reTextT2);
	    result2.add(Box.createRigidArea(new Dimension(10,0)));
	    result2.add(reM2);
	    result2.add(Box.createRigidArea(new Dimension(10,0)));
	    result2.add(reTextM2);
	    result2.add(Box.createRigidArea(new Dimension(30,0)));
	    
	    //Results of the third beam area
	    reTextN3.setMaximumSize(new Dimension(60,20));
        reTextT3.setMaximumSize(new Dimension(60,20));
        reTextM3.setMaximumSize(new Dimension(60,20));
	    result3 = new JPanel();
	    result3.setLayout(new BoxLayout(result3, BoxLayout.X_AXIS));
	    result3.add(reBeam3);
	    result3.add(Box.createRigidArea(new Dimension(10,0)));
	    result3.add(reN3);
	    result3.add(Box.createRigidArea(new Dimension(10,0)));
	    result3.add(reTextN3);
	    result3.add(Box.createRigidArea(new Dimension(10,0)));
	    result3.add(reT3);
	    result3.add(Box.createRigidArea(new Dimension(10,0)));
	    result3.add(reTextT3);
	    result3.add(Box.createRigidArea(new Dimension(10,0)));
	    result3.add(reM3);
	    result3.add(Box.createRigidArea(new Dimension(10,0)));
	    result3.add(reTextM3);
	    result3.add(Box.createRigidArea(new Dimension(30,0)));
	    
	    //Results of the fourth beam area
	    reTextN4.setMaximumSize(new Dimension(60,20));
        reTextT4.setMaximumSize(new Dimension(60,20));
        reTextM4.setMaximumSize(new Dimension(60,20));
	    result4 = new JPanel();
	    result4.setLayout(new BoxLayout(result4, BoxLayout.X_AXIS));
	    result4.add(reBeam4);
	    result4.add(Box.createRigidArea(new Dimension(10,0)));
	    result4.add(reN4);
	    result4.add(Box.createRigidArea(new Dimension(10,0)));
	    result4.add(reTextN4);
	    result4.add(Box.createRigidArea(new Dimension(10,0)));
	    result4.add(reT4);
	    result4.add(Box.createRigidArea(new Dimension(10,0)));
	    result4.add(reTextT4);
	    result4.add(Box.createRigidArea(new Dimension(10,0)));
	    result4.add(reM4);
	    result4.add(Box.createRigidArea(new Dimension(10,0)));
	    result4.add(reTextM4);
	    result4.add(Box.createRigidArea(new Dimension(30,0)));
	    
	    //Results of the displacement u
	    reTextDeltaX1.setMaximumSize(new Dimension(65,20));
        reTextDeltaX2.setMaximumSize(new Dimension(65,20));
        reTextDeltaX3.setMaximumSize(new Dimension(65,20));
	    deltaXArea = new JPanel();
	    deltaXArea.setLayout(new BoxLayout(deltaXArea, BoxLayout.X_AXIS));
	    deltaXArea.add(Box.createRigidArea(new Dimension(10,0)));
	    deltaXArea.add(reDeltaX1);
	    deltaXArea.add(Box.createRigidArea(new Dimension(10,0)));
	    deltaXArea.add(reTextDeltaX1);
	    deltaXArea.add(Box.createRigidArea(new Dimension(10,0)));
	    deltaXArea.add(reDeltaX2);
	    deltaXArea.add(Box.createRigidArea(new Dimension(10,0)));
	    deltaXArea.add(reTextDeltaX2);
	    deltaXArea.add(Box.createRigidArea(new Dimension(10,0)));
	    deltaXArea.add(reDeltaX3);
	    deltaXArea.add(Box.createRigidArea(new Dimension(10,0)));
	    deltaXArea.add(reTextDeltaX3);
	    deltaXArea.add(Box.createRigidArea(new Dimension(30,0)));
	    
	    //Results of the displacement v
	    reTextDeltaY1.setMaximumSize(new Dimension(65,20));
        reTextDeltaY2.setMaximumSize(new Dimension(65,20));
        reTextDeltaY3.setMaximumSize(new Dimension(65,20));
	    deltaYArea = new JPanel();
	    deltaYArea.setLayout(new BoxLayout(deltaYArea, BoxLayout.X_AXIS));
	    deltaYArea.add(Box.createRigidArea(new Dimension(12,0)));
	    deltaYArea.add(reDeltaY1);
	    deltaYArea.add(Box.createRigidArea(new Dimension(10,0)));
	    deltaYArea.add(reTextDeltaY1);
	    deltaYArea.add(Box.createRigidArea(new Dimension(11,0)));
	    deltaYArea.add(reDeltaY2);
	    deltaYArea.add(Box.createRigidArea(new Dimension(10,0)));
	    deltaYArea.add(reTextDeltaY2);
	    deltaYArea.add(Box.createRigidArea(new Dimension(11,0)));
	    deltaYArea.add(reDeltaY3);
	    deltaYArea.add(Box.createRigidArea(new Dimension(10,0)));
	    deltaYArea.add(reTextDeltaY3);
	    deltaYArea.add(Box.createRigidArea(new Dimension(30,0)));
	    
	    //Results of the first and second constraints 
	    reTextCritera1.setMaximumSize(new Dimension(60,20));
        reTextCritera2.setMaximumSize(new Dimension(60,20));
	    criteraArea12 = new JPanel();
	    criteraArea12.setLayout(new BoxLayout(criteraArea12, BoxLayout.X_AXIS));
	    criteraArea12.add(Box.createRigidArea(new Dimension(10,0)));
	    criteraArea12.add(reCritera1);
	    criteraArea12.add(Box.createRigidArea(new Dimension(10,0)));
	    criteraArea12.add(reTextCritera1);
	    criteraArea12.add(Box.createRigidArea(new Dimension(10,0)));
	    criteraArea12.add(reCritera2);
	    criteraArea12.add(Box.createRigidArea(new Dimension(10,0)));
	    criteraArea12.add(reTextCritera2);
	    criteraArea12.add(Box.createRigidArea(new Dimension(20,0)));
	    
	    //Results of the third and fourth constraints
	    reTextCritera3.setMaximumSize(new Dimension(60,50));
        reTextCritera4.setMaximumSize(new Dimension(60,50));
	    criteraArea34 = new JPanel();
	    criteraArea34.setLayout(new BoxLayout(criteraArea34, BoxLayout.X_AXIS));
	    criteraArea34.add(Box.createRigidArea(new Dimension(10,0)));
	    criteraArea34.add(reCritera3);
	    criteraArea34.add(Box.createRigidArea(new Dimension(10,0)));
	    criteraArea34.add(reTextCritera3);
	    criteraArea34.add(Box.createRigidArea(new Dimension(10,0)));
	    criteraArea34.add(reCritera4);
	    criteraArea34.add(Box.createRigidArea(new Dimension(10,0)));
	    criteraArea34.add(reTextCritera4);
	    criteraArea34.add(Box.createRigidArea(new Dimension(20,0)));
	    
	    //The buttons that will allow to execute the calculation and optimization code from all the data inserted in the text fields
	    btnfield = new JPanel();
	    btnfield.setLayout(new BoxLayout(btnfield, BoxLayout.X_AXIS));
	    btnfield.add(Box.createRigidArea(new Dimension(10,0)));
	    btnfield.add(btnCalculate);
	    btnfield.add(Box.createRigidArea(new Dimension(20,0)));
	    btnfield.add(btnOptimize);
	    btnfield.add(Box.createRigidArea(new Dimension(20,0)));
	    btnfield.add(checkGa);
	    btnfield.add(Box.createRigidArea(new Dimension(10,0)));
	    btnfield.add(checkSa);
	     
        //Adding vertically from top to bottom in the set area all the widgets subAreas
        fieldset = new JPanel();
        fieldset.setPreferredSize(new Dimension(500, 320));
        fieldset.setLayout(new BoxLayout(fieldset, BoxLayout.Y_AXIS));
        fieldset.add(geoArea);
        fieldset.add(Box.createRigidArea(new Dimension(0,10)));
        fieldset.add(alphaArea);
        fieldset.add(Box.createRigidArea(new Dimension(0,10)));
        fieldset.add(beam1Area);
        fieldset.add(beam2Area);
        fieldset.add(beam3Area);
        fieldset.add(beam4Area);
        fieldset.add(Box.createRigidArea(new Dimension(0,5)));
        fieldset.add(loadsTitleArea);
        fieldset.add(Box.createRigidArea(new Dimension(0,5)));
        fieldset.add(loadsArea);
        fieldset.add(Box.createRigidArea(new Dimension(0,5)));
        fieldset.add(precisionTitleArea);
        fieldset.add(Box.createRigidArea(new Dimension(0,5)));
        fieldset.add(precisionArea);
        fieldset.add(Box.createRigidArea(new Dimension(0,5)));
        fieldset.add(btnfield);
        fieldset.add(Box.createRigidArea(new Dimension(700,20)));
        
        //Adding vertically from top to bottom in the result area all the widgets subAreas
        fieldresult = new JPanel();
        fieldresult.setPreferredSize(new Dimension(500, 320));
        fieldresult.setLayout(new BoxLayout(fieldresult, BoxLayout.Y_AXIS));
        fieldresult.add(Box.createRigidArea(new Dimension(0,20)));
        fieldresult.add(resultsArea);
        fieldresult.add(Box.createRigidArea(new Dimension(0,10)));
        fieldresult.add(result1);
        fieldresult.add(Box.createRigidArea(new Dimension(0,10)));
        fieldresult.add(result2);
        fieldresult.add(Box.createRigidArea(new Dimension(0,10)));
        fieldresult.add(result3);
        fieldresult.add(Box.createRigidArea(new Dimension(0,10)));
        fieldresult.add(result4);
        fieldresult.add(Box.createRigidArea(new Dimension(0,10)));
        fieldresult.add(deltaXArea);
        fieldresult.add(Box.createRigidArea(new Dimension(0,10)));
        fieldresult.add(deltaYArea);
        fieldresult.add(Box.createRigidArea(new Dimension(0,10)));
        fieldresult.add(criteraArea12);
        fieldresult.add(Box.createRigidArea(new Dimension(0,10)));
        fieldresult.add(criteraArea34);
        fieldresult.add(Box.createRigidArea(new Dimension(0,20)));
        
        //Creating the panel configuration which will be the bottom of the window
        southMainContainer = new JPanel();
        southMainContainer.setPreferredSize(new Dimension(1000, 320));
        southMainContainer.setLayout(new BoxLayout(southMainContainer, BoxLayout.X_AXIS));
        southMainContainer.setBorder(BorderFactory.createTitledBorder("Configuration"));
        southMainContainer.add(fieldset);
        southMainContainer.add(fieldresult);
        
        //Creating the main container that will contain the configuration panel at the south and the scheme at the center
        mainContainer = new JPanel(new BorderLayout());
        mainContainer.add(southMainContainer,BorderLayout.SOUTH);
        
        //Display of the Scheme
        ImageIcon icone = new ImageIcon("src//SchemaPortique.jpg");
        JLabel image = new JLabel(icone);
        JPanel PanelImage = new JPanel();
        image.setSize(PanelImage.getWidth(),PanelImage.getHeight());
        PanelImage.add(image);
        mainContainer.add(PanelImage,BorderLayout.CENTER);
        
        //Giving actions when we click on the buttons. EcouteurCalculate allows to calculate everything and to display it on the main window whereas EcouteurOptimize open the window associated with the selected optimization algorithm
        EcouteurCalculate CalculateAction=new EcouteurCalculate(this);
		btnCalculate.addActionListener(CalculateAction);
		
		EcouteurOptimize OpenAction=new EcouteurOptimize(this);
		btnOptimize.addActionListener(OpenAction);
        
        this.setContentPane(mainContainer);
	    setVisible(true);
	}
	//Methods that allow to access and set everything
	public double getAlpha() {
		return Double.parseDouble(geoFieldAlpha.getText());
	}
	public double getlength1() {
		return Double.parseDouble(geoFieldLength1.getText());
	}
	public double getlength2() {
		return Double.parseDouble(geoFieldLength2.getText());
	}
	public double getlength3() {
		return Double.parseDouble(geoFieldLength3.getText());
	}
	public double getlength4() {
		return Double.parseDouble(geoFieldLength4.getText());
	}
	public double getq1() {
		return -Double.parseDouble(loFieldq1.getText());
	}
	public double getq2() {
		return Double.parseDouble(loFieldq2.getText());
	}
	public double getq3() {
		return -Double.parseDouble(loFieldq3.getText());
	}
	public int getdx() {
		return Integer.parseInt(preFielddx.getText());
	}
	public JComboBox getList1() {
		return list1;
	}
	public JComboBox getList2() {
		return list2;
	}
	public JComboBox getList3() {
		return list3;
	}
	public JComboBox getList4() {
		return list4;
	}
	public boolean isGaSelected() {
		return checkGa.isSelected();
	}
	public boolean isSaSelected() {
		return checkSa.isSelected();
	}
	public void setDeltaX1(String str) {
		reTextDeltaX1.setText(str);
	}
	public void setDeltaX2(String str) {
		reTextDeltaX2.setText(str);
	}
	public void setDeltaX3(String str) {
		reTextDeltaX3.setText(str);
	}
	public void setDeltaY1(String str) {
		reTextDeltaY1.setText(str);
	}
	public void setDeltaY2(String str) {
		reTextDeltaY2.setText(str);
	}
	public void setDeltaY3(String str) {
		reTextDeltaY3.setText(str);
	}
	public void setN1(String str) {
		reTextN1.setText(str);
	}
	public void setT1(String str) {
		reTextT1.setText(str);
	}
	public void setM1(String str) {
		reTextM1.setText(str);
	}
	public void setN2(String str) {
		reTextN2.setText(str);
	}
	public void setT2(String str) {
		reTextT2.setText(str);
	}
	public void setM2(String str) {
		reTextM2.setText(str);
	}
	public void setN3(String str) {
		reTextN3.setText(str);
	}
	public void setT3(String str) {
		reTextT3.setText(str);
	}
	public void setM3(String str) {
		reTextM3.setText(str);
	}
	public void setN4(String str) {
		reTextN4.setText(str);
	}
	public void setT4(String str) {
		reTextT4.setText(str);
	}
	public void setM4(String str) {
		reTextM4.setText(str);
	}
	public void setCritera1(String str) {
		reTextCritera1.setText(str);
	}
	public void setCritera2(String str) {
		reTextCritera2.setText(str);
	}
	public void setCritera3(String str) {
		reTextCritera3.setText(str);
	}
	public void setCritera4(String str) {
		reTextCritera4.setText(str);
	}
	//Here is the "main" where we open the main window. This is basically the main execution of the code 
	public static void main (String args[]) {
		JavaAlgoGen execution = new JavaAlgoGen();
	}	
}
