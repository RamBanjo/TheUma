//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.megacrit.cardcrawl.actions.defect;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import java.util.UUID;

public class PermaIncreaseMagicAction extends AbstractGameAction {
    private int miscIncrease;
    private UUID uuid;

    public PermaIncreaseMagicAction(UUID targetUUID, int miscValue, int miscIncrease) {
        this.miscIncrease = miscIncrease;
        this.uuid = targetUUID;
    }

    public void update() {
        for(AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.uuid.equals(this.uuid)) {
                c.misc += this.miscIncrease;
                c.applyPowers();
                c.baseMagicNumber = c.misc;
                c.isMagicNumberModified = false;
            }
        }

        for(AbstractCard c : GetAllInBattleInstances.get(this.uuid)) {
            c.misc += this.miscIncrease;
            c.applyPowers();
            c.baseMagicNumber = c.misc;
        }

        this.isDone = true;
    }
}
