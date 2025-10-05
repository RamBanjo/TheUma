package theuma.relics;

import theuma.CharacterFile;

import static theuma.ModFile.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, CharacterFile.Enums.UMA_COLOR);
    }
}
