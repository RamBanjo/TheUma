package theuma.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.powers.UmaMoodPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;

public class ExhaustiveCoverageCard extends AbstractEasyCard {
    public final static String ID = makeID("ExhaustiveCoverage");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public ExhaustiveCoverageCard() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 15;
        this.baseMagicNumber = this.magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new UmaMoodPower(p, this.magicNumber * -1));
    }

    @Override
    public void upp() {
        upgradeBlock(5);
    }
}