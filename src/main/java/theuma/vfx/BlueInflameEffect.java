package theuma.vfx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class BlueInflameEffect extends AbstractGameEffect {
    float x;
    float y;

    public BlueInflameEffect(AbstractCreature creature) {
        this.x = creature.hb.cX;
        this.y = creature.hb.cY;
    }

    public void update() {
        CardCrawlGame.sound.play("ATTACK_FIRE");

        for(int i = 0; i < 75; ++i) {
            AbstractDungeon.effectsQueue.add(new BlueFlameParticleEffect(this.x, this.y));
        }

        for(int i = 0; i < 20; ++i) {
            AbstractDungeon.effectsQueue.add(new BlueEmberEffect(this.x, this.y));
        }

        this.isDone = true;
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}
