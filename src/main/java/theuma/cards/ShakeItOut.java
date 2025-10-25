package theuma.cards;

import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;
import com.megacrit.cardcrawl.actions.common.ModifyBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theuma.actions.ModifyMagicAction;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class ShakeItOut extends AbstractEasyCard implements OnCreateCardInterface {
    public final static String ID = makeID("ShakeItOut");

    private final static int TRUE_BASE_BLOCK = 6;

    public ShakeItOut() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = TRUE_BASE_BLOCK;
        this.baseMagicNumber = this.magicNumber = 1;
        this.selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

//    public void applyPowers() {
//        super.applyPowers();
//        this.isBlockModified = this.baseBlock != TRUE_BASE_BLOCK;
//    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void onCreateCard(AbstractCard abstractCard) {
        if (adp().hand.contains(this)){
            atb(new ModifyBlockAction(this.uuid, this.magicNumber));
            this.superFlash();
            this.applyPowers();
        }
    }

    public void onMoveToDiscard() {
//        this.rawDescription = cardStrings.DESCRIPTION;
//        this.initializeDescription();
    }

}