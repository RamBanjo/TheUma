package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Apparition;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theuma.util.CommonUmaMethods;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;
import static theuma.util.Wiz.atb;

public class SpeedEater extends AbstractEasyCard {

    public final static String ID = makeID("SpeedEater");
    // intellij stuff skill, self, uncommon

    public SpeedEater() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);

        this.baseDamage = 4;
        this.baseMagicNumber = this.magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);

        int exhausted = 0;

        for(AbstractCard c: p.hand.group){

            if (CommonUmaMethods.cardIsNonUma(c)){
                exhausted += 1;
                atb(new ExhaustSpecificCardAction(c, p.hand));
            }
        }

        int vigorGain = exhausted * magicNumber;

        if (vigorGain > 0) {
            applyToSelf(new VigorPower(p, vigorGain));
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
//        upgradeBaseCost(0);
    }
}