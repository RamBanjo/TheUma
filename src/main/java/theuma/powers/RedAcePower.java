package theuma.powers;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class RedAcePower extends AbstractEasyPower{

    public final static String ID = makeID("RedAcePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public RedAcePower(AbstractCreature owner, int amount) {
        super(ID, NAME, PowerType.BUFF, true, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        removePower(this);
    }

    @Override
    public void onCardDraw(AbstractCard card) {

        this.flash();
        applyToSelf(new LateSurgerPower(adp(), amount));
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }
}
