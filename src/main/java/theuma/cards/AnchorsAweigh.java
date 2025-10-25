package theuma.cards;


import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theuma.cards.status.LateStartStatus;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class AnchorsAweigh extends AbstractEasyCard{
    public final static String ID = makeID("AnchorsAweigh");

    public AnchorsAweigh(){
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);

        this.baseMagicNumber = this.magicNumber = 99;

        this.cardsToPreview = new LateStartStatus();
        this.exhaust = true;
    }

    @Override
    public void upp() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        if (this.upgraded && abstractMonster.hasPower(ArtifactPower.POWER_ID)){
            atb(new RemoveSpecificPowerAction(abstractMonster, abstractPlayer, ArtifactPower.POWER_ID));
        }

        applyToEnemy(abstractMonster, new WeakPower(abstractMonster, magicNumber, false));
        applyToEnemy(abstractMonster, new VulnerablePower(abstractMonster, magicNumber, false));

        atb(new MakeTempCardInDrawPileAction(new LateStartStatus(), 3, true, false));

    }
}
