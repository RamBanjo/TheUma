package theuma.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.powers.DeepBreathsPower;
import theuma.powers.EmperorsDivinityPower;
import theuma.powers.EmperorsMightPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;

public class DivineMight extends AbstractEasyCard {
    public final static String ID = makeID("DivineMight");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public DivineMight() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 2;
        this.baseSecondMagic = this.secondMagic = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EmperorsDivinityPower(p, secondMagic));
        applyToSelf(new EmperorsMightPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondDamage(1);
    }
}