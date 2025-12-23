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
        addToBot(new MakeTempCardInHandAction(newPulse));
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        this.cost = 1;
    }

    @Override
    public void setCostForTurn(int amt) {
        super.setCostForTurn(amt);
        this.costForTurn = 1;
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}