package casseTete.model;

import java.util.ArrayList;
import java.util.List;

public class Chemin {
	
	private List<Case> lst;
	
	public Chemin() {
		lst = new ArrayList<Case>();
	}
	
	public List<Case> getLst() {
		return lst;
	}

	public void setLst(List<Case> lst) {
		this.lst = lst;
	}
	
	
	

}
