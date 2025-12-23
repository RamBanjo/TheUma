package theuma.events;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.city.TheJoust;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import com.megacrit.cardcrawl.vfx.combat.ScreenOnFireEffect;
import theuma.cards.LegacyLivesOn;
import theuma.cards.curses.MigraineCurse;
import theuma.cards.curses.PracticePoorCurse;
import theuma.cards.curses.SkinOutbreakCurse;

import static theuma.ModFile.makeID;
import static theuma.ModFile.makeImagePath;
import static theuma.util.Wiz.adp;

public class AcupuncturistEvent extends AbstractImageEvent {

    public static String ID = makeID(AcupuncturistEvent.class.getSimpleName());
    public static EventStrings strings = CardCrawlGame.languagePack.getEventString(ID);
    public static final String NAME = strings.NAME;
    public static final String[] DESCRIPTIONS = strings.DESCRIPTIONS;
    public static final String[] OPTIONS = strings.OPTIONS;
    private static final String DIALOG_1 = DESCRIPTIONS[0];
    private CurScreen screen;
    private boolean success;

    public AcupuncturistEvent(){
        super(NAME, DIALOG_1, makeImagePath("events/AcupunctureEvent.png"));

        int winChance = 50;
        if (AbstractDungeon.ascensionLevel >= 15) {
            winChance = 40;
        }

        this.screen = CurScreen.INTRO;
        this.imageEventText.setDialogOption(OPTIONS[0] + winChance + OPTIONS[3] + OPTIONS[4] + (100 - winChance) + OPTIONS[3] + OPTIONS[8]);
        this.imageEventText.setDialogOption(OPTIONS[1] + winChance + OPTIONS[3] + OPTIONS[5] + (100 - winChance) + OPTIONS[3] + OPTIONS[7]);
        this.imageEventText.setDialogOption(OPTIONS[2] + winChance + OPTIONS[3] + OPTIONS[6] + (100 - winChance) + OPTIONS[3] + OPTIONS[9]);
        this.imageEventText.setDialogOption(OPTIONS[10]);
        this.success = AbstractDungeon.miscRng.randomBoolean(winChance / 100f);
    }

    @Override
    protected void buttonEffect(int i) {
        switch (this.screen){
            case INTRO:
                switch (i){
                    case 0:
                        //Strength Chakra for Rare Relic or Migraine

                        AbstractDungeon.topLevelEffects.add(new BorderFlashEffect(Color.RED));
                        CardCrawlGame.sound.play("ATTACK_DAGGER_2");

                        if(success){
                            this.screen = CurScreen.SUCCESS;
                            this.imageEventText.updateBodyText(DESCRIPTIONS[2] + DESCRIPTIONS[3]);
                            CardCrawlGame.sound.play("HEAL_1");
                            AbstractRelic newRelic = AbstractDungeon.returnRandomScreenlessRelic(AbstractRelic.RelicTier.RARE);
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.drawX, this.drawY, newRelic);
                            logMetricObtainRelic(ID, OPTIONS[0], newRelic);
                        }else{
                            this.screen = CurScreen.FAIL;
                            this.imageEventText.updateBodyText(DESCRIPTIONS[2] + DESCRIPTIONS[4]);
                            logMetricObtainCard(ID, OPTIONS[0], new MigraineCurse());
                            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new MigraineCurse(), (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                        }

                        this.imageEventText.clearRemainingOptions();
                        this.imageEventText.updateDialogOption(0, OPTIONS[10]);

                        break;
                    case 1:
                        //Winning Chakra for Legacies Live On or Practice Poor

                        AbstractDungeon.topLevelEffects.add(new BorderFlashEffect(Color.RED));
                        CardCrawlGame.sound.play("ATTACK_DAGGER_2");

                        if(success){
                            this.screen = CurScreen.SUCCESS;
                            this.imageEventText.updateBodyText(DESCRIPTIONS[2] + DESCRIPTIONS[3]);
                            CardCrawlGame.sound.play("HEAL_1");
                            logMetricObtainCard(ID, OPTIONS[1], new LegacyLivesOn());
                            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new LegacyLivesOn(), (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                        }else{
                            this.imageEventText.updateBodyText(DESCRIPTIONS[2] + DESCRIPTIONS[4]);
                            logMetricObtainCard(ID, OPTIONS[1], new SkinOutbreakCurse());
                            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new PracticePoorCurse(), (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                        }

                        this.imageEventText.clearRemainingOptions();
                        this.imageEventText.updateDialogOption(0, OPTIONS[10]);

                        break;
                    case 2:
                        //Health Chakra for Max HP or Skin Outbreak

                        AbstractDungeon.topLevelEffects.add(new BorderFlashEffect(Color.RED));
                        CardCrawlGame.sound.play("ATTACK_DAGGER_2");

                        if(success){
                            this.screen = CurScreen.SUCCESS;
                            this.imageEventText.updateBodyText(DESCRIPTIONS[2] + DESCRIPTIONS[3]);
                            int maxGain = 10;
                            CardCrawlGame.sound.play("HEAL_1");
                            logMetricMaxHPGain(ID, OPTIONS[2], maxGain);
                            adp().increaseMaxHp(maxGain, true);
                        }else{
                            this.screen = CurScreen.FAIL;
                            this.imageEventText.updateBodyText(DESCRIPTIONS[2] + DESCRIPTIONS[4]);
                            logMetricObtainCard(ID, OPTIONS[2], new SkinOutbreakCurse());
                            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new SkinOutbreakCurse(), (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                        }

                        this.imageEventText.clearRemainingOptions();
                        this.imageEventText.updateDialogOption(0, OPTIONS[10]);

                        break;
                    default:
                        //Leave Event
                        this.screen = CurScreen.LEAVE;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.imageEventText.clearRemainingOptions();
                        this.imageEventText.updateDialogOption(0, OPTIONS[10]);
                }
                break;
            default:
                this.openMap();
                break;
        }

    }

    private static enum CurScreen{
        INTRO,
        SUCCESS,
        FAIL,
        LEAVE
    }
}
