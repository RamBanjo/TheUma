package theuma.cards.status;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.DrawReductionPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theuma.cards.AbstractEasyCard;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;
import static theuma.util.Wiz.applyToSelfTop;

public class SlowMetabolismStatus extends AbstractEasyCard {
    public final static String ID = makeID("SlowMetabolismStatus");
    // intellij stuff STATUS, SELF, COMMON, 0, 0, 0, 0, 0, 0

    public SlowMetabolismStatus() {
        super(ID, -2, CardType.STATUS, CardRarity.COMMON, CardTarget.NONE, CardColor.COLORLESS);

        this.baseMagicNumber = this.magicNumber = 2;
        this.baseSecondMagic = this.secondMagic = 1;
//        this.selfRetain = true;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.dontTriggerOnUseCard) {
            applyToSelf(new StrengthPower(AbstractDungeon.player, secondMagic));
            applyToSelf(new DexterityPower(AbstractDungeon.player, magicNumber * -1));
        }

    }

    public void triggerOnEndOfTurnForPlayingCard() {
        this.dontTriggerOnUseCard = true;
        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, true));
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    public void upp() {

    }
}