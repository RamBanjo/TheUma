package theuma.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.powers.AgemasenPlusPower;
import theuma.powers.AgemasenPower;
import theuma.powers.DeepBreathsPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;

public class ExtraTank extends AbstractEasyCard {
    public final static String ID = makeID("ExtraTank");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public ExtraTank() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        if(upgraded){
            applyToSelf(new AgemasenPlusPower(p, magicNumber));
        }else{
            applyToSelf(new AgemasenPower(p, magicNumber));
        }
    }

    @Override
    public void upp() {

    }
}