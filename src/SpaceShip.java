public class SpaceShip {
    private String shipName;
    private int fuelLevel;
    private int durability;

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(int fuelLevel) {
        this.fuelLevel = fuelLevel;
    }


    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public SpaceShip(String shipName, int fuelLevel, int durability) {
        this.shipName = shipName;
        this.fuelLevel = fuelLevel;
        this.durability = durability;
    }
    public String getSpaceShipStatus() {
        String message = this.shipName + " has fuel level " + this.fuelLevel + " and durability " + this.durability;
        return message;
    }
    public int takeDamage(int damage) throws CriticalDMGExeption {

        this.durability -= damage;

        if (this.durability < 0) {
            return 0;
        } else if (this.durability <= 20) {
            throw new CriticalDMGExeption("your ship is in critical condition");
        } else {
            return 1;
        }

    }
    public int fuelDrain(int fuel) {
        this.fuelLevel -= fuel;
        if (this.fuelLevel < 0) {
            return 0;
        }
        return 1;
    }
}
