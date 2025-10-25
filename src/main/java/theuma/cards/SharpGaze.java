package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import theuma.actions.ChangePlayedCardExhaustAction;
import theuma.util.CommonUmaMethods;

import java.util.ArrayList;
import java.util.Collections;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class SharpGaze extends AbstractEasyCard {
    public final static String ID = makeID("SharpGaze");
    // intellij stuff attack, enemy, rare, , , , , 0, 1

    public SharpGaze() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 2;
        this.baseMagicNumber = this.magicNumber = 1;
        this.exhaust = true;
    }

    public static int countCards() {
        int count = 0;

        for(AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.cardID.equals(ID)) {
                ++count;
            }
        }

        for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.cardID.equals(ID)) {
                ++count;
            }
        }

        for(AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.cardID.equals(ID)) {
                ++count;
            }
        }

        return count;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        ArrayList<String> debuffChosen = new ArrayList<>();
        debuffChosen.add("vuln");
        debuffChosen.add("weak");
        debuffChosen.add("pois");

        for(int i = 0; i < countCards(); i++){

            Collections.shuffle(debuffChosen);

            switch (debuffChosen.get(0)){
                case "vuln":
                    dmgRandom(AbstractGameAction.AttackEffect.FIRE, null, mon -> applyToEnemy(mon, new VulnerablePower(mon, magicNumber, false)));
                    break;
                case "weak":
                    dmgRandom(AbstractGameAction.AttackEffect.NONE, null, mon -> {
                        this.addToTop(new VFXAction(new LightningEffect(mon.drawX, mon.drawY)));
                        this.addToTop(new VFXAction(new FlashAtkImgEffect(mon.hb.cX, mon.hb.cY, AbstractGameAction.AttackEffect.NONE)));
                        this.addToTop(new SFXAction("ORB_LIGHTNING_EVOKE", 0.1F));
                        applyToEnemy(mon, new WeakPower(mon, magicNumber, false));
                    });
                    break;
                case "pois":
                    dmgRandom(AbstractGameAction.AttackEffect.POISON, null, mon -> applyToEnemy(mon, new PoisonPower(mon, p, magicNumber)));
                    break;
            }
        }

        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void applyPowers() {

        super.applyPowers();

        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0] + countCards();
        if (countCards() == 1) {
            this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[2];
        }

        this.initializeDescription();
    }


    @Override
    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }
}