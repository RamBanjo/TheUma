package theuma.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import theuma.util.ImageHelper;
import theuma.util.TexLoader;

import static theuma.ModFile.makeImagePath;

public class MoodDisplayEffect extends AbstractGameEffect {

    private float x;
    private float y;
    private float startY;
    private float dstY;
    private int mood;
    private TextureAtlas.AtlasRegion img;


    public MoodDisplayEffect(Hitbox hb, int inputmood) {
        this.mood = inputmood + 2;

        this.img = ImageHelper.asAtlasRegion(TexLoader.getTexture(makeImagePath("vfx/MoodButton" + this.mood + ".png")));
        this.x = hb.cX - (float)this.img.packedWidth / 2.0F;
        this.y = hb.cY + hb.height / 2.0F - (float)this.img.packedHeight / 2.0F;
        this.startY = this.y - 50.0F * Settings.scale;
        this.dstY = this.y + 70.0F * Settings.scale;
        this.startingDuration = 0.8F;
        this.duration = this.startingDuration;
        this.color = Color.WHITE.cpy();
        this.color.a = 0.0F;
    }

    public void update() {
        this.y = Interpolation.swingIn.apply(this.dstY, this.startY, this.duration * (1.0F / this.startingDuration));
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < this.startingDuration * 0.8F) {
            this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration * (1.0F / this.startingDuration / 0.5F));
        } else {
            this.color.a = MathHelper.fadeLerpSnap(this.color.a, 0.0F);
        }

        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(this.color);
        sb.draw(this.img, this.x, this.y, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale, this.scale, this.rotation);
        sb.setBlendFunction(770, 771);
    }

    @Override
    public void dispose() {

    }
}
