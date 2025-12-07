package theuma.cards;

import basemod.abstracts.AbstractCardModifier;
import basemod.cardmods.ExhaustMod;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.AllForOne;
import com.megacrit.cardcrawl.cards.blue.MachineLearning;
import com.megacrit.cardcrawl.cards.green.Doppelganger;
import com.megacrit.cardcrawl.cards.purple.Scrawl;
import com.megacrit.cardcrawl.cards.red.Bash;
import com.megacrit.cardcrawl.cards.red.Clothesline;
import com.megacrit.cardcrawl.cards.red.Uppercut;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.actions.PickCardsFromListAction;

import java.util.ArrayList;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class HugeLead extends AbstractEasyCard {

    public final static String ID = makeID("HugeLead");
    // intellij stuff skill, self, uncommon

    public HugeLead() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        MultiCardPreview.add(this, new MachineLearning(), new Scrawl(), new AllForOne());

        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        ArrayList<AbstractCard> cardArrayList = new ArrayList<AbstractCard>();

        cardArrayList.add(new MachineLearning());
        cardArrayList.add(new Scrawl());
        cardArrayList.add(new AllForOne());

        atb(new PickCardsFromListAction(cardArrayList, -1, cardStrings.EXTENDED_DESCRIPTION[0], 0, this.upgraded));
    }

    @Override
    public void upp() {
        MultiCardPreview.multiCardPreview.get(this).forEach(AbstractCard::upgrade);
    }
}