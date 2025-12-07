package theuma.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import theuma.cards.status.SlowMetabolismStatus;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;
import static theuma.util.Wiz.atb;

public class Yakisoba extends AbstractEasyCard {
    public final static String ID = makeID("Yakisoba");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Yakisoba() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 10;
        this.baseMagicNumber = this.magicNumber = 2;
        this.cardsToPreview = new SlowMetabolismStatus();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new BlurPower(p, this.magicNumber));
        atb(new MakeTempCardInHandAction(new SlowMetabolismStatus(), 1));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}