package theuma.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import javassist.CtBehavior;
import theuma.powers.hooks.OnEnemyDeathPower;
import theuma.powers.hooks.OnLoseEnergyPower;

@SpirePatch(
        clz = EnergyPanel.class,
        method = "useEnergy",
        paramtypez = {int.class}
)

public class LoseEnergyPatch {

    @SpirePostfixPatch
    public static void Postfix(int e){
        for(AbstractPower p : AbstractDungeon.player.powers) {
            if (p instanceof OnLoseEnergyPower) {
                ((OnLoseEnergyPower)p).onLoseEnergy(e);
            }
        }

        for(AbstractMonster m : AbstractDungeon.getMonsters().monsters){
            for(AbstractPower p : m.powers) {
                if (p instanceof OnEnemyDeathPower) {
                    ((OnLoseEnergyPower)p).onLoseEnergy(e);
                }
            }
        }
    }
}
