package theuma.actions;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.vfx.combat.LightBulbEffect;
import theuma.util.CommonUmaMethods;
import theuma.util.Wiz;
import theuma.vfx.ThatsMyCardThoughtBubbleEffect;

public class InspirationStrikesAction extends ExhaustOneThenCheckCardConditionAction {

    public InspirationStrikesAction(int extraCards) {
        super(CommonUmaMethods::cardIsNonUma,
                new LightBulbEffect(Wiz.adp().hb),
                new DrawCardAction(extraCards),
                null,
                null,
                FailText.MY_CARD);
    }

}


