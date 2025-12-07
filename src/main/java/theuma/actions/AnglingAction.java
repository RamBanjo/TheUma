package theuma.actions;

import basemod.BaseMod;
import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.ArrayList;

import static theuma.util.Wiz.adp;

public class AnglingAction extends AbstractGameAction {
    public static final String[] TEXT;
    private AbstractPlayer player;
    private int copiesToMake;

    public AnglingAction(int copies){
        this.copiesToMake = copies;
        player = adp();
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == this.startDuration) {
            if (!this.player.discardPile.isEmpty()) {
                if (this.player.discardPile.size() <= 1) {
                    ArrayList<AbstractCard> cardsToMove = new ArrayList<>();

                    for(AbstractCard c : this.player.discardPile.group) {
                        cardsToMove.add(c);
                    }

                    for(AbstractCard c : cardsToMove) {
                        if (this.player.hand.size() < BaseMod.MAX_HAND_SIZE) {

                            AbstractCard newCard = c.makeStatEquivalentCopy();
                            CardModifierManager.addModifier(newCard, new ExhaustMod());

                            addToTop(new MakeTempCardInHandAction(newCard, copiesToMake));
                        }

                        c.lighten(false);
                        c.applyPowers();
                    }

                    this.isDone = true;
                } else {
                    AbstractDungeon.gridSelectScreen.open(this.player.discardPile, 1, TEXT[0], false);
                    this.tickDuration();
                }
            } else {
                this.isDone = true;
            }
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {

//                System.out.println(AbstractDungeon.gridSelectScreen.selectedCards);

                for(AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    if (this.player.hand.size() < BaseMod.MAX_HAND_SIZE) {

                        AbstractCard newCard = c.makeStatEquivalentCopy();
                        CardModifierManager.addModifier(newCard, new ExhaustMod());

                        addToTop(new MakeTempCardInHandAction(newCard, copiesToMake));
                    }

                    c.lighten(false);
                    c.unhover();
                    c.applyPowers();
                }

                for(AbstractCard c : this.player.discardPile.group) {
                    c.unhover();
                    c.target_x = (float)CardGroup.DISCARD_PILE_X;
                    c.target_y = 0.0F;
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }

            this.tickDuration();
            if (this.isDone) {
                for(AbstractCard c : this.player.hand.group) {
                    c.applyPowers();
                }
            }

        }
    }

    static {
        TEXT = CardCrawlGame.languagePack.getUIString("umapyoi:AnglingAction").TEXT;
    }
}
