package theuma.powers.hooks;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public interface OnEnemyDeathPower {
    void onEnemyDeath(AbstractMonster m);
}
