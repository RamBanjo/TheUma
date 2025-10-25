package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.util.CommonUmaMethods;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.adp;
import static theuma.util.Wiz.atb;

public class SuperDuperStoked extends AbstractEasyCard {
    public final static String ID = makeID("SuperDuperStoked");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public SuperDuperStoked() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);

        if (cardConditional()){
            dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = cardConditional() ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public boolean cardConditional(){
        return CommonUmaMethods.playerIsJoyful(adp());
    }

    @Override
    public void upp() {
        upgradeDamage(4);
    }
}