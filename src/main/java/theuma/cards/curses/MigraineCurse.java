package theuma.cards.curses;

import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.DrawReductionPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theuma.cards.AbstractEasyCard;
import theuma.powers.UmaMoodPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;
import static theuma.util.Wiz.applyToSelfTop;

public class MigraineCurse extends AbstractEasyCard {
    public final static String ID = makeID("MigraineCurse");
    // intellij stuff STATUS, SELF, COMMON, 0, 0, 0, 0, 0, 0

    public MigraineCurse() {
        super(ID, -2, CardType.CURSE, CardRarity.COMMON, CardTarget.NONE, CardColor.CURSE);
        this.isEthereal = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.dontTriggerOnUseCard) {
            applyToSelf(new UmaMoodPower(p, -2));
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