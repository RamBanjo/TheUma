package theuma.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.cards.status.SlackerStatus;
import theuma.cards.status.SlowMetabolismStatus;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class Donuts extends AbstractEasyCard {

    public final static String ID = makeID("Donuts");
    // intellij stuff skill, self, uncommon

    public Donuts() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 8;
        this.exhaust = true;
        this.cardsToPreview = new SlowMetabolismStatus();

        this.tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new HealAction(p, p, magicNumber));
        atb(new MakeTempCardInDrawPileAction(new SlowMetabolismStatus(), 2, true, true));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }
}