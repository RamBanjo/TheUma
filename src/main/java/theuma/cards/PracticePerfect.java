package theuma.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.powers.HorsepitalPower;
import theuma.powers.PracticePerfectPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;

public class PracticePerfect extends AbstractEasyCard {
    public final static String ID = makeID("PracticePerfect");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public PracticePerfect() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new PracticePerfectPower(p, -1));
    }

    @Override
    public void upp() {
        this.isInnate = true;
    }
}