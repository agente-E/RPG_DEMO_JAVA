
public class Player {
    private int score = 0;
    private int experience = 0;
    private int level = 1;
    private int damage = 10;
    private int health;
    private int defense;

    public  Player(int health, int defense) {
        this.health = health;
        this.defense = defense;
    }

    public int getScore() { return score; }
    public int getExperience() { return experience; }
    public int getLevel() { return level; }
    public int getHealth() { return health; }
    public int getDefense() { return defense; }
    public int getDamage() { return damage; }


    public void setScore(int score) { this.score = score; }
    public void setExperience(int experience) { this.experience = experience; }
    public void setLevel(int level) { this.level = level; }
    public void setHealth(int health) { this.health = health; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setDamage(int damage) { this.damage = damage; }
}
