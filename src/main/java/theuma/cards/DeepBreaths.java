package theuma.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.powers.CertainVictoryPower;
import theuma.powers.DeepBreathsPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;

public class DeepBreaths extends AbstractEasyCard {
    public final static String ID = makeID("DeepBreaths");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public DeepBreaths() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DeepBreathsPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(3);
    }
}