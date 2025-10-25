package theuma.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theuma.util.CommonUmaMethods;
import theuma.vfx.PurpleInflameEffect;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class ForVittoria extends AbstractEasyCard{
    public final static String ID = makeID("ForVittoria");

    public ForVittoria(){
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);

        this.baseMagicNumber = this.magicNumber = 3;
        this.baseSecondMagic = this.secondMagic = 2;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        atb(new DrawCardAction(magicNumber, new ForVittoriaDrawAction(secondMagic)));
    }

    public static class ForVittoriaDrawAction extends AbstractGameAction{

        ForVittoriaDrawAction(int strGain){
            amount = strGain;
        }

        public void update() {
            for (AbstractCard c: DrawCardAction.drawnCards){
                if (CommonUmaMethods.cardIsNonUma(c)){
                    atb(new VFXAction(new PurpleInflameEffect(adp())));
                    applyToSelf(new StrengthPower(adp(), amount));
                }
            }
            isDone = true;
        }
    }
}
