package theuma.cards;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theuma.actions.EasyXCostAction;
import theuma.vfx.OutOfCardsThoughtBubbleEffect;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class DailyShop extends AbstractEasyCard {
    public final static String ID = makeID("DailyShop");
    // intellij stuff attack, enemy, rare, , , , , 0, 1

    public DailyShop() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 0;
        exhaust = true;
    }

    public static class DailyShopDrawAction extends AbstractGameAction{

        DailyShopDrawAction(int costRemaining){
            this.amount = costRemaining;
        }


        @Override
        public void update() {

            int drawnCost = 0;

            for (AbstractCard c: DrawCardAction.drawnCards){

                drawnCost = Math.max(c.costForTurn, 0);
                c.setCostForTurn(0);
            }

            if (this.amount <= 0) {
                this.isDone = true;

            }else if (hand().size() >= BaseMod.MAX_HAND_SIZE) {

                adp().createHandIsFullDialog();
                this.isDone = true;

            }else if (adp().hasPower(NoDrawPower.POWER_ID)) {

                adp().getPower(NoDrawPower.POWER_ID).flash();
                this.isDone = true;


            }else if (drawPile().isEmpty() && discardPile().isEmpty()){
                vfx(new OutOfCardsThoughtBubbleEffect(adp().dialogX, adp().dialogY));
                this.isDone = true;
            }else{
                atb(new DrawCardAction(1, new DailyShopDrawAction(this.amount - drawnCost)));
            }

            this.isDone = true;
        }


    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        atb(new EasyXCostAction(this, (effect, params) -> {

            atb(new DrawCardAction(1, new DailyShopDrawAction(effect + params[0])));

            return true;
        }, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}