package projet_java.npc;

import projet_java.Inventory;
import projet_java.item.Item;

/**
 * Classe qui repr�sente un marchand
 *
 */
public class Merchant extends NPC {

	/**
	 * Inventaire du marchand (objets qu'il met en vente)
	 */
	private Inventory inventory;
	
	
	/**
	 * Constructeur basique
	 * @param name nom du marchand
	 */
	public Merchant(String name) {
		super(name, null);
		this.inventory = new Inventory();
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void talk() {
		System.out.println("Bonjour, voici les objets que je vends :");
		this.inventory.displayWithPrice();
	}
	
	/**
	 * Ajout d'un item dans l'inventaire du marchand
	 * @param item item a ajouter
	 * @return le marchand avec le nouvel item
	 */
	public Merchant addItem(Item item) {
		this.inventory.addItem(item);
		return this;
	}
	
	/**
	 * getter de inventory
	 * @return l'inventaire du marchand
	 */
	public Inventory inventory() {
		return this.inventory;
	}
}