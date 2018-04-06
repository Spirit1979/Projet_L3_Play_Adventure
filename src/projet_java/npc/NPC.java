package projet_java.npc;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui repr�sente un PNJ basique
 *
 */
public class NPC{

	/**
	 * Nom du PNJ
	 */
	private String name;
	
	/**
	 * Liste des dialogues du PNJ (chaque dialogue est utilis� en fonction de l'�tat du PNJ)
	 */
	private List<String> dialogs;
	
	/**
	 * L'�tat du PNJ change le dialogue du PNJ
	 */
	private int state = 0; 
	
	/**
	 * Constructeur basique
	 * @param name nom du PNJ
	 * @param dialogs dialogues du PNJ
	 */
	public NPC(String name, List<String> dialogs){
		this.name = name;
		this.dialogs = dialogs;
		if(this.dialogs == null)
			this.dialogs = new ArrayList<String>(3);
	}		

	/**
	 * Declenche le dialogue dans l'etat correspondant du PNJ
	 */
	public void talk() {
		if(this.dialogs != null && this.state < this.dialogs.size()) {
			System.out.println(this.dialogs.get(state));
		} else {
			System.out.println("Aucun dialogue.");
		}
	}
	
	/**
	 * Renvoie le nom du PNJ sans majuscules
	 * et sans espaces en debut et fin de nom
	 * @return le nom nettoye
	 */
	public String getCleanName() {
		return this.name.toLowerCase().trim();
	}
	
	
	/**
	 * getter de name
	 * @return nom du PNJ
	 */
	public String getName(){
		return name;
	}

	/**
	 * getter de dialogs
	 * @return la liste des dialogues du PNJ
	 */
	public List<String> getDialogs() {
		return dialogs;
	}

	
	/**
	 * setter de dialogs
	 * @param dialogs nouveaus dialogues du PNJ
	 */
	public void setDialogs(List<String> dialogs) {
		this.dialogs = dialogs;
	}
	
	
	/**
	 * Passe au prochain etat du PNJ s'il y en a un
	 */
	public void nextState(){
		int max = this.dialogs.size() - 1;
		if(this.state + 1 <= max){
			this.state++;
		}
	}
}