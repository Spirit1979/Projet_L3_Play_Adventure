package projet_java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import projet_java.exit.Exit;
import projet_java.item.Food;
import projet_java.item.Item;
import projet_java.monster.Monster;
import projet_java.npc.Merchant;
import projet_java.npc.NPC;
import projet_java.npc.NpcQuest;

/**
 * 
 * Enumeration qui contient toutes les commandes 
 * disponibles pour le joueur
 *
 */
public enum Command {
	help("Affiche ce menu d'aide."),
	go("Permet d'emprunter la sortie specifiee.", "<sortie>"),
	look("Permet d'avoir une liste des sorties et des objets,\n      ou une information detaille sur un objet ou une sortie.", "[sortie:objet]"),
	take("Ramasser l'objet specifie.", "<objet>"),
	use("Essaye d'utiliser l'objet specifie.", "<objet>"),
	talk("Parler a un PNJ.", "<pnj>"),
	attack("Attaquer un monstre.", "<monstre>"),
	infos("Affiche l'argent, la vie et l'inventaire."),
	buy("Acheter un item a un marchand.", "<item>"),
	quit("Quitter la partie."),
	
	
	wrongCommand;
	
	/**
	 * Description du fonctionnement de la commande
	 */
	private String description;
	
	/**
	 * Parametres de la commande
	 */
	private String args;
	
	/**
	 * Decrit si la commande est utilisable par le joueur ou non
	 */
	private boolean useable;
	
	/**
	 * Constructeur commande non utilisable
	 */
	private Command() {
		this.useable = false;
	}
	
	/**
	 * Constructeur commande sans arguments
	 * @param description de la commande
	 */
	private Command(String description) {
		this.useable = true;
		this.args = null;
		this.description = description;
	}
	
	/**
	 * Constructeur commande avec arguments
	 * @param description de la commande
	 * @param args de la commande
	 */
	private Command(String description, String args) {
		this.useable = true;
		this.description = description;
		this.args = args;
	}
	
	/**
	 * Concatene et nettoie les arguments de la commande (["Jean","Francois"] => "jean francois")
	 * @param cmd complete avec les arguments
	 * @return les arguments concat�n�s et nettoy�s
	 */
	private static String concatArgs(String[] cmd) {
		String arg = "";
		for(int i = 1; i < cmd.length; i++) {
			arg += cmd[i] + " ";
		}
		arg = arg.toLowerCase().trim();
		return arg;
	}
	
	/**
	 * Indique si la commande existe ou non
	 * @param cmd String du nom de commande
	 * @return true la commande existe, false sinon
	 */
	public static boolean exists(String cmd){
		try {
			Command.valueOf(cmd);
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Renvoie la commande correspondante � l'�num�ration
	 * @param cmd nom de la commande � r�cup�rer
	 * @return la commande ou Command.wrongCommand si elle n'existe pas
	 */
	public static Command get(String cmd) {
		if(Command.exists(cmd)) {
			return Command.valueOf(cmd);
		} else {
			return Command.wrongCommand;
		}
	}
	
	
	
	/**
	 * Action � effectuer pour la commande look
	 * @param cmd arguments
	 * @param hero qui effectue l'action
	 */
	public static void look(String cmd[], Hero hero){
		Place p = hero.getPlace();
		if(cmd.length == 0){
			p.displayPlace();
		} else {
			if(cmd.length == 1) { //No args
				p.displayExits();
				p.displayObjects();
				p.displayNpc();
				p.displayMonster();
			} else { //Looking for exit or object name.
				HashMap<String, Exit> exits = p.getExits();
				HashMap<String, Item> placeItems = p.getItems();
				
				String arg = concatArgs(cmd);
				
				Exit exit;
				if((exit = exits.get(arg)) != null) {
					exit.display();
					return;
				}
				
				Item obj;
				if((obj = placeItems.get(arg)) != null) {
					obj.display();
					return;
				}
				
				
				List<Merchant> merchants = new ArrayList<>(3);
				
				for(NPC npc : hero.getPlace().getNPCS().values()){
					if(npc instanceof Merchant)
						merchants.add((Merchant) npc);
				}
				
				for(Merchant merch : merchants) {
					if(merch.inventory().isInInventory(arg)) {
						merch.inventory().getItem(arg).display();
						return;
					}
				}
				
				System.out.println("Aucun objet ou sortie portant ce nom present sur le lieu.");
			}
		}
	}
	
	/**
	 * Action � effectuer pour la commande talk
	 * @param cmd arguments de la commande
	 * @param hero qui effectue l'action
	 */
	public static void talk(String[] cmd, Hero hero){
		if(cmd.length < 2) {
			displayHelp();
			return;
		}
		
		String name = concatArgs(cmd);
		
		NPC npc = hero.getPlace().getNPC(name);
		
		if(npc != null){
			if(npc instanceof NpcQuest)
				((NpcQuest)npc).talk(hero);
			else
				npc.talk();
		} else {
			System.out.println("Le personnage n'existe pas.");
		}
	}
	
	/**
	 * Action � effectuer pour la commande attack
	 * @param cmd arguments de la commande
	 * @param hero qui effecute l'action
	 */
	public static void attack(String[] cmd, Hero hero){
		if(cmd.length < 2){
			Command.attack.displayCommandHelp();
			return;
		}
		
		String name = concatArgs(cmd);
		
		Monster monster = hero.getPlace().getMonster(name);
		
		if(monster != null){
			monster.attaque(hero);
		} else {
			System.out.println("Le monstre n'existe pas");
		}
	}
	
	/**
	 * Action � effectuer pour la commande use
	 * @param cmd arguments de la commande
	 * @param hero qui effecute l'action
	 */
	
	public static void use(String[] cmd, Hero hero){
		if(cmd.length < 2){
			Command.use.displayCommandHelp();
			return;
		}
		
		String name = concatArgs(cmd);
		
		if(hero.getInventory().isInInventory(name)){
			Item item = hero.getInventory().getItem(name);
			
			if(item instanceof Food)
			{
				((Food) item).usePotion(hero);	
			}
		}
		else{
			System.out.println("Cet objet n'existe pas");
		}
	}
	
	/**
	 * Action � effectuer pour la commande go
	 * @param cmd arguments de la commande
	 * @param hero qui effecute l'action
	 */
	public static void goTo(String[] cmd, Hero hero) {
		if(cmd.length < 2) {
			Command.go.displayCommandHelp();
			return;
		}
		String inputLocation = concatArgs(cmd);
		
		HashMap<String, Exit> exits = hero.getPlace().getExits();
		Exit exit;
		if((exit = exits.get(inputLocation.toLowerCase().trim())) != null) {
			hero.takeExit(exit);
		} else {
			System.out.println("La sortie n'existe pas.");
		}
	}
	
	/**
	 * Action � effecuter pour la commande help
	 */
	public static void displayHelp() {
		System.out.println("================== Commandes disponibles ==================");
		for(Command cmd : Command.values()) {
			if(cmd.useable) {
				cmd.displayCommandHelp();
			}
		}
		System.out.println("===========================================================");
	}
	
	/**
	 * Affiche l'aide pour une commande en particulier
	 */
	private void displayCommandHelp() {
		System.out.println(this.toString() + (this.args == null ? "" : " " + this.args) + " : " + this.description);
	}
	
	/**
	 * Action � effectuer pour la commande infos
	 * @param hero
	 */
	public static void displayInfos(Hero hero) {
		System.out.println("============= Informations =============");
		System.out.println("Position actuelle : "+hero.getPlace().getName());
		System.out.println("Argent: " + hero.getMoney() + " PO");
		System.out.println("Points de vie: " + hero.getHealth() + " PDV");
		System.out.println("Objet(s): ");
		hero.displayInventory();
		System.out.println("========================================");
	}
	
	/**
	 * Action � effectuer pour la commande take
	 * @param cmd arguments de la commande
	 * @param hero qui effectue l'action
	 */
	public static void take(String[] cmd, Hero hero) {
		if(cmd.length > 1) {
			String arg = concatArgs(cmd);
			
			HashMap<String, Item> objects = hero.getPlace().getItems();
			
			Item obj = objects.get(arg);
			if(obj != null) {
				if(obj.isTakeable()) {
					hero.getPlace().removeObject(obj);
					hero.getInventory().addItem(obj);
					System.out.println("Vous venez de ramassser: " + obj.getName());
				} else {
					System.out.println(obj.getName() + " ne peut pas etre ramasse.");
				}
			} else {
				System.out.println("Aucun objet portant ce nom.");
			}
		} else {
			Command.take.displayCommandHelp();
		}
	}
	
	
	
	
	/**
	 * Action a effectuer pour la commande hero
	 * @param cmd arguments de la commande
	 * @param hero qui effectue l'action
	 */
	public static void buy(String[] cmd, Hero hero) {
		if(cmd.length < 2) {
			Command.buy.displayCommandHelp();
		} else {
			String itemName = concatArgs(cmd);
			
			List<Merchant> merchants = new ArrayList<>(3);
			
			for(NPC npc : hero.getPlace().getNPCS().values()){
				if(npc instanceof Merchant)
					merchants.add((Merchant) npc);
			}
			
			for(Merchant merch : merchants) {
				if(merch.inventory().isInInventory(itemName)) {
					Item item = merch.inventory().getItem(itemName);
					if(hero.pay(item.getPrice())) {
						//merch.inventory().removeItem(item);
						hero.getInventory().addItem(item);
						System.out.println("Vous venez d'acheter : " + item.getName() + " pour " + item.getPrice() + " PO.");
					} else {
						System.out.println("Vous n'avez pas assez d'argent.");
					}
					return;
				}
			}
		}
	}
	
	/**
	 * Action a effectuer pour une command qui n'existe pas
	 */
	public static void wrongCommand() {
		System.out.println("La commande n'existe pas. Tapez 'help' pour avoir la liste des commandes.");
	}
}
