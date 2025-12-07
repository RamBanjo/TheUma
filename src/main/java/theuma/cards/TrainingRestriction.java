package theuma.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EntanglePower;
import com.megacrit.cardcrawl.powers.NoBlockPower;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import theuma.actions.TrainingRestrictAction;
import theuma.cards.status.SlackerStatus;
import theuma.powers.EntangleWithAmount;
import theuma.powers.NoGainsPower;
import theuma.powers.UmaMoodPower;
import theuma.powers.WitlessPower;
import theuma.util.Wiz;

import java.util.ArrayList;
import java.util.Collections;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;
import static theuma.util.Wiz.atb;

public class TrainingRestriction extends AbstractEasyCard {

    public final static String ID = makeID("TrainingRestricted");
    // intellij stuff skill, self, uncommon

    public TrainingRestriction() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(magicNumber));
        applyToSelf(new UmaMoodPower(p, 2, false));
        atb(new TrainingRestrictAction(
                cardStrings.EXTENDED_DESCRIPTION[0],
                cardStrings.EXTENDED_DESCRIPTION[1],
                cardStrings.EXTENDED_DESCRIPTION[2],
                cardStrings.EXTENDED_DESCRIPTION[3],
                cardStrings.EXTENDED_DESCRIPTION[4]
        ));

    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }
}