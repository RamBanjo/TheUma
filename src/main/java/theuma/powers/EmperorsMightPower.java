package theuma.powers;

import basemod.devcommands.draw.Draw;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class EmperorsMightPower extends AbstractEasyPower {

    public final static String ID = makeID("EmperorsMightPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private boolean triggeredThisTurn = false;

    public EmperorsMightPower(AbstractCreature owner, int amount) {
        super(ID, NAME, PowerType.BUFF, false, owner, amount);

        this.isTwoAmount = true;
        this.amount2 = 0;
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1){
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        }else{
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }

        if (triggeredThisTurn){
            description += DESCRIPTIONS[3];
        }

    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (!triggeredThisTurn){
            amount2 += 1;

            if(amount2 == 3){
                this.flash();
                atb(new DrawCardAction(amount));
                triggeredThisTurn = true;
                updateDescription();
            }
        }
    }

    @Override
    public void atStartOfTurn() {
        triggeredThisTurn = false;
        amount2 = 0;
        updateDescription();
    }
}
