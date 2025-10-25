package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.WallopEffect;
import theuma.util.Wiz;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class SuperDuperClimax extends AbstractEasyCard {
    public final static String ID = makeID("SuperDuperClimax");
    // intellij stuff SKILL, NONE, COMMON, 0, 0, 4, 4, 2, 3

    public SuperDuperClimax() {
        super(ID, 5, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 20;
        this.exhaust = true;
    }

    public void upp() {
        upgradeDamage(5);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (abstractMonster.hb != null){
            Wiz.vfx(new WallopEffect(this.damage, abstractMonster.hb.cX, abstractMonster.hb.cY));
        };
        dmg(abstractMonster, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    @Override
    public void triggerOnExhaust() {

        AbstractCard newSuperCX = this.makeStatEquivalentCopy();

        newSuperCX.updateCost(- 1);
        
        atb(new MakeTempCardInDrawPileAction(newSuperCX, 1, true, true));
    }
}