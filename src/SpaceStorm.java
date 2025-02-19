import java.util.Scanner;

public class SpaceStorm {
    private final int min = 0;
    private final int max = 30;

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

    public String getDiscription() {
        discription = "You encountered a space storm ";
        return discription;
    }

    public String getChoices() {
        String choices = """
                what will you do?
                Fly through space storm: 1
                Fly around Storm: 2
                """;
        return choices;
    }


    public int getDamage() {
        return damage;
    }

    public int getFueldepletion() {
        return fueldepletion;
    }


}
