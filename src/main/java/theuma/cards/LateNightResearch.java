package theuma.cards;


import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Bash;
import com.megacrit.cardcrawl.cards.red.Clothesline;
import com.megacrit.cardcrawl.cards.red.Uppercut;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.cards.status.NightOwlStatus;
import theuma.powers.UmaMoodPower;
import theuma.util.CommonUmaMethods;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;
import static theuma.util.Wiz.atb;

public class LateNightResearch extends AbstractEasyCard{
    public final static String ID = makeID("LateNightResearch");

    public LateNightResearch(){
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);

        this.baseMagicNumber = this.magicNumber = 3;
        MultiCardPreview.add(this, new NightOwlStatus());
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        //draw 3 cards
        atb(new DrawCardAction(3));

        //Worsen Mood by 1 Rank
        applyToSelf(new UmaMoodPower(abstractPlayer, -1));

        //If Sorrowful, then add the Night Owl
        if (CommonUmaMethods.playerIsSorrowful(abstractPlayer)){
            atb(new MakeTempCardInDrawPileAction(new NightOwlStatus(), 1, true, true));
        }

    }
}
