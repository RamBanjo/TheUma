package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.FTLAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theuma.cards.AbstractEasyCard;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class LongShotStar extends AbstractEasyCard {
    public final static String ID = makeID("LongShotStar");
    // intellij stuff , , , 4, , , , 2, 3


    public LongShotStar() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 4;
        baseMagicNumber = magicNumber = 2;
    }

    private boolean cardConditional(int count){
        int FIXED_MAGIC = 3;
        return count <= FIXED_MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        int count = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
        int vigor = 0;
        AbstractPower vigorPow = adp().getPower(VigorPower.POWER_ID);

        if (vigorPow != null){
            vigor = vigorPow.amount;
        }

        int multiplier = cardConditional(count) ? magicNumber : 1;

        this.baseDamage = ((this.baseDamage + vigor) * multiplier) - vigor;

        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;

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
        this.glowColor = AbstractDungeon.actionManager.cardsPlayedThisTurn.size() < 3 ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        int count = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
        int vigor = 0;
        AbstractPower vigorPow = adp().getPower(VigorPower.POWER_ID);

        if (vigorPow != null){
            vigor = vigorPow.amount;
        }

        int multiplier = cardConditional(count) ? magicNumber : 1;

        this.baseDamage = ((this.baseDamage + vigor) * multiplier) - vigor;

        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}