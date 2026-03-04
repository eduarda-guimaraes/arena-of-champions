package turn_based_battle;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("⚔️ Bem-vindo à Arena dos Campeões!");
        System.out.print("Digite seu nome: ");

        String name = scanner.nextLine();

        Character hero = chooseClass(name);

        startArena(hero);

        scanner.close();
    }

    public static Character chooseClass(String name) {

        int choice = 0;

        while (choice < 1 || choice > 3) {

            System.out.println("\nEscolha sua classe:");
            System.out.println("1 - Guerreiro");
            System.out.println("2 - Mago");
            System.out.println("3 - Arqueiro");

            try {

                choice = scanner.nextInt();

            } catch (InputMismatchException e) {

                System.out.println("❌ Entrada inválida! Digite um número.");
                scanner.nextLine();
                continue;
            }
        }

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

    public static void startArena(Character hero){

        int totalBattles = 3;

        for (int i = 1; i<= totalBattles; i++){

            System.out.println("\n⚔️ Batalha " + i);

            Character enemy = EnemyGenerator.generateEnemy();

            System.out.println("Um inimigo apareceu: " + enemy.name + "!");

            startBattle(hero, enemy);

            if (!hero.isAlive()) {

                System.out.println("\n💀 Você foi derrotado na arena...");
                return;
            }

            System.out.println("\n✅ Você venceu a batalha!");
        }

        System.out.println("\n🏆 Você é o campeão da arena!");
    }

    public static void startBattle(Character hero, Character enemy) {

        int turn = 1;

        while (hero.isAlive() && enemy.isAlive()) {

            hero.defending = false;
            enemy.defending = false;

            System.out.println("\nTurno " + turn);

            showStatus(hero, enemy);

            playerTurn(hero, enemy);

            if (enemy.isAlive()) {
                enemyTurn(hero, enemy);
            }

            turn++;
        }
    }

    public static void showStatus(Character hero, Character enemy) {

        System.out.println(hero.name + " vida: " + hero.health +
                " | " + enemy.name + " vida: " + enemy.health);

    }

    public static void playerTurn(Character hero, Character enemy) {

        int choice = 0;

        while (choice < 1 || choice > 3) {

            System.out.println("\nEscolha sua ação:");
            System.out.println("1 - Atacar");
            System.out.println("2 - Defender");
            System.out.println("3 - Usar Poção ("+ hero.inventory.size() +" restantes)");

            try {

                choice = scanner.nextInt();

            } catch (InputMismatchException e) {

                System.out.println("❌ Entrada inválida! Digite um número.");
                scanner.nextLine();
                continue;
            }

            if (choice == 1) {

                hero.attack(enemy);

            } else if (choice == 2) {

                hero.defend();

            } else if (choice == 3) {

                hero.usePotion();

            } else {

                System.out.println("❌ Escolha uma opção válida!");
            }
        }
    }

    public static void enemyTurn(Character hero, Character enemy) {

        System.out.println("\nTurno do inimigo!");

        double action = Math.random();

        if (action < 0.7) {

            enemy.attack(hero);

        } else {

            enemy.defend();

        }
    }
}