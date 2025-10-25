package theuma.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;
import static theuma.util.Wiz.att;

public class AgemasenPower extends AbstractEasyPower{

    public final static String ID = makeID("AgemasenPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private boolean triggeredThisTurn;


    public AgemasenPower(AbstractCreature owner, int amount) {
        super(ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void updateDescription() {

        if(amount == 1){
            description = DESCRIPTIONS[0];
        }else{
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }

        if(triggeredThisTurn){
            description += DESCRIPTIONS[3];
        }
    }

    @Override
    public void atStartOfTurn() {
        this.triggeredThisTurn = false;
        updateDescription();
    }

    @Override
    public void onExhaust(AbstractCard card) {
        if(!triggeredThisTurn){
            this.flash();
            att(new MakeTempCardInDiscardAction(card.makeStatEquivalentCopy(), amount));
            triggeredThisTurn = true;
            updateDescription();
        }
    }
}
