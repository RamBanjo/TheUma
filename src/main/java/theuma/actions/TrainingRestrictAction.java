package theuma.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.EntanglePower;
import com.megacrit.cardcrawl.powers.NoBlockPower;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import theuma.powers.EntangleWithAmount;
import theuma.powers.NoGainsPower;
import theuma.powers.WitlessPower;

import java.util.ArrayList;
import java.util.Collections;

import static theuma.util.Wiz.adp;
import static theuma.util.Wiz.applyToSelf;

public class TrainingRestrictAction extends AbstractGameAction {

    AbstractPlayer p;

    String spdDialog;
    String staDialog;
    String strDialog;
    String gutDialog;
    String witDialog;

    public TrainingRestrictAction(String spdDialog, String staDialog, String strDialog, String gutDialog, String witDialog){
        p = adp();
        this.spdDialog = spdDialog;
        this.staDialog = staDialog;
        this.strDialog = strDialog;
        this.gutDialog = gutDialog;
        this.witDialog = witDialog;
    }


    @Override
    public void update() {

        ArrayList<String> restricted = new ArrayList<>();
        restricted.add("SPD"); //Speed Restricted -> Cannot draw cards
        restricted.add("STA"); //Stamina Restricted -> Cannot gain Block
        restricted.add("POW"); //Power Restricted -> Cannot gain Strength and Dex
        restricted.add("GUT"); //Guts Restricted -> Cannot use Attacks
        restricted.add("WIT"); //Wit Restricted -> Cannot use Power Cards

        Collections.shuffle(restricted);

        switch (restricted.get(0)){
            case "SPD":
                AbstractDungeon.effectList.add(new SpeechBubble(p.dialogX, p.dialogY, spdDialog, true));
                applyToSelf(new NoDrawPower(p));
                break;
            case "STA":
                AbstractDungeon.effectList.add(new SpeechBubble(p.dialogX, p.dialogY, staDialog, true));
                applyToSelf(new NoBlockPower(p, 1, true));
                break;
            case "POW":
                AbstractDungeon.effectList.add(new SpeechBubble(p.dialogX, p.dialogY, strDialog, true));
                applyToSelf(new NoGainsPower(p, 1));
                break;
            case "GUT":
                AbstractDungeon.effectList.add(new SpeechBubble(p.dialogX, p.dialogY, gutDialog, true));
                applyToSelf(new EntanglePower(p));
                break;
            case "WIT":
                AbstractDungeon.effectList.add(new SpeechBubble(p.dialogX, p.dialogY, witDialog, true));
                applyToSelf(new WitlessPower(p, 1));
                break;
        }

        this.isDone = true;
    }
}
