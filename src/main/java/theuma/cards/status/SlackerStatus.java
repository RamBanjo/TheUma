package theuma.cards.status;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawReductionPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theuma.cards.AbstractEasyCard;
import theuma.powers.UmaMoodPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class SlackerStatus extends AbstractEasyCard {
    public final static String ID = makeID("SlackerStatus");
    // intellij stuff STATUS, SELF, COMMON, 0, 0, 0, 0, 0, 0

    public SlackerStatus() {
        super(ID, -2, CardType.STATUS, CardRarity.COMMON, CardTarget.NONE, CardColor.COLORLESS);
        this.isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void triggerWhenDrawn(){
        atb(new GainEnergyAction(1));
        applyToSelf(new UmaMoodPower(AbstractDungeon.player, -1));
        applyToSelf(new StrengthPower(AbstractDungeon.player, -1));
    }

    public void upp() {
//        upgradeDamage(0);
//        upgradeBlock(0);
//        upgradeMagicNumber(0);
//        upgradeBaseCost(-2);
    }
}