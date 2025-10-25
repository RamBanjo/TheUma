package theuma.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theuma.cards.hooks.HeldOnVictoryCard;
import theuma.powers.hooks.OnEnemyDeathPower;
import theuma.powers.hooks.OnLoseEnergyPower;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "onVictory"
)

public class OnCardHeldVictoryPatch {

    @SpirePostfixPatch
    public static void Postfix(AbstractPlayer __instance){
        if(!__instance.isDying){
            for (AbstractCard c : __instance.hand.group){
                if(c instanceof HeldOnVictoryCard){
                    ((HeldOnVictoryCard) c).onVictoryWhileHeld();
                }
            }
        }
    }
}
