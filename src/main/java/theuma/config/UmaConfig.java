package theuma.config;

import basemod.EasyConfigPanel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theuma.CharacterFile;
import theuma.ModFile;

public class UmaConfig extends EasyConfigPanel {
    public static boolean umaEventToggle = true;
    public static boolean umaAllCharToggle = true;

    public UmaConfig() {
        super(ModFile.modID, ModFile.makeID("UmaConfig"));
    }

    public static boolean canSpawnUmaEvents() {
        if (!umaEventToggle) return false;
        if (umaAllCharToggle) return true;

        if (AbstractDungeon.player == null) return false;

        return AbstractDungeon.player.chosenClass == CharacterFile.Enums.THE_UMA;
    }
}
