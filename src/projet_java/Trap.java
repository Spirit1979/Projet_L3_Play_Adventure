package projet_java;

/**
 * Classe qui represente un piege
 *
 */
public class Trap {
	
	/**
	 * Dommages du piege
	 */
	int damage;
	
	/**
	 * Etat du piege (true = arme, false = desactive)
	 */
	boolean state = true; 
	
	/**
	 * Message du piege lorsque declenche
	 */
	String description;
	
	/**
	 * Constructeur de piege
	 * @param damage dommages du pige
	 * @param description message au declenchement
	 */
	public Trap(int damage, String description){
		this.damage = damage;
		this.description = description;
	}
	
	/**
	 * Action a effectuer quand le piege est
	 * declenche par le hero
	 * @param hero hero qui declenche le piege
	 */
	public void triggerTrap(Hero hero){
		hero.looseHealth(this.damage);
		System.out.println(description);
	}
	
	
	/**
	 * getter de description
	 * @return le message du piege
	 */
	public String getDescription(){
		return this.description;
	}
	
	/**
	 * desactive le piege
	 */
	public void setStateFalse(){
		this.state = false;
	}
	
	/**
	 * getter de state
	 * @return l'etat du piege (arme ou neutralise)
	 */
	public boolean getState(){
		return this.state;
	}
}