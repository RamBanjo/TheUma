package theuma.cards;

import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.util.CommonUmaMethods;

import java.util.ArrayList;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.adp;
import static theuma.util.Wiz.atb;

public class StonePiercingBlue extends AbstractEasyCard {
    public final static String ID = makeID("StonePiercingBlue");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public StonePiercingBlue() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 10;

        MultiCardPreview.add(this, new Trick());
        MultiCardPreview.add(this, new Tether());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);

        if (cardConditional()){
            ArrayList<AbstractCard> cardsToAdd = new ArrayList<>();

            cardsToAdd.add(new Trick());
            cardsToAdd.add(new Tether());

            cardsToAdd.forEach(c -> {
                CardModifierManager.addModifier(c, new ExhaustMod());
                atb(new MakeTempCardInHandAction(c, 1));
            });

        }
    }

    public void triggerOnGlowCheck() {

        if (this.upgraded){
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR;
            return;
        }

        this.glowColor = cardConditional() ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public boolean cardConditional(){
        return CommonUmaMethods.playerIsSorrowful(adp()) || this.upgraded;
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }
}