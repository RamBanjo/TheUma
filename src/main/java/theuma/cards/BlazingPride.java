package theuma.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.powers.EmpressPridePower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;

public class BlazingPride extends AbstractEasyCard {
    public final static String ID = makeID("BlazingPride");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public BlazingPride() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EmpressPridePower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}