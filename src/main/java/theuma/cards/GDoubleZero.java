package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.PermaIncreaseMagicAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theuma.util.CommonUmaMethods;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;
import static theuma.util.Wiz.atb;

public class GDoubleZero extends AbstractEasyCard {
    public final static String ID = makeID("GDoubleZero");
    // intellij stuff Attack, ALL_ENEMIES, RARE, 2, , , , 1, 1

    public GDoubleZero() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.SELF_AND_ENEMY);
        baseDamage = 8;
        baseMagicNumber = magicNumber = misc = 2;
        baseSecondMagic = secondMagic = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        applyToSelf(new VigorPower(p, magicNumber));
    }

    @Override
    public void triggerOnExhaust() {
        atb(new PermaIncreaseMagicAction(this.uuid, this.misc, this.secondMagic));
    }

    public void applyPowers() {
        this.baseMagicNumber = this.magicNumber = this.misc;
        super.applyPowers();
        this.initializeDescription();
    }

    public void upp() {
        upgradeSecondMagic(1);
    }
}