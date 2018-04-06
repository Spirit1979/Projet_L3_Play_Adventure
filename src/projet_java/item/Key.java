package projet_java.item;

public class Key extends Item {

	/**
	 * 
	 * @param name nom de la cle
	 * @param description description de la cle
	 */
	public Key(String name, String description) {
		super(name, description);
		this.setTakeable(true);
	}
	
	
}
