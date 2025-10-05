package theuma.cards.status;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawReductionPower;
import theuma.cards.AbstractEasyCard;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class LateStartStatus extends AbstractEasyCard {
    public final static String ID = makeID("LateStartStatus");
    // intellij stuff STATUS, SELF, COMMON, 0, 0, 0, 0, 0, 0

    public LateStartStatus() {
        super(ID, -2, CardType.STATUS, CardRarity.COMMON, CardTarget.NONE, CardColor.COLORLESS);
        this.isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void triggerOnExhaust(){
        applyToSelfTop(new DrawReductionPower(AbstractDungeon.player, 1));
    }

    public void upp() {
//        upgradeDamage(0);
//        upgradeBlock(0);
//        upgradeMagicNumber(0);
//        upgradeBaseCost(-2);
    }
}