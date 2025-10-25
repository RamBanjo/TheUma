package theuma.util;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theuma.CharacterFile;
import theuma.powers.UmaMoodPower;

public class CommonUmaMethods {

    public static boolean playerIsJoyful(AbstractCreature p){
        return getCurrentPlayerMood(p) > 0;
    }

    public static boolean playerIsSorrowful(AbstractCreature p){
        return getCurrentPlayerMood(p) < 0;
    }

    public static int getCurrentPlayerMood(AbstractCreature p){

        AbstractPower moodPower = p.getPower(UmaMoodPower.ID);

        if (moodPower != null) return moodPower.amount;

        return 0;
    }

    public static boolean cardIsNonUma(AbstractCard c){
        return c.color != CharacterFile.Enums.UMA_COLOR;
    }
}
