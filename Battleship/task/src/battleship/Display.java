package battleship;

import java.util.Objects;
import java.util.Scanner;

public class Display {
    public static Field[][] generateField() {
        Field[][] gameField = new Field[12][12];
        for (int row = 0; row < 12; row++) {
            for (int column = 0; column < 12; column++) {
                if (row == 0 && column == 0) {
                    gameField[row][column] = new Field(' ');
                } else if (row == 0) {
                    gameField[row][column] = new Field((char) ((column - 1) + '1'));
                }else if (column == 0) {
                    gameField[row][column] = new Field((char) ((row - 1) + 'A'));
                }else {
                    gameField[row][column] = new Field();
                }

            }

        }
        return gameField;
    }
    public static void fillFogOfWar(Field[][] gameField){
        for (int row = 1; row < 11; row++) {
            for (int column = 1; column < 11; column++) {
                gameField[row][column].displayValue = '~';
            }

        }
    }
    public static void updateWholeField(Field[][] gameField){
        for (int row = 1; row < 11; row++) {
            for (int column = 1; column < 11; column++) {
                gameField[row][column].updateField();
            }

        }
    }
    public static void updateHitMiss(Field[][] gameField){
        for (int row = 1; row < 11; row++) {
            for (int column = 1; column < 11; column++) {
                gameField[row][column].updateHitMiss();
            }

        }
    }

    public static void displayField(Field[][] gameField) {
        for (int row = 0; row < 11; row++) {
            for (int column = 0; column < 11; column++) {
                if (column == 10 && row == 0) {
                    System.out.println("10");
                } else if (column == 10) {
                    System.out.println(gameField[row][column].displayValue + " ");
                } else {
                    System.out.print(gameField[row][column].displayValue + " ");
                }
            }

        }
    }

    public static void placeShip(String name, int numOfCells, Field[][] gameField) {
        while (true) {
            System.out.println();
            System.out.println("Enter the coordinates of the " + name + "(" + numOfCells + "Cells):");
            Scanner sc = new Scanner(System.in);
            String firstCoordinate = sc.next();
            String secondCoordinate = sc.next();
            int firstCoordinateA = (firstCoordinate.charAt(0) - '@');
            int firstCoordinateB = coordinateToInt(firstCoordinate);
            int secondCoordinateA = (secondCoordinate.charAt(0) - '@');
            int secondCoordinateB = coordinateToInt(secondCoordinate);
            if (checkIfValidLength(firstCoordinateA, firstCoordinateB, secondCoordinateA, secondCoordinateB, numOfCells, name)) {
                String direction = checkShipDirection(firstCoordinateA, firstCoordinateB, secondCoordinateA, secondCoordinateB);
                if (checkIfValidLocation(direction, firstCoordinateA, firstCoordinateB, numOfCells, gameField)) {
                    insertShipIntoField(name, direction, firstCoordinateA, firstCoordinateB, numOfCells, gameField);
                    return;
                }
            }
        }
    }

    static int coordinateToInt(String input) {
        if (input.length() == 3) {
            return 10;
        } else {
            return (input.charAt(1) - '0');
        }

    }

    static boolean checkIfValidLength(int fCordA, int fCordB, int sCordA, int sCordB, int numOfCells, String name) {
        int lengthVertical = Math.abs(fCordA - sCordA);
        int lengthHorizontal = Math.abs(fCordB - sCordB);
        if (lengthVertical != 0 && lengthHorizontal != 0) {
            System.out.println("Error! Wrong ship location! Try again:");
            return false;
        } else if (lengthVertical == numOfCells - 1 || lengthHorizontal == numOfCells - 1) {
            return true;
        } else {
            System.out.println("Error! Wrong length of the" + name + "! Try again:");
            return false;
        }
    }

    static String checkShipDirection(int fCordA, int fCordB, int sCordA, int sCordB) {
        int horizontal = fCordA - sCordA;
        int vertical = fCordB - sCordB;
        if (0 > vertical) {
            return "right";
        } else if (0 < vertical) {
            return "left";
        } else if (horizontal > 0) {
            return "up";
        } else {
            return "down";
        }
    }

    static boolean checkIfValidLocation(String dir, int fCordA, int fCordB, int numOfCells, Field[][] gameField) {
        switch (dir) {
            case "right" -> {
                for (int count = 0; count < numOfCells; count++)
                    if (!gameField[fCordA][fCordB + count].isValid ||
                            gameField[fCordA][fCordB + count].isShip ||
                            gameField[fCordA + 1][fCordB + count].isShip ||
                            gameField[fCordA - 1][fCordB + count].isShip ||
                            gameField[fCordA][fCordB + 1 + count].isShip ||
                            gameField[fCordA][fCordB - 1 + count].isShip) {
                        System.out.println("Error! You placed it too close to another one. Try again");
                        return false;
                    }
                return true;
            }
            case "left" -> {
                for (int count = 0; count < numOfCells; count++) {
                    if (!gameField[fCordA][fCordB - count].isValid ||
                            gameField[fCordA][fCordB - count].isShip ||
                            gameField[fCordA + 1][fCordB - count].isShip ||
                            gameField[fCordA - 1][fCordB - count].isShip ||
                            gameField[fCordA][fCordB + 1 - count].isShip ||
                            gameField[fCordA][fCordB - 1 - count].isShip) {
                        System.out.println("Error! You placed it too close to another one. Try again");
                        return false;
                    }
                }
                return true;
            }
            case "up" -> {
                for (int count = 0; count < numOfCells; count++) {
                    if (!gameField[fCordA - count][fCordB].isValid ||
                            gameField[fCordA - count][fCordB].isShip ||
                            gameField[fCordA + 1 - count][fCordB].isShip ||
                            gameField[fCordA - 1 - count][fCordB].isShip ||
                            gameField[fCordA - count][fCordB + 1].isShip ||
                            gameField[fCordA - count][fCordB - 1].isShip) {
                        System.out.println("Error! You placed it too close to another one. Try again");
                        return false;
                    }
                }
                return true;
            }
            case "down" -> {
                for (int count = 0; count < numOfCells; count++) {
                    if (!gameField[fCordA + count][fCordB].isValid ||
                            gameField[fCordA + count][fCordB].isShip ||
                            gameField[fCordA + 1 + count][fCordB].isShip ||
                            gameField[fCordA - 1 + count][fCordB].isShip ||
                            gameField[fCordA + count][fCordB + 1].isShip ||
                            gameField[fCordA + count][fCordB - 1].isShip) {
                        System.out.println("Error! You placed it too close to another one. Try again");
                        return false;
                    }
                }
                return true;
            }
            default -> throw new IllegalStateException("Unexpected value: " + dir);
        }

    }

    static void insertShipIntoField(String name, String dir, int fCordA, int fCordB, int numOfCells, Field[][] gameField) {
        switch (dir) {
            case "right" -> {
                for (int count = 0; count < numOfCells; count++) {
                    gameField[fCordA][fCordB + count].isShip = true;
                    gameField[fCordA][fCordB + count].name = name;
                    gameField[fCordA][fCordB + count].updateField();
                }
            }
            case "left" -> {
                for (int count = 0; count < numOfCells; count++) {
                    gameField[fCordA][fCordB - count].isShip = true;
                    gameField[fCordA][fCordB - count].name = name;
                    gameField[fCordA][fCordB - count].updateField();

                }
            }
            case "up" -> {
                for (int count = 0; count < numOfCells; count++) {
                    gameField[fCordA - count][fCordB].isShip = true;
                    gameField[fCordA - count][fCordB].name = name;
                    gameField[fCordA - count][fCordB].updateField();
                }
            }
            case "down" -> {
                for (int count = 0; count < numOfCells; count++) {
                    gameField[fCordA + count][fCordB].isShip = true;
                    gameField[fCordA + count][fCordB].name = name;
                    gameField[fCordA + count][fCordB].updateField();
                }
            }
        }
    }

    static void takeShot(Field[][] gamefield,RemainingShips ships) {
        System.out.println("Take a shot!");
        while (true) {
            int[] userInputArr = getUserInput();
            if (checkUserInput(userInputArr)) {
                if (gamefield[userInputArr[0]][userInputArr[1]].isShip) {
                    gamefield[userInputArr[0]][userInputArr[1]].isHit = true;
                    for (ShipTypes ship : ShipTypes.values()) {
                        if (Objects.equals(gamefield[userInputArr[0]][userInputArr[1]].name, ship.getName())) {

                            System.out.println("You hit a ship!");
                            shipCounter(gamefield[userInputArr[0]][userInputArr[1]].name,ships);
                            gamefield[userInputArr[0]][userInputArr[1]].updateField();
                            return;
                        }
                    }
                } else {
                    gamefield[userInputArr[0]][userInputArr[1]].isMiss = true;
                    System.out.println("You missed!");
                    gamefield[userInputArr[0]][userInputArr[1]].updateField();
                    return;
                }
            }
        }

    }
    static void shipCounter(String shipType,RemainingShips ships){
        switch (shipType){
            case "Aircraft carrier" ->{
                ships.aircraftCarrier--;
                if (ships.aircraftCarrier == 0) System.out.println("You sank a ship");
            }
            case "Battleship" ->{
                ships.battleship--;
                if (ships.battleship == 0) System.out.println("You sank a ship");
            }
            case "Submarine" ->{
                ships.submarine--;
                if (ships.submarine == 0) System.out.println("You sank a ship");
            }
            case "Cruiser" ->{
                ships.cruiser--;
                if (ships.cruiser == 0) System.out.println("You sank a ship");
            }
            case "Destroyer" ->{
                ships.destroyer--;
                if (ships.destroyer == 0) System.out.println("You sank a ship");
            }
        }

    }

    private static int[] getUserInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        return new int[]{(input.charAt(0) - '@'), coordinateToInt(input)};
    }

    private static boolean checkUserInput(int[] arr) {
        if (0 < arr[0] && arr[0] < 11 && 0 < arr[1] && arr[1] < 11) {
            return true;
        } else {
            System.out.println("Error! You entered the wrong coordinates! Try again: ");
            return false;
        }
    }
    static boolean checkIfGameRunning(RemainingShips ships){
        if(ships.aircraftCarrier > 0){
            return true;
        }else if(ships.battleship > 0){
            return true;
        } else if (ships.submarine > 0) {
            return true;
        } else if (ships.cruiser > 0) {
            return true;
        } else if (ships.destroyer > 0) {
            return true;
        }else {
            return false;
        }

    }
    static void display2PlayerField(Field[][] opponent,Field[][] player){
        updateHitMiss(opponent);
        displayField(opponent);
        fillFogOfWar(opponent);
        System.out.println("---------------------");
        updateWholeField(player);
        displayField(player);
        fillFogOfWar(player);
    }
}