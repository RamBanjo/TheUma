package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMiscAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.WallopEffect;
import com.megacrit.cardcrawl.vfx.combat.LightBulbEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import theuma.actions.ExhaustOneThenCheckCardConditionAction;
import theuma.actions.FailText;
import theuma.actions.PermaIncreaseDamageAction;
import theuma.util.CommonUmaMethods;

import java.util.UUID;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;
import static theuma.util.Wiz.att;

public class ShootingStar extends AbstractEasyCard {
    public final static String ID = makeID("ShootingStar");
    // intellij stuff attack, enemy, rare, , , , , 0, 1

    public ShootingStar() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = this.misc = 10;
        this.baseMagicNumber = this.magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        atb(new ShootingStarDamageAction(m, this.uuid, this.damage, this.magicNumber));

    }

    public class ShootingStarDamageAction extends AbstractGameAction{

        UUID uuid;
        int scaling;

        ShootingStarDamageAction(AbstractMonster target, UUID uuid, int damage, int scaling){
            this.target = target;
            this.uuid = uuid;
            this.amount = damage;
            this.scaling = scaling;
        }


        @Override
        public void update() {
            if (target != null){
                atb(new VFXAction(new WallopEffect(this.amount, target.hb.cX, target.hb.cY)));
            }

            dmg((AbstractMonster) target, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
            att(new ExhaustOneThenCheckCardConditionAction(
                    CommonUmaMethods::cardIsNonUma,
                    new MiracleEffect(),
                    new PermaIncreaseDamageAction(this.uuid, this.amount, this.scaling),
                    null,
                    null,
                    FailText.MY_CARD
            ));

            this.isDone = true;
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}
