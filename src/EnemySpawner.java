import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

class EnemySpawner extends Thread {
    private AtomicBoolean inGame;
    private Random random;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Enemy currentEnemy = new Enemy();
    private boolean spawned = false;

    public EnemySpawner(AtomicBoolean inGame) {
        this.inGame = inGame;
        this.random = new Random();
        setDaemon(true);

        // ! This is a wrong way to do this, hardcoded af, but I'm not downloading a parser to read files
        Enemy snake = new Enemy("Snake", 50, 10, 5, 0.8f, 20);
        Enemy goblin = new Enemy("Goblin", 200, 10, 10, 0.5f, 50);
        Enemy spider = new Enemy("Spider", 25, 3, 2,  0.6f, 10);
        Enemy fish = new Enemy("KILLER FISH FROM SAN DIEGO", 1, 0, 0, 0.1f, 100);
        enemies.add(snake);
        enemies.add(goblin);
        enemies.add(spider);
        enemies.add(fish);
    }

    public Enemy getCurrentEnemy() { return this.currentEnemy; }
    public boolean hasSpawned() { return this.spawned; }

    @Override
    public void run() {
        try {
            while (inGame.get()) {
                sleep(random.nextInt(3000) + 2000);
                if (inGame.get()) {
                    if (currentEnemy == null || currentEnemy.getHealth() <= 0) {

                        spawned = false;

                        // Spawn an enemy by its spawn rate
                        float totalSpawnRate = 0;

                        // Sum all rates
                        for (Enemy enemy : enemies) {
                            totalSpawnRate += enemy.getSpawnRate();
                        }

                        // Normalize the chances by 1
                        float spawnChance = random.nextFloat() * totalSpawnRate;
                        float accumulatedChance = 0;

                        // Select the enemy randomly
                        for (Enemy enemy : enemies) {

                            // Check if the generated number is smaller than the chance and then, select the enemy
                            accumulatedChance += enemy.getSpawnRate();
                            if (spawnChance <= accumulatedChance) {
                                currentEnemy = enemy.clone();
                                break;
                            }
                        }
                        spawned = true;
                        continue;
                    };
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
