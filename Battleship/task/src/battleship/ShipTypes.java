package battleship;

public enum ShipTypes {
    AIRCRAFTCARRIER("Aircraft carrier",5),
    BATTLESHIP("Battleship",4),
    SUBMARINE("Submarine",3),
    CRUISER("Cruiser",3),
    DESTROYER("Destroyer",2);

    private final int size;
    private final String name;
    ShipTypes(String name, int numOfCells) {
        this.name = name;
        this.size = numOfCells;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }
}
