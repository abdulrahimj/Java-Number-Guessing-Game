public class Guess {
   private int computer;
   private int human;

   public Guess (int computer, int human) {
      this.computer = computer;
      this.human = human;
   }

   public int getComputer() {
      return computer;
   }

   public void setComputer(int computer) {
      this.computer = computer;
   }

   public int getHuman() {
      return human;
   }

   public void setHuman(int human) {
      this.human = human;
   }
}
