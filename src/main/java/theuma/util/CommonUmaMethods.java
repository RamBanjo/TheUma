package theuma.util;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theuma.CharacterFile;
import theuma.powers.UmaMoodPower;
import theuma.vfx.MoodDisplayEffect;

import static theuma.util.Wiz.adp;
import static theuma.util.Wiz.att;

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

    public static void spawnVFXForCurrentPlayerMood(int delta){

        int moodValue = delta;
        if (adp().getPower(UmaMoodPower.ID) != null){
           moodValue = adp().getPower(UmaMoodPower.ID).amount + delta;
        }

        if (moodValue > 2) moodValue = 2;
        if (moodValue < -2) moodValue = -2;

        att(new VFXAction(new MoodDisplayEffect(adp().hb, moodValue)));

    }

}
