package theuma.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.CharacterFile;
import theuma.powers.UmaMoodPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class CarrotCupcake extends AbstractEasyRelic{

    public static final String ID = makeID("CarrotCupcake");
    private static final int CARD_PLAY_TO_TRIGGER = 10;

    public CarrotCupcake() {
        super(ID, RelicTier.SHOP, LandingSound.FLAT, CharacterFile.Enums.UMA_COLOR);
        this.counter = 0;
    }

    public void onEquip() {
        AbstractDungeon.player.increaseMaxHp(10, true);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        this.counter += 1;

        if (this.counter % CARD_PLAY_TO_TRIGGER == 0){
            this.counter = 0;
            this.flash();

            atb(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            applyToSelf(new UmaMoodPower(adp(), 1));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + CARD_PLAY_TO_TRIGGER + DESCRIPTIONS[1];
    }
}
