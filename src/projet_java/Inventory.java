package projet_java;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import projet_java.item.Item;

/**
 * Classe qui rep�sente un inventaire
 * 
 *
 */
public class Inventory {
	
	/**
	 * Liste des items dans l'inventaire avec le nombre associ� en valeur
	 */
	private HashMap<Item, Integer> items;
	
	/**
	 * Constructeur de l'inventaire
	 */
	public Inventory() {
		this.items = new HashMap<>(10);
	}
	
	/**
	 * Ajout d'un ou plusieurs items dans l'inventaire
	 * @param item a ajouter dans l'inventaire
	 * @param count le nombre a ajouter
	 */
	public void addItem(Item item, int count) {
		if(this.items.containsKey(item)) {
			this.items.put(item, this.items.get(item) + count);
		} else {
			this.items.put(item, count);
		}
	}
	
	/**
	 * Ajout d'un seul item dans l'inventaire
	 * @param item a ajouter
	 */
	public void addItem(Item item) {
		this.addItem(item, 1);
	}
	
	/**
	 * Suppresion d'un ou plusieurs item dans l'invetaire
	 * @param item a supprimer
	 * @param count nombre a supprimer
	 */
	public void removeItem(Item item, int count) {
		int countInInventory = this.items.get(item);
		if(countInInventory <= count) {
			this.items.remove(item);
		} else {
			this.items.put(item, countInInventory - 1);
		}
	}
	
	/**
	 * Suppression d'un seul item dans l'inventaire
	 * @param item a supprimer
	 */
	public void removeItem(Item item) {
		this.removeItem(item, 1);
	}
	
	/**
	 * Affichage de l'inventaire (item et le nombre)
	 */
	public void display() {
		Iterator<Entry<Item, Integer>> iterator = this.items.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<Item, Integer> entry = iterator.next();
			Item item = entry.getKey();
			int count = entry.getValue();
			
			System.out.println(count + "x - " + item.getName());
		}
	}
	
	/**
	 * Affiche de l'inventaire avec le prix (item, nombre et le prix)
	 */
	public void displayWithPrice() {
		Iterator<Entry<Item, Integer>> iterator = this.items.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<Item, Integer> entry = iterator.next();
			Item item = entry.getKey();
			
			System.out.println("- " + item.getName() + " - " + item.getPrice() +" PO/u");
		}
	}
	
	/**
	 * Indique si l'item existe dans l'inventaire
	 * @param item a tester la presence
	 * @return true si l'item est present, false sinon
	 */
	public boolean isInInventory(Item item){
		return this.items.containsKey(item);
	}
	
	/**
	 * Indique si un item dans l'inventaire poss�de 
	 * le nom donn� en param�tre
	 * @param itemName nom de l'item a tester la presence
	 * @return true si le nom est present, false sinon
	 */
	public boolean isInInventory(String itemName) {
		return this.getItem(itemName) != null;
	}
	
	/**
	 * Renvoie un item qui porte le nom donn� en param�tre
	 * @param itemName nom de l'item a recuperer
	 * @return Item s'il existe, null sinon
	 */
	public Item getItem(String itemName) {
		Iterator<Item> iterator = this.items.keySet().iterator();
		while(iterator.hasNext()) {
			Item item = iterator.next();			
			if(item.getCleanName().equals(itemName)) {
				return item;
			}
		}
		return null;
	}
}