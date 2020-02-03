import java.util.*;

public class Person {
	
	int[] location = new int[2]; //location of people
	int[] homeLocation = new int[2];
	double spreadIndex;
	double dieIndex;
	int icubationPeriod;
	
	Person(int locx, int locy) {
		location[0] = locx;
		location[1] = locy;
		homeLocation[0] = locx;
		homeLocation[1] = locy;
	}
	
	public void travel(int[] area) {
		
		Random rand = new Random();
		
		do {
			location[0] = location[0] + rand.nextInt(2000)-1000;
		} while(location[0] > area[0]);
		do {
			location[1] = location[1] + rand.nextInt(2000)-1000;
		} while(location[1] > area[1]);
	}
	
	public void returnHome() {
		location[0] = homeLocation[0];
		location[1] = homeLocation[1];
	}
	
}
