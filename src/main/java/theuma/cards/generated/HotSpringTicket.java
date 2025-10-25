package theuma.cards.generated;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.unique.IncreaseMaxHpAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import theuma.cards.AbstractEasyCard;
import theuma.cards.hooks.HeldOnVictoryCard;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.adp;
import static theuma.util.Wiz.atb;

public class HotSpringTicket extends AbstractEasyCard implements HeldOnVictoryCard {
    public final static String ID = makeID("HotSpringTicket");
    // intellij stuff ATTACK, ENEMY, COMMON, 6, , , , 3, 2

    public HotSpringTicket() {
        super(ID, -2, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = 3;
        this.selfRetain = true;

        this.tags.add(CardTags.HEALING);

    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void upp() {
        upgradeMagicNumber(2);
    }

    @Override
    public void onVictoryWhileHeld() {
        adp().increaseMaxHp(magicNumber, false);
    }
}