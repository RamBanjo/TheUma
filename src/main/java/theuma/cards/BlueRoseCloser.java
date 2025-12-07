package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;
import theuma.actions.BlueRoseCloserAction;
import theuma.cards.curses.MigraineCurse;
import theuma.util.CommonUmaMethods;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.adp;
import static theuma.util.Wiz.atb;

public class BlueRoseCloser extends AbstractEasyCard {
    public final static String ID = makeID("BlueRoseCloser");
    // intellij stuff attack, enemy, rare, , , , , 0, 1

    public BlueRoseCloser() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
        baseMagicNumber = magicNumber = 1;
        cardsToPreview = new Shiv();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
//        atb(new BlueRoseCloserAction(m, new DamageInfo(p, damage, this.damageType), magicNumber));

        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        atb(new MakeTempCardInHandAction(new Shiv(), magicNumber));

        if (cardConditional()) atb(new MakeTempCardInHandAction(new Shiv()));

    }

    public void triggerOnGlowCheck() {
        this.glowColor = cardConditional() ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public boolean cardConditional(){
        return CommonUmaMethods.playerIsSorrowful(adp());
    }

    @Override
    public void upp() {

        upgradeDamage(3);
        upgradeMagicNumber(1);
    }
}