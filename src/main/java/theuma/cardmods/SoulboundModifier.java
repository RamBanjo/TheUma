package theuma.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.SoulboundField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

public class SoulboundModifier extends AbstractCardModifier {
    public static String ID = "umapyoi:SoulboundMod";
    private static final UIStrings uiStrings;

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return String.format(uiStrings.TEXT[0], rawDescription);
    }

    public boolean shouldApply(AbstractCard card) {
        return !SoulboundField.soulbound.get(card);
    }

    public void onInitialApplication(AbstractCard card) {
        SoulboundField.soulbound.set(card, true);
    }

    public void onRemove(AbstractCard card) {
        SoulboundField.soulbound.set(card, false);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new SoulboundModifier();
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    }
}
