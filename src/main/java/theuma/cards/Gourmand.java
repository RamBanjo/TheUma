package theuma.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.LightBulbEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import theuma.actions.ExhaustOneThenCheckCardConditionAction;
import theuma.actions.ExhaustOneThenHealCostAction;
import theuma.actions.FailText;
import theuma.powers.UmaMoodPower;
import theuma.util.CommonUmaMethods;

import java.util.function.Predicate;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;
import static theuma.util.Wiz.atb;

public class Gourmand extends AbstractEasyCard {
    public final static String ID = makeID("Gourmand");
    // intellij stuff attack, enemy, rare, , , , , 0, 1

    public Gourmand() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        this.exhaust = true;

        tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ExhaustOneThenHealCostAction(this.magicNumber));
        applyToSelf(new UmaMoodPower(p, 1));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}
