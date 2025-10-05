package theuma.actions;

import basemod.abstracts.AbstractCardModifier;
import basemod.cardmods.EtherealMod;
import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import static theuma.util.Wiz.*;

public class PickCardsFromListAction extends AbstractGameAction {

    private ArrayList<AbstractCard> cardArrayList;

    //If the sampleAmount is a positive integer then it will pick randomly that many cards from the cardArrayList. If it is -1, every single card in the list will be displayed.
    private int sampleAmount;

    //The text at the bottom of the screen
    private String myString;

    //Cost Discount. Setting this to -1 makes all cards cost 0 until played. Setting it to a positive integer reduces the cost by that much until played. 0 = no discount
    private int discount;

    //Add card modifiers here.
    private ArrayList<AbstractCardModifier> cardMods;

    //If true, upgraded cards are added.
    private boolean addUpgradedCards;

    public PickCardsFromListAction(ArrayList<AbstractCard> cardsList, int sampleAmt, String myText, int newDiscount, boolean addUpgraded, ArrayList<AbstractCardModifier> cardModifiers){
        cardArrayList = cardsList;
        sampleAmount = sampleAmt;
        myString = myText;
        discount = newDiscount;
        cardMods = cardModifiers;
        addUpgradedCards = addUpgraded;
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

            if (!Objects.equals(cardMods, null)){
                for (AbstractCardModifier newCM : cardMods){
                    CardModifierManager.addModifier(eligibleCardsList.get(i), newCM);
                }
            }

            if (discount != 0){
                if (discount == -1){
                    eligibleCardsList.get(i).setCostForTurn(0);
                }else{

                    int newCost = eligibleCardsList.get(i).costForTurn - discount;
                    eligibleCardsList.get(i).setCostForTurn(Math.max(newCost, 0));

                }
            }

            if (addUpgradedCards){
                eligibleCardsList.get(i).upgrade();
            }

            myCardsList.add(eligibleCardsList.get(i));

        }
        atb(new SelectCardsAction(myCardsList, 1, myString, (cards) -> {
            att(new MakeTempCardInHandAction(cards.get(0), 1, true));
        }));

        this.isDone = true;
    }
}
