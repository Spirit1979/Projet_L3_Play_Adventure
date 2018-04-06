package projet_java.npc;

import java.util.List;

import projet_java.Inventory;
import projet_java.item.Item;

public class NPCQuestItemGiver extends NpcQuest {
	
	/**
	 * Item a ajouter a l'inventaire quand la quete est accomplie
	 */
	private Item itemToAdd;
	
	/**
	 * Inventaire dans lequel il faut ajouter l'item quand la quete est accomplie
	 */
	private Inventory inventory;

	
	/**
	 * Constructeur
	 * @param name nom du PNJ
	 * @param dialogs dialogues du PNJ
	 * @param item item a donner au PNJ pour finir la quete
	 * @param itemToAdd item a ajouter a l'inventaire quand la quete est finie
	 * @param inventory inventaire qui recoit l'item quand la quete est finie
	 */
	public NPCQuestItemGiver(String name, List<String> dialogs, Item item, Item itemToAdd, Inventory inventory) {
		super(name, dialogs, item);
		this.itemToAdd = itemToAdd;
		this.inventory = inventory;
	}

	@Override
	protected void action() {
		if(this.inventory != null && this.itemToAdd != null) {
			this.inventory.addItem(this.itemToAdd);
		}
	}

}
