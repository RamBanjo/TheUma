package theuma.vfx;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

public class OutOfCardsThoughtBubbleEffect extends ThoughtBubble {

    private static final UIStrings uiStrings;
    public static final String[] TEXT;

    public OutOfCardsThoughtBubbleEffect(float x, float y, String msg, boolean isPlayer) {
        super(x, y, msg, isPlayer);
    }

    public OutOfCardsThoughtBubbleEffect(float x, float y) {
        super(x, y, TEXT[0], true);
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("umapyoi:NothingToDrawText");
        TEXT = uiStrings.TEXT;
    }
}
