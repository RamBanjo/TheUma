package theuma.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
import theuma.actions.ClawMachineScalingAction;
import theuma.powers.UmaMoodPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;
import static theuma.util.Wiz.atb;

public class ClawMachine extends AbstractEasyCard {
    public final static String ID = makeID("ClawMachine");
    // intellij stuff , , , 4, , , , 2, 3

    public ClawMachine() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 3;
        baseMagicNumber = magicNumber = 2;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            atb(new VFXAction(new ClawEffect(m.hb.cX, m.hb.cY, Color.CYAN, Color.PINK), 0.1F));
        }

        dmg(m, AbstractGameAction.AttackEffect.NONE);
        applyToSelf(new UmaMoodPower(p, 1));
        atb(new ClawMachineScalingAction(this, magicNumber));
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}