package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.util.CommonUmaMethods;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.adp;

public class AdoredByAll extends AbstractEasyCard {
    public final static String ID = makeID("AdoredByAll");
    // intellij stuff Attack, Enemy, Common, 5, 8, , , , 

    public AdoredByAll() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 10;
        baseSecondDamage = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        altDmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);

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
        upgradeDamage(5);
        upgradeSecondDamage(3);

    }
}