package projet_java.item;



/**
 * Classe qui repr�sente un objet basique
 *
 */
public class Item {

	/**
	 * Nom de l'item
	 */
	private String name;
	
	/**
	 * Description de l'item
	 */
	private String description;
	
	/**
	 * Prix de l'item (facultatif)
	 */
	protected int price = 1;
	
	/**
	 * Indique si l'item peut �tre ramass� ou non
	 */
	private boolean takeable = true;
	
	
	/**
	 * Constructeur basique
	 * @param name nom de l'item
	 * @param description description de l'item
	 */
	public Item(String name, String description) {
		this.description = description;
		this.name = name;
	}
	
	/**
	 * Constructeur d'un item avec un prix
	 * @param name nom de l'item
	 * @param description description de l'item
	 * @param price prix de l'item
	 */
	public Item(String name, String description, int price) {
		this(name, description);
		this.price = price;
	}
	
	
	/**
	 * getter de name
	 * @return nom de l'item
	 */
	public String getName() {
		return this.name;
	}
	
	
	/**
	 * Renvoie le nom de l'item sans majuscules 
	 * et sans espaces en debut et fin de nom
	 * @return nom nettoye
	 */
	public String getCleanName() {
		return this.name.toLowerCase().trim();
	}
	
	
	/**
	 * setter de name
	 * @param name nouveau nom de l'item
	 * @return l'item modifi�
	 */
	public Item setName(String name) {
		this.name = name;
		return this;
	}
	
	
	/**
	 * setter de description
	 * @param description nouvelle description
	 * @return l'item avec la nouvelle description
	 */
	public Item setDescription(String description) {
		this.description = description;
		return this;
	}
	
	
	/**
	 * getter de description
	 * @return la description de l'item
	 */
	public String getDescription() {
		return this.description;
	}
	
	
	/**
	 * affiche l'objet (nom et description)
	 */
	public void display() {
		System.out.println(this.getName() + " : " + this.getDescription());
	}
	
	
	/**
	 * setter de takeable
	 * @param takeable indique si l'objet peut etre ramass�
	 * @return l'item avec le nouvel etat
	 */
	public Item setTakeable(boolean takeable) {
		this.takeable = takeable;
		return this;
	}
	
	/**
	 * indique si l'objet peut etre ramass�
	 * @return true = peut etre ramasse, false = ne peut pas etre ramass�
	 */
	public boolean isTakeable() {
		return this.takeable;
	}
	
	/**
	 * getter de price
	 * @return prix de l'item
	 */
	public int getPrice() {
		return this.price;
	}
	
	public Item setPrice(int price){
		this.price = price;
		
		return this;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return this.name.concat(this.description).hashCode();
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object o) {
		return this.name.concat(this.description).equals(o);
	}
}