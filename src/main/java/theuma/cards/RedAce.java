package theuma.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.powers.CutAndDrivePower;
import theuma.powers.RedAcePower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;
import static theuma.util.Wiz.atb;

public class RedAce extends AbstractEasyCard {
    public final static String ID = makeID("RedAce");
    // intellij stuff ATTACK, ENEMY, UNCOMMON, 9, , , , 3, 2

    public RedAce() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new RedAcePower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);

    }
}