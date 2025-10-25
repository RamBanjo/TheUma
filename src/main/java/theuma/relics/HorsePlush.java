package theuma.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theuma.CharacterFile;
import theuma.powers.UmaMoodPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class HorsePlush extends AbstractEasyRelic{

    public static final String ID = makeID("HorsePlush");

    public HorsePlush() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, CharacterFile.Enums.UMA_COLOR);
    }

    @Override
    public void atBattleStart() {
        this.flash();
        atb(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        applyToSelf(new UmaMoodPower(adp(), 1));
    }
}
