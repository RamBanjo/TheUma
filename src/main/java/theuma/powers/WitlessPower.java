package theuma.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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

public class WitlessPower extends AbstractEasyPower {

    public final static String ID = makeID("WitlessPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public WitlessPower(AbstractCreature owner, int amount) {
        super(ID, NAME, PowerType.DEBUFF, true, owner, amount);
    }

    @Override
    public boolean canPlayCard(AbstractCard card) {

        if (card.type == AbstractCard.CardType.POWER){
            return false;
        }

        return super.canPlayCard(card);
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
