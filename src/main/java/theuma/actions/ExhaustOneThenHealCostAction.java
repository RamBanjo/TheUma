package theuma.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import theuma.util.Wiz;
import theuma.vfx.ThatsMyCardThoughtBubbleEffect;

import java.util.Objects;
import java.util.function.Predicate;

public class ExhaustOneThenHealCostAction extends AbstractGameAction {

    private static final UIStrings uiStrings;
    public static final String[] TEXT;

    private AbstractPlayer p;

    private int healMult;

    public ExhaustOneThenHealCostAction(int healMult) {

        this.p = Wiz.adp();

        //no matter what we'll always exhaust 1 card.
        this.amount = 1;

        this.healMult = healMult;

        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
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
        if (c.costForTurn > -2){

            int healbase = c.costForTurn;

            if(healbase == -1){
                healbase = EnergyPanel.getCurrentEnergy();
            }

            AbstractDungeon.effectList.add(new MiracleEffect(Color.GREEN, Color.LIME, "EVENT_VAMP_BITE"));
            addToTop(new HealAction(p, p, healbase * healMult));
        }
    }


}

