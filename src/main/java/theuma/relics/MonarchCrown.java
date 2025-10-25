package theuma.relics;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.curses.CurseOfTheBell;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.rewards.RewardItem;
import theuma.CharacterFile;
import theuma.cards.curses.LegFractureCurse;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class MonarchCrown extends AbstractEasyRelic {
    public static final String ID = makeID("MonarchCrown");

    public MonarchCrown() {
        super(ID, RelicTier.BOSS, LandingSound.CLINK, CharacterFile.Enums.UMA_COLOR);
    }

    private boolean cardsReceived = true;

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(MonarchCrown.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(MonarchCrown.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else { super.obtain(); }

        this.cardsReceived = false;
    }

    public void update() {
        super.update();
        if (!this.cardsReceived && !AbstractDungeon.isScreenUp && this.isObtained) {
            this.cardsReceived = true;

            CardCrawlGame.sound.playA("BELL", MathUtils.random(-0.2F, -0.3F));

            CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            AbstractCard legBreaky = new LegFractureCurse();
            group.addToBottom(legBreaky.makeCopy());

            AbstractDungeon.gridSelectScreen.openConfirmationGrid(group, this.DESCRIPTIONS[1]);
        }
    }


    @Override
    public void onPlayerEndTurn() {
        if (adp().hasPower(VigorPower.POWER_ID)){

            this.flash();
            atb(new RelicAboveCreatureAction(adp(), this));

            int vigorStacks = Math.floorDiv(adp().getPower(VigorPower.POWER_ID).amount , 2);

            atb(new RemoveSpecificPowerAction(adp(), adp(), VigorPower.POWER_ID));

            if (vigorStacks > 0){
                applyToSelf(new StrengthPower(adp(), vigorStacks));
            }
        }
    }
}
