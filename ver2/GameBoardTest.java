import javax.swing.*;

public class GameBoardTest extends JFrame {
   public static void main(String[] args) {
      new GameBoardTest();
   }
   
   public GameBoardTest() {
      setSize(866, 638);   
      
      add(new GameBoard());
      
      setVisible(true);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }
}
