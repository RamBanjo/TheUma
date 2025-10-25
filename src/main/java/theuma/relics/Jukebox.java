package theuma.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theuma.CharacterFile;
import theuma.cards.Bakushin;
import theuma.cards.GolshiKick;
import theuma.cards.TeioStep;
import theuma.powers.UmaMoodPower;

import java.util.ArrayList;
import java.util.Collections;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class Jukebox extends AbstractEasyRelic {
    public static final String ID = makeID("Jukebox");

    public Jukebox() {
        super(ID, RelicTier.COMMON, LandingSound.HEAVY, CharacterFile.Enums.UMA_COLOR);
    }

    @Override
    public void atBattleStart() {
        this.flash();

        ArrayList<AbstractCard> cards = new ArrayList<>();

        cards.add(new Bakushin());
        cards.add(new GolshiKick());
        cards.add(new TeioStep());

        Collections.shuffle(cards);

        AbstractCard addThis = cards.get(0);

        addThis.upgrade();
        addThis.freeToPlayOnce = true;

        atb(new MakeTempCardInHandAction(addThis, 1));
    }
}
