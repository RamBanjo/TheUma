package theuma.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theuma.CharacterFile;
import theuma.cards.status.LateStartStatus;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class TwelveBillion extends AbstractEasyRelic {
    public static final String ID = makeID("TwelveBillion");

    public TwelveBillion() {
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
        atb(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        atb(new MakeTempCardInDrawPileAction(new LateStartStatus(), 3, true, true));
    }
}
