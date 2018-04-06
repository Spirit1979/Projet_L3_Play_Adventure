package projet_java.npc;

import java.util.List;

import projet_java.Hero;
import projet_java.item.Item;


/**
 * Classe qui represente un PNJ qui ouvre une sortie lorsqu'on
 * lui ramene un objet
 *
 */
public abstract class NpcQuest extends NPC{
	
	/**
	 * Item qui declenche l'action du PNJ
	 */
	private Item item;
	
	
	/**
	 * Constructeur basique
	 * @param name nom du PNJ
	 * @param dialogs dialogues du PNJ
	 * @param item item qui declenche l'action
	 */
	public NpcQuest(String name, List<String> dialogs, Item item){
		super(name, dialogs);
		
		this.item = item;
	}
	
	/**
	 * Declenchement du dialogue et verification de l'item
	 * dans l'inventaire du heros, declenche l'action 
	 * si l'item est present
	 * @param hero heros qui declenche le dialogue
	 */
	public void talk(Hero hero){
		if(hero.getInventory().isInInventory(this.item)){
			hero.getInventory().removeItem(this.item);
			
			this.action();
			
			this.nextState();
			super.talk();
			this.nextState();
		} else {
			super.talk();
		}
	}
	
	
	/**
	 * Action a effectuer quand la quete est accomplie.
	 */
	protected abstract void action();
}
