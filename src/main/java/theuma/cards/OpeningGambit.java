package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theuma.util.CommonUmaMethods;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class OpeningGambit extends AbstractEasyCard {
    public final static String ID = makeID("OpeningGambit");
    // intellij stuff Attack, Enemy, Common, 5, 8, , , ,

    public OpeningGambit() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 7;
        this.baseMagicNumber = this.magicNumber = 1;
        this.exhaust = true;
        this.isInnate = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);

        for (AbstractMonster mon : AbstractDungeon.getCurrRoom().monsters.monsters){
            if(!mon.isDying && !mon.isDead){
                applyToEnemy(mon, new StrengthPower(mon, magicNumber * -1));
            }
        }

    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }
}