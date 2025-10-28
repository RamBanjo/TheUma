package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.util.CommonUmaMethods;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.adp;
import static theuma.util.Wiz.atb;

public class AdoredByAll extends AbstractEasyCard {
    public final static String ID = makeID("AdoredByAll");
    // intellij stuff Attack, Enemy, Common, 5, 8, , , , 

    static final int SINGLE_TARGET_DMG = 5;
    static final int SPLASH_DMG = 10;
    static final int SINGLE_TARGET_UPG = 3;
    static final int SPLASH_UPG = 5;


    public AdoredByAll() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = SINGLE_TARGET_DMG;
        baseSecondDamage = SPLASH_DMG;

        this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);

        if (cardConditional()){
            atb(new DamageAllEnemiesAction(p, multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));

//            allDmg(AbstractGameAction.AttackEffect.FIRE);
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = cardConditional() ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }


    @Override
    public void applyPowers() {
        int origBase = this.baseDamage;

        this.baseDamage = baseSecondDamage;
        this.isMultiDamage = true;
        super.applyPowers();

        int aoeDmg = this.damage;
        boolean aoeModified = this.isDamageModified;

        this.baseDamage = origBase;
        this.isMultiDamage = false;
        super.applyPowers();

        //recalcing also resets variables and auto recalculates these...
        this.secondDamage = aoeDmg;
        this.isSecondDamageModified = aoeModified;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int origBase = this.baseDamage;

        this.baseDamage = baseSecondDamage;
        this.isMultiDamage = true;
        super.calculateCardDamage(null);

        int aoeDmg = this.damage;
        boolean aoeModified = this.isDamageModified;

        this.baseDamage = origBase;
        this.isMultiDamage = false;
        super.calculateCardDamage(mo);

        this.secondDamage = aoeDmg;
        this.isSecondDamageModified = aoeModified;
    }


    public boolean cardConditional(){
        return CommonUmaMethods.playerIsJoyful(adp());
    }

    public void upp() {
        upgradeDamage(SINGLE_TARGET_UPG);
        upgradeSecondDamage(SPLASH_UPG);

    }
}