package theuma.cards.curses;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.red.Bash;
import com.megacrit.cardcrawl.cards.red.Clothesline;
import com.megacrit.cardcrawl.cards.red.Uppercut;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.cards.AbstractEasyCard;
import theuma.powers.UmaMoodPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;
import static theuma.util.Wiz.atb;

public class LegFractureCurse extends AbstractEasyCard {
    public final static String ID = makeID("LegFractureCurse");
    // intellij stuff STATUS, SELF, COMMON, 0, 0, 0, 0, 0, 0

    public LegFractureCurse() {
        super(ID, -2, CardType.CURSE, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
//        this.isEthereal = true;
        this.selfRetain = true;
        this.isInnate = true;

        MultiCardPreview.add(this, new MigraineCurse(), new SkinOutbreakCurse(), new PracticePoorCurse());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void onRetained(){
        applyToSelf(new UmaMoodPower(AbstractDungeon.player, -1));
    }

    @Override
    public void triggerOnExhaust(){
        atb(new MakeTempCardInDrawPileAction(new MigraineCurse(), 1, false, true, false));
        atb(new MakeTempCardInDrawPileAction(new SkinOutbreakCurse(), 1, false, true, false));
        atb(new MakeTempCardInDrawPileAction(new PracticePoorCurse(), 1, false, true, false));
    }

    public void upp() {
//        upgradeDamage(0);
//        upgradeBlock(0);
//        upgradeMagicNumber(0);
//        upgradeBaseCost(-2);
    }
}