package projet_java.monster;

import projet_java.Hero;


public class Monster {
	
	protected String name ;
	protected int damage;
	protected int money;

	public Monster(String name , int damage ,int money){
		this.name = name;
		this.damage = damage;
		this.money = money;
	}
	
	public String getCleanName() {
		return this.name.toLowerCase().trim();
	}

	public void attaque(Hero hero){
		hero.looseHealth(damage);
		hero.credit(money);		
		
		if(hero.getHealth() > 0){
			System.out.println("Vous avez tué le "+this.name+" et avez gagné "+this.money+" pièces d'or et avez perdu "+this.damage+" points de vie.");
		}
		else{
			System.out.println("Vous avez perdu "+this.damage+" points de vie et êtes mort.");
		}
	}	
	
	public String getName(){
		return name;
	}
}
