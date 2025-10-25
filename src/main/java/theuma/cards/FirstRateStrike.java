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

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        if (m != null){
            atb(new VFXAction(new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.GREEN)));
        }
        atb(new FirstRateStrikeAction(m, new DamageInfo(p, this.damage, this.damageType)));
    }

    public void upp() {
        upgradeDamage(1);
    }
}