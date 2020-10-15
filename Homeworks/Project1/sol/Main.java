import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player playerOne = new AIPlayer();
        Player playerTwo = new AIPlayer();

            System.out.println();
            System.out.println("-- Final Board Status --");
            ticTacToe.observe().showBoard();
            System.out.println("--------------------------");

            switch (ticTacToe.observe().getWinnerStatus()) {
                case PLAYER_ONE -> {
                    playerOne.win();
                    System.out.printf("%s has won!\n", playerOne.name);
                }
                case PLAYER_TWO -> {
                    playerTwo.win();
                    System.out.printf("%s has won!\n", playerTwo.name);
                }
                default -> System.out.println("The game ended tie.");
            }
            System.out.println();
        }

        System.out.println("-- Final Score --");
        System.out.printf(" %s %d : %d %s\n", playerOne.getName(), playerOne.getTotalWin(),
                                            playerTwo.getTotalWin(), playerTwo.getName());
        System.out.println("-----------------");
        System.out.println();
    }
}
