import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class GameController {
   private Guess model;
   private GameView view;

   private int humanScore = 0;
   private int compScore = 0;

   //CONSTRUCTOR INJECTION
   //Main method injects the dependencies here
   public GameController(Guess model, GameView view) {
      this.model = model;
      this.view = view;
   }

   //START GAME BY DISPLAYING WELCOME MESSAGE
   //MANAGES OTHER METHODS
   public void startGame() {
      //loadScore
      loadScore();

      view.displayWelcome();

      //Game Menu
      int checkMenu = view.gameMenu();
      if(checkMenu == 1) {
         //NEW GAME: Reset Score
         humanScore = 0;
         compScore = 0;
         System.out.println("\nRESET SCORE. STARTING NEW GAME.");
         System.out.println("=================================");

      } else if (checkMenu == 2) {
         //CONTINUE: Keep loaded score
         System.out.println("\nGAME CONTINUES WITH PREVIOUS SCORE.");
         System.out.println("===================================");

      } else if (checkMenu == 3) {
         //EXIT: Close game
         return;

      } else {
         System.out.println("Invalid input. Enter only numbers from the list.");
         System.out.println("Game Exiting");
         return;
         //END GAME
      }

      //Runs for both new and continue
      playRound();
   }

   //PLAY THE ROUND (this runs for both new and continue)
   private void playRound() {
      //call computer
      computerProcess();

      //call human (End Game if human input is not number)
      int checkReturn = humanProcess();
      if(checkReturn == -1) {
         return; //End Game & Do not proceed to result
      }

      //call result
      result();
   }

   //--- HELPER METHODS ---

   //COMPUTER DATA PROCESS
   private void computerProcess() {
      int compNum = (int) (Math.random() * 10);
      model.setComputer(compNum); //update model
      view.computerDisplay(); //update view
   }

   //HUMAN DATA PROCESS
   private int humanProcess() {
      int humanNum = view.userInput();

      if(humanNum == -1) {
         return humanNum;
      }

      model.setHuman(humanNum); //update model

      return humanNum;
   }

   //DISPLAY CURRENT PLAYING RESULT
   private void result() {
      int computer = model.getComputer();
      int human = model.getHuman();

      boolean isWin = (computer == human);

     String score = view.displayResult(computer, human, isWin);
     saveScore(score);
   }

   //SAVE SCORE TO FILE
   private void saveScore(String score) {

      if (score.equalsIgnoreCase("human")) {
         humanScore += 1;
      } else {
         compScore += 1;
      }

      File path = new File("C:\\Users\\Abdulrahim Jalloh\\Desktop\\gameScore.txt");
      try(BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
         writer.write(String.valueOf(humanScore));
         writer.newLine();
         writer.write(String.valueOf(compScore));

      } catch (IOException e) {
         e.printStackTrace();
      }

      view.scoreBoard(humanScore, compScore);
   }

   //LOAD SCORE BEFORE PLAYING
   private void loadScore() {
      File path = new File("C:\\Users\\Abdulrahim Jalloh\\Desktop\\gameScore.txt");

      if(!path.exists()) {
         humanScore = 0;
         compScore = 0;
         System.out.println("No score found. Starting fresh.");
         return;
      }

      try(BufferedReader br = new BufferedReader(new FileReader(path))) {

         String humanLine = br.readLine();
         String compLine = br.readLine();

         if(humanLine != null && compLine != null) {
            humanScore = Integer.parseInt(humanLine.trim());
            compScore = Integer.parseInt(compLine.trim());
            System.out.println("Score Restored: \nHuman: " + humanScore + "\nComputer: " + compScore);
            System.out.println();
         }

      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (NumberFormatException e) {
         System.out.println("Corrupted score file. Starting fresh");
         humanScore = 0;
         compScore = 0;
      }
   }
}
