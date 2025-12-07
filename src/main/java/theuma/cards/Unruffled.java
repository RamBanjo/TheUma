package theuma.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.AllForOne;
import com.megacrit.cardcrawl.cards.blue.Buffer;
import com.megacrit.cardcrawl.cards.blue.MachineLearning;
import com.megacrit.cardcrawl.cards.purple.Scrawl;
import com.megacrit.cardcrawl.cards.purple.SpiritShield;
import com.megacrit.cardcrawl.cards.red.Impervious;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.actions.PickCardsFromListAction;
import theuma.util.ProAudio;

import java.util.ArrayList;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;
import static theuma.util.Wiz.playAudio;

public class Unruffled extends AbstractEasyCard {

    public final static String ID = makeID("Unruffled");
    // intellij stuff skill, self, uncommon

    public Unruffled() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        MultiCardPreview.add(this, new Impervious(), new SpiritShield(), new Buffer());

        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        playAudio(ProAudio.MAMBO);

        ArrayList<AbstractCard> cardArrayList = new ArrayList<AbstractCard>();

        cardArrayList.add(new Impervious());
        cardArrayList.add(new SpiritShield());
        cardArrayList.add(new Buffer());

        atb(new PickCardsFromListAction(cardArrayList, -1, cardStrings.EXTENDED_DESCRIPTION[0], 0, false));
    }

    @Override
    public void upp() {
        upgradeBaseCost(1);
    }
}