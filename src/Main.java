import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choiceRandomMin = 1;
        int choiceRandomMax = 3;
        boolean waitingForInput = true;
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

        int random = (int) (Math.random() * (choiceRandomMax - choiceRandomMin) + choiceRandomMin);

        switch (random) {
            case 1:
                int choice = Integer.parseInt(null);
                SpaceStorm spaceStorm = new SpaceStorm();
                System.out.println(spaceStorm.getDiscription());
                System.out.println("what will you do");
                System.out.println("Fly through space storm: 1 ");
                System.out.println("Fly around Storm: 2 ");
                while (waitingForInput) {
                    try {
                        choice = input.nextInt();
                        if (choice == 1 || choice == 2) {
                            waitingForInput = false;
                        }
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                if(choice == 1){
                    try {
                        spaceShip.takeDamage(spaceStorm.getDamage());
                    } catch (CriticalDMGExeption e) {
                        e.printStackTrace();
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