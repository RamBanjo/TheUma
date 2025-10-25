package theuma.cards.generated;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlickCoinEffect;
import theuma.actions.ConditionalScryAction;
import theuma.cards.AbstractEasyCard;
import theuma.cards.LuckySeven;
import theuma.util.CommonUmaMethods;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;

public class TripleSevens extends AbstractEasyCard {
    public final static String ID = makeID("TripleSevens");
    // intellij stuff ATTACK, ENEMY, COMMON, 6, , , , 3, 2

    public TripleSevens() {
        this(true);
    }

    public TripleSevens (boolean hasPreview){
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);

        baseDamage = 7;
        baseMagicNumber = magicNumber = 3;

        PurgeField.purge.set(this, true);

        if(hasPreview){
            cardsToPreview = new LuckySeven(false);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        for(int i = 0; i < magicNumber; i++){
            atb(new VFXAction(new FlickCoinEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY), Settings.ACTION_DUR_MED));
            dmg(m, AbstractGameAction.AttackEffect.NONE);
        }

        AbstractCard newLuckySev = new LuckySeven();

        if (this.upgraded){
            newLuckySev.upgrade();
        }

        atb(new MakeTempCardInHandAction(newLuckySev));
    }

    public void upp() {
        if (cardsToPreview != null){
            cardsToPreview.upgrade();
        }
    }
}