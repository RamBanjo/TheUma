package theuma.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Skim;
import com.megacrit.cardcrawl.cards.green.CalculatedGamble;
import com.megacrit.cardcrawl.cards.green.Expertise;
import com.megacrit.cardcrawl.cards.purple.LessonLearned;
import com.megacrit.cardcrawl.cards.purple.Omniscience;
import com.megacrit.cardcrawl.cards.purple.Wish;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.actions.PickCardsFromListAction;

import java.util.ArrayList;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class DirectorTracen extends AbstractEasyCard {

    public final static String ID = makeID("DirectorTracen");
    // intellij stuff skill, self, uncommon

    public DirectorTracen() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        MultiCardPreview.add(this, new Wish(), new Omniscience(), new LessonLearned());

        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        ArrayList<AbstractCard> cardArrayList = new ArrayList<AbstractCard>();

        cardArrayList.add(new Wish());
        cardArrayList.add(new Omniscience());
        cardArrayList.add(new LessonLearned());

        atb(new PickCardsFromListAction(cardArrayList, -1, cardStrings.EXTENDED_DESCRIPTION[0], 1, false, null));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }
}