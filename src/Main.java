import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choiceRandomMin = 1;
        int choiceRandomMax = 3;
        boolean waitingForInput = true;
        boolean running = true;
        SpaceShip spaceShip = null;
        Player player = null;
        System.out.println("Welcome to Space Journey" + "\n" + "please enter your name");

        try {
            String name = input.nextLine();
            int score = 0;
            player = new Player(name, score);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Enter a ship name");

        try {
            String name = input.nextLine();
            int fuel = 100;
            int durability = 100;
            spaceShip = new SpaceShip(name, fuel, durability);
        } catch (Exception e) {
            System.out.println("ship naming fault" + e.getMessage());
        }
        System.out.println(spaceShip.getSpaceShipStatus());
        while (running) {
            //int random = (int) (Math.random() * (choiceRandomMax - choiceRandomMin) + choiceRandomMin);
            int random = 1;
            switch (random) {
                case 1:
                    SpaceStorm spaceStorm = new SpaceStorm();
                    System.out.println(spaceStorm.getDiscription());
                    System.out.println("what will you do");
                    System.out.println("Fly through space storm: 1 ");
                    System.out.println("Fly around Storm: 2 ");
                    int choice = Integer.parseInt(input.next());
                    while (waitingForInput) {
                        try {
                            choice = Integer.parseInt(input.next());
                            if (choice == 1 || choice == 2) {
                                waitingForInput = false;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(e.getMessage());
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    if (choice == 1) {
                        try {
                            spaceStorm.randomDmg();
                            System.out.println("Damage to space storm");
                            spaceShip.takeDamage(spaceStorm.getDamage());
                            System.out.println(spaceShip.getSpaceShipStatus());
                        } catch (CriticalDMGExeption e) {
                            System.out.println("WATCH OUT:"+ e.getMessage());
                        }
                        if (spaceShip.getDurability() < 0){
                            running = false;
                        }
                    } else if (choice == 2) {
                        try {
                            spaceStorm.randomFuel();
                            spaceShip.fuelDrain(spaceStorm.getFueldepletion());
                        } catch (ArithmeticException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Something went wrong");
                        }
                        if (spaceShip.getFuelLevel() < 0) {
                            System.out.println("You don't have enough fuel");
                            System.out.println("GAME OVER");
                            running = false;
                        }
                    }
                    waitingForInput = true;
                    break;
                case 2:
                    AlienInvasion alienInvasion = new AlienInvasion();
                    System.out.println(alienInvasion.getDiscription());

                    break;
                case 3:
                    EngineFailure engineFailure = new EngineFailure();
                    System.out.println(engineFailure.getDiscription());

                    break;
            }
        }
    }
}