package theuma.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import theuma.cards.AbstractEasyCard;
import theuma.powers.LateSurgerPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class LateSurgerCard extends AbstractEasyCard {
    public final static String ID = makeID("LateSurger");
    // intellij stuff SKILL, SELF, COMMON, 0, 0, 0, 0, 1, 1

    public LateSurgerCard() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseDamage = 0;
        baseBlock = 0;
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 6;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EnergizedPower(p, magicNumber));
        atb(new WaitAction(Settings.ACTION_DUR_FAST));
        applyToSelf(new LateSurgerPower(p, secondMagic));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(2);
    }

}