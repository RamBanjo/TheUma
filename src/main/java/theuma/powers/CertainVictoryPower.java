package theuma.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theuma.powers.hooks.OnEnemyDeathPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class CertainVictoryPower extends AbstractEasyPower{

    public final static String ID = makeID("CertainVictoryPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CertainVictoryPower(AbstractCreature owner, int amount) {
        super(ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        applyToSelfTop(new VigorPower(adp(), amount));
    }

    //    @Override
//    public void onEnemyDeath(AbstractMonster m) {
//        applyToSelfTop(new VigorPower(adp(), amount));
//        att(new DrawCardAction(1));
//    }
}
