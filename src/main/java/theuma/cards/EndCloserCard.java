package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.powers.UmaMoodPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class EndCloserCard extends AbstractEasyCard {
    public final static String ID = makeID("EndCloser");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public EndCloserCard() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = 2;
        this.baseMagicNumber = this.magicNumber = 4;
        this.baseSecondMagic = secondMagic = -1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        for (int i = 0; i < magicNumber; i++){

            atb(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
//            dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        }
        atb(new PressEndTurnButtonAction());
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}