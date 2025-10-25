package theuma.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.cards.blue.Seek;
import com.megacrit.cardcrawl.cards.green.Nightmare;
import com.megacrit.cardcrawl.cards.purple.Scrawl;
import com.megacrit.cardcrawl.cards.red.Exhume;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.powers.PracticePerfectPower;
import theuma.powers.UmapyoiPower;
import theuma.util.ProAudio;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.*;

public class UmapyoiForm extends AbstractEasyCard {
    public final static String ID = makeID("UmapyoiForm");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public UmapyoiForm() {
        super(ID, 4, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.isEthereal = true;

        MultiCardPreview.add(this, new Exhume(), new Nightmare(), new Seek(), new Scrawl());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.UMAPYOI);
        applyToSelf(new UmapyoiPower(p, 1));
    }

    @Override
    public void upp() {
        this.isEthereal = false;
    }
}