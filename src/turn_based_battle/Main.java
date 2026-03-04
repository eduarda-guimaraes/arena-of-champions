package turn_based_battle;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("⚔️ Bem-vindo à Arena dos Campeões!");
        System.out.print("Digite seu nome: ");

        String name = scanner.nextLine();

        Character hero = chooseClass(name);

        Character enemy = new Character("Orc", 80, 15, 3);

        startBattle(hero, enemy);

        scanner.close();
    }

    public static Character chooseClass(String name) {

        System.out.println("\nEscolha sua classe:");
        System.out.println("1 - Guerreiro");
        System.out.println("2 - Mago");
        System.out.println("3 - Arqueiro");

        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("Você escolheu Guerreiro!");
            return new Warrior(name);
        }
        else if (choice == 2) {
            System.out.println("Você escolheu Mago!");
            return new Mage(name);
        }
        else {
            System.out.println("Você escolheu Arqueiro!");
            return new Archer(name);
        }
    }

    public static void startBattle(Character hero, Character enemy) {

        int turn = 1;

        System.out.println("\nUm inimigo apareceu: " + enemy.name);

        while (hero.isAlive() && enemy.isAlive()) {

            System.out.println("\nTurno " + turn);

            showStatus(hero, enemy);

            playerTurn(hero, enemy);

            if (enemy.isAlive()) {
                enemyTurn(hero, enemy);
            }

            turn++;
        }

        endGame(hero);
    }

    public static void showStatus(Character hero, Character enemy) {

        System.out.println("Sua vida: " + hero.health + " | Vida do inimigo: " + enemy.health);

    }

    public static void playerTurn(Character hero, Character enemy) {

        System.out.println("\nEscolha sua ação:");
        System.out.println("1 - Atacar");
        System.out.println("2 - Defender");
        System.out.println("3 - Usar Poção ("+ hero.inventory.size() +" restantes)");

        int choice = scanner.nextInt();

        if (choice == 1) {
            hero.attack(enemy);
        }
        else if (choice == 2) {
            System.out.println("Você se defendeu e reduziu o dano neste turno!");
        } else if (choice == 3) {
            hero.usePotion();;
        } else {
            System.out.println("Opção inválida!");
        }
    }

    public static void enemyTurn(Character hero, Character enemy) {
        System.out.println("\nTurno do inimigo!");
        enemy.attack(hero);
    }

    public static void endGame(Character hero) {

        if (hero.isAlive()) {
            System.out.println("\n🏆 Você venceu a batalha!");
        }
        else {
            System.out.println("\n💀 Você foi derrotado...");
        }
    }
}
