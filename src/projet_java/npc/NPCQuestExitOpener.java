package projet_java.npc;

import java.util.List;

import projet_java.exit.Exit;
import projet_java.item.Item;

public class NPCQuestExitOpener extends NpcQuest {
	
	/**
	 * Sortie a debloquer quand la quete est accomplie
	 */
	private Exit exit;

	/**
	 * Constructeur
	 * @param name nom du NPC
	 * @param dialogs dialogues du NPC
	 * @param item item a ramener au NPC pour finir la quete
	 * @param exit porte a ouvrir quand la quete est finie
	 */
	public NPCQuestExitOpener(String name, List<String> dialogs, Item item, Exit exit) {
		super(name, dialogs, item);
		this.exit = exit;
	}

	@Override
	protected void action() {
		if(this.exit != null)
			this.exit.open();
	}

}
