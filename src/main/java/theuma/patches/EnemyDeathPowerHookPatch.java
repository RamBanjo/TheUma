package theuma.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;
import theuma.powers.hooks.OnEnemyDeathPower;

@SpirePatch(
    clz = AbstractMonster.class,
    method = "die",
    paramtypez = {boolean.class}
)

public class EnemyDeathPowerHookPatch {
    @SpireInsertPatch(
            locator = EnemyDeathPowerHookPatch.Locator.class
    )

    public static void Insert(AbstractMonster __instance, boolean triggerRelics) {
        for(AbstractPower p : AbstractDungeon.player.powers) {
            if (p instanceof OnEnemyDeathPower) {
                ((OnEnemyDeathPower)p).onEnemyDeath(__instance);
            }
        }

        for(AbstractMonster m : AbstractDungeon.getMonsters().monsters){

            if(__instance == m){
                continue;
            }

            for(AbstractPower p : m.powers) {
                if (p instanceof OnEnemyDeathPower) {
                    ((OnEnemyDeathPower)p).onEnemyDeath(__instance);
                }
            }
        }

    }

    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractDungeon.class, "getMonsters");
            return LineFinder.findInOrder(ctBehavior, finalMatcher);
        }
    }
}
