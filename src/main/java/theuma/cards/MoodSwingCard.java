package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.powers.UmaMoodPower;
import theuma.util.CommonUmaMethods;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class MoodSwingCard extends AbstractEasyCard {
    public final static String ID = makeID("MoodSwing");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public MoodSwingCard() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 6;

        this.baseMagicNumber = this.magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        applyToSelf(new UmaMoodPower(p, -1));

        if(cardConditional()){
            for (int i = 0; i < magicNumber; i++){
                dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
            }
        }


    }

    public void triggerOnGlowCheck() {
        this.glowColor = cardConditional() ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public boolean cardConditional(){
        return CommonUmaMethods.playerIsSorrowful(adp());
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}