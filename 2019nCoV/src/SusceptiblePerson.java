public class SusceptiblePerson extends Person{
	
	SusceptiblePerson (int locx, int locy, Disease disease){
		super(locx, locy);
		spreadIndex = disease.spreadIndex;
		dieIndex = disease.dieIndex;
		icubationPeriod = disease.icubationPeriod;
	}
	
}
