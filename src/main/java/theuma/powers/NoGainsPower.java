package theuma.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theuma.util.Wiz;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;
import static theuma.util.Wiz.att;

public class NoGainsPower extends AbstractEasyPower implements OnReceivePowerPower {

    public final static String ID = makeID("NoGainsPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public NoGainsPower(AbstractCreature owner, int amount) {
        super(ID, NAME, PowerType.DEBUFF, true, owner, amount);
    }

    @Override
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature target, AbstractCreature source) {

        if(abstractPower.ID.equals(DexterityPower.POWER_ID) || abstractPower.ID.equals(StrengthPower.POWER_ID)){
            if (abstractPower.amount >= 0){
                this.flash();
                return false;
            }
        }

        return true;
    }

    @Override
    public void atEndOfRound() {

        Wiz.reducePower(this, 1);
        if (this.amount == 0){
            atb(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }

    @Override
    public void updateDescription() {

        if (amount == 1){
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        }else{
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }

    }
}
