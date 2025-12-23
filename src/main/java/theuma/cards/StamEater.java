package theuma.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.unique.VampireDamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BloodShotEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;
import theuma.actions.FirstRateStrikeAction;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class StamEater extends AbstractEasyCard {
    public final static String ID = makeID("StamEater");
    // intellij stuff , , , , , , , ,

    public StamEater() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);

        this.baseDamage = 6;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        atb(new SFXAction("ORB_LIGHTNING_EVOKE", 0.1F));

        if(m != null){
            CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
            atb(new VFXAction(new LightningEffect(m.hb.cX, m.hb.cY)));
        }

        atb(new VampireDamageAction(m, new DamageInfo(p, this.damage, this.damageType), AbstractGameAction.AttackEffect.NONE));
    }

    public void upp() {
        upgradeDamage(2);
    }
}