package theuma.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.powers.CutAndDrivePower;
import theuma.powers.WinningTicketPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;
import static theuma.util.Wiz.atb;

public class TicketToWin extends AbstractEasyCard {
    public final static String ID = makeID("TicketToWin");

    public TicketToWin() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new WinningTicketPower(p, magicNumber));

    }

    public void upp() {
        upgradeMagicNumber(1);

    }
}