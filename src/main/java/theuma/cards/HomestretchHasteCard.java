package theuma.cards;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.actions.BlueRoseCloserAction;
import theuma.actions.HomestretchHasteAction;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class HomestretchHasteCard extends AbstractEasyCard {
    public final static String ID = makeID("HomestretchHaste");
    // intellij stuff attack, enemy, rare, , , , , 0, 1

    public HomestretchHasteCard() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new HomestretchHasteAction(m, new DamageInfo(p, damage, this.damageType), magicNumber, this));
    }

    @Override
    public void upp() {

        upgradeDamage(4);
        upgradeMagicNumber(2);
    }
}