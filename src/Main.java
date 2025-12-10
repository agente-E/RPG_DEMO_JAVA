import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static AtomicInteger score = new AtomicInteger(0);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AtomicBoolean inGame = new AtomicBoolean(true);
        boolean isEnemySpawned= false;

        System.out.println("Monster Slayer");

        System.out.println("How to play:");
        System.out.println("- Random enemies will spawn");
        System.out.println("- Write the enemy name to deal damage");
        System.out.println("- You have 30 seconds to kill everything you can");
        System.out.println("You can write 'exit' to finish whenever you want\n");

        System.out.print("Press enter to continue:");
        scanner.nextLine();

        System.out.println("\nGAME START\n");

        EnemySpawner spawner = new EnemySpawner(inGame);
        Timer timer = new Timer(inGame, 3000);
        Player player = new Player(100, 30);

        spawner.start();
        timer.start();

        while (inGame.get()) {
            var enemy = spawner.getCurrentEnemy();

            if (!spawner.hasSpawned()) continue;

            if (!isEnemySpawned) {
                System.out.println(enemy.getName() + " has spawned!");
                isEnemySpawned = true;
            }



            System.out.print("What will you do?\n[Attack] || [Defense]\nWrite: ");


            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equalsIgnoreCase("attack")) {
                int damage = player.getDamage();
                int defense = enemy.getDefense();
                int enemyHealth = enemy.getHealth();

                double reduction = defense / 100.0;
                double finalDamage = damage * (1 - reduction);

                int newEnemyHealth = enemyHealth - (int)finalDamage;

                enemy.setHealth(newEnemyHealth);

                System.out.println("\nYou've dealt " + finalDamage + " points of damage to the enemy");
                System.out.printf("Enemy current health: " + ShowHealth(enemy.getHealth(), enemy.getMaxHealth())+ "\n\n");
                if (enemy.getHealth() <= 0){
                    System.out.println("You've defeated the enemy, here's your reward:\n");
                }
            }
            else if (input.equalsIgnoreCase("defense")) {
                // TODO Take damage when defending (it's dumb, it's the only way to get damaged because I don't have time to program enemy's turn)
            }
            else if (input.equals("exit")) {
                System.out.println("\nBye bye!");
                inGame.set(false);
            }
        }

        try {
            timer.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // TODO print final stats
        scanner.close();
    }


    public static String ShowHealth(int health, int maxHealth) {
        StringBuilder bar = new StringBuilder();

        int totalBars = maxHealth / 20;
        int filledBars = health / 20;

        for (int i = 0; i < totalBars; i++) {
            bar.append(i < filledBars ? "█" : "░");
        }

        return bar.toString();
    }
}