package theuma.vfx;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

public class ThatsMyCardThoughtBubbleEffect extends ThoughtBubble {

    private static final UIStrings uiStrings;
    public static final String[] TEXT;

    public ThatsMyCardThoughtBubbleEffect(float x, float y, String msg, boolean isPlayer) {
        super(x, y, msg, isPlayer);
    }

    public ThatsMyCardThoughtBubbleEffect(float x, float y) {
        super(x, y,3.0F, TEXT[0], true);
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("umapyoi:MyCardText");
        TEXT = uiStrings.TEXT;
    }
}
