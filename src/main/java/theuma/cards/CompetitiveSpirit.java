package theuma.cards;

import basemod.devcommands.draw.Draw;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.actions.BlueRoseCloserAction;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class CompetitiveSpirit extends AbstractEasyCard {
    public final static String ID = makeID("CompetitiveSpirit");
    // intellij stuff attack, enemy, rare, , , , , 0, 1

    public CompetitiveSpirit() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 3;
        baseMagicNumber = magicNumber = 1;
    }

    public static int countCards() {
        int count = 0;

        for(AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.cardID.equals(ID)) {
                ++count;
            }
        }

        for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.cardID.equals(ID)) {
                ++count;
            }
        }

        for(AbstractCard c : AbstractDungeon.player.discardPile.group) {
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
        atb(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(), 1));
//        atb(new DrawCardAction(p, 1));


    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}