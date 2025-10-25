package theuma.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightBulbEffect;
import theuma.actions.ExhaustOneThenCheckCardConditionAction;
import theuma.actions.FailText;
import theuma.actions.InspirationStrikesAction;
import theuma.util.CommonUmaMethods;
import theuma.vfx.ThatsMyCardThoughtBubbleEffect;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class PureHeart extends AbstractEasyCard {
    public final static String ID = makeID("PureHeart");
    // intellij stuff attack, enemy, rare, , , , , 0, 1

    public PureHeart() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new ExhaustOneThenCheckCardConditionAction(
                CommonUmaMethods::cardIsNonUma,
                new LightBulbEffect(p.hb),
                new GainBlockAction(p, block),
                null,
                null,
                FailText.MY_CARD
        ));
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }
}
