package theuma.cards;


import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

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
            int effective_block_mult = card_drawn.costForTurn;

            //since we know -1 is x cost and -2 is unplayable
            //we would set the effective mult to be the max between the mult itself or second magic.
            effective_block_mult = Math.max(effective_block_mult, secondMagic);

            System.out.println(effective_block_mult + " times " + magicNumber + " makes " + effective_block_mult * magicNumber);

            atb(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, effective_block_mult * magicNumber));
        }else{
//            ThoughtBubble tb = new ThoughtBubble(abstractPlayer.dialogX, abstractPlayer.dialogY, CANT_DRAW_STRING, true);
//            AbstractDungeon.effectList.add(tb);
        }
    }
}
