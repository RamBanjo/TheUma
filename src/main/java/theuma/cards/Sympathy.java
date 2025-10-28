package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class Sympathy extends AbstractEasyCard {
    public final static String ID = makeID("Sympathy");
    // intellij stuff attack, enemy, rare, , , , , 0, 1

    public Sympathy() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 3;
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

    public void applyPowers() {
        int realBaseBlock = this.baseBlock;
        this.baseBlock += this.magicNumber * countCards();
        super.applyPowers();
        this.baseBlock = realBaseBlock;
        this.isBlockModified = this.block != this.baseBlock;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainBlockAction(p, this.block + (this.magicNumber * countCards())));
        atb(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(), 1));
//        atb(new DrawCardAction(p, 1));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}