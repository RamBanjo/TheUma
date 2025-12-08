package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theuma.ModFile.makeID;

public class TriumphantPulse extends AbstractEasyCard {
    public final static String ID = makeID("TriumphantPulse");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public TriumphantPulse() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 8;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);

        AbstractCard newPulse = makeStatEquivalentCopy();
        if (newPulse.costForTurn != 1 || newPulse.cost != 1){
            newPulse.cost = 0;
            newPulse.setCostForTurn(1);
            newPulse.isCostModified = false;
        }

        addToBot(new MakeTempCardInHandAction(newPulse));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}