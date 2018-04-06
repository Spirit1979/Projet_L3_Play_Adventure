package projet_java;

import projet_java.exit.Exit;

/**
 * Classe du hero, cette classe est instanci� une seule fois
 * 
 */
public class Hero {
	
	/**
	 * Position du hero
	 */
	private Place actualPlace;
	
	/**
	 * Argent du hero
	 */
	private int money;
	
	/**
	 * Inventaire du hero
	 */
	private Inventory inventory;
	
	/**
	 * Points de vie du hero
	 */
	private int health = 10;
	
	/**
	 * Points de vie max du hero
	 */
	private int maxHealth = 20;
	
	
	
	
	
	/**
	 * Constructeur du hero
	 * @param place lieu ou commence le hero
	 * @param money argent initial du hero
	 */
	public Hero(Place place, int money) {
		this.actualPlace = place;
		this.money = money;
		this.inventory = new Inventory();
	}

	/**
	 * getter de place
	 * @return position du hero
	 */
	public Place getPlace() {
		return actualPlace;
	}

	/**
	 * change le hero de lieux
	 * @param newPlace nouveau lieu du hero
	 */
	public void changePlace(Place newPlace){
		this.actualPlace = newPlace;
	}
	
	/**
	 * Essaye de prendre la sortie si elle n'est pas bloqu�e
	 * @param exit sortie � prendre
	 */
	public void takeExit(Exit exit) {
		exit.useExit(this);
	}
	
	/**
	 * Essaye de payer le montant, si le hero 
	 * a assez d'argent la somme est d�duite
	 * sinon rien ne se passe
	 * @param amount to pay
	 * @return true si le hero peut payer, false sinon
	 */
	public boolean pay(int amount) {
		if(amount <= this.money) {
			this.money = this.money - amount;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Fait perde des points de vie au hero
	 * @param health nombre de points de vie que le hero perd
	 */
	public void looseHealth(int health){
		this.health -= health;
	}
	
	/**
	 * Ajouter de l'argent au hero
	 * @param amount montant a ajouter
	 */
	public void credit(int amount) {
		this.money += amount;
	}
	
	/**
	 * getter money
	 * @return l'argent du hero
	 */
	public int getMoney() {
		return this.money;
	}
	
	/**
	 * Soigne le hero
	 * @param amount nombre de points de vie a ajouter
	 */
	public void heal(int amount) {
		this.health += amount;
		if(this.health > this.maxHealth)
			this.health = this.maxHealth;
	}
	
	/**
	 * Affiche l'inventaire du hero
	 */
	public void displayInventory() {
		this.inventory.display();
	}
	
	/**
	 * getter health
	 * @return nombre de points de vie du hero
	 */
	public int getHealth(){
		return this.health;
	}
	
	/**
	 * getter inventory
	 * @return l'inventaire du hero
	 */
	public Inventory getInventory() {
		return this.inventory;
	}
}