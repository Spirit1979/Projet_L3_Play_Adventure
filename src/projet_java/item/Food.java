package projet_java.item;

import projet_java.Hero;


/**
 * Classe qui repr�sente un item qui permet de se soigner
 *
 */
public class Food extends Item{
	
	/**
	 * Quantite de soin
	 */
	int healthAmount;
	

	
	/**
	 * Constructeur basique
	 * @param name nom de la potion/nourriture
	 * @param description description de l'item
	 * @param health le nombre de points de vie que restore l'item
	 */
	public Food(String name, String description, int health) {
		super(name, description);
		this.healthAmount = health;
	}
	
	
	/**
	 * Constructeur basique
	 * @param name nom de la potion/nourriture
	 * @param description description de l'item
	 * @param health le nombre de points de vie que restore l'item
	 */
	public Food(String name, String description, int health, int price) {
		super(name, description);
		this.healthAmount = health;
		this.price = price;
	}
	
	/**
	 * Utilisation de l'objet sur un hero
	 * @param hero hero sur lequel utiliser l'objet
	 */
	public void usePotion(Hero hero) {
		hero.heal(healthAmount);
		hero.getInventory().removeItem(this);
		
		System.out.println("Vous venez de vous soigner de "+ healthAmount + " PDV.");
	}
}