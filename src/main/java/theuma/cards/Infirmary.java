package theuma.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.powers.EmpressPridePower;
import theuma.powers.HorsepitalPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;

public class Infirmary extends AbstractEasyCard {
    public final static String ID = makeID("Infirmary");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Infirmary() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new HorsepitalPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeBaseCost(1);
    }
}