package theuma.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theuma.CharacterFile;
import theuma.powers.UmaMoodPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class BlueRose extends AbstractEasyRelic {
    public static final String ID = makeID("BlueRose");

    public BlueRose() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, CharacterFile.Enums.UMA_COLOR);
    }

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void atBattleStart() {
        this.flash();
        att(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        applyToSelfTop(new UmaMoodPower(adp(), -2));
    }
}
