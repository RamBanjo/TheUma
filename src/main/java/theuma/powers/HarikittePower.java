package theuma.powers;

import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theuma.util.ProAudio;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;
import static theuma.util.Wiz.playAudio;

public class HarikittePower extends AbstractEasyPower implements OnCreateCardInterface {

    public final static String ID = makeID("HarikittePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HarikittePower(AbstractCreature owner, int amount) {
        super(ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void onCreateCard(AbstractCard abstractCard) {

        if (abstractCard.type == AbstractCard.CardType.ATTACK){
            this.flashWithoutSound();
            playAudio(ProAudio.HARIKITTE_IKOU);
            atb(new ModifyDamageAction(abstractCard.uuid, this.amount));
        }

    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }
}
