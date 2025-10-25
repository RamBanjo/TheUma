package theuma.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class HorsepitalPower extends AbstractEasyPower{

    public final static String ID = makeID("HorsepitalPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HorsepitalPower(AbstractCreature owner, int amount) {
        super(ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void onExhaust(AbstractCard card) {

        if (card.type == AbstractCard.CardType.STATUS || card.color == AbstractCard.CardColor.CURSE){
            atb(new GainEnergyAction(amount));
        }
    }
}
