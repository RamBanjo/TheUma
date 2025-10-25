package theuma.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.FlameAnimationEffect;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import com.megacrit.cardcrawl.vfx.PetalEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.megacrit.cardcrawl.vfx.combat.GrandFinalEffect;
import com.megacrit.cardcrawl.vfx.combat.LightBulbEffect;
import theuma.vfx.BlueInflameEffect;

public class BlueRoseCloserAction extends AbstractGameAction {

    //Amount of card draw and energy that will happen if an enemy dies from this
    int drawAndEnergy;

    DamageInfo di;

    public BlueRoseCloserAction(AbstractMonster target, DamageInfo di, int drawAndEnergy){

        this.setValues(target, di);
        this.drawAndEnergy = drawAndEnergy;
        this.actionType = ActionType.DAMAGE;
        this.duration = 0.1F;
        this.di = di;
    }


    @Override
    public void update() {
        if (this.duration == 0.1F && this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.SLASH_DIAGONAL));
            this.target.damage(this.di);
            if (((this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead) {
                AbstractDungeon.player.gainEnergy(drawAndEnergy);
                this.addToTop(new DrawCardAction(AbstractDungeon.player, drawAndEnergy));

                AbstractDungeon.effectList.add(new BlueInflameEffect(AbstractDungeon.player));
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }

        this.tickDuration();
    }
}

