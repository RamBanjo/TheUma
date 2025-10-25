package theuma.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.FreeAttackPower;
import theuma.powers.CertainVictoryPower;
import theuma.powers.LambdaPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;
import static theuma.util.Wiz.atb;

public class CertainVictory extends AbstractEasyCard {
    public final static String ID = makeID("CertainVictory");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public CertainVictory() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new CertainVictoryPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(3);
    }
}