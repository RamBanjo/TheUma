package theuma.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theuma.powers.LateSurgerPower;
import theuma.util.CommonUmaMethods;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class KiryuinsFavor extends AbstractEasyCard {
    public final static String ID = makeID("KiryuinsFavor");
    // intellij stuff SKILL, SELF, COMMON, 0, 0, 0, 0, 1, 1

    public KiryuinsFavor() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        baseSecondMagic = secondMagic = 4;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        if (CommonUmaMethods.playerIsJoyful(p)){
            applyToSelf(new VigorPower(p, magicNumber));
        }else{
            applyToSelf(new VigorPower(p, secondMagic));
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = CommonUmaMethods.playerIsJoyful(adp()) ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }


    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(2);
    }

}