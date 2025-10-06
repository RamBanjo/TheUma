package theuma.cards.curses;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.cards.AbstractEasyCard;
import theuma.powers.UmaMoodPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;
import static theuma.util.Wiz.atb;

public class SkinOutbreakCurse extends AbstractEasyCard {
    public final static String ID = makeID("SkinOutbreakCurse");
    // intellij stuff STATUS, SELF, COMMON, 0, 0, 0, 0, 0, 0

    public SkinOutbreakCurse() {
        super(ID, -2, CardType.CURSE, CardRarity.COMMON, CardTarget.NONE, CardColor.COLORLESS);

        this.cardsToPreview = new Burn();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.dontTriggerOnUseCard) {
            applyToSelf(new UmaMoodPower(AbstractDungeon.player, -1));
            atb(new MakeTempCardInDrawPileAction(new Burn(), 1, true, true, false));
        }

    }

    public void triggerOnEndOfTurnForPlayingCard() {
        this.dontTriggerOnUseCard = true;
        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, true));
    }

    public void upp() {
//        upgradeDamage(0);
//        upgradeBlock(0);
//        upgradeMagicNumber(0);
//        upgradeBaseCost(-2);
    }
}