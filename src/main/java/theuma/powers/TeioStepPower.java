package theuma.powers;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;
import static theuma.util.Wiz.removePower;

public class TeioStepPower extends AbstractEasyPower{

    public final static String ID = makeID("TeioStepPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public TeioStepPower(AbstractCreature owner, int amount) {
        super(ID, NAME, PowerType.DEBUFF, true, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        removePower(this);
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        this.flash();
        new DrawCardAction(3);
        atb(new LoseHPAction(owner, null, this.amount));
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }
}
