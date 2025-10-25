package theuma.cards;

import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ModifyBlockAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class TailHeldHigh extends AbstractEasyCard implements OnCreateCardInterface {
    public final static String ID = makeID("TailHeldHigh");

    private final static int TRUE_BASE_DAMAGE = 6;

    public TailHeldHigh() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = TRUE_BASE_DAMAGE;
        this.baseMagicNumber = this.magicNumber = 1;
        this.selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

//    public void applyPowers() {
//        super.applyPowers();
//        this.isDamageModified = this.baseDamage != TRUE_BASE_DAMAGE;
//    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void onCreateCard(AbstractCard abstractCard) {
        if (adp().hand.contains(this)){
            atb(new ModifyDamageAction(this.uuid, this.magicNumber));
            this.superFlash();
            this.applyPowers();
        }
    }

    public void onMoveToDiscard() {
//        this.rawDescription = cardStrings.DESCRIPTION;
//        this.initializeDescription();
    }

}