package theuma.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.actions.PermaPickCardsFromListAction;
import theuma.actions.PickCardsFromListAction;

import java.util.ArrayList;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class LegacyLivesOn extends AbstractEasyCard{

    public final static String ID = makeID("LegaciesLiveOn");
    // intellij stuff skill, self, uncommon

    public LegacyLivesOn() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
        FleetingField.fleeting.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        ArrayList<AbstractCard> cardArrayList = getCardsMatchingPredicate(card -> card.color != p.getCardColor() && card.rarity == CardRarity.RARE, true);
        atb(new PermaPickCardsFromListAction(cardArrayList, magicNumber, cardStrings.EXTENDED_DESCRIPTION[0]));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(5);
    }
}