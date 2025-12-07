package theuma.cards;

import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import theuma.powers.LateSurgerPower;
import theuma.powers.TenAMPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;
import static theuma.util.Wiz.atb;

public class TenAMUma extends AbstractEasyCard {
    public final static String ID = makeID("TenAMUma");
    // intellij stuff SKILL, SELF, COMMON, 0, 0, 0, 0, 1, 1

    public TenAMUma() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EnergizedPower(p, magicNumber));
        atb(new WaitAction(Settings.ACTION_DUR_FAST));
        applyToSelf(new TenAMPower(p, 1));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

}