package theuma.cards;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import theuma.cards.curses.MigraineCurse;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class RedShift extends AbstractEasyCard {
    public final static String ID = makeID("RedShift");
    // intellij stuff attack, enemy, rare, , , , , 0, 1

    public RedShift() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.cardsToPreview = new Burn();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int cardsToDraw = BaseMod.MAX_HAND_SIZE - hand().size();

        cardsToDraw = Math.min(cardsToDraw, p.drawPile.size() + p.discardPile.size());

        if(hand().size() == BaseMod.MAX_HAND_SIZE){
            p.createHandIsFullDialog();
        }else if(p.hasPower(NoDrawPower.POWER_ID)){
            p.getPower(NoDrawPower.POWER_ID).flash();
        }else{
            atb(new DrawCardAction(cardsToDraw));

            if(cardsToDraw < 6){
                atb(new MakeTempCardInDiscardAction(new Burn(), cardsToDraw));
            }else{
                while(cardsToDraw > 0){
                    if (cardsToDraw > 5){
                        atb(new MakeTempCardInDiscardAction(new Burn(), 5));
                        cardsToDraw -= 5;
                    }else{
                        atb(new MakeTempCardInDiscardAction(new Burn(), cardsToDraw));
                        cardsToDraw = 0;
                    }
                }
            }
        }
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }
}