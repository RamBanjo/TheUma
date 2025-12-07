package theuma.cards;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Apparition;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;
import static theuma.util.Wiz.atb;

public class ShadowBreak extends AbstractEasyCard {

    public final static String ID = makeID("ShadowBreak");
    // intellij stuff skill, self, uncommon

    public ShadowBreak() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;

        this.baseMagicNumber = this.magicNumber = 3;
        this.cardsToPreview = new Apparition();
        cardsToPreview.upgrade();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        int exhausted = 0;

        for(AbstractCard c: p.hand.group){

            if (c == this) continue;

            exhausted += 1;
            atb(new ExhaustSpecificCardAction(c, p.hand));
        }

        int apparitions = (int) Math.floor(exhausted / (double) magicNumber);

        if(apparitions > 0){

            Apparition a = new Apparition();
            a.upgrade();

            atb(new MakeTempCardInHandAction(a, apparitions));
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(-1);
//        upgradeBaseCost(0);
    }
}