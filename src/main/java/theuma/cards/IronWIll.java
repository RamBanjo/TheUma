package theuma.cards;

import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import theuma.powers.UmaMoodPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;
import static theuma.util.Wiz.atb;

public class IronWIll extends AbstractEasyCard {
    public final static String ID = makeID("IronWill");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public IronWIll() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 20;
        this.baseMagicNumber = this.magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new BlurPower(p, this.magicNumber));
        applyToSelf(new EnergizedPower(p, this.magicNumber));
        atb(new PressEndTurnButtonAction());
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}