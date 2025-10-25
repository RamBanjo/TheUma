package theuma.relics;

import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static theuma.CharacterFile.Enums.UMA_COLOR;
import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class ChampionGoldenCleats extends AbstractEasyRelic implements OnCreateCardInterface {

    private static final String ID = makeID("ChampionGoldenCleats");
    private static final int VIGOR_GAINED = 4;

    public ChampionGoldenCleats(){
        super(ID, RelicTier.BOSS, LandingSound.MAGICAL, UMA_COLOR);
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(StandardTracenCleats.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(StandardTracenCleats.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else { super.obtain(); }
    }

    @Override
    public void onCreateCard(AbstractCard abstractCard) {
        this.flash();
        atb(new RelicAboveCreatureAction(adp(), this));
        applyToSelf(new VigorPower(adp(), VIGOR_GAINED));
    }


    @Override
    public boolean canSpawn() {
        return adp().hasRelic(StandardTracenCleats.ID);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + VIGOR_GAINED + DESCRIPTIONS[1];
    }
}
