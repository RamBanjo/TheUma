package theuma.powers;

import basemod.cardmods.EtherealMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Seek;
import com.megacrit.cardcrawl.cards.green.Nightmare;
import com.megacrit.cardcrawl.cards.purple.Scrawl;
import com.megacrit.cardcrawl.cards.red.Exhume;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theuma.util.CommonUmaMethods;

import java.util.ArrayList;
import java.util.Collections;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.adp;
import static theuma.util.Wiz.atb;

public class UmapyoiPower extends AbstractEasyPower{

    public final static String ID = makeID("UmapyoiPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static ArrayList<AbstractCard> cards = new ArrayList<>();


    public UmapyoiPower(AbstractCreature owner, int amount) {
        super(ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void atStartOfTurn() {

        initCardsList();

        for (int i = 0; i < this.amount ; i++){
            Collections.shuffle(cards);

            atb(new MakeTempCardInHandAction(cards.get(0)));
        }
    }


    public static void initCardsList(){
        cards.clear();

        cards.add(new Exhume());
        cards.add(new Nightmare());
        cards.add(new Seek());
        cards.add(new Scrawl());

        cards.forEach(c -> CardModifierManager.addModifier(c, new EtherealMod()));
    }

    @Override
    public void updateDescription() {
        if (amount == 1){
            description = DESCRIPTIONS[0];
        }else{
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }

    static {
        initCardsList();
    }
}
