package theuma.cards;

import basemod.abstracts.AbstractCardModifier;
import basemod.cardmods.ExhaustMod;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Terror;
import com.megacrit.cardcrawl.cards.red.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.actions.ChangePlayedCardExhaustAction;
import theuma.actions.PickCardsFromListAction;
import theuma.util.CommonUmaMethods;

import java.util.ArrayList;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class PetrifyingGaze extends AbstractEasyCard {

    public final static String ID = makeID("PetrifyingGaze");
    // intellij stuff skill, self, uncommon

    public PetrifyingGaze() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        MultiCardPreview.add(this, new Intimidate(), new Terror(), new Shockwave());
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> cardArrayList = new ArrayList<AbstractCard>();

        cardArrayList.add(new Intimidate());
        cardArrayList.add(new Terror());
        cardArrayList.add(new Shockwave());

        atb(new PickCardsFromListAction(cardArrayList, -1, cardStrings.EXTENDED_DESCRIPTION[0], 0, this.upgraded, null));

        if(CommonUmaMethods.playerIsSorrowful(p)){
            atb(new ChangePlayedCardExhaustAction(this, false));
        }

    }

    @Override
    public void upp() {
        MultiCardPreview.multiCardPreview.get(this).forEach(AbstractCard::upgrade);
    }
}