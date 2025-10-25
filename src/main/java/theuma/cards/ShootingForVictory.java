package theuma.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theuma.util.CommonUmaMethods;
import theuma.util.ProAudio;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static theuma.ModFile.makeID;
import static theuma.util.Wiz.atb;
import static theuma.util.Wiz.playAudio;

public class ShootingForVictory extends AbstractEasyCard {
    public final static String ID = makeID("ShootingForVictory");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public ShootingForVictory() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
        baseMagicNumber = magicNumber = 1;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        playAudio(ProAudio.HERMIT_GUN);

        //Cards that are valid are in our hand and have a cost of 1 or greater and are non-uma (which can be modified)
        List<AbstractCard> validCardsInHand = AbstractDungeon.player.hand.group.stream().filter(
                c -> c.cost >= 0 && CommonUmaMethods.cardIsNonUma(c)
        ).collect(Collectors.toList());

        //don't modify if we can't modify
        if (validCardsInHand.isEmpty()){
            return;
        }

        Collections.shuffle(validCardsInHand);
        AbstractCard cardToModifyCost = validCardsInHand.get(0);

        if (cardToModifyCost != null){
            cardToModifyCost.setCostForTurn(cardToModifyCost.costForTurn - magicNumber);
        }
    }

    @Override
    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }
}