package projet_java.exit;

import projet_java.Hero;
import projet_java.Place;
import projet_java.item.Key;

public class KeyExit extends Exit {
	
	/**
	 * La cle qu'il faut pour debloquer la porte
	 */
	private Key key;
	
	
	/**
	 * Le message qui s'affiche quand la porte est debloquee
	 */
	private String msgWhenOpen = "Vous venez de debloquer la sortie vers %1";

	/**
	 * 
	 * @param exit destination de la sortie
	 */
	public KeyExit(Place exit, Key key) {
		super(exit);
		this.close();
		this.key = key;
	}
	
	/**
	 * Setter de msgWhenOpen
	 * @param msg nouveau message quand la sortie est debloquee
	 * @return la sortie avec le nouveau message
	 */
	public KeyExit setMsg(String msg) {
		if(msg != null) {
			this.msgWhenOpen = msg;
		}
		return this;
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
			if(hero.getInventory().isInInventory(this.key)) {
				hero.getInventory().removeItem(this.key);
				this.open();
				
				String msg = this.msgWhenOpen;
				msg = msg.replace("%1", this.getDestination().getName());
				System.out.println(msg);
				hero.changePlace(this.getDestination());
				this.getDestination().triggerEnter(hero);
			} else {
				this.display();
			}	
		}
	}

}