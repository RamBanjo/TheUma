package theuma.cards.generated;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import theuma.cards.AbstractEasyCard;
import theuma.powers.UmaMoodPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;
import static theuma.util.Wiz.atb;

public class CarrotHamburg extends AbstractEasyCard {
    public final static String ID = makeID("CarrotHamburg");
    // intellij stuff ATTACK, ENEMY, COMMON, 6, , , , 3, 2

    public CarrotHamburg() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = 2;
        this.exhaust = true;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.effectList.add(new MiracleEffect(Color.GREEN, Color.LIME, "EVENT_VAMP_BITE"));

        applyToSelf(new UmaMoodPower(p, 2));
        atb(new GainEnergyAction(magicNumber));
        applyToSelf(new StrengthPower(p, magicNumber));
        applyToSelf(new DexterityPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}