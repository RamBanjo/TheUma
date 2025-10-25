package theuma.cards;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.actions.PickCardsFromListAction;

import java.util.ArrayList;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;
import static theuma.util.Wiz.getCardsMatchingPredicate;

public class LegacyOfTheStrong extends AbstractEasyCard {

    public final static String ID = makeID("LegacyOfTheStrong");
    // intellij stuff skill, self, uncommon

    public LegacyOfTheStrong() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        ArrayList<AbstractCard> cardArrayList = getCardsMatchingPredicate(card -> card.color == CardColor.RED && !card.hasTag(CardTags.HEALING), true);

        atb(new PickCardsFromListAction(cardArrayList, magicNumber, cardStrings.EXTENDED_DESCRIPTION[0], -1, false, null));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
        upgradeMagicNumber(2);
    }
}