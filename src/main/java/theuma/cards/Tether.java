package theuma.cards;


import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToEnemy;
import static theuma.util.Wiz.atb;

public class Tether extends AbstractEasyCard{
    public final static String ID = makeID("Tether");

    public Tether(){
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);

        this.baseMagicNumber = this.magicNumber = 2;

    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        //apply Weak
        applyToEnemy(abstractMonster, new WeakPower(abstractMonster, magicNumber, false));

        //draw 1 card
        atb(new DrawCardAction(1));

    }
}
