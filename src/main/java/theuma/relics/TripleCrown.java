package theuma.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.CharacterFile;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.adp;
import static theuma.util.Wiz.atb;

public class TripleCrown extends AbstractEasyRelic{

    private static final String ID = makeID("TripleCrown");

    public final static int CARDS_PLAYED = 3;
    public final static int CARDS_DRAWN = 1;


    public TripleCrown() {
        super(ID, RelicTier.RARE, LandingSound.CLINK, CharacterFile.Enums.UMA_COLOR);
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();

        this.flash();
        this.counter = 0;
        this.grayscale = false;
    }

    @Override
    public void onVictory() {
        super.onVictory();

        this.counter = -1;

        this.grayscale = false;
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        super.onPlayCard(c, m);

        if (counter < CARDS_PLAYED){
            this.counter += 1;

            if (counter == CARDS_PLAYED){
                this.flash();
                atb(new RelicAboveCreatureAction(adp(), this));
                atb(new DrawCardAction(CARDS_DRAWN));

                this.grayscale = true;
            }
        }
    }


    @Override
    public String getUpdatedDescription() {

        return this.DESCRIPTIONS[0] + CARDS_PLAYED + DESCRIPTIONS[1] + CARDS_DRAWN + DESCRIPTIONS[2];
    }
}
