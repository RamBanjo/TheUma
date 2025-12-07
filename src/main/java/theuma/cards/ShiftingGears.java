package theuma.cards;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theuma.cards.AbstractEasyCard;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class ShiftingGears extends AbstractEasyCard {
    public final static String ID = makeID("ShiftingGears");
    // intellij stuff SKILL, SELF, UNCOMMON, , , , , , 

    public ShiftingGears() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        if (p.getPower(VigorPower.POWER_ID) == null){
            return;
        }

        int vigorStacks = Math.floorDiv(p.getPower(VigorPower.POWER_ID).amount , 2);

        atb(new ReducePowerAction(adp(), adp(), VigorPower.POWER_ID, vigorStacks));

        if (vigorStacks > 0){
            applyToSelf(new StrengthPower(adp(), vigorStacks));
        }
    }

    public void upp() {
        this.exhaust = false;
    }
}