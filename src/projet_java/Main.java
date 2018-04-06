package projet_java;

import java.util.Arrays;
import java.util.Scanner;

import projet_java.exit.Exit;
import projet_java.exit.KeyExit;
import projet_java.item.Food;
import projet_java.item.Item;
import projet_java.item.Key;
import projet_java.monster.Boss;
import projet_java.monster.Monster;
import projet_java.npc.Merchant;
import projet_java.npc.NPC;
import projet_java.npc.NPCQuestExitOpener;
import projet_java.npc.NPCQuestItemGiver;

/**
 * Point de lancement, classe principale
 *
 */
public class Main {

	/**
	 * Point de lancement, boucle principale
	 * @param args
	 */
	public static void main(String[] args) {
		Object tabInit[] = initGame();

		Hero hero = (Hero) tabInit[0];
		Boss boss = (Boss) tabInit[1];
		
		
		introduction(hero); //Game introduction
	
		//Init some vars
		String input;
		Scanner sc = new Scanner(System.in);
		Command cmdEnum;
		String[] cmd = new String[5];
		
		
		//Main loop game
		do {
			//Empty last command
			Arrays.fill(cmd, null);
			
			//Get player input command
			input = sc.nextLine();
			input = input.toLowerCase().trim();
			cmd = input.split(" ");
			
			cmdEnum = Command.get(cmd[0]);
			
			
			//Action to do depending on the input
			switch (cmdEnum) {
				case help:
					Command.displayHelp();
					break;
				case go:
					Command.goTo(cmd, hero);
					break;
				case look:
					Command.look(cmd, hero);
					break;
				case take:
					Command.take(cmd, hero);
					break;
				case use:
					Command.use(cmd, hero);
					break;
				case talk:
					Command.talk(cmd, hero);
					break;
				case infos:
					Command.displayInfos(hero);
					break;
				case buy:
					Command.buy(cmd, hero);
					break;
				case attack:
					Command.attack(cmd, hero);
					break;
				case quit:
					break;
				default:
					Command.wrongCommand();
					break;
			}
		} while (cmdEnum != Command.quit && hero.getHealth() > 0 && boss.isDead() == false);

		if(hero.getHealth() <= 0) {
			System.out.println("Partie perdu");
		}

		if(boss.isDead()){
			System.out.println("L'ogre s'écroule après un long combat, vous libérez votre soeur, encore sous le choc. Partie gagnée.");
		}

		sc.close();
	}

	/**.
	 * Initialisation du jeu (lieux, sorties, pnj, objets, ...)
	 * @return le hero principal du jeu
	 */
	public static Object[] initGame() {
		//Init Hero
		Hero hero = new Hero(null, 30); //Init hero
		hero.getInventory().addItem(new Food("Pomme", "Regenere 3 PDV.", 3, 0));
		
		
		//================Init places===========================
		
		Item corde = new Item("Corde","Une longue corde");
		Item tresor = new Item("Trésor", "Un magnifique coffre fermé");
		Key keyMonturc = new Key("Cle rouille", "Une grosse clé rouillée");
		Key keyBassinVert = (Key) new Key("Petit radeau", "Un petit radeau pour vous déplacer sur l'eau").setPrice(10);

		
		Place monts_d_oree = new Place("Les monts d'oree", "Un homme en panique vous dit que plusieurs gobelins sont passé par " +
				"là il y a peu,\net ont détruit le pont de cordes après leur passage. ");
		
		
		Place bleu_d_oree = new Place("Bleu d'Oree");
		Place val_perdu = new Place("Le Val perdu");
		
		Place les_puys = new Place("Les Puys");
		les_puys.addItem(corde);
		
		//test npcquestitemgiver
		//les_puys.addNPC(new NPCQuestItemGiver("Jean", Arrays.asList("Bonjour, je peux t'echanger une corde contre une pomme.", "Ah, merci, voici ta pomme, la corde va me servir pour etendre mon linge", "Je ne saurais jamais comment te remercier"), corde, new Food("Pomme", "Regenere 1 PDV.", 1), hero.getInventory()));
		

		Place radoute = new Place("Radoute");
		Place sulfurieux = new Place("Sulfurieux");
		Place rustignac = new Place("Rustignac", "Vous arrivez devant une enorme grotte et pouvez apercevoir des centaines de petits yeux jaunes briller au fond de la caverne.");
		Place le_gue_ratelet = new Place("Le Gue Ratelet");
		Place monturc = new Place("Monturc");
		Place mornes_les_eaux = new Place("Morne Les Eaux");
		
		Place le_bassin_vert = new Place("Le Bassin Vert");
		le_bassin_vert.addItem(tresor);
		
		Place eaux_boues = new Place("Eaux Boues");
		Place voie_de_fosse = new Place("Voie De Fosse", "Vous arrivez devant une grotte, de là, vous entendez votre soeur hurlez, vous vous précipitez dans la grotte" +
				" et vous vous retrouvez nez à nez face à un énorme Ogre.");

		//======================================================
	
		
		//===============Init exits=============================
		Exit m_vers_b = new Exit(bleu_d_oree, false);
		monts_d_oree.addExit(m_vers_b);
		monts_d_oree.addExit(new Exit(val_perdu));
		
		/*Test porte bloquee, raison
		val_perdu.addExit(new Exit(les_puys).close().setReason("La route vers %1 est bloquee par une inondation."));*/
		
		
		/*Test KeyExit
		Key key = new Key("Cle", "La belle cle");
		val_perdu.addItem(key);
		val_perdu.addExit(new KeyExit(les_puys,key).setMsg("Vous avez evacue l'eau en ouvrant la bouche d'egout. La sortie vers %1 est maintenant ouverte.").close().setReason("La route vers %1 est bloquee par une inondation."));
		*/
		
		val_perdu.addExit(new Exit(les_puys));
		val_perdu.addExit(new Exit(monts_d_oree));

		Exit exit_val_perdu = new Exit(val_perdu);
		les_puys.addExit(exit_val_perdu);
		
		
		bleu_d_oree.addExit(new Exit(monts_d_oree));
		bleu_d_oree.addExit(new Exit(radoute));
		bleu_d_oree.addExit(new Exit(sulfurieux));

		radoute.addExit(new Exit(bleu_d_oree));
		radoute.addExit(new Exit(le_gue_ratelet));

		sulfurieux.addExit(new Exit(bleu_d_oree));
		sulfurieux.addExit(new Exit(rustignac));
		
		le_gue_ratelet.addExit(new Exit(le_bassin_vert));
		le_gue_ratelet.addExit(new KeyExit(le_bassin_vert, keyBassinVert).setMsg("Vous passez le lac avec votre petit radeau de fortune.").close().setReason("Un grand lac se trouve devant vos yeux, vous auriez peut être besoin d'un radeau pour avancer."));

		le_gue_ratelet.addExit(new Exit(radoute));
		le_gue_ratelet.addExit(new Exit(monturc));

		rustignac.addExit(new Exit(sulfurieux));
		
		le_bassin_vert.addExit(new Exit(mornes_les_eaux));
		le_bassin_vert.addExit(new Exit(le_gue_ratelet));

		monturc.addExit(new Exit(le_gue_ratelet));
		monturc.addExit(new Exit(voie_de_fosse));
		monturc.addExit(new KeyExit(voie_de_fosse, keyMonturc).setMsg("La grande porte en fer s'ouvre dans un enorme bruit").close().setReason("Une énorme porte en fer vous bloque la route."));

		
		mornes_les_eaux.addExit(new Exit(le_bassin_vert));
		mornes_les_eaux.addExit(new Exit(eaux_boues));

		eaux_boues.addExit(new Exit(mornes_les_eaux));
		
		voie_de_fosse.addExit(new Exit(monturc));

		//======================================================
		
		
		//================Init NPC===========================
		monts_d_oree.addNPC(new NPCQuestExitOpener("Didier", Arrays.asList("Des gobelins sont arrivés et ont détruit mon joli pont !\nJ'aurai besoin d'une corde assez longue pour le reconstruire, pourrais tu me ramener ça ?"
				, "Merci ! Je me met au boulot tout de suite !"
				, "Le pont est reconstruit ! Tu peux y aller."), corde, m_vers_b));
		
		bleu_d_oree.addNPC(new NPC("Odile", Arrays.asList("J'ai vu des gobelins aller vers Radoute, c'est bizzare..\n" +
				"normalement l'antre des gobelins dans la région se trouve à Rustignac, direction Sulfurieux.")));
		
		
		eaux_boues.addNPC(new NPCQuestItemGiver("Jean", Arrays.asList("Bonjour, je sais qu'il y a un trésor au bassin-vert, mais je suis trop malade pour aller le récupérer moi même, si tu me le ramène, je te donne cette clé.. qui sait ce qu'elle peut ouvrir", "Merci !!!!! Je suis riche !! Tiens, prends ta clé," +
				" j'en ai pas besoin de toute façon.", "Va t'en, j'ai plus besoin de toi maintenant."), tresor, keyMonturc, hero.getInventory()));
	
		le_gue_ratelet.addNPC(new Merchant("Benoit").addItem(new Food("Potion de santé", "Une potion de santé qui vous rend 5 points de vie", 5, 5)).addItem(keyBassinVert));
		
		
		//================Init Trap===========================
		radoute.addTrap(new Trap(5, "Vous êtes tombé dans un trou fait par les gobelins ! Vous perdez 5 points de vie"));

		//================Init Monster===========================
		rustignac.addMonster(new Monster("Gobelin", 3, 5));
		Boss boss = new Boss("Ogre", 12, 1000, false);
		voie_de_fosse.addMonster(boss);
		
		
		hero.changePlace(val_perdu); // Lieu de depart du jeu/heros
		
		return new Object[]{hero, boss};
	}
	
	/**
	 * Affichage de l'introduction du jeu.
	 * @param hero principal du jeu
	 */
	public static void introduction(Hero hero) {
		System.out.println("Bienvenue dans le monde des trois comtes. Utilisez la commande help pour commencer. \n" +
				"Vous vous réveillez dans le bruit des grognements et des cries " +
				"de votre petite soeur. \nAu loin par la fenêtre, vous apercevez " +
				"des gobelins se diriger vers Les monts d'orées avec votre soeur sur le dos.\n" +
				"Vous attrapez le peu d'équipements que vous avez chez vous, et décidez " +
				"d'aller secourir votre soeur.");
		hero.getPlace().triggerEnter(hero);
	}
}
