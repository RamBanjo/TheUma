package theuma.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.ModifyBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.cards.AbstractEasyCard;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class FreshRyegrassCard extends AbstractEasyCard {
    public final static String ID = makeID("FreshRyegrass");
    // intellij stuff SKILL, NONE, COMMON, 0, 0, 4, 4, 2, 3

    public FreshRyegrassCard() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseDamage = 0;
        baseBlock = 4;
        baseMagicNumber = magicNumber = 2;
    }

    public void upp() {
        upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        blck();
    }

    @Override
    public void triggerOnExhaust() {

        AbstractCard newRyegrass = this.makeStatEquivalentCopy();

        atb(new ModifyBlockAction(newRyegrass.uuid, this.magicNumber));
        atb(new MakeTempCardInDrawPileAction(newRyegrass, 1, true, true, false));
    }
}