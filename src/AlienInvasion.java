import java.util.Scanner;

public class AlienInvasion {
    private int min = 1;
    private int max = 30;
    private int damage;
    private String discription;
    private int scrapCost = 0;
    private int tradedFuel = 0;
    private String tradeOffer;

    public int randomDmg() {
        damage = (int) (Math.random() * (max - min) + min);
        return damage;
    }

    public String getDiscription() {
        discription = """
                You encountered an alien!
                They seem willing to trade with you
                """;
        return discription;
    }

    public void getTradeOfferCost() throws IllegalArgumentException {
        scrapCost = randomDmg() / 2;
        tradedFuel = randomDmg();
    }

    public String getTradeOffer() {
        return tradeOffer = ("They offer " + tradedFuel + " fuel, if you let them scrape " + scrapCost + " durability of your ships hull");
    }

    public boolean isPaymentPossible(SpaceShip spaceShip) {
        if (spaceShip.getDurability() < spaceShip.getCriticalDurabilityLevel()) {
            System.out.println("Your ships hull is in critical condition");
            System.out.println("You don't have enough durability to trade");
            return false;
        }
        return true;
    }

    public void playerWantsToTrade(Scanner input, SpaceShip spaceShip) throws InvalidTradeException {
        System.out.println("How much durability you would like to trade?");
        boolean tradeAccepted = false;
        int tradeCost = 0;

        try {
            tradeCost = Integer.parseInt(input.next());
            tradeAccepted = isTradeAccepted(tradeCost);

            if (!tradeAccepted) {
                System.out.println("Your offer has angered the aliens...");
                System.out.println("They shoot your shields once as a warning before leaving");

            }
            else {
                System.out.println("Success the aliens accepted your trade");
                    spaceShip.setFuelLevel(spaceShip.getFuelLevel() + this.getTradedFuel());
                    spaceShip.takeDamage(tradeCost);
            }

        } catch (InvalidTradeException | CriticalDMGExeption e) {
            throw new InvalidTradeException("Your offer was not accepted");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isTradeAccepted(int tradeCost) {
        if (tradeCost <= this.getScrapCost() * 0.9 || tradeCost >= this.getScrapCost() * 1.5) {
            return false;
        } else {
            return true;
        }
    }

    public int getScrapCost() {
        return scrapCost;
    }

    public int getTradedFuel() {
        return tradedFuel;
    }


}
