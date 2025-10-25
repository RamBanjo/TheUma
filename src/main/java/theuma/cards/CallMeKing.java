package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class CallMeKing extends AbstractEasyCard {
    public final static String ID = makeID("CallMeKing");
    // intellij stuff attack, enemy, rare, , , , , 0, 1

    public CallMeKing() {
        super(ID, 0, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 2;
        baseMagicNumber = magicNumber = 2;
        baseSecondMagic = secondMagic = 2;
        this.exhaust = true;
    }

    public static int countCards() {
        int count = 0;

        for(AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c.cardID.equals(ID)) {
                ++count;
            }
        }

        return count;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * countCards();
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * countCards();
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        atb(new DrawCardAction(magicNumber));
        atb(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(), secondMagic));
    }

    @Override
    public void upp() {
        upgradeSecondMagic(1);
    }
}