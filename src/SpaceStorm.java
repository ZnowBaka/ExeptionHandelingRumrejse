public class SpaceStorm {
    private int min = 0;
    private int max = 30;
    private int damage;
    private String discription;
    private int fueldepletion;

    public int randomDmg() {
        damage = (int) (Math.random() * (max - min) + min);
        return damage;
    }

    public int randomFuel() {
        fueldepletion = (int) (Math.random() * (max - min) + min);
        return fueldepletion;
    }

    public String getDiscription(){
        discription = "You encountered a space storm ";
        return discription;
    }

    public int getDamage(){
        return damage;
    }

    public int getFueldepletion(){
        return fueldepletion;
    }



}
