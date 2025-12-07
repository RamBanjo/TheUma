package theuma.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import theuma.cards.status.RushedStatus;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;
import static theuma.util.Wiz.atb;

public class UMaTwo extends AbstractEasyCard {

    public final static String ID = makeID("UMaTwo");
    // intellij stuff skill, self, uncommon

    public UMaTwo() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;

        this.baseMagicNumber = this.magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        int exhausted = 0;

        for(AbstractCard c: p.hand.group){

            if (c == this) continue;

            exhausted += 1;
            atb(new ExhaustSpecificCardAction(c, p.hand));
        }

        int arteGain = (int) Math.floor(exhausted / (double) magicNumber);

        if(arteGain > 0){
            applyToSelf(new ArtifactPower(p, arteGain));
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(-1);
//        upgradeBaseCost(0);
    }
}