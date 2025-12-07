package theuma.cards;

import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BerserkPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.RitualPower;
import theuma.powers.EmperorsDivinityPower;
import theuma.powers.EmperorsMightPower;
import theuma.powers.OversightPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;
import static theuma.util.Wiz.atb;

public class Oversight extends AbstractEasyCard {
    public final static String ID = makeID("Oversight");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Oversight() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new BerserkPower(p, 1));
        atb(new WaitAction(Settings.ACTION_DUR_FAST));
        applyToSelf(new RitualPower(p, 2, true));
        atb(new WaitAction(Settings.ACTION_DUR_FAST));
        applyToSelf(new MetallicizePower(p, 4));
        atb(new WaitAction(Settings.ACTION_DUR_FAST));
        applyToSelf(new OversightPower(p));
    }

    @Override
    public void upp() {
        upgradeBaseCost(2);
    }
}