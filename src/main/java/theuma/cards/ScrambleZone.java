package theuma.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theuma.actions.EasyModalChoiceAction;
import theuma.powers.UmaMoodPower;

import java.util.ArrayList;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class ScrambleZone extends AbstractEasyCard {
    public final static String ID = makeID(ScrambleZone.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , , , ,

    public ScrambleZone() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        ArrayList<AbstractCard> easyCardList = new ArrayList<>();
        easyCardList.add(new EasyModalChoiceCard(cardStrings.EXTENDED_DESCRIPTION[0], cardStrings.EXTENDED_DESCRIPTION[1], () -> applyToSelf(new UmaMoodPower(p, 1))));
        easyCardList.add(new EasyModalChoiceCard(cardStrings.EXTENDED_DESCRIPTION[2], cardStrings.EXTENDED_DESCRIPTION[3], () -> applyToSelf(new UmaMoodPower(p, -1))));
        atb(new EasyModalChoiceAction(easyCardList));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }
}