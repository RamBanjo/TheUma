package theuma.cards;

import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import theuma.powers.OversightPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;
import static theuma.util.Wiz.atb;

public class Buono extends AbstractEasyCard {
    public final static String ID = makeID("Buono");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Buono() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);

        this.baseMagicNumber = this.magicNumber = 0;
        this.selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractPower currentDex = p.getPower(DexterityPower.POWER_ID);

        if(currentDex != null){
            if(currentDex.amount < 0){
                applyToSelf(new DexterityPower(p, (currentDex.amount * -1) + magicNumber));
            }
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }
}