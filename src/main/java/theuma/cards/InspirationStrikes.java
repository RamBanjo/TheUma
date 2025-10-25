package theuma.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.actions.InspirationStrikesAction;
import theuma.cards.curses.MigraineCurse;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class InspirationStrikes extends AbstractEasyCard {
    public final static String ID = makeID("InspirationStrikes");
    // intellij stuff attack, enemy, rare, , , , , 0, 1

    public InspirationStrikes() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(p, 1));
        atb(new InspirationStrikesAction(magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}
