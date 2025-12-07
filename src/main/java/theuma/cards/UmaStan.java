package theuma.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Buffer;
import com.megacrit.cardcrawl.cards.purple.SpiritShield;
import com.megacrit.cardcrawl.cards.red.Impervious;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.CharacterFile;
import theuma.powers.UmaMoodPower;

import java.util.ArrayList;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.adp;
import static theuma.util.Wiz.applyToSelf;

public class UmaStan extends AbstractEasyCard{

    public final static String ID = makeID("UmaStan");

    public UmaStan() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);

        this.baseDamage = 8;
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        dmg(abstractMonster, AbstractGameAction.AttackEffect.FIRE);
        if (cardConditional()) applyToSelf(new UmaMoodPower(abstractPlayer, 1));
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = cardConditional() ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public boolean cardConditional(){

        for(AbstractCard c : adp().hand.group){
            if(c.color != CharacterFile.Enums.UMA_COLOR){
                return true;
            }
        }

        return false;
    }

}
