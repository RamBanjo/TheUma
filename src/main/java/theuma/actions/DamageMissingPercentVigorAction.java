package theuma.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import static theuma.util.Wiz.*;

public class DamageMissingPercentVigorAction extends AbstractGameAction {

    private DamageInfo info;


    private int missingThreshold;

    public DamageMissingPercentVigorAction(AbstractCreature target, DamageInfo info, int missingThreshold) {
        this.info = info;
        this.setValues(target, info);
        this.missingThreshold = missingThreshold;
        this.actionType = ActionType.DAMAGE;
        this.startDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startDuration;
    }

    @Override
    public void update() {
        if (this.shouldCancelAction()) {
            this.isDone = true;
        } else {
            this.tickDuration();
            if (this.isDone) {

                AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.BLUNT_LIGHT, false));
                this.target.damage(this.info);
                int missingP = (int) Math.floor((1 - (((float)this.target.currentHealth) / (float)this.target.maxHealth)) * 100);
                int thresholdsPassed = Math.floorDiv(missingP, missingThreshold);

                applyToSelf(new VigorPower(adp(), thresholdsPassed));

                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                    AbstractDungeon.actionManager.clearPostCombatActions();
                } else {
                    att(new WaitAction(0.1F));
                }
            }
        }
    }
}
