package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.defect.PermaIncreaseMagicAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.cards.AbstractEasyCard;
import theuma.util.CommonUmaMethods;

import java.util.UUID;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class Yada extends AbstractEasyCard {
    public final static String ID = makeID("Yada");
    // intellij stuff Attack, ALL_ENEMIES, RARE, 2, , , , 1, 1

    public Yada() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 2;
        baseMagicNumber = magicNumber = misc = 1;
        baseSecondMagic = secondMagic = 1;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
//        for(int i = 0; i < magicNumber; i++){
//            dmgRandom(AbstractGameAction.AttackEffect.BLUNT_LIGHT);
//        }
//
//        if(CommonUmaMethods.getCurrentPlayerMood(adp()) == -2){
//            att(new PermaIncreaseMagicAction(this.uuid, this.misc, this.secondMagic));
//        }

        atb(new YadaRandomDamageAction(this, this.secondMagic));
    }

    public class YadaRandomDamageAction extends AbstractGameAction{

        AbstractCard card;
        int scaling;

        YadaRandomDamageAction(AbstractCard card, int scalingAmt){
            this.card = card;
            this.amount = card.magicNumber;
            this.scaling = scalingAmt;
            this.duration = 0.1F;
        }

        @Override
        public void update() {

            if (this.duration == 0.1F) {
                for(int i = 0; i < this.amount; i++){
                    dmgRandomTop(AbstractGameAction.AttackEffect.BLUNT_LIGHT);
                }

                if(CommonUmaMethods.getCurrentPlayerMood(adp()) == -2){
                    att(new PermaIncreaseMagicAction(card.uuid, card.misc, this.scaling));
                }
            }

            this.tickDuration();
        }

    }

    public void applyPowers() {
        this.baseMagicNumber = this.magicNumber = this.misc;
        super.applyPowers();
        this.initializeDescription();
    }

    public void upp() {
        upgradeSecondMagic(1);
    }
}