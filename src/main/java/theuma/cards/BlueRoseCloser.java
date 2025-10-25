package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;
import theuma.actions.BlueRoseCloserAction;
import theuma.cards.curses.MigraineCurse;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class BlueRoseCloser extends AbstractEasyCard {
    public final static String ID = makeID("BlueRoseCloser");
    // intellij stuff attack, enemy, rare, , , , , 0, 1

    public BlueRoseCloser() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new BlueRoseCloserAction(m, new DamageInfo(p, damage, this.damageType), magicNumber));

    }

    @Override
    public void upp() {

        upgradeDamage(4);
        upgradeMagicNumber(1);
    }
}