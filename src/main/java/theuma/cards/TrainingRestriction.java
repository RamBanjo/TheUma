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

        ArrayList<String> restricted = new ArrayList<>();
        restricted.add("SPD"); //Speed Restricted -> Cannot draw cards
        restricted.add("STA"); //Stamina Restricted -> Cannot gain Block
        restricted.add("POW"); //Power Restricted -> Cannot gain Strength and Dex
        restricted.add("GUT"); //Guts Restricted -> Cannot use Attacks
        restricted.add("WIT"); //Wit Restricted -> Cannot use Power Cards

        Collections.shuffle(restricted);

        switch (restricted.get(0)){
            case "SPD":
                AbstractDungeon.effectList.add(new SpeechBubble(p.dialogX, p.dialogY, cardStrings.EXTENDED_DESCRIPTION[0], true));
                applyToSelf(new NoDrawPower(p));
                break;
            case "STA":
                AbstractDungeon.effectList.add(new SpeechBubble(p.dialogX, p.dialogY, cardStrings.EXTENDED_DESCRIPTION[1], true));
                applyToSelf(new NoBlockPower(p, 2, true));
                break;
            case "POW":
                AbstractDungeon.effectList.add(new SpeechBubble(p.dialogX, p.dialogY, cardStrings.EXTENDED_DESCRIPTION[2], true));
                applyToSelf(new NoGainsPower(p, 2));
                break;
            case "GUT":
                AbstractDungeon.effectList.add(new SpeechBubble(p.dialogX, p.dialogY, cardStrings.EXTENDED_DESCRIPTION[3], true));
                applyToSelf(new EntangleWithAmount(p, 2));
                break;
            case "WIT":
                AbstractDungeon.effectList.add(new SpeechBubble(p.dialogX, p.dialogY, cardStrings.EXTENDED_DESCRIPTION[4], true));
                applyToSelf(new WitlessPower(p, 2));
                break;
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }
}