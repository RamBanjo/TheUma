package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.actions.AnglingAction;
import theuma.util.CommonUmaMethods;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.adp;
import static theuma.util.Wiz.atb;

public class Angling extends AbstractEasyCard {
    public final static String ID = makeID("Angling");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Angling() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.SELF_AND_ENEMY);
        baseDamage = 8;
        baseMagicNumber = magicNumber = 1;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        atb(new AnglingAction(magicNumber));
    }


    @Override
    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }
}