package theuma.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.CommonKeywordIconsField;
import com.evacipated.cardcrawl.mod.stslib.icons.CustomIconHelper;
import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theuma.actions.ModifyMagicAction;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class Groundwork extends AbstractEasyCard implements OnCreateCardInterface {
    public final static String ID = makeID("Groundwork");
    // intellij stuff , , , 4, , , , 2, 3


    private final int FIXED_MAGIC = 3;
    private final int TRUE_BASE_MAGIC = 1;


    public Groundwork() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = TRUE_BASE_MAGIC;
        this.misc = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrengthPower(p, magicNumber));
    }

    public void upp() {
        this.selfRetain = true;
    }

    @Override
    public void onCreateCard(AbstractCard abstractCard) {

        if (adp().hand.contains(this)){

            System.out.println("Hand does contain Groundwork!");

            atb(new ModifyMagicAction(this.uuid, 1));
            this.superFlash();
            this.applyPowers();
        }
    }

    public void onMoveToDiscard() {
//        this.rawDescription = cardStrings.DESCRIPTION;
//        this.initializeDescription();
    }

}