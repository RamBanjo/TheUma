package theuma.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import theuma.cards.status.SlowMetabolismStatus;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;
import static theuma.util.Wiz.atb;

public class HowHungry extends AbstractEasyCard {

    public final static String ID = makeID("HowHungry");
    // intellij stuff skill, self, uncommon

    public HowHungry() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        this.exhaust = true;
        this.cardsToPreview = new SlowMetabolismStatus();

        this.tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new RegenPower(p, magicNumber));
        atb(new GainEnergyAction(2));
        atb(new MakeTempCardInDrawPileAction(new SlowMetabolismStatus(), 1, false, true, false));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}