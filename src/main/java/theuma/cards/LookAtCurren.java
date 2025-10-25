package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.util.CommonUmaMethods;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.adp;

public class LookAtCurren extends AbstractEasyCard {
    public final static String ID = makeID("LookAtCurren");
    // intellij stuff Attack, Enemy, Common, 5, 8, , , ,

    public LookAtCurren() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 10;
        this.exhaust = true;

        this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);

        if (cardConditional()){
            allDmg(AbstractGameAction.AttackEffect.FIRE);
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = cardConditional() ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public boolean cardConditional(){
        return CommonUmaMethods.playerIsJoyful(adp());
    }

    public void upp() {
        upgradeDamage(3);
        upgradeSecondDamage(5);

    }
}