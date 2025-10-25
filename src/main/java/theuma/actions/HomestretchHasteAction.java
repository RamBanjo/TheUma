package theuma.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import com.megacrit.cardcrawl.vfx.combat.LightBulbEffect;

public class HomestretchHasteAction extends AbstractGameAction {

    //Amount of card draw.
    int drawnCardCount;

    //The card that called this action. It will be exhausted if it kills an enemy.
    AbstractCard sourceCard;

    DamageInfo di;

    public HomestretchHasteAction(AbstractMonster target, DamageInfo di, int drawnCardCount, AbstractCard sourceCard){

        this.setValues(target, di);
        this.drawnCardCount = drawnCardCount;
        this.actionType = ActionType.DAMAGE;
        this.duration = 0.1F;
        this.di = di;
        this.sourceCard = sourceCard;
    }


    @Override
    public void update() {
        if (this.duration == 0.1F && this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.SLASH_DIAGONAL));
            this.target.damage(this.di);
            if (((this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead) {

                AbstractDungeon.effectList.add(new InflameEffect(AbstractDungeon.player));
                this.addToTop(new DrawCardAction(AbstractDungeon.player, drawnCardCount));
                this.addToTop(new ChangePlayedCardExhaustAction(sourceCard, true));
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }

        this.tickDuration();
    }
}

