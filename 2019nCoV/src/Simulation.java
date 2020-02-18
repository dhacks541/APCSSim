import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Simulation {

	public static int spPopulationSize = 9000; //the amount of susceptible people in a certain area
	public static int uspPopulationSize = 1000; //the amount of unsusceptible people in a certain area
	public static int[] area = {5000, 5000}; //the area's size in meter
	public static int days = 100;
	public static int dayCount=0;
	public static double masterSpreadIndex=0.8;
	public static int incubationPeriod=10;
	public static double lethality=0.2;
	static int infectedNumber = 0;
	static int recoveredNumber = 0;
	static int deadNumber =0;
	static int clean = 0;

	static int totInfected = 0; 
	static int totRecovered = 0;
	static int totDead = 0;
	static int totClean = 0;


	
	
	public static void main(String[] args) throws IOException{
        String filePath;
        filePath ="/Users/colleenhackett/Downloads/SuppresedGrowth.txt"; //default name and path of the file for writing stored in filePath
        FileWriter fw = new FileWriter(filePath); //file creation
        PrintWriter output = new PrintWriter(fw); 
		
		final int HOURS_IN_DAY = 24;
		
		Random rand = new Random();
		
		Disease disease= new Disease(masterSpreadIndex, lethality, incubationPeriod);
		SusceptiblePerson[] spPopulation = new SusceptiblePerson[spPopulationSize];
		UnsusceptiblePerson[] uspPopulation = new UnsusceptiblePerson[uspPopulationSize];
		
		//initialization of susceptible population and unsusceptible population
		for(int i=0; i<spPopulation.length; i++) {
			spPopulation[i] = new SusceptiblePerson(rand.nextInt(area[0]+1), rand.nextInt(area[1]+1), disease);
			spPopulation[i].isDead=false;
		}
		spPopulation[0].isInfected = true;
		for(int i=0; i<uspPopulation.length; i++) {
			uspPopulation[i] = new UnsusceptiblePerson(rand.nextInt(area[0]+1), rand.nextInt(area[1]+1));
			spPopulation[i].isDead=false;
		}

		for(int k=0; k<days; k++) {
			for(int i=0; i<HOURS_IN_DAY; i++) {
				//System.out.println(i);

				if(i>7 && i<19) {
					for(int j=0; j<spPopulation.length; j++) {
						if (!spPopulation[j].isDead && !spPopulation[i].isSymptomatic)
						spPopulation[j].travel(area);
					}
				}
				else {
					for(int j=0; j<spPopulation.length; j++) {
						if (!spPopulation[j].isDead)
						spPopulation[j].returnHome();
					}
				}

				infectPopulation(spPopulation);
				spPopulation[i].becomeSymptomatic();
				Disease.suppression();

				for(int j=0; j<spPopulation.length; j++) {
					spPopulation[j].returnHome();
				}

			}

		
		tally(spPopulation, output);
		}
		fw.close();
        output.close();
        System.out.println("Done!"); //prints in console after the progress bar reaches 100%
	}
	
	public static void infectPopulation (SusceptiblePerson[] spPopulation) {
		for(int i=0; i<spPopulation.length; i++) {
			if(spPopulation[i].isInfected && !spPopulation[i].isDead &&!spPopulation[i].isRecovered) {
				spPopulation[i].infect(spPopulation);
				spPopulation[i].killOrRecover();
         
			}
		}
	}

	public static void tally(SusceptiblePerson[] spPopulation, PrintWriter output) {
	for(int i=0; i<spPopulation.length; i++) {
		if(spPopulation[i].isInfected) {
			spPopulation[i].diseaseTimer++;
			totInfected++;
			if (spPopulation[i].isDead)
				totDead++;
			if (spPopulation[i].isRecovered) 
				totRecovered++;
		}
		else totClean++;
	}
	clean=totClean;
	infectedNumber=totInfected;
	deadNumber=totDead;
	recoveredNumber=totRecovered;
	totInfected = 0;
	totRecovered=0;
	totDead = 0;
	totClean=0;
	dayCount++;
	output.println(infectedNumber + "," + deadNumber + "," + recoveredNumber);
	System.out.println("Day " + dayCount + ":  " + Math.round((infectedNumber*1.0)/spPopulationSize*100)+"% infected: " + infectedNumber + " infected, " + deadNumber + " dead, " + recoveredNumber + " recovered, "+clean+" uninfected.");
	}

	
}
