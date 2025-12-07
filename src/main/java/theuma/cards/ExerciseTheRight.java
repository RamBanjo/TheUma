package theuma.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.powers.EmpressPridePower;
import theuma.powers.ExerciseTheRightPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;

public class ExerciseTheRight extends AbstractEasyCard {
    public final static String ID = makeID("ExerciseTheRight");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public ExerciseTheRight() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
        this.baseMagicNumber = this.magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ExerciseTheRightPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}