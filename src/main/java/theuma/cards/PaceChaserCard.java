package theuma.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import theuma.vfx.OutOfCardsThoughtBubbleEffect;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class PaceChaserCard extends AbstractEasyCard{
    public final static String ID = makeID("PaceChaser");

    public PaceChaserCard(){
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);

        this.baseMagicNumber = this.magicNumber = 4;
        this.baseSecondMagic = this.secondMagic = 0;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
        upgradeSecondMagic(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        //draw 1 card, do a followup action
        atb(new DrawCardAction(1, new PaceChaserDrawAction(magicNumber, secondMagic)));
    }

    public static class PaceChaserDrawAction extends AbstractGameAction{

        private int minimum = 0;

        PaceChaserDrawAction(int blockMult, int minimum){
            amount = blockMult;
            this.minimum = minimum;
        }

        @Override
        public void update() {
            for (AbstractCard c: DrawCardAction.drawnCards){
                int effectiveCost = Math.max(c.costForTurn, minimum);

                if(c.costForTurn == -1){
                    effectiveCost = Math.max(EnergyPanel.getCurrentEnergy(), minimum);
                }

                if(effectiveCost * amount > 0){
                    att(new GainBlockAction(adp(), adp(), effectiveCost * amount));
                }
            }
            isDone = true;
        }
    }
}
