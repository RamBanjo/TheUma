package theuma.cards;

import basemod.abstracts.AbstractCardModifier;
import basemod.cardmods.ExhaustMod;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.CarveReality;
import com.megacrit.cardcrawl.cards.purple.DeceiveReality;
import com.megacrit.cardcrawl.cards.purple.Evaluate;
import com.megacrit.cardcrawl.cards.red.Bash;
import com.megacrit.cardcrawl.cards.red.Clothesline;
import com.megacrit.cardcrawl.cards.red.Uppercut;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.actions.PickCardsFromListAction;

import java.util.ArrayList;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class SignalOfSomethingNew extends AbstractEasyCard {

    public final static String ID = makeID("SignalNew");
    // intellij stuff skill, self, uncommon

    public SignalOfSomethingNew() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        MultiCardPreview.add(this, new CarveReality(), new DeceiveReality(), new Evaluate());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> cardArrayList = new ArrayList<AbstractCard>();

        cardArrayList.add(new CarveReality());
        cardArrayList.add(new DeceiveReality());
        cardArrayList.add(new Evaluate());

        ArrayList<AbstractCardModifier> modifierList = new ArrayList<AbstractCardModifier>();
        modifierList.add(new ExhaustMod());

        atb(new PickCardsFromListAction(cardArrayList, -1, cardStrings.EXTENDED_DESCRIPTION[0], 0, this.upgraded, modifierList));
    }

    @Override
    public void upp() {
        MultiCardPreview.multiCardPreview.get(this).forEach(AbstractCard::upgrade);
    }
}