package theuma.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.AllForOne;
import com.megacrit.cardcrawl.cards.blue.EchoForm;
import com.megacrit.cardcrawl.cards.blue.MachineLearning;
import com.megacrit.cardcrawl.cards.green.Burst;
import com.megacrit.cardcrawl.cards.green.Doppelganger;
import com.megacrit.cardcrawl.cards.purple.Scrawl;
import com.megacrit.cardcrawl.cards.red.DoubleTap;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.actions.PickCardsFromListAction;

import java.util.ArrayList;
import java.util.Collections;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class AFriend extends AbstractEasyCard {

    public final static String ID = makeID("AFriend");
    // intellij stuff skill, self, uncommon

    public AFriend() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        MultiCardPreview.add(this, new Doppelganger(), new DoubleTap(), new Burst());

        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        ArrayList<AbstractCard> cardArrayList = new ArrayList<AbstractCard>();

        cardArrayList.add(new Doppelganger());
        cardArrayList.add(new DoubleTap());
        cardArrayList.add(new Burst());

        if(this.upgraded){
            atb(new PickCardsFromListAction(cardArrayList, -1, cardStrings.EXTENDED_DESCRIPTION[0], 0, false));
        }else{
            Collections.shuffle(cardArrayList);
            atb(new MakeTempCardInHandAction(cardArrayList.get(0)));
        }

    }

    @Override
    public void upp() {

    }
}