package theuma.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.purple.Swivel;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.FreeAttackPower;
import theuma.powers.SkillRefundPower;
import theuma.powers.UmaMoodPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;
import static theuma.util.Wiz.atb;

public class BreathOfFreshAir extends AbstractEasyCard {
    public final static String ID = makeID("BreathOfFreshAir");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public BreathOfFreshAir() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(magicNumber));
        applyToSelf(new SkillRefundPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}