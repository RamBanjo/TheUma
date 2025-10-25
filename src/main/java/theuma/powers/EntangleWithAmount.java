package theuma.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.EntanglePower;
import theuma.util.Wiz;

import static theuma.util.Wiz.atb;
import static theuma.util.Wiz.att;

public class EntangleWithAmount extends EntanglePower {
    public EntangleWithAmount(AbstractCreature owner, int amount) {
        super(owner);
        this.amount = amount;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {

        if(isPlayer){
            Wiz.reducePower(this, 1);
            if (this.amount == 0){
                atb(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            }
        }

    }

    public static PowerStrings powerStrings2 = CardCrawlGame.languagePack.getPowerStrings("umapyoi:EntangledAmount");;

    @Override
    public void updateDescription() {
        if (amount == 1){
            description = powerStrings2.DESCRIPTIONS[0] + amount + powerStrings2.DESCRIPTIONS[1];
        }else{
            description = powerStrings2.DESCRIPTIONS[0] + amount + powerStrings2.DESCRIPTIONS[2];
        }
    }

}
