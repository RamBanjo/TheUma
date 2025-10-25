package theuma.actions;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import static theuma.util.Wiz.atb;
import static theuma.util.Wiz.att;

public class PermaPickCardsFromListAction extends AbstractGameAction {

    private ArrayList<AbstractCard> cardArrayList;

    //If the sampleAmount is a positive integer then it will pick randomly that many cards from the cardArrayList. If it is -1, every single card in the list will be displayed.
    private int sampleAmount;

    //The text at the bottom of the screen
    private String myString;

    public PermaPickCardsFromListAction(ArrayList<AbstractCard> cardsList, int sampleAmt, String myText){
        cardArrayList = cardsList;
        sampleAmount = sampleAmt;
        myString = myText;
    }

    @Override
    public void update() {
        ArrayList<AbstractCard> myCardsList = new ArrayList<>();
        ArrayList<AbstractCard> eligibleCardsList = cardArrayList;

        int finalCount = sampleAmount;
        if (sampleAmount != -1){
            Collections.shuffle(eligibleCardsList);
        }else{
            finalCount = eligibleCardsList.size();
        }

        for (int i = 0; i < finalCount; i++) {
            myCardsList.add(eligibleCardsList.get(i));
        }

        atb(new SelectCardsAction(myCardsList, 1, myString, (cards) -> {
            att(new AddCardToDeckAction(cards.get(0)));
            att(new MakeTempCardInHandAction(cards.get(0), 1, true));
        }));

        this.isDone = true;
    }
}
