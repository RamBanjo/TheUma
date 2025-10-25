package theuma.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theuma.vfx.OutOfCardsThoughtBubbleEffect;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;
import static theuma.util.Wiz.adp;

public class FrontRunnerCard extends AbstractEasyCard{
    public final static String ID = makeID("FrontRunner");

    public FrontRunnerCard(){
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);

        this.baseDamage = 3;
        this.baseMagicNumber = this.magicNumber = 0;
//        this.baseSecondMagic = this.secondMagic = 0;

    }

    @Override
    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
//        upgradeSecondMagic(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        //draw 1 card, do a followup action
        atb(new DrawCardAction(1, new FrontRunnerDrawAction(damage, secondMagic, abstractMonster)));
    }

    public static class FrontRunnerDrawAction extends AbstractGameAction{

        private int minimum = 0;

        FrontRunnerDrawAction(int dam, int minimum, AbstractMonster target){
            amount = dam;
            this.minimum = minimum;
            this.target = target;
        }

        @Override
        public void update() {
            for (AbstractCard c: DrawCardAction.drawnCards){
                int effectiveCost = Math.max(c.costForTurn, minimum);

                if(c.costForTurn == -1){
                    effectiveCost = Math.max(EnergyPanel.getCurrentEnergy(), minimum);
                }

                for(int i = 0; i < effectiveCost; i ++){
                    att(new DamageAction(target, new DamageInfo(adp(), amount), AttackEffect.BLUNT_LIGHT));
                }

            }
            isDone = true;
        }
    }
}
