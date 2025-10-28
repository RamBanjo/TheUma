package theuma.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import org.lwjgl.Sys;
import theuma.util.Wiz;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class UmadolPower extends AbstractEasyPower{

    public final static String ID = makeID("UmadolPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public UmadolPower(AbstractCreature owner, int amount) {
        super(ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void atEndOfRound() {
        Wiz.reducePower(this, 1);
        if (this.amount == 0){
            atb(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {

        this.flash();

        int vigorGain = (int) Math.floor((float)info.output * 0.5);

        if (vigorGain > 0){
            atb(new ApplyPowerAction(this.owner, this.owner, new VigorPower(this.owner, vigorGain)));
        }

        return 0;
    }

    @Override
    public void updateDescription() {

        if (this.amount == 1){
            description = DESCRIPTIONS[0];
        }else{
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }

    }
}
