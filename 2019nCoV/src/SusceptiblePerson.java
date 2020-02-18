import java.util.*;

public class SusceptiblePerson extends Person{
	
	boolean isInfected;
	boolean isSymptomatic;
	boolean isRecovered;
	boolean isDead;
	int diseaseTimer;
	double deathChance;
	
	
	
	
	SusceptiblePerson (int locx, int locy, Disease disease){
		super(locx, locy);
		spreadIndex = Disease.spreadIndex;
		dieIndex = disease.dieIndex;
		incubationPeriod = disease.incubationPeriod;
		isInfected = false;
		isSymptomatic = false;
		isRecovered=false;
		isDead = false;
		diseaseTimer=0;
		deathChance=0;
		
		
		
	}
	
	public void infect(SusceptiblePerson[] spPopulation) {
		
		Random rand = new Random();
		
		for(int i=0; i<spPopulation.length; i++) {
			if(!spPopulation[i].isInfected) {
				if(Math.abs(spPopulation[i].location[0] - location[0])<5 && Math.abs(spPopulation[i].location[1] - location[1])<5) {
					if (rand.nextDouble()<spreadIndex) {
					spPopulation[i].isInfected = true;
				}
				}
			}
		}
		
	}
	
	public void becomeSymptomatic() {
		if (diseaseTimer>incubationPeriod)
			isSymptomatic=true;
	}
	
	public void killOrRecover() {
		Random rand = new Random();
		if (diseaseTimer>incubationPeriod && diseaseTimer<2*incubationPeriod)  deathChance=diseaseTimer*(dieIndex/800);
		if (diseaseTimer>=2*incubationPeriod) {
			deathChance=0;
			if (diseaseTimer>3*incubationPeriod)
			isRecovered=true;
		}
		
		
		isDead = rand.nextDouble()<deathChance;
	}
	
}
