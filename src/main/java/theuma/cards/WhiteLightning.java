package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class WhiteLightning extends AbstractEasyCard {
    public final static String ID = makeID("WhiteLightning");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public WhiteLightning() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 4;
        this.baseMagicNumber = this.magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        for (int i = 0; i < magicNumber; i++){
            atb(new VFXAction(new LightningEffect(m.drawX, m.drawY)));
            atb(new SFXAction("ORB_LIGHTNING_EVOKE", 0.1F));
            dmg(m, AbstractGameAction.AttackEffect.NONE);
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}