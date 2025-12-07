package theuma.powers;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.actions.TrainingRestrictAction;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class OversightPower extends AbstractEasyPower {

    public final static String ID = makeID("OversightPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public OversightPower(AbstractCreature owner) {
        super(ID, NAME, PowerType.DEBUFF, false, owner, -1);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public void atStartOfTurn() {
        atb(new TrainingRestrictAction(DESCRIPTIONS[1], DESCRIPTIONS[2], DESCRIPTIONS[3], DESCRIPTIONS[4], DESCRIPTIONS[5]));
    }
}
