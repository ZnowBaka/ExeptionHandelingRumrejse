public class AlienInvasion {
    private int min = 0;
    private int max = 30;
    private int damage;
    private String discription;

    public int randomDmg() {
        damage = (int) (Math.random() * (max - min) + min);
        return damage;
    }

    public String getDiscription(){
        discription = "You encountered an alien ";
        return discription;
    }


}
