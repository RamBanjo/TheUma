package theuma.potions;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.PowerTip;
import theuma.CharacterFile;
import theuma.ModFile;
import theuma.powers.UmaMoodPower;

import static theuma.util.Wiz.adp;
import static theuma.util.Wiz.applyToSelf;


import static theuma.ModFile.makeID;

public class CarrotJuice extends AbstractEasyPotion {
    public static String ID = makeID("CarrotJuice");

    public CarrotJuice() {
        super(ID, PotionRarity.COMMON, PotionSize.S, new Color(1f, 0.549f, 0, 1f), new Color(0.486f, 0.988f, 0, 1f), null, CharacterFile.Enums.THE_UMA, ModFile.characterColor);
    }

    public int getPotency(int ascensionlevel) {
        return 1;
    }

    public void use(AbstractCreature target) {
        addToBot(new DrawCardAction(potency));
        addToBot(new GainEnergyAction(potency));
        applyToSelf(new UmaMoodPower(adp(), potency));
    }

    public String getDescription() {

        this.potency = this.getPotency();

        if (this.potency == 1){
            return strings.DESCRIPTIONS[0] + potency + strings.DESCRIPTIONS[1] + potency + strings.DESCRIPTIONS[3] + potency + strings.DESCRIPTIONS[4];
        }

        return strings.DESCRIPTIONS[0] + potency + strings.DESCRIPTIONS[2] + potency + strings.DESCRIPTIONS[3] + potency + strings.DESCRIPTIONS[5];
    }

    public void addAdditionalTips() {
        tips.add(new PowerTip(BaseMod.getKeywordTitle(makeID("mood")), BaseMod.getKeywordDescription(makeID("mood"))));
    }
}