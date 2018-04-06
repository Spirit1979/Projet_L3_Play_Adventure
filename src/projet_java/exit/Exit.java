package projet_java.exit;

import projet_java.Hero;
import projet_java.Place;


/**
 * Classe qui rep�sente une sortie
 *
 */
public class Exit {

	/**
	 * Etat de la sortie (true = ouverte, false = bloquee)
	 */
	private boolean open;
	
	/**
	 * Raison qui bloque la porte( ex: pont cass�)
	 */
	private String blockedReason = "Impossible d'aller vers %1";
	
	/**
	 * Destination de la sortie
	 */
	private Place exit;

	
	/**
	 * Constructeur basique
	 * @param exit destination de la sortie
	 */
	public Exit(Place exit){
		this.exit = exit;
		this.open = true;
	}
	
	/**
	 * Constructeur avec etat de la porte
	 * @param exit destination de la sorite
	 * @param state etat de la porte
	 */
	public Exit(Place exit, boolean state) {
		this(exit);
		this.open = state;
	}
	
	
	/**
	 * ouvre la porte
	 * @return la porte ouverte
	 */
	public Exit open() {
		this.open = true;
		return this;
	}

	
	/**
	 * ferme la porte
	 * @return la porte ferm�
	 */
	public Exit close() {
		this.open = false;
		return this;
	}

	/**
	 * teste la porte est ouverte ou ferm�
	 * @return l'etat de la porte
	 */
	public boolean isOpen() {
		return open;
	}

	
	/**
	 * getter de exit
	 * @return destination de la sortie
	 */
	public Place getDestination() {
		return exit;
	}

	/**
	 * setter de destination
	 * @param exit nouvelle destination
	 * @return la sortie avec la nouvelle destination 
	 */
	public Exit setDestination(Place exit) {
		this.exit = exit;
		return this;
	}
	
	/**
	 * setter de reasonBlocked
	 * @param reason la raison pour laquelle la sortie est bloqu�
	 * @return la sortie avec la nouvelle raison
	 */
	public Exit setReason(String reason) {
		this.blockedReason = reason;
		return this;
	}
	
	
	/**
	 * Affiche le nom de la porte et si elle est ouverte ou bloqu�e.
	 */
	public void display(){
		if(this.isOpen()) {
			System.out.println("La sortie vers " + this.getDestination().getName() + " est disponible.");
		} else {
			String reason = this.blockedReason;
			reason = reason.replace("%1", this.getDestination().getName());
			System.out.println(reason);
		}
	}
	
	
	/**
	 * Essaye d'emprunter la sortie, si elle est ouverte le 
	 * hero est deplace, sinon un message s'affiche
	 * @param hero hero qui essaye d'emprunter la sortie
	 */
	public void useExit(Hero hero) {
		if(this.isOpen()) {
			hero.changePlace(this.getDestination());
			this.getDestination().triggerEnter(hero);
		} else {
			this.display();
		}
	}	
}