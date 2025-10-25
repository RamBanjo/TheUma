package theuma.powers.hooks;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public interface OnOtherPowerRemovedPower {

    void onOtherPowerRemoved(AbstractCreature owner, AbstractPower removedPower);

}
