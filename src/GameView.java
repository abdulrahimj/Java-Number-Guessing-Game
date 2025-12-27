import javax.swing.text.View;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameView {

   private Scanner input = new Scanner(System.in);

   public void displayWelcome() {
      System.out.println("WELCOME TO THE GUESSING GAME");
      System.out.println("=============================");
   }

   //GAME OPTION
   public int gameMenu() {
      System.out.println("\n--- MENU ---");
      System.out.println("1.New Game");
      System.out.println("2.Continue Game");
      System.out.println("3.Reset Scoreboard");
      System.out.println("4.Exit Game");

      System.out.print("Choice: ");

      try {
         return input.nextInt();
      } catch (InputMismatchException e) {
         input.nextLine();
         return -1;
      }
   }

   public void computerDisplay() {
      System.out.println("Computer has selected a number (0 - 9).");
   }

   public int userInput() {
      System.out.print("Input your number (0 - 9): ");

      try {
         return input.nextInt();

      } catch (InputMismatchException e) {
         input.nextLine(); //clear remaining invalid input.
         System.out.println("\nInvalid input. Only numbers are allowed.");
         return -1;
      }
   }

   public void displayResult(int comp, int human, boolean isWin) {
      System.out.println("\nNOTICE");
      System.out.println("=======");
      System.out.println("Computer selected: " + comp);
      System.out.println("You selected: " + human);

      if (isWin) {
         System.out.println("Congratulations. You got it!");
      } else {
         System.out.println("OOPS! You missed it.");
      }
   }

   public void scoreBoard(int humanScore, int compScore) {
      System.out.println("\nSCOREBOARD");
      System.out.println("===========");
      System.out.println("Human: " + humanScore);
      System.out.println("Computer: " + compScore);
   }
}
