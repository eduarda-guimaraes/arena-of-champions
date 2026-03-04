package turn_based_battle;

import java.util.ArrayList;
import java.util.List;

public class Character {
    String name;
    int health;
    int attack;
    int defense;
    List<String> inventory;

    public Character (String name, int health, int attack, int defense){
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;

        inventory = new ArrayList<>();

        inventory.add("Potion");
        inventory.add("Potion");
        inventory.add("Potion");
    }

    public void attack (Character target){
        int damage = this.attack - target.defense;

        if (damage < 0){
            damage = 0;
        }
        target.health-=damage;

        System.out.println(this.name +" atacou "+ target.name +" e causou "+ damage + " de dano!");
    }

    public void usePotion(){
        if(inventory.contains("Potion")){
            inventory.remove("Potion");

            health+=20;

            System.out.println("Você usou uma poção e recuperou 20 de vida!");
            System.out.println("Vida atual: " + health);
        }
        else{
            System.out.println("Você não tem mais poções!");
        }
    }

    public boolean isAlive(){
        return health > 0;
    }
}
