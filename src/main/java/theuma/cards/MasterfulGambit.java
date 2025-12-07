package theuma.cards;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.actions.DamageMissingPercentVigorAction;
import theuma.cards.AbstractEasyCard;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class MasterfulGambit extends AbstractEasyCard {
    public final static String ID = makeID("MasterfulGambit");
    // intellij stuff CardType.ATTACK, CardTarget.ENEMY, CardRarity.COMMON, 6, 2, , , 15, -5

    public MasterfulGambit() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 6;
        baseMagicNumber = magicNumber = 15;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DamageMissingPercentVigorAction(m, new DamageInfo(p, baseDamage, damageType), magicNumber));
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(-5);

    }
}