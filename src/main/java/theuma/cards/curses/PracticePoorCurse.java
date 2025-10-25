package theuma.cards.curses;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.cards.AbstractEasyCard;
import theuma.powers.UmaMoodPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;
import static theuma.util.Wiz.atb;

public class PracticePoorCurse extends AbstractEasyCard {
    public final static String ID = makeID("PracticePoorCurse");
    // intellij stuff STATUS, SELF, COMMON, 0, 0, 0, 0, 0, 0

    public PracticePoorCurse() {
        super(ID, -2, CardType.CURSE, CardRarity.COMMON, CardTarget.NONE, CardColor.CURSE);
        this.isEthereal = true;
        this.cardsToPreview = new Dazed();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void triggerOnExhaust(){
        applyToSelf(new UmaMoodPower(AbstractDungeon.player, -1));
        atb(new MakeTempCardInDrawPileAction(new Dazed(), 2, true, true, false));
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    public void upp() {

    }
}