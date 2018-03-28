package casseTete;

import java.util.List;
import java.util.Observable;

public class Grille extends Observable {
	
	private List<Case> lst;
	private Case[][] tab;
	
	int lastI;
	int lastJ;
	
	void startDD(int i , int j ) {
		System.out.println("startDD : " + i + "-" + j);
        setChanged();
        notifyObservers();
	}
	
	void parcoursDD(int i , int j){
		    lastI = i;
	        lastJ = j;
	        System.out.println("parcoursDD : " + i + "-" + j);
	        setChanged();
	        notifyObservers();
	}
	
	void stopDD(int i , int j) {
        System.out.println("stopDD : " + i + "-" + j + " -> " + lastI + "-" + lastJ);
        setChanged();
        notifyObservers();
	}
	

}
