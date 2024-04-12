
import battleship.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * A Sample random shooter - Takes no precaution on double shooting and has no strategy once
 * a ship is hit - This is not a good solution to the problem!
 *
 * @author mark.yendt@mohawkcollege.ca (Dec 2021)
 */
public class BetterBot implements BattleShipBot {
    private int gameSize;
    private BattleShip2 battleShip;
    private Random random;
    static ArrayList<Point> shotsFired = new ArrayList<>();
    public long count = 0;

    /**
     * Constructor keeps a copy of the BattleShip instance
     * Create instances of any Data Structures and initialize any variables here
     *
     * @param b previously created battleship instance - should be a new game
     */

    @Override
    public void initialize(BattleShip2 b) {
        battleShip = b;
        gameSize = b.BOARD_SIZE;

        // Need to use a Seed if you want the same results to occur from run to run
        // This is needed if you are trying to improve the performance of your code

        random = new Random(0xAAAAAAAA);   // Needed for random shooter - not required for more systematic approaches
    }

    /**
     * Create a random shot and calls the battleship shoot method
     * Put all logic here (or in other methods called from here)
     * The BattleShip API will call your code until all ships are sunk
     */

    @Override
    public void fireShot() {

        boolean try_again = true;
        boolean hit = false;
        int x = 0, y = 0;
        while (try_again) {
            for (int i = 0; i < gameSize; i++) {
                x = i;
                for (int j = 0; j < gameSize; j++) {
                    y = j;

                    Point fire = new Point(x, y);
                    if (shotsFired == null) {
                        shotsFired.add(fire);
                        try_again = false;
                    } else {
                        if (shotsFired.contains(fire)) {
                            try_again = true;
                        } else {
                            shotsFired.add(fire);
                            try_again = false;
                        }
                        hit = battleShip.shoot(fire);
                        if (hit) {
                            count++;
                            if (count >= 24) {
                                shotsFired = new ArrayList<>();
                            }
                        }
                    }
                }
            }
        }
        // Will return true if we shot a ship
    }


    /**
     * Authorship of the solution - must return names of all students that contributed to
     * the solution
     *
     * @return names of the authors of the solution
     */

    @Override
    public String getAuthors() {
        return "Andrew Rerecich, Nicolas Chaisson";
    }
}
