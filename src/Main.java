import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static char[][] arrayField = new char[][]{
            {'-', '-', '-', '-', '-', '-', '-'},
            {'|', '7', '|', '8', '|', '9', '|'},
            {'-', '-', '-', '-', '-', '-', '-'},
            {'|', '4', '|', '5', '|', '6', '|'},
            {'-', '-', '-', '-', '-', '-', '-'},
            {'|', '1', '|', '2', '|', '3', '|'},
            {'-', '-', '-', '-', '-', '-', '-'}};
    private static char[][] arrayFieldResult = new char[3][3];

    private static ArrayList player = new ArrayList();
    private static ArrayList computer = new ArrayList();
    private static Scanner scan;
    private static Random random;
    private static int playerPosition;
    private static int computerPosition;
    private static char symbol = ' ';

    public static void printTableField(char[][] arrayField) {
        for (int i = 0; i < arrayField.length; i++) {
            for (int j = 0; j < arrayField.length; j++) {
                System.out.print(arrayField[i][j]);

            }
            System.out.println();
        }
        initGameField();

    }

    public static void initGameField() {
        for (int i = 0; i < arrayFieldResult.length; i++) {
            for (int j = 0; j < arrayFieldResult.length; j++) {
                arrayFieldResult[i][j] = ' ';
            }

        }
    }

    public static void gameLogic(int position, String user) {

        if (user.equals("player")) {
            symbol = 'x';
            player.add(position);
        } else if (user.equals("computer")) {
            symbol = 'o';
            computer.add(position);
        }

        switch (position) {
            case 1:
                arrayFieldResult[2][0] = symbol;
                break;
            case 2:
                arrayFieldResult[2][1] = symbol;
                break;
            case 3:
                arrayFieldResult[2][2] = symbol;
                break;
            case 4:
                arrayFieldResult[1][0] = symbol;
                break;
            case 5:
                arrayFieldResult[1][1] = symbol;
                break;
            case 6:
                arrayFieldResult[1][2] = symbol;
                break;
            case 7:
                arrayFieldResult[0][0] = symbol;
                break;
            case 8:
                arrayFieldResult[0][1] = symbol;
                break;
            case 9:
                arrayFieldResult[0][2] = symbol;
                break;
        }

    }

    public static void main(String[] args) {

        System.out.println("Start Game!" + "\n");
        System.out.println("Use the following mapping table to specify a cell using numbers from 1 to 9:" + "\n");
        printTableField(arrayField);

        while (true) {
            scan = new Scanner(System.in);

            playerPosition = scan.nextInt();
            while (player.contains(playerPosition) || computer.contains(playerPosition)) {
                System.out.println("Клетка занята. Введи другое число");
                playerPosition = scan.nextInt();
            }

            gameLogic(playerPosition, "player");

            random = new Random();
            computerPosition = random.nextInt(9) + 1;
            while (computer.contains(computerPosition) || player.contains(computerPosition)) {
                System.out.println("");
                computerPosition = random.nextInt(9) + 1;
            }
            gameLogic(computerPosition, "computer");
            printArray();

            if (winnerWinnerChickenDinner('x')) {
                System.out.println("You WIN!");
                System.out.print("\n-------------\n");
                System.out.println("GAME OVER!");
                break;
            } else if (winnerWinnerChickenDinner('o')) {
                System.out.println("Computer WIN!");
                System.out.print("\n-------------\n");
                System.out.println("GAME OVER!");
                break;
            }
        }
    }

    public static void printArray() {
        System.out.print("\n-------------\n");

        for (int i = 0; i < arrayFieldResult.length; i++) {
            for (int j = 0; j < arrayFieldResult.length; j++) {
                System.out.print("| " + arrayFieldResult[i][j] + " ");
            }
            System.out.print("|\n-------------\n");
        }
    }

    public static boolean winnerWinnerChickenDinner(char symbol) {
        for (int i = 0; i < 3; i++)
            if ((arrayFieldResult[i][0] == symbol && arrayFieldResult[i][1] == symbol &&
                    arrayFieldResult[i][2] == symbol) ||
                    (arrayFieldResult[0][i] == symbol && arrayFieldResult[1][i] == symbol &&
                            arrayFieldResult[2][i] == symbol))
                return true;
        if ((arrayFieldResult[0][0] == symbol && arrayFieldResult[1][1] == symbol &&
                arrayFieldResult[2][2] == symbol) ||
                (arrayFieldResult[2][0] == symbol && arrayFieldResult[1][1] == symbol &&
                        arrayFieldResult[0][2] == symbol))
            return true;
        return false;
    }
}
