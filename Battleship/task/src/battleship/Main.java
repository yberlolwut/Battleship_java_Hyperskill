package battleship;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Field[][] gameFieldP1 = Display.generateField();
        Field[][] gameFieldP2 = Display.generateField();
        RemainingShips shipsP1 = new RemainingShips();
        RemainingShips shipsP2 = new RemainingShips();
        placeShips(gameFieldP1);
        waitEnterInputToContinue();
        placeShips(gameFieldP2);
        System.out.println("The game starts!");
        System.out.println();
        while (true) {
            waitEnterInputToContinue();
            Display.display2PlayerField(gameFieldP2, gameFieldP1);
            System.out.println("Player 1, it's your turn: ");
            Display.takeShot(gameFieldP2, shipsP2);
            if (!Display.checkIfGameRunning(shipsP2)) {
                System.out.println("You sank the last ship. You won. Congratulations!");
                break;
            }
            waitEnterInputToContinue();
            Display.display2PlayerField(gameFieldP1, gameFieldP2);
            System.out.println("Player 2, it's your turn: ");
            Display.takeShot(gameFieldP1, shipsP1);
            if (!Display.checkIfGameRunning(shipsP1)) {
                System.out.println("You sank the last ship. You won. Congratulations!");
                break;
            }
        }

    }

    private static void placeShips(Field[][] gameField) {
        Display.displayField(gameField);
        for (ShipTypes ship : ShipTypes.values()) {
            Display.placeShip(ship.getName(), ship.getSize(), gameField);
            Display.displayField(gameField);
            Display.fillFogOfWar(gameField);
        }
    }

    private static void waitEnterInputToContinue() {
        System.out.println("Press Enter and pass the move to another player");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }
}



