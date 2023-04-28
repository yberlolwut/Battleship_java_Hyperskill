package battleship;

public class RemainingShips {
    int aircraftCarrier;
    int battleship;
    int submarine;
    int cruiser;
    int destroyer;

    public RemainingShips(){
        this.aircraftCarrier = ShipTypes.AIRCRAFTCARRIER.getSize();
        this.battleship = ShipTypes.BATTLESHIP.getSize();
        this.submarine = ShipTypes.SUBMARINE.getSize();
        this.cruiser = ShipTypes.CRUISER.getSize();
        this.destroyer = ShipTypes.DESTROYER.getSize();
    }
}