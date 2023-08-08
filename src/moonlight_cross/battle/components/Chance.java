package moonlight_cross.battle.components;
import java.util.Random;

public class Chance {
    static Random rng = new Random();

    //Returns an integer between 'min' and 'max' (Inclusive)
    //+ UNTESTED +
    public static int intRange(int min, int max){
        int result = rng.nextInt(max - min +1) + min;
        return result;
    }

    //Checks if a random number between 1 and 999 (inclusive)
    //falls within range. Essentially a percentage roll, but with 1000
    //So we don't have to use to use Floats, which fuck shit up.
    //+ UNTESTED +
    public static boolean perMilChance(int perMilChance){
        int roll = Chance.intRange(1, 1000);
        return roll < perMilChance;
    }

}
