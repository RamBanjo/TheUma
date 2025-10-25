package theuma.cards;

import basemod.devcommands.draw.Draw;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.FreeAttackPower;
import theuma.powers.SkillRefundPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;
import static theuma.util.Wiz.atb;

public class SwingingMaestro extends AbstractEasyCard {
    public final static String ID = makeID("SwingingMaestro");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public SwingingMaestro() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
        applyToSelf(new FreeAttackPower(p, 1));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}