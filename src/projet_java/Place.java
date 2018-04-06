package projet_java;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import projet_java.exit.Exit;
import projet_java.item.Item;
import projet_java.monster.Monster;
import projet_java.npc.NPC;


/**
 * Classe qui repr�sente un lieu dans le jeu
 * 
 */
public class Place{

	/**
	 * Nom du lieu
	 */
	private String name;
	
	/**
	 * Description du lieu
	 */
	private String description;
	
	/**
	 * Objets present dans le lieu
	 */
	private HashMap<String, Item> items;
	
	/**
	 * Liste des sorties vers d'autres lieux
	 */
	private HashMap<String, Exit> exits;
	
	/**
	 * Liste des PNJ dans le lieu
	 */
	private HashMap<String, NPC> npcs;
	
	/**
	 * Liste des monstres dans le lieu
	 */
	private HashMap<String, Monster> monsters;
	
	/**
	 * Piege dans le lieu
	 */
	private Trap trap;
	
	/**
	 * Constructeur de base du lieu
	 */
	public Place() {
		this.name = "";
		this.description = "";
		this.items = new HashMap<>(6);
		this.exits = new HashMap<>(4);
		this.npcs = new HashMap<>(4);
		this.monsters = new HashMap<>(4);
	}
	
	/**
	 * Constructeur avec un nom de lieu
	 * @param name nom du lieu
	 */
	public Place(String name) {
		this();
		this.name = name;
	}
	
	/**
	 * Constructeur nom et description
	 * @param name nom du lieu
	 * @param description du lieu
	 */
	public Place(String name, String description) {
		this(name);
		this.description = description;
	}
	
	/**
	 * Constructeur avec nom, description et liste d'objets
	 * @param name
	 * @param description
	 * @param objects
	 */
	public Place(String name, String description, List<Item> objects) {
		this(name, description);
		
		for(Item o : objects) {
			this.items.put(o.getCleanName(), o);
		}
	}
	
	/**
	 * Constructeur avec nom, description, liste d'objets et liste de PNJ
	 * @param name
	 * @param description
	 * @param objects
	 * @param npcs
	 */
	public Place(String name, String description, List<Item> objects, List<NPC> npcs){
		this(name, description, objects);
		for(NPC npc : npcs) {
			this.npcs.put(npc.getCleanName(), npc);
		}
	}
	
	
	/**
	 * Actions a effectuer quand le hero arrive dans le lieu
	 */
	public void triggerEnter(Hero hero) {
		System.out.println("Vous venez d'arriver à " + this.name +"\n"+this.description);
		if(this.trap != null && this.trap.getState()== true)
		{
			trap.triggerTrap(hero);
			this.trap.setStateFalse();
		}
	}
	
	/**
	 * Affiche la description du lieu
	 */
	public void displayPlace(){
		System.out.println(this.description);
	}
	
	/**
	 * Affiche toutes les sorties disponibles du lieu
	 */
	public void displayExits(){
		if(this.exits.isEmpty()) {
			System.out.println("Aucune sortie disponible.");
		} else {
			String str = "Direction(s) : ";
			Collection<Exit> cExits = this.exits.values();
			
			for(Exit e : cExits)
			{
				str += "\n -" +e.getDestination().getName();
			}
			
			System.out.println(str);
		}
	}
	
	/**
	 * Affiche tous les objets du lieu
	 */
	public void displayObjects() {
		if(this.items.isEmpty()) {
			System.out.println("Aucun objet present.");
		} else {
			String str = "Objet(s) : ";
			Collection<Item> cObjects = this.items.values();
			
			for(Item o : cObjects) {
				str += "\n -" + o.getName();
			}
			
			System.out.println(str);
		}
	}
	
	/**
	 * Affiche tous les PNJ du lieu
	 */
	public void displayNpc(){
		if(this.npcs.isEmpty()){
			System.out.println("Aucun personnage dans la zone.");
		}
		else{
			String str = "Personnage(s) : ";
			Collection<NPC> cNpcs = this.npcs.values();
			
			for(NPC n : cNpcs){
				str += "\n -"+ n.getName();
			}
			
			System.out.println(str);
		}
	}
	
	/**
	 * Affiche tous les Monstre du lieu
	 */
	public void displayMonster(){
		if(this.monsters.isEmpty()){
			System.out.println("Aucun monstre dans la zone.");
		}
		else{
			String str = "Monstre(s) : ";
			Collection<Monster> cMonsters = this.monsters.values();
			
			for(Monster m : cMonsters){
				str += "\n -"+ m.getName();
			}
			
			System.out.println(str);
		}
	}
	
	/**
	 * Ajoute une sortie a la liste des sorties
	 * @param exit sortie a ajouter
	 */
	public void addExit(Exit exit){
		this.exits.put(exit.getDestination().getCleanName(), exit);
	}

	/**
	 * getter de name
	 * @return nom du lieu
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * setter de name
	 * @param name nouveau nom
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Renvoie le nom du lieu sans majuscules 
	 * et sans espaces en debut et fin de nom
	 * @return nom nettoy�
	 */
	public String getCleanName() {
		return this.name.toLowerCase().trim();
	}


	/**
	 * Ajoute ou modifier le piege du lieu
	 * @param trap piege a ajouter
	 */
	public void addTrap(Trap trap){
		this.trap = trap;
	}

	/**
	 * getter de description
	 * @return description du lieu
	 */
	public String getDescription() {
		return this.description;
	}

	
	/**
	 * getter de exits
	 * @return liste des sorties
	 */
	public HashMap<String, Exit> getExits() {
		return this.exits;
	}

	/**
	 * setter de description
	 * @param description nouvelle description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	/**
	 * ajouter un item a la liste des items
	 * @param obj nouvel item a ajouter
	 */
	public void addItem(Item obj){
		this.items.put(obj.getCleanName(), obj);
	}
	
	/***
	 * supprime un item dans la liste des items
	 * @param obj item a supprimer
	 */
	public void removeObject(Item obj) {
		this.items.remove(obj.getCleanName());
	}
	

	/**
	 * getter de items
	 * @return liste des items du lieu
	 */
	public HashMap<String, Item> getItems(){
		return this.items;
	}
	
	/**
	 * ajout d'un PNJ dans la liste
	 * @param npc PNJ a ajouter
	 */
	public void addNPC(NPC npc){
		this.npcs.put(npc.getCleanName(),npc);
	}
	
	/**
	 * ajout d'un Monstre dans la liste
	 * @param monster Monstre a ajouter
	 */
	public void addMonster(Monster monster){
		this.monsters.put(monster.getCleanName(), monster);
	}
	
	/**
	 * Recupere le PNJ portant le nom donn� en parametres
	 * @param name nom a chercher
	 * @return NPC s'il existe, null sinon
	 */
	public NPC getNPC(String name){
		return this.npcs.get(name);
	}
	
	/**
	 * Recupere le Monstre portant le nom donn� en parametres
	 * @param name nom a chercher
	 * @return Monstre s'il existe, null sinon
	 */
	public Monster getMonster(String name){
		return this.monsters.get(name);
	}
	
	/**
	 * getter de npcs
	 * @return la liste des PNJ du lieu
	 */
	public HashMap<String, NPC> getNPCS(){
		return this.npcs;
	}
	
}
