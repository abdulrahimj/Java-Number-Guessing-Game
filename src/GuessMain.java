
public class GuessMain {
   public static void main(String[] args) {
      Guess model = new Guess(0, 0);
      GameView view = new GameView();
      GameController controller = new GameController(model, view);

      controller.startGame();
   }
}