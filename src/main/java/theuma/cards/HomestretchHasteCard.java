package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import theuma.actions.BlueRoseCloserAction;
import theuma.actions.ChangePlayedCardExhaustAction;
import theuma.actions.DamageMissingPercentDrawAction;
import theuma.actions.HomestretchHasteAction;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class HomestretchHasteCard extends AbstractEasyCard {
    public final static String ID = makeID("HomestretchHaste");
    // intellij stuff attack, enemy, rare, , , , , 0, 1

    public HomestretchHasteCard() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
        baseMagicNumber = magicNumber = 25;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
//        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        AbstractDungeon.effectList.add(new InflameEffect(AbstractDungeon.player));
//        atb(new DrawCardAction(AbstractDungeon.player, calculateDrawnCard(m, magicNumber)));
        atb(new DamageMissingPercentDrawAction(m, new DamageInfo(p, this.damage, this.damageType), magicNumber));
    }

    static public int calculateDrawnCard(AbstractMonster m, float threshold){
        float missingPercent = ((float)m.currentHealth / (float)m.maxHealth) * 100;

        System.out.println(missingPercent);
        System.out.println(threshold);
        System.out.println(Math.floor(missingPercent / threshold));
        System.out.println((int) Math.floor(missingPercent / threshold));

        return (int) Math.floor(missingPercent / threshold);
    }

    @Override
    public void upp() {

        upgradeDamage(4);
        upgradeMagicNumber(-5);
    }
}