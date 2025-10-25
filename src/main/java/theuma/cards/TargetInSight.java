package theuma.cards;

import basemod.abstracts.AbstractCardModifier;
import basemod.cardmods.EtherealMod;
import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.BeamCell;
import com.megacrit.cardcrawl.cards.blue.GoForTheEyes;
import com.megacrit.cardcrawl.cards.green.Neutralize;
import com.megacrit.cardcrawl.cards.red.Bash;
import com.megacrit.cardcrawl.cards.red.Clothesline;
import com.megacrit.cardcrawl.cards.red.Uppercut;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.actions.PickCardsFromListAction;

import java.util.ArrayList;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class TargetInSight extends AbstractEasyCard {

    public final static String ID = makeID("TargetInSight");
    // intellij stuff skill, self, uncommon

    public TargetInSight() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        MultiCardPreview.add(this, new GoForTheEyes(), new BeamCell());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        ArrayList<AbstractCard> cardArrayList = new ArrayList<AbstractCard>();

        if (!upgraded){
            cardArrayList.add(new GoForTheEyes());
        }else{
            cardArrayList.add(new Neutralize());
        }

        cardArrayList.add(new BeamCell());

        ArrayList<AbstractCardModifier> modifierList = new ArrayList<AbstractCardModifier>();
        modifierList.add(new ExhaustMod());
        modifierList.add(new EtherealMod());

        for (AbstractCard c : cardArrayList){
            if (upgraded){
                c.upgrade();
            }

            for (AbstractCardModifier mod : modifierList){
                CardModifierManager.addModifier(c, mod);
            }

            atb(new MakeTempCardInHandAction(c, 1));
        }
    }

    @Override
    public void upp() {
        MultiCardPreview.clear(this);
        MultiCardPreview.add(this, new Neutralize(), new BeamCell());
        MultiCardPreview.multiCardPreview.get(this).forEach(AbstractCard::upgrade);
    }
}