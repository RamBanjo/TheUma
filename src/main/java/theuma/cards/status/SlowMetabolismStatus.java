package theuma.cards.status;

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
        this.selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void onRetained() {
        applyToSelf(new StrengthPower(AbstractDungeon.player, 1));
        applyToSelf(new DexterityPower(AbstractDungeon.player, -2));
    }
    public void upp() {
//        upgradeDamage(0);
//        upgradeBlock(0);
//        upgradeMagicNumber(0);
//        upgradeBaseCost(-2);
    }
}