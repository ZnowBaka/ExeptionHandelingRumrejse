public class SpaceStorm {
    private int min = 0;
    private int max = 30;
    private int damage;
    private String discription;

    public int randomDmg() {
        damage = (int) (Math.random() * (max - min) + min);
        return damage;
    }

    public String getDiscription(){
        discription = "You encountered a space storm ";
        return discription;
    }

    public int getDamage(){
        return damage;
    }


}
