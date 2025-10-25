package theuma.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import com.megacrit.cardcrawl.vfx.combat.LightBulbEffect;
import theuma.util.CommonUmaMethods;
import theuma.util.Wiz;
import theuma.vfx.ThatsMyCardThoughtBubbleEffect;

import java.util.Objects;
import java.util.function.Predicate;

public class ExhaustOneThenCheckCardConditionAction extends AbstractGameAction {

    private static final UIStrings uiStrings;
    public static final String[] TEXT;

    private AbstractPlayer p;

    private Predicate<AbstractCard> pred;
    private AbstractGameEffect passEffect;
    private AbstractGameAction actIfPredPasses;

    private AbstractGameEffect failEffect;
    private AbstractGameAction actIfPredFails;
    private FailText textIfFail = FailText.NONE;

    public ExhaustOneThenCheckCardConditionAction(Predicate<AbstractCard> pred, AbstractGameEffect passEffect, AbstractGameAction actIfPredPasses, AbstractGameEffect failEffect, AbstractGameAction actIfPredFails) {

        this.p = Wiz.adp();

        //no matter what we'll always exhaust 1 card.
        this.amount = 1;

        this.pred = pred;

        this.passEffect = passEffect;
        this.actIfPredPasses = actIfPredPasses;

        this.failEffect = failEffect;
        this.actIfPredFails = actIfPredFails;

        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    }

    public ExhaustOneThenCheckCardConditionAction(Predicate<AbstractCard> pred, AbstractGameEffect passEffect, AbstractGameAction actIfPredPasses, AbstractGameEffect failEffect, AbstractGameAction actIfPredFails, FailText textIfFail){
        this(pred, passEffect, actIfPredPasses, failEffect, actIfPredFails);

        this.textIfFail = textIfFail;
    }

    @Override
    public void update() {
        if (this.duration == this.startDuration) {
            if (this.p.hand.size() == 0) {
                this.isDone = true;
                return;
            }

            if (this.p.hand.size() <= this.amount) {
                this.amount = this.p.hand.size();
                int tmp = this.p.hand.size();

                for(int i = 0; i < tmp; ++i) {
                    AbstractCard c = this.p.hand.getTopCard();
                    this.p.hand.moveToExhaustPile(c);
                    exhaustedCardActionCheck(c);
                }

                CardCrawlGame.dungeon.checkForPactAchievement();
                return;
            }

            AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false, false);
            this.tickDuration();
            return;

        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for(AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                this.p.hand.moveToExhaustPile(c);
                exhaustedCardActionCheck(c);
            }

            CardCrawlGame.dungeon.checkForPactAchievement();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        this.tickDuration();
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
        TEXT = uiStrings.TEXT;
    }

    public void exhaustedCardActionCheck(AbstractCard c){
        if (pred.test(c)){

            if (passEffect != null){
                AbstractDungeon.effectList.add(passEffect);
            }

            if (actIfPredPasses != null){
                addToTop(actIfPredPasses);
            }

        }else{
            if (failEffect != null){
                AbstractDungeon.effectList.add(failEffect);
            }

            if (Objects.requireNonNull(textIfFail) == FailText.MY_CARD) {
                AbstractDungeon.effectList.add(new ThatsMyCardThoughtBubbleEffect(p.dialogX, p.dialogY));
            }

            if (actIfPredFails != null){
                addToTop(actIfPredFails);
            }
        }
    }


}

