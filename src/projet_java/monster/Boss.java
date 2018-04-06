package projet_java.monster;

import projet_java.Hero;

public class Boss extends Monster{

	boolean dead;
	
	public Boss(String name, int damage, int money, boolean dead) {
		super(name, damage, money);
		this.dead = dead;
	}
	
	@Override
	public void attaque(Hero hero){
		hero.looseHealth(damage);
		hero.credit(money);		
		
		if(hero.getHealth() > 0){
			System.out.println("Vous avez tué le "+this.name+" et avez gagné "+this.money+" pièces d'or et avez perdu "+this.damage+" points de vie.");
			this.setIsDead(true);
		}
		else{
			System.out.println("Vous avez perdu "+this.damage+" points de vie et êtes mort.");
		}
	}	
	
	public boolean isDead(){
		return this.dead;
	}
	
	public void setIsDead(boolean dead){
		this.dead = dead;
	}
	
}
