package theuma.powers;

import com.evacipated.cardcrawl.mod.stslib.patches.relicInterfaces.OnRemoveCardFromMasterDeckPatch;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnPlayerDeathPower;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import theuma.cards.ALittleFurther;

import static theuma.ModFile.makeID;

public class WinSometimesPower extends AbstractEasyPower implements OnPlayerDeathPower {

    public final static String ID = makeID("WinSometimesPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public WinSometimesPower(AbstractCreature owner, int amount) {
        super(ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public boolean onPlayerDeath(AbstractPlayer abstractPlayer, DamageInfo damageInfo) {

        int recovery = (int) Math.floor(abstractPlayer.maxHealth * (this.amount / 100.0));

        addToBot(new HealAction(abstractPlayer, abstractPlayer, recovery));
        addToBot(new RemoveSpecificPowerAction(abstractPlayer, abstractPlayer, WinSometimesPower.ID));

        AbstractCard toRemove = abstractPlayer.masterDeck.findCardById(ALittleFurther.ID);

        if (toRemove != null){
            AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(toRemove));
            CardCrawlGame.sound.play("CARD_EXHAUST");
            AbstractDungeon.player.masterDeck.removeCard(toRemove);
        }

        return false;
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }
}
