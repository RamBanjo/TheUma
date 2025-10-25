package theuma.potions;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theuma.CharacterFile;
import theuma.ModFile;
import theuma.powers.UmaMoodPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class RoyalBitterJuice extends AbstractEasyPotion {
    public static String ID = makeID("RoyalBitterJuice");

    public RoyalBitterJuice() {
        super(ID, PotionRarity.UNCOMMON, PotionSize.T, new Color(0.333f, 0.42f, 0.182f, 0.5f), new Color(0.333f, 0.42f, 0.182f, 1f), null, CharacterFile.Enums.THE_UMA, ModFile.characterColor);
    }

    public int getPotency(int ascensionlevel) {
        return 1;
    }

    public void use(AbstractCreature creature) {
        applyToSelf(new UmaMoodPower(adp(), potency * -1));
        atb(new GainEnergyAction(potency * 5));
    }

    public String getDescription() {

        this.potency = this.getPotency();

        if (this.potency == 1){
            return strings.DESCRIPTIONS[0] + potency + strings.DESCRIPTIONS[1] + (potency*5) + strings.DESCRIPTIONS[3];
        }

        return strings.DESCRIPTIONS[0] + potency + strings.DESCRIPTIONS[2] + (potency*5) + strings.DESCRIPTIONS[3];
    }

    public void addAdditionalTips() {
        tips.add(new PowerTip(BaseMod.getKeywordTitle(makeID("mood")), BaseMod.getKeywordDescription(makeID("mood"))));
    }
}