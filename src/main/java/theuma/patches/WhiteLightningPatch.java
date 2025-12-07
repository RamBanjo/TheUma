package theuma.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theuma.cards.WhiteLightning;

public class WhiteLightningPatch {
    @SpirePatch(clz = VigorPower.class, method = "onUseCard")
    public static class DontConsumeVigorPatch {
        @SpirePrefixPatch
        public static SpireReturn<Void> patch(VigorPower __instance, AbstractCard card, UseCardAction action) {
            if (card instanceof WhiteLightning) {
                __instance.flash();
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }
}
