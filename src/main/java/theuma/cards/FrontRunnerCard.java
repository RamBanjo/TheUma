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

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

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

        //draw 1 card
        atb(new DrawCardAction(1));

        //check if we actually drew something
        AbstractCard card_drawn = null;

        boolean something_was_drawn = !DrawCardAction.drawnCards.isEmpty();
            if (something_was_drawn){
            card_drawn = DrawCardAction.drawnCards.get(0);
        }

        //if we drew a card...
        if (card_drawn != null){
            int seen_cost = card_drawn.costForTurn;

            //since we know -1 is x cost and -2 is unplayable
            //we would set the effective mult to be the max between the mult itself or second magic.
            seen_cost = Math.max(seen_cost, magicNumber);

            for (int i = 0; i < seen_cost; i ++){
                dmg(abstractMonster, AbstractGameAction.AttackEffect.FIRE);
            }
        }else{
//            ThoughtBubble tb = new ThoughtBubble(abstractPlayer.dialogX, abstractPlayer.dialogY, CANT_DRAW_STRING, true);
//            AbstractDungeon.effectList.add(tb);
        }
    }
}
