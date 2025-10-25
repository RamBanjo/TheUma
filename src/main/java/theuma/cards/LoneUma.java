package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.actions.ChangePlayedCardExhaustAction;
import theuma.util.CommonUmaMethods;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class LoneUma extends AbstractEasyCard {
    public final static String ID = makeID("LoneUma");
    // intellij stuff attack, enemy, rare, , , , , 0, 1

    public LoneUma() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 15;
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

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);

        AbstractCard newLoneUma = new LoneUma();
        if (this.upgraded) newLoneUma.upgrade();

        atb(new MakeTempCardInDiscardAction(newLoneUma, 1));

        if(CommonUmaMethods.playerIsSorrowful(p)){
            atb(new ChangePlayedCardExhaustAction(this, true));
        }
    }

    @Override
    public void triggerWhenDrawn() {
        this.costForTurn = Math.max(countCards() - 1, 0);
    }

    @Override
    public void upp() {
        upgradeDamage(5);
    }
}