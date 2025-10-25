package theuma.cards;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import com.megacrit.cardcrawl.vfx.WallopEffect;
import theuma.CharacterFile;
import theuma.util.Wiz;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class LeadView extends AbstractEasyCard {
    public final static String ID = makeID("LeadView");
    // intellij stuff SKILL, NONE, COMMON, 0, 0, 4, 4, 2, 3

    public LeadView() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 4;
    }

    public void upp() {
        upgradeDamage(2);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        dmg(abstractMonster, AbstractGameAction.AttackEffect.FIRE);
        atb(new DrawCardAction(1, new LeadViewDrawAction(damage, abstractMonster)));
    }

    public static class LeadViewDrawAction extends AbstractGameAction{

        LeadViewDrawAction(int dmg, AbstractCreature victim){
            this.amount = dmg;
            this.target = victim;
        }

        @Override
        public void update() {

            //The effects will only activate if the hand isn't full and if nonUma >= Uma cards in hand.

            int umaCards = (int) hand().group.stream().filter(c -> c.color == CharacterFile.Enums.UMA_COLOR).count();
            int nonUmaCards = hand().size() - umaCards;

            boolean handIsFull = hand().size() >= BaseMod.MAX_HAND_SIZE;

            if(!handIsFull && nonUmaCards >= umaCards && !adp().hasPower(NoDrawPower.POWER_ID)){
                atb(new DamageAction(target, new DamageInfo(adp(), amount), AttackEffect.FIRE));
                atb(new DrawCardAction(1, new LeadViewDrawAction(amount, target)));
            }

            this.isDone = true;
        }
    }

}