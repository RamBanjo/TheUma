package theuma.potions;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theuma.CharacterFile;
import theuma.ModFile;
import theuma.powers.UmaMoodPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class Parfait extends AbstractEasyPotion {
    public static String ID = makeID("Parfait");

    public Parfait() {
        super(ID, PotionRarity.RARE, PotionSize.JAR, Color.WHITE, Color.ORANGE, null, CharacterFile.Enums.THE_UMA, ModFile.characterColor);
    }

    public int getPotency(int ascensionlevel) {
        return 30;
    }

    public void use(AbstractCreature creature) {
        applyToSelf(new UmaMoodPower(adp(), 4));
        atb(new HealAction(adp(), adp(), (int)((float) AbstractDungeon.player.maxHealth * ((float)this.potency / 100.0F))));
    }

    public String getDescription() {
        return strings.DESCRIPTIONS[0] + potency + strings.DESCRIPTIONS[1];
    }

    public void addAdditionalTips() {
        tips.add(new PowerTip(BaseMod.getKeywordTitle(makeID("mood")), BaseMod.getKeywordDescription(makeID("mood"))));
    }
}