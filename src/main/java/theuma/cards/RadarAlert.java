package theuma.cards;

import basemod.abstracts.AbstractCardModifier;
import basemod.cardmods.EtherealMod;
import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.BeamCell;
import com.megacrit.cardcrawl.cards.blue.GoForTheEyes;
import com.megacrit.cardcrawl.cards.green.Neutralize;
import com.megacrit.cardcrawl.cards.purple.WheelKick;
import com.megacrit.cardcrawl.cards.red.Dropkick;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import theuma.actions.PickCardsFromListAction;
import theuma.util.ProAudio;

import java.sql.Array;
import java.util.ArrayList;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;
import static theuma.util.Wiz.playAudio;

public class RadarAlert extends AbstractEasyCard {

    public final static String ID = makeID("RadarAlert");
    // intellij stuff skill, self, uncommon

    public RadarAlert() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        MultiCardPreview.add(this, new Dropkick(), new WheelKick(), new GolshiKick());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        int discount = 0;

        if (m != null && m.getIntentBaseDmg() >= 0) {
            AbstractDungeon.effectList.add(new SpeechBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, cardStrings.EXTENDED_DESCRIPTION[1], true));
            playAudio(ProAudio.TRAINER_DETECTED);
            discount = -1;
        } else {
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, cardStrings.EXTENDED_DESCRIPTION[2], true));
        }

        ArrayList<AbstractCard> cards = new ArrayList<>();
        cards.add(new Dropkick());
        cards.add(new WheelKick());
        cards.add(new GolshiKick());

        ArrayList<AbstractCardModifier> mods = new ArrayList<>();
        mods.add(new ExhaustMod());

        atb(new PickCardsFromListAction(cards, -1, cardStrings.EXTENDED_DESCRIPTION[0], discount, this.upgraded, mods));

    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();

        for(AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m.isDeadOrEscaped() && m.getIntentBaseDmg() >= 0) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }

    }

    @Override
    public void upp() {
        MultiCardPreview.multiCardPreview.get(this).forEach(AbstractCard::upgrade);
    }
}