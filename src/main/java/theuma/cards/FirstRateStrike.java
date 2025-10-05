package theuma.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;
import theuma.actions.FirstRateStrikeAction;
import theuma.cards.AbstractEasyCard;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class FirstRateStrike extends AbstractEasyCard {
    public final static String ID = makeID("FirstRateStrike");
    // intellij stuff , , , , , , , , 

    public FirstRateStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);

        this.baseDamage = 3;
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new FirstRateStrikeAction(m, new DamageInfo(p, this.damage, this.damageType)));
//        System.out.println(m.lastDamageTaken);
//        dmg(m, AbstractGameAction.AttackEffect.SMASH);
//        att(new VFXAction(new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.GREEN), 0.5f));
//        att(new WaitAction(0.5f));
//
//        m.damage(new DamageInfo(p, this.damage, this.damageType));
////        System.out.println(m.lastDamageTaken);
//        if (m.lastDamageTaken > 0){
//            applyToSelfTop(new VigorPower(p, m.lastDamageTaken));
//        }
    }

    public void upp() {
        upgradeDamage(1);
    }
}