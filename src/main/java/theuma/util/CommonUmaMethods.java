package theuma.util;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theuma.powers.UmaMoodPower;

public class CommonUmaMethods {

    public static boolean playerIsJoyful(AbstractPlayer p){
        return getCurrentPlayerMood(p) > 0;
    }

    public static boolean playerIsSorrowful(AbstractPlayer p){
        return getCurrentPlayerMood(p) < 0;
    }

    public static int getCurrentPlayerMood(AbstractPlayer p){

        AbstractPower moodPower = p.getPower(UmaMoodPower.ID);

        if (moodPower != null) return moodPower.amount;

        return 0;
    }
}
