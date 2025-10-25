package theuma.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.powers.UmapyoiPower;
import theuma.powers.WinSometimesPower;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.applyToSelf;

public class ALittleFurther extends AbstractEasyCard {
    public final static String ID = makeID("ALittleFurther");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public ALittleFurther() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 20;
        this.isEthereal = true;

        tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new WinSometimesPower(p, magicNumber));
    }

    @Override
    public void upp() {
        this.isEthereal = false;
    }
}