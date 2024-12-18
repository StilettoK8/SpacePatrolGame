import java.util.ArrayList;

public class Main
{

    public Main()
    {
        //new Ship(ShipType.PLAYER);
        Ship playerShip = new Ship(ShipType.PLAYER);
        ArrayList<ArrayList<Character>> shipLayout = playerShip.getShip();
        int size = shipLayout.size();
        for (int i = 0; i <= size;i++){
            ArrayList<Character> row = shipLayout.get(i);

            for (int j = 0; j < row.size(); j++) {
                System.out.print(row.get(j)); // Print each character in the row
            }
            System.out.println(); // Move to the next line after each row
        }

    }

}
