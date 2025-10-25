package theuma.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlickCoinEffect;
import theuma.actions.ConditionalScryAction;
import theuma.cards.AbstractEasyCard;
import theuma.util.CommonUmaMethods;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class LuckBeWithMe extends AbstractEasyCard {
    public final static String ID = makeID("LuckBeWithMe");
    // intellij stuff ATTACK, ENEMY, COMMON, 6, , , , 3, 2

    public LuckBeWithMe() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 6;
        baseMagicNumber = magicNumber = 3;
        baseSecondMagic = secondMagic = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new FlickCoinEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY), 0.3F));
        dmg(m, AbstractGameAction.AttackEffect.NONE);

        atb(new ConditionalScryAction(magicNumber,
                CommonUmaMethods::cardIsNonUma,
                new ModifyDamageAction(this.uuid, secondMagic)
                ));
    }

    public void upp() {
        upgradeMagicNumber(2);
        upgradeSecondMagic(1);

    }
}