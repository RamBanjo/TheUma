package theuma.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.cards.curses.MigraineCurse;
import theuma.cards.status.NightOwlStatus;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class RestDay extends AbstractEasyCard {
    public final static String ID = makeID("RestDay");
    // intellij stuff attack, enemy, rare, , , , , 0, 1

    public RestDay() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        exhaust = true;
        this.cardsToPreview = new NightOwlStatus();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(magicNumber));
        atb(new MakeTempCardInDiscardAction(new NightOwlStatus(), 1));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}