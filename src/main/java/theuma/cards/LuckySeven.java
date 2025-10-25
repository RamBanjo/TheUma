package theuma.cards;

import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Trip;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.cards.generated.TripleSevens;
import theuma.util.CommonUmaMethods;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.adp;
import static theuma.util.Wiz.atb;

public class LuckySeven extends AbstractEasyCard implements OnCreateCardInterface {
    public final static String ID = makeID("LuckySeven");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public LuckySeven() {
        this(true);
    }

    public LuckySeven(boolean hasPreview){
        super(ID, 7, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;

        if(hasPreview){
            cardsToPreview = new TripleSevens(false);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard newTripSev = new TripleSevens();

        if (this.upgraded){
            newTripSev.upgrade();
        }

        atb(new MakeTempCardInHandAction(newTripSev));
    }

    @Override
    public void upp() {
        this.retain = true;

        if (cardsToPreview != null){
            cardsToPreview.upgrade();
        }
    }

    @Override
    public void onCreateCard(AbstractCard abstractCard) {
        if (this.costForTurn > 0){
            this.flash();
            this.updateCost(-1);
        }
    }
}