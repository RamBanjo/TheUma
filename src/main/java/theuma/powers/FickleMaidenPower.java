package theuma.powers;

import basemod.BaseMod;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theuma.cards.FickleMaidenCard;
import theuma.powers.hooks.OnOtherPowerRemovedPower;
import theuma.util.CommonUmaMethods;

import java.util.ArrayList;
import java.util.Objects;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.adp;

public class FickleMaidenPower extends AbstractEasyPower implements OnOtherPowerRemovedPower {

    public final static String ID = makeID("FickleMaidenPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int moodOnExhaust;

    public FickleMaidenPower(AbstractCreature owner, int amount) {
        super(ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void onInitialApplication() {
        moodOnExhaust = CommonUmaMethods.getCurrentPlayerMood(adp());
        updateDescription();
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {

        if (power.ID.equals(UmaMoodPower.ID) && target.isPlayer) {
            if (moodHasChanged(power.amount)) {
                returnFickleMaidens();
            }
        }
    }

    @Override
    public void onOtherPowerRemoved(AbstractCreature owner, AbstractPower removedPower) {
        if (removedPower.ID.equals(UmaMoodPower.ID) && moodOnExhaust != 0){
            returnFickleMaidens();
        }
    }

    public boolean moodHasChanged(int moodChange){
        int newMood = CommonUmaMethods.getCurrentPlayerMood(adp()) + moodChange;

        newMood = Math.max(newMood, UmaMoodPower.MIN_MOOD);
        newMood = Math.min(newMood, UmaMoodPower.MAX_MOOD);

        return newMood != moodOnExhaust;
    }


    public void returnFickleMaidens(){

        AbstractPlayer p = adp();
        ArrayList<AbstractCard> exhumed = new ArrayList<>();


        boolean handFull = false;
        for (AbstractCard checkCard : p.exhaustPile.group) {
            if (Objects.equals(checkCard.cardID, FickleMaidenCard.ID)) {
                checkCard.unfadeOut();

                if (p.hand.size() < BaseMod.MAX_HAND_SIZE){
                    p.hand.addToHand(checkCard);
                }else{
                    p.discardPile.addToTop(checkCard);
                    handFull = true;
                }

                exhumed.add(checkCard);
                checkCard.unhover();
            }
        }

        exhumed.forEach(exCard -> p.exhaustPile.removeCard(exCard));

        if (handFull) p.createHandIsFullDialog();
        addToBot(new RemoveSpecificPowerAction(adp(), adp(), ID));
    }


    @Override
    public void updateDescription() {
        if (amount == 1){
            this.description = DESCRIPTIONS[0] + UmaMoodPower.getMoodString(moodOnExhaust) + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }else{
            this.description = DESCRIPTIONS[0] + UmaMoodPower.getMoodString(moodOnExhaust) + DESCRIPTIONS[1] + amount + DESCRIPTIONS[3];
        }

    }
}
