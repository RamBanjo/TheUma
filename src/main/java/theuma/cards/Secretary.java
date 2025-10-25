package theuma.cards;

import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.FreeAttackPower;
import com.megacrit.cardcrawl.vfx.UpgradeHammerImprintEffect;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class Secretary extends AbstractEasyCard {
    public final static String ID = makeID("Secretary");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Secretary() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        hand().group.forEach(c -> {
            if (c.type == CardType.ATTACK){

                int effectiveCost = Math.max(0, c.costForTurn);

                c.superFlash();
                atb(new ModifyDamageAction(c.uuid, this.magicNumber * effectiveCost));
            }
        });

    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}