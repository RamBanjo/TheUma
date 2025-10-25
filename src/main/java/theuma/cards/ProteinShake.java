package theuma.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.ModifyBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theuma.actions.ModifyMagicAction;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class ProteinShake extends AbstractEasyCard {
    public final static String ID = makeID("ProteinShake");
    // intellij stuff SKILL, NONE, COMMON, 0, 0, 4, 4, 2, 3

    public ProteinShake() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseDamage = 0;
        baseBlock = 8;
        baseMagicNumber = magicNumber = 2;
    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        blck();
    }

    @Override
    public void triggerOnExhaust() {

        applyToSelf(new StrengthPower(adp(), this.magicNumber));

        AbstractCard newShake = this.makeStatEquivalentCopy();

        newShake.baseMagicNumber = newShake.magicNumber -= 1;

        if (newShake.magicNumber >= 0){
            atb(new MakeTempCardInDrawPileAction(newShake, 1, true, true, false));
        }
    }
}