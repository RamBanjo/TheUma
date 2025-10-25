package theuma.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theuma.actions.ChangePlayedCardExhaustAction;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;
import static theuma.util.Wiz.removePower;

public class ExerciseTheRightPower extends AbstractEasyPower{

    public final static String ID = makeID("ExerciseTheRightPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ExerciseTheRightPower(AbstractCreature owner, int amount) {
        super(ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        if (amount == 1){
            description = DESCRIPTIONS[0];
        }else{
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        atb(new MakeTempCardInDrawPileAction(card.makeStatEquivalentCopy(), amount, true, true));
        atb(new ChangePlayedCardExhaustAction(card, true));
        removePower(this);
    }
}
