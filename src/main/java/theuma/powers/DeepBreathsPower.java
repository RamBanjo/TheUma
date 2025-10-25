package theuma.powers;

import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theuma.powers.hooks.OnLoseEnergyPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class DeepBreathsPower extends AbstractEasyPower implements OnLoseEnergyPower {

    public final static String ID = makeID("DeepBreathsPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private boolean triggeredThisTurn = false;

    public DeepBreathsPower(AbstractCreature owner, int amount) {
        super(ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        if (triggeredThisTurn){
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }else{
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        }
    }

    @Override
    public void onLoseEnergy(int lostEnergy) {
        if(EnergyPanel.getCurrentEnergy() == 0 && !triggeredThisTurn){
            this.flash();
            triggeredThisTurn = true;
            applyToSelf(new VigorPower(adp(), amount));
            updateDescription();
        }
    }

    @Override
    public void atStartOfTurn() {
        triggeredThisTurn = false;
        updateDescription();
    }
}
