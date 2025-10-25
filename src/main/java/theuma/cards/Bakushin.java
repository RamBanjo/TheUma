package theuma.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.cards.status.RushedStatus;
import theuma.cards.status.SlackerStatus;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class Bakushin extends AbstractEasyCard {

    public final static String ID = makeID("Bakushin");
    // intellij stuff skill, self, uncommon

    public Bakushin() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        this.cardsToPreview = new RushedStatus();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(1));
        atb(new DrawCardAction(magicNumber));
        atb(new MakeTempCardInDrawPileAction(new RushedStatus(), 2, true, true));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}