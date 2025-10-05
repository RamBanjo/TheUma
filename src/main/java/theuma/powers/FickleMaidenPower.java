package theuma.powers;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Objects;

import static theuma.ModFile.makeID;

public class FickleMaidenPower extends AbstractEasyPower{

    public final static String ID = makeID("FickleMaiden");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FickleMaidenPower(AbstractCreature owner, int amount) {
        super(ID, NAME, PowerType.BUFF, true, owner, amount);
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {

        if (Objects.equals(power.ID, makeID("UmaMood")) && target.isPlayer){

            AbstractPlayer p = (AbstractPlayer) target;
            ArrayList<AbstractCard> exhumed = new ArrayList<>();


            boolean handFull = false;
            for (AbstractCard checkCard : p.exhaustPile.group) {
                if (Objects.equals(checkCard.cardID, makeID("FickleMaiden"))) {
                    checkCard.unfadeOut();

                    if (p.hand.size() < 10){
                        p.hand.addToHand(checkCard);
                    }else{
                        p.discardPile.addToTop(checkCard);
                        handFull = true;
                    }

                    if (AbstractDungeon.player.hasPower("Corruption") && checkCard.type == AbstractCard.CardType.SKILL) {
                        checkCard.setCostForTurn(-9);
                    }

                    exhumed.add(checkCard);
                    checkCard.unhover();
                }
            }

            for (AbstractCard exCard: exhumed){
                p.exhaustPile.removeCard(exCard);
            }

            if (handFull) p.createHandIsFullDialog();
            addToBot(new RemoveSpecificPowerAction(target, source, ID));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

}
