package theuma.powers;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import static theuma.ModFile.makeID;

public class UmaMoodPower extends AbstractEasyPower{
    public final static String ID = makeID("UmaMood");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    private static final int MIN_MOOD = -2;
    private static final int MAX_MOOD = 2;

    public UmaMoodPower(AbstractCreature owner, int amount) {
        super(ID, NAME, PowerType.BUFF, false, owner, amount);

        this.canGoNegative = true;
        clamp_mood_amt();

        System.out.println("UmaMood: " + amount);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + get_mood_string() + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2] + this.get_energy_gain() + DESCRIPTIONS[3];

        if (this.amount <= MIN_MOOD){
            this.description += DESCRIPTIONS[9];
        }
    }

    private String get_mood_string(){
        switch (this.amount){
            case -2:
                return DESCRIPTIONS[8];
            case -1:
                return DESCRIPTIONS[7];
            case 1:
                return DESCRIPTIONS[5];
            case 2:
                return DESCRIPTIONS[4];
            default:
                return DESCRIPTIONS[6];
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        if (this.amount == 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NAME));
        }
        clamp_mood_amt();
    }

    @Override
    public void reducePower(int reduceAmount) {
        this.fontScale = 8.0F;
        this.amount -= reduceAmount;
        if (this.amount == 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, makeID("UmaMood")));
        }
        clamp_mood_amt();
    }

    private void clamp_mood_amt(){

        if (this.amount > MAX_MOOD){
            this.amount = MAX_MOOD;
            AbstractDungeon.effectList.add(new ThoughtBubble(owner.dialogX, owner.dialogY, 3.0F, DESCRIPTIONS[10], true));
        }

        if (this.amount < MIN_MOOD){
            this.amount = MIN_MOOD;
            AbstractDungeon.effectList.add(new ThoughtBubble(owner.dialogX, owner.dialogY, 3.0F, DESCRIPTIONS[11], true));
        }


    }

    private int get_energy_gain(){
        switch (this.amount){
            case MIN_MOOD:
                return -1;
            case MAX_MOOD:
                return +1;
            default:
                return 0;
        }
    }

    @Override
    public void atStartOfTurn() {

        if (get_energy_gain() != 0){
            this.addToBot(new GainEnergyAction(get_energy_gain()));
            this.flash();
        }
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage + (float)this.amount : damage;
    }

    @Override
    public void onVictory() {
        if (AbstractDungeon.cardRandomRng.random(4) == 0 && this.amount == MIN_MOOD){
            this.flash();
            AbstractDungeon.topLevelEffects.add(new ShowCardAndObtainEffect(AbstractDungeon.getCardWithoutRng(AbstractCard.CardRarity.CURSE), (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
        }
    }
}
