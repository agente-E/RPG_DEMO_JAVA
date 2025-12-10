public class Enemy {
    private String name;
    private int health;
    private int damage;
    private int defense;
    private float spawnRate;
    private float expReward;
    private int maxHealth;
    public Enemy() {}

    public Enemy(String name, int health, int damage, int defense, float spawnRate, float expReward) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
        this.defense = defense;
        this.spawnRate = spawnRate;
        this.expReward = expReward;
    }

    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getDamage() { return damage; }
    public int getDefense() { return defense; }
    public float getSpawnRate() { return spawnRate; }
    public float getExpReward() { return expReward; }
    public int getMaxHealth() { return maxHealth; }

    public void setHealth(int health) { this.health = health; }

    @Override
    public Enemy clone() {
        return new Enemy(this.name, this.health, this.maxHealth, this.damage, this.defense, this.spawnRate);
    }
}
