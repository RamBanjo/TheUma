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

public class SoftStep extends AbstractEasyCard {
    public final static String ID = makeID("SoftStep");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public SoftStep() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.SELF_AND_ENEMY);
        baseDamage = 4;
        baseBlock = 4;
        baseMagicNumber = magicNumber = 1;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        blck();

        if (cardConditional()){
            atb(new DrawCardAction(magicNumber));
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
        upgradeDamage(2);
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }
}