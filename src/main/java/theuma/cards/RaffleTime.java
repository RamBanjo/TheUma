package theuma.cards;

import basemod.BaseMod;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import theuma.cards.generated.Carrot;
import theuma.cards.generated.CarrotBushel;
import theuma.cards.generated.CarrotHamburg;
import theuma.cards.generated.HotSpringTicket;
import theuma.cards.status.ConsolationPrize;

import java.util.ArrayList;
import java.util.Collections;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;
import static theuma.util.Wiz.hand;

public class RaffleTime extends AbstractEasyCard {
    public final static String ID = makeID("RaffleTime");
    // intellij stuff attack, enemy, rare, , , , , 0, 1

    public RaffleTime() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        MultiCardPreview.add(this,
                new ConsolationPrize(),
                new Carrot(),
                new CarrotBushel(),
                new CarrotHamburg(),
                new HotSpringTicket());
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        ArrayList<AbstractCard> cards = new ArrayList<>();

        if (!this.upgraded){
            cards.add(new ConsolationPrize());
        }

        cards.add(new Carrot());
        cards.add(new CarrotBushel());
        cards.add(new CarrotHamburg());
        cards.add(new HotSpringTicket());

        Collections.shuffle(cards);

        atb(new MakeTempCardInHandAction(cards.get(0)));

    }

    @Override
    public void upp() {
        MultiCardPreview.multiCardPreview.get(this).remove(0);
    }
}