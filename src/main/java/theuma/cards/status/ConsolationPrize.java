package theuma.cards.status;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import theuma.cards.AbstractEasyCard;
import theuma.powers.UmaMoodPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class ConsolationPrize extends AbstractEasyCard {
    public final static String ID = makeID("ConsolationPrize");
    // intellij stuff ATTACK, ENEMY, COMMON, 6, , , , 3, 2

    public ConsolationPrize() {
        super(ID, -2, CardType.STATUS, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = 1;
        this.isEthereal = true;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void triggerOnExhaust() {
        applyToSelf(new UmaMoodPower(adp(), -1));
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    public void upp() {

    }
}