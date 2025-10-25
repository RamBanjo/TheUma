package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theuma.util.CommonUmaMethods;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class MystifyingMurmur extends AbstractEasyCard {
    public final static String ID = makeID("MystifyingMurmur");
    // intellij stuff Attack, Enemy, Common, 5, 8, , , ,

    public MystifyingMurmur() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 10;
        baseMagicNumber = 2;
        this.exhaust = true;

        this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);
        for(AbstractMonster mons : getEnemies()){
            applyToEnemy(mons, new StrengthPower(mons, -1 * baseMagicNumber));
        }

        if (cardConditional()){
            atb(new MakeTempCardInDiscardAction(new MystifyingMurmur(), 1));
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = cardConditional() ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public boolean cardConditional(){
        return CommonUmaMethods.playerIsSorrowful(adp());
    }

    public void upp() {
        upgradeDamage(4);
        upgradeMagicNumber(1);

    }
}