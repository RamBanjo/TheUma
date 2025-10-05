package theuma.cards.status;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.cards.AbstractEasyCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static theuma.ModFile.makeID;

public class NightOwlStatus extends AbstractEasyCard {
    public final static String ID = makeID("SlowMetabolismStatus");
    // intellij stuff STATUS, SELF, COMMON, 0, 0, 0, 0, 0, 0

    public NightOwlStatus() {
        super(ID, 2, CardType.STATUS, CardRarity.COMMON, CardTarget.NONE, CardColor.COLORLESS);
        this.selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void onRetained() {

        //Cards that are valid are in our hand and have a cost of 0 or greater (which can be modified)
        List<AbstractCard> validCardsInHand = AbstractDungeon.player.hand.group.stream().filter(
            c -> c.cost >= 0
        ).collect(Collectors.toList());

        //don't modify if we can't modify
        if (validCardsInHand.isEmpty()){
            return;
        }

        Collections.shuffle(validCardsInHand);
        AbstractCard cardToModifyCost = validCardsInHand.getFirst();

        if (cardToModifyCost != null){
            cardToModifyCost.updateCost(1);
        }
    }
    public void upp() {

    }
}