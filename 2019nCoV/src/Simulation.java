import java.util.*;

public class Simulation {

	public static void main(String[] args) {
		
		int populationSize = 5; //population density
		int[] area = {100, 100}; //the area's size in kilometer
		final double infectI=0.1, killI=0.2; //Infection index & lethality, respectively
		final int speedI=40;	//incubation period in days
		
		
		Disease disease= new Disease(infectI, killI, speedI);
		Person[] population = new Person[populationSize];
		generatingPopulation(population, area);
		
		for(int i=0; i<population.length; i++) {
			population[i].travel(area);
		}
		
		for(int i=0; i<population.length; i++) {
			population[i].returnHome();
		}
		
	}
	
	public static void generatingPopulation (Person[] population, int[] area) {	
		Random rand = new Random();
		
		for(int i=0; i<population.length; i++) {
			population[i] = new Person(rand.nextInt(area[0]+1), rand.nextInt(area[1]+1));
		}
	}
	
}



