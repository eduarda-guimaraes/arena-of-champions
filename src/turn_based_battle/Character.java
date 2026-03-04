package turn_based_battle;

import java.util.ArrayList;
import java.util.List;

public class Character {

    protected String name;
    protected int health;
    protected int attackPower;
    protected int defense;
    protected boolean defending;
    protected List<String> inventory;

    public Character(String name, int health, int attackPower, int defense) {

        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.defense = defense;
        this.defending = false;

        this.inventory = new ArrayList<>();

        inventory.add("Potion");
        inventory.add("Potion");
        inventory.add("Potion");
    }

    public boolean isAlive() {

        return health > 0;
    }

    public void attack(Character target) {

        int damage = attackPower - target.defense;

        if (damage < 1) {
            damage = 1;
        }

        System.out.println(name + " atacou " + target.name + " causando " + damage + " de dano!");

        target.takeDamage(damage);
    }

    public void defend() {

        defending = true;

        System.out.println(name + " está se defendendo!");
    }

    public void takeDamage(int damage) {

        if (defending) {
            damage = damage / 2;
        }

        health -= damage;

        if (health < 0) {
            health = 0;
        }

        System.out.println(name + " recebeu " + damage + " de dano!");
    }

    public void usePotion() {

        if (inventory.size() > 0) {

            inventory.remove(0);

            health += 20;

            System.out.println(name + " usou uma poção e recuperou 20 de vida!");

        } else {

            System.out.println("Você não tem mais poções!");
        }
    }
}