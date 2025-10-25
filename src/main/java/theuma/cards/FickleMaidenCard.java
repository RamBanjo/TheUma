package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.cards.AbstractEasyCard;
import theuma.powers.FickleMaidenPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class FickleMaidenCard extends AbstractEasyCard {
    public final static String ID = makeID("FickleMaiden");
    // intellij stuff CardType.ATTACK, CardTarget.ENEMY, CardRarity.COMMON, 4, 2, 0, 0, 0, 0

    public FickleMaidenCard() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 4;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }


    @Override
    public void triggerOnExhaust() {
        applyToSelf(new FickleMaidenPower(adp(), 1));
    }

    public void upp() {
        upgradeDamage(2);
    }
}