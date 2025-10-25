package theuma.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.powers.SkillRefundPower;
import theuma.powers.UmadolPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;
import static theuma.util.Wiz.atb;

public class SparklingStardom extends AbstractEasyCard {
    public final static String ID = makeID("SparklingStardom");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public SparklingStardom() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);

        this.isEthereal = true;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new UmadolPower(p, 1));
    }

    @Override
    public void upp() {
        this.isEthereal = false;
    }
}