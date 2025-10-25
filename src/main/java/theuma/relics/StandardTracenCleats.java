package theuma.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theuma.CharacterFile;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class StandardTracenCleats extends AbstractEasyRelic{

    public static final String ID = makeID("StandardTracenCleats");

    private static final int VIGOR_GAINED = 2;
    private static final int CARD_DRAW_TO_TRIGGER = 3;

    public StandardTracenCleats() {
        super(ID, RelicTier.STARTER, LandingSound.SOLID, CharacterFile.Enums.UMA_COLOR);
        this.counter = 0;
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        this.counter += 1;

        if (this.counter % CARD_DRAW_TO_TRIGGER == 0){
            this.counter = 0;
            this.flash();

            atb(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            applyToSelf(new VigorPower(adp(), VIGOR_GAINED));
        }
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + VIGOR_GAINED + this.DESCRIPTIONS[1] + CARD_DRAW_TO_TRIGGER + this.DESCRIPTIONS[2];
    }
}
