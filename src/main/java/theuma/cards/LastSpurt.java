package theuma.cards;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import theuma.actions.EasyXCostAction;
import theuma.vfx.OutOfCardsThoughtBubbleEffect;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class LastSpurt extends AbstractEasyCard {
    public final static String ID = makeID("LastSpurt");
    // intellij stuff attack, enemy, rare, , , , , 0, 1

    public LastSpurt() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public static class LastSpurtDrawAction extends AbstractGameAction{

        public LastSpurtDrawAction(int cardsToMakeFree){
            this.amount = cardsToMakeFree;
        }


        @Override
        public void update() {

            int attacksMadeFree = 0;

            for (AbstractCard c: DrawCardAction.drawnCards){
                if(c.type == CardType.ATTACK && attacksMadeFree < this.amount){
                    attacksMadeFree += 1;
                    c.freeToPlayOnce = true;
                }
            }

            this.isDone = true;
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        atb(new EasyXCostAction(this, (effect, params) -> {

            atb(new DrawCardAction(effect * 2, new LastSpurtDrawAction(params[0])));

            return true;
        }, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}