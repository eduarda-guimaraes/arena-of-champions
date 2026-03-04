package turn_based_battle;

import java.util.Random;

public class EnemyGenerator {

    static Random random = new Random();

    public static Enemy generateEnemy() {

        String[] names = {
                "Zargor the Orc",
                "Shadow Goblin",
                "Dark Skeleton",
                "Cursed Knight"
        };

        String name = names[random.nextInt(names.length)];

        int health = 60 + random.nextInt(40);
        int attack = 10 + random.nextInt(10);
        int defense = 3 + random.nextInt(5);

        return new Enemy(name, health, attack, defense);
    }
}