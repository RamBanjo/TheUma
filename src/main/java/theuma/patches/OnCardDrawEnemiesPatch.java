package theuma.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;
import theuma.powers.hooks.OnEnemyDeathPower;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "draw",
        paramtypez = {int.class}
)

public class OnCardDrawEnemiesPatch {
    @SpireInsertPatch(
            locator = OnCardDrawEnemiesPatch.Locator.class,
            localvars = {"c"}
    )

    public static void Insert(AbstractPlayer __instance, int numCards, AbstractCard c) {
        for(AbstractMonster mon : AbstractDungeon.getMonsters().monsters) {
            for(AbstractPower p : mon.powers) {
                p.onCardDraw(c);
            }
        }
    }

    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "powers");
            return LineFinder.findInOrder(ctBehavior, finalMatcher);
        }
    }
}


