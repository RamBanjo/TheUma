package theuma.cards;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.actions.PickCardsFromListAction;
import theuma.cards.status.SlackerStatus;

import java.util.ArrayList;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;
import static theuma.util.Wiz.getCardsMatchingPredicate;

public class AnythingButTraining extends AbstractEasyCard {

    public final static String ID = makeID("AnythingButTraining");
    // intellij stuff skill, self, uncommon

    public AnythingButTraining() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
        this.exhaust = true;
        this.cardsToPreview = new SlackerStatus();

        this.tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new HealAction(p, p, magicNumber));
        atb(new GainEnergyAction(2));
        atb(new MakeTempCardInDrawPileAction(new SlackerStatus(), 1, true, true));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }
}