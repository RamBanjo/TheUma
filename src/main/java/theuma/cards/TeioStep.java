package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theuma.cards.AbstractEasyCard;
import theuma.powers.LambdaPower;
import theuma.powers.TeioStepPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class TeioStep extends AbstractEasyCard {
    public final static String ID = makeID("TeioStep");
    // intellij stuff ATTACK, ENEMY, UNCOMMON, 9, , , , 3, 2

    public TeioStep() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 9;
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);

        applyToEnemy(m, new TeioStepPower(m, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);

    }
}