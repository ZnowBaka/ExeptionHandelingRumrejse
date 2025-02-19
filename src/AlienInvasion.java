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
        scrapCost = randomDmg() / 4;
        tradedFuel = randomDmg() / 2;
    }

    public String getTradeOffer() {
        return tradeOffer = ("They offer " + tradedFuel + " fuel, if you let them scrape " + scrapCost + " durability of your ships hull");
    }

    public int getScrapCost() {
        return scrapCost;
    }

    public int getTradedFuel() {
        return tradedFuel;
    }


}
