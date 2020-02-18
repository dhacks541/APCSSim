//Kevin Xu & Damian Hackett
//20200129
//Person class of project simulation for diseases

import java.util.*;

public class Person {
	
	int[] location = new int[2]; //location of people
	int[] homeLocation = new int[2];
	int travelDistance = 1000;
	double spreadIndex;
	double dieIndex;
	int incubationPeriod;
	
	Person(int locx, int locy) {
		location[0] = locx;
		location[1] = locy;
		homeLocation[0] = locx;
		homeLocation[1] = locy;
	}
	
	public void travel(int[] area) {
		
		Random rand = new Random();
		
		location[0] = location[0] + rand.nextInt(2*travelDistance)-travelDistance;

		location[1] = location[1] + rand.nextInt(2*travelDistance)-travelDistance;

	}
	
	public void returnHome() {
		location[0] = homeLocation[0];
		location[1] = homeLocation[1];
	}
	
}

