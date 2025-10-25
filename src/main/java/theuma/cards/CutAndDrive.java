package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.powers.CutAndDrivePower;
import theuma.powers.TeioStepPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class CutAndDrive extends AbstractEasyCard {
    public final static String ID = makeID("CutAndDrive");
    // intellij stuff ATTACK, ENEMY, UNCOMMON, 9, , , , 3, 2

    public CutAndDrive() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new CutAndDrivePower(p, magicNumber));
        atb(new DrawCardAction(1));
    }

    public void upp() {
        upgradeMagicNumber(2);

    }
}