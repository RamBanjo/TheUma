package theuma.powers;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateFastAttackAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.BouncingFlask;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.megacrit.cardcrawl.vfx.combat.PotionBounceEffect;
import com.megacrit.cardcrawl.vfx.combat.WaterSplashParticleEffect;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class CutAndDrivePower extends AbstractEasyPower{

    public final static String ID = makeID("CutAndDrivePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CutAndDrivePower(AbstractCreature owner, int amount) {
        super(ID, NAME, PowerType.BUFF, true, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        removePower(this);
    }

    @Override
    public void onCardDraw(AbstractCard card) {

        if(!AbstractDungeon.getMonsters().areMonstersBasicallyDead()){
            this.flash();
            AbstractMonster victim = AbstractDungeon.getRandomMonster();
            if (victim != null){
                atb(new LoseHPAction(victim, null, this.amount));
//                if(victim.currentHealth > 0 && !victim.halfDead){
//
//                }
            }

        }
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }
}
