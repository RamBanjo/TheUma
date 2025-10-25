package theuma.cards;

import basemod.abstracts.AbstractCardModifier;
import basemod.cardmods.EtherealMod;
import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.BeamCell;
import com.megacrit.cardcrawl.cards.green.Neutralize;
import com.megacrit.cardcrawl.cards.red.Bash;
import com.megacrit.cardcrawl.cards.red.Clothesline;
import com.megacrit.cardcrawl.cards.red.Uppercut;
import com.megacrit.cardcrawl.cards.tempCards.Safety;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.actions.PickCardsFromListAction;

import java.util.ArrayList;
import java.util.Collections;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class VictoriaPorPlancha extends AbstractEasyCard {

    public final static String ID = makeID("VictoriaPorPlancha");
    // intellij stuff skill, self, uncommon

    public VictoriaPorPlancha() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        MultiCardPreview.add(this, new Bash(), new Clothesline(), new Uppercut());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
//        ArrayList<AbstractCard> myCardsList = new ArrayList<>();
//        ArrayList<AbstractCard> eligibleCardsList = new ArrayList<AbstractCard>();
//        eligibleCardsList.add(new Bash());
//        eligibleCardsList.add(new Clothesline());
//        eligibleCardsList.add(new Uppercut());
//
//        for (int i = 0; i < 3; i++) {
//            CardModifierManager.addModifier(eligibleCardsList.get(i), new EtherealMod());
//            CardModifierManager.addModifier(eligibleCardsList.get(i), new ExhaustMod());
//            eligibleCardsList.get(i).costForTurn = 0;
//
//            if (this.upgraded){
//                eligibleCardsList.get(i).upgrade();
//            }
//
//            myCardsList.add(eligibleCardsList.get(i));
//        }
//        atb(new SelectCardsAction(myCardsList, 1, cardStrings.EXTENDED_DESCRIPTION[0], (cards) -> {
//            att(new MakeTempCardInHandAction(cards.get(0), 1, true));
//        }));
        ArrayList<AbstractCard> cardArrayList = new ArrayList<AbstractCard>();

        cardArrayList.add(new Bash());
        cardArrayList.add(new Clothesline());
        cardArrayList.add(new Uppercut());

        ArrayList<AbstractCardModifier> modifierList = new ArrayList<AbstractCardModifier>();
        modifierList.add(new ExhaustMod());

        atb(new PickCardsFromListAction(cardArrayList, -1, cardStrings.EXTENDED_DESCRIPTION[0], 0, this.upgraded, modifierList));
    }

    @Override
    public void upp() {
        MultiCardPreview.multiCardPreview.get(this).forEach(AbstractCard::upgrade);
    }
}