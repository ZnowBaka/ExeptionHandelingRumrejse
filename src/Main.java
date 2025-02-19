import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

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
            int random = 2;
            switch (random) {
                case 1:
                    SpaceStorm spaceStorm = new SpaceStorm();
                    System.out.println(spaceStorm.getDiscription());
                    System.out.println(spaceStorm.getChoices());
                    int choice = 0;
                    while (waitingForInput) {
                        try {
                            choice = Integer.parseInt(input.next());
                            if (choice == 1 || choice == 2) {
                                waitingForInput = false;
                            } else {
                                System.out.println("You must enter '1' or '2' to select an option");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a Valid Choice:");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    if (choice == 2) {
                        try {
                            spaceStorm.randomDmg();
                            System.out.println("Damage to space storm");
                            spaceShip.takeDamage(spaceStorm.getDamage());
                            System.out.println(spaceShip.getSpaceShipStatus());
                        } catch (CriticalDMGExeption e) {
                            System.out.println("WATCH OUT:" + e.getMessage());
                        }
                        if (spaceShip.getDurability() < 0) {
                            running = false;
                        }
                    } else if (choice == 2) {
                        try {
                            spaceStorm.randomFuel();
                            spaceShip.fuelDrain(spaceStorm.getFueldepletion());
                        } catch (ArithmeticException | CriticalDMGExeption e) {
                            System.out.println(e.getMessage());
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
                    int tradeCost;

                    AlienInvasion alienInvasion = new AlienInvasion();
                    System.out.println(alienInvasion.getDiscription());
                    System.out.println(spaceShip.getSpaceShipStatus());

                    alienInvasion.getTradeOfferCost();
                    if (spaceShip.getDurability() < alienInvasion.getScrapCost()) {
                        System.out.println("You don't have enough durability to trade");
                        // next scenario
                    } else {
                        System.out.println(alienInvasion.getTradeOffer());
                        System.out.println("Are you willing trade that much?\n if you give them less they might get angry\n How much durability you would like to trade");
                        tradeCost = Integer.parseInt(input.next());
                        if (tradeCost <= alienInvasion.getScrapCost() * 0.9) {
                            System.out.println("Your offer has angered the aliens...");
                            break;
                        } else {
                            System.out.println("Success the aliens accepted your trade");
                            spaceShip.setFuelLevel(spaceShip.getFuelLevel() + alienInvasion.getTradedFuel());
                            spaceShip.setDurability(spaceShip.getDurability() - tradeCost);
                        }
                    }


                    break;
                case 3:
                    EngineFailure engineFailure = new EngineFailure();
                    try {
                        System.out.println(engineFailure.getDiscription());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    } finally {
                        System.out.println("Our engineers are trying to restart the engine");
                        boolean success = false;
                        while (!success) {
                            if (engineFailure.engineFailureStatus()) {
                                System.out.println("Success our engineers fixed the engine");
                                success = true;
                            } else if (!engineFailure.engineFailureStatus()) {
                                System.out.println(" Failed our engineers are trying to restart the engine");
                            }
                        }
                    }


                    break;
            }
        }
    }
}