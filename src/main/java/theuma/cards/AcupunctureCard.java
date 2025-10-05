package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theuma.actions.EasyXCostAction;

import java.util.ArrayList;
import java.util.Collections;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class AcupunctureCard extends AbstractEasyCard {
    public final static String ID = makeID("Acupuncture");
    // intellij stuff attack, enemy, rare, , , , , 0, 1

    public AcupunctureCard() {
        super(ID, -1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        atb(new EasyXCostAction(this, (effect, params) -> {

            ArrayList<AbstractCard> statusList = getCardsMatchingPredicate(c -> c.type == CardType.STATUS, true);
            for (int i = 0; i < effect; i++){
                applyToSelfTop(new StrengthPower(p, magicNumber));
                applyToSelfTop(new DexterityPower(p, magicNumber));

                Collections.shuffle(statusList);
                att(new MakeTempCardInDrawPileAction(statusList.get(0), 1, true, true));
            }

            return true;
        }));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}