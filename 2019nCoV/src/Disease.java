//Kevin Xu & Damian Hackett
//20200129
//Disease class of project simulation for diseases

public class Disease {
	
	public static double spreadIndex; //the chance, within a certain proximity, that an infected individual will transmit the disease
	public double dieIndex;
	public double lethality; //number of deaths over number of sick with a specific disease (x100%)
	public int incubationPeriod; //the average amount of time takes for infected person to becomes symptomatic.
	public static boolean diseaseSuppression; //dictates whether disease will be suppressed over time (ex. through gov't response)
	
	Disease(double sIndex, double dIndex, int iPeriod){
		spreadIndex = sIndex;
		dieIndex = dIndex;
		incubationPeriod = iPeriod;
	}
	
	public static void suppression () {
		if (diseaseSuppression==true)
	spreadIndex=Simulation.masterSpreadIndex-Simulation.masterSpreadIndex*(Simulation.dayCount/100);
	}
}
