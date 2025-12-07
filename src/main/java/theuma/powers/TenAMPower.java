package theuma.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theuma.actions.TrainingRestrictAction;
import theuma.util.Wiz;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class TenAMPower extends AbstractEasyPower {

    public final static String ID = makeID("TenAMPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public TenAMPower(AbstractCreature owner, int amt) {
        super(ID, NAME, PowerType.DEBUFF, false, owner, amt);
    }

    @Override
    public void updateDescription() {
        if(amount > 1){
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }else{
            description = DESCRIPTIONS[0];
        }


    }

    @Override
    public void atStartOfTurn() {
        atb(new TrainingRestrictAction(DESCRIPTIONS[3], DESCRIPTIONS[4], DESCRIPTIONS[5], DESCRIPTIONS[6], DESCRIPTIONS[7]));

        Wiz.reducePower(this);
        if (this.amount == 0){
            atb(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }
}
