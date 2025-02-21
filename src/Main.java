import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choiceRandomMin = 1;
        int choiceRandomMax = 3;
        int journeyMaxEncounters = 5;
        boolean waitingForInput = true;
        int encounterCounter = 0;
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
        while (encounterCounter <= journeyMaxEncounters) {
            if (spaceShip.getDurability() <= 0 || spaceShip.getFuelLevel() <= 0) {
                System.out.println("Your ship is beyond critical condition");
                System.out.println("It is no longer operational, your journey ends here");
                player.setScore(spaceShip.getDurability() * 3 + spaceShip.getFuelLevel() * 2);
                System.out.println("Your score is : " + player.getScore());
                System.exit(0);
            }
            if (encounterCounter == journeyMaxEncounters) {
                System.out.println("Congratulations you have won the space journey!");
                player.setScore(spaceShip.getDurability() * 3 + spaceShip.getFuelLevel() * 2);
                System.out.println("Your score is : " + player.getScore());
                System.exit(0);
            }


            int random = (int) (Math.random() * (choiceRandomMax - choiceRandomMin) + choiceRandomMin);
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
                    if (choice == 1) {
                        try {
                            spaceStorm.randomDmg();
                            System.out.println("Damage to space storm");
                            spaceShip.takeDamage(spaceStorm.getDamage());
                            System.out.println(spaceShip.getSpaceShipStatus());
                            encounterCounter++;
                        } catch (CriticalDMGExeption e) {
                            System.out.println("WATCH OUT:" + e.getMessage());
                        }
                        if (spaceShip.getDurability() < 0) {
                        }
                    } else if (choice == 2) {
                        try {
                            spaceStorm.randomFuel();
                            spaceShip.fuelDrain(spaceStorm.getFueldepletion());
                            encounterCounter++;
                        } catch (ArithmeticException | CriticalDMGExeption e) {
                            System.out.println(e.getMessage());
                        }
                        if (spaceShip.getFuelLevel() < 0) {
                            System.out.println("You don't have enough fuel");
                            System.out.println("GAME OVER");
                        }
                    }
                    waitingForInput = true;
                    break;
                case 2:
                    int tradeCost;
                    boolean tradeIsPossible = false;
                    AlienInvasion alienInvasion = new AlienInvasion();
                    System.out.println(alienInvasion.getDiscription());
                    System.out.println(spaceShip.getSpaceShipStatus());

                    alienInvasion.getTradeOfferCost();// This generates random cost and fuel
                    tradeIsPossible = alienInvasion.isPaymentPossible(spaceShip);// checks if there is enough durability to offer trade

                    if (tradeIsPossible) {
                        int tradeChoice = 0;
                        waitingForInput = true;
                        SpaceStorm alienStorm = new SpaceStorm();
                        System.out.println(alienInvasion.getTradeOffer());// Displays the trade offer

                        while (waitingForInput) {
                            System.out.println("Do you want to trade?");
                            System.out.println("1 for yes or 2 for no");
                            try {
                                tradeChoice = Integer.parseInt(input.next());

                                if (tradeChoice == 1 || tradeChoice == 2) {
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

                        switch (tradeChoice) {
                            case 1:
                                try {
                                    alienInvasion.playerWantsToTrade(input, spaceShip);
                                    spaceShip.fuelDrain(alienStorm.randomFuel());
                                    encounterCounter++;
                                    // Next scenario
                                } catch (ArithmeticException | InvalidTradeException | CriticalDMGExeption e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 2:
                                System.out.println("You fly off without trading ");
                                try {
                                    spaceShip.fuelDrain(alienStorm.randomFuel());
                                    encounterCounter++;
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            default:
                                System.out.println("Enter a valid choice");
                        }
                    } else {
                        System.out.println("Since your ship is in critical condition");
                        System.out.println("You fly off without trading");
                        // Next scenario
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
                                encounterCounter++;
                                success = true;
                            } else if (!engineFailure.engineFailureStatus()) {
                                System.out.println(" Failed our engineers are trying to restart the engine");
                                encounterCounter++;
                            }
                        }
                    }
                    break;
            }
        }
    }
}