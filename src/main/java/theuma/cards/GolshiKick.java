package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.util.ProAudio;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.playAudio;

public class GolshiKick extends AbstractEasyCard {
    public final static String ID = makeID("GolshiKick");
    // intellij stuff , , , 4, , , , 2, 3

    public GolshiKick() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 5;
        baseMagicNumber = magicNumber = 5;
        baseSecondMagic = secondMagic = 1;
    }

    private boolean cardConditional(boolean onUse){

        //since on use needs to not count the current card
        int excludeCurrentCard = onUse ? 1 : 0;

        return AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - excludeCurrentCard >= magicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);

        if (cardConditional(true)){
            playAudio(ProAudio.GOLSHI_ENDING_1);
            addToBot(new GainEnergyAction(1));
            addToBot(new DrawCardAction(secondMagic));
        }

        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void applyPowers() {
        int count = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();

        super.applyPowers();

        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0] + count;
        if (count == 1) {
            this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[2];
        }

        this.initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void triggerOnGlowCheck() {
        this.glowColor = cardConditional(false) ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }


    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(-1);
        upgradeSecondMagic(1);
    }
}