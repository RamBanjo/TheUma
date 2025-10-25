package theuma.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;
import theuma.powers.hooks.OnOtherPowerRemovedPower;

@SpirePatch(
        clz = RemoveSpecificPowerAction.class,
        method = "update"

)

public class OnOtherPowerRemovePatch {
    @SpireInsertPatch(
            locator = OnOtherPowerRemovePatch.Locator.class,
            localvars = {"removeMe"}
    )

    private static void Insert(RemoveSpecificPowerAction __instance, AbstractPower removeMe){
        for(AbstractPower p : __instance.target.powers){
            if(p instanceof OnOtherPowerRemovedPower){
                ((OnOtherPowerRemovedPower) p).onOtherPowerRemoved(__instance.target, removeMe);
            }
        }
    }

    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractDungeon.class, "onModifyPower");
            return LineFinder.findInOrder(ctBehavior, finalMatcher);
        }
    }
}
