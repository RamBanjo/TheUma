package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import com.megacrit.cardcrawl.vfx.CollectorStakeEffect;
import theuma.powers.NoGainsPower;
import theuma.util.CommonUmaMethods;
import theuma.util.ProAudio;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class Dominator extends AbstractEasyCard {
    public final static String ID = makeID("Dominator");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public Dominator() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 15;
        baseMagicNumber = magicNumber = 3;
        this.exhaust = true;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(m != null){
            atb(new SFXAction("MONSTER_COLLECTOR_DEBUFF"));
            atb(new VFXAction(new CollectorCurseEffect(m.hb.cX, m.hb.cY)));
        }

        dmg(m, AbstractGameAction.AttackEffect.NONE);

        applyToEnemy(m, new NoGainsPower(m, 3));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }
}