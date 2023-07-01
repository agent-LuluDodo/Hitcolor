package de.luludodo.hitcolor.config;

import com.mojang.blaze3d.platform.GlConst;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.texture.NativeImage;

import java.awt.*;

public class HitcolorConfig extends MidnightConfig {
    @Comment(centered = true) public static Comment hitcolor;
    @Entry(isColor = true, width = 7, min = 7) public static String color = "#ff0000";
    @Entry(isSlider = true, min = 0, max = 100, precision = 10) public static float alpha = 32.8f;

    private static Integer processedColor;

    public static int getColor() {
        if (processedColor == null) {
            updateColor();
        }
        return processedColor;
    }

    public static void updateColor() {
        Color rgbColor;
        try {
            rgbColor = new Color(Integer.parseInt(color.substring(1), 16));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            if (processedColor == null) {
                processedColor = -1308622593;
            }
            return;
        }
        processedColor = rgbColor.getRed() | rgbColor.getGreen() << 8 | rgbColor.getBlue() << 16 | Math.round(0xFF * (1 - alpha / 100)) << 24;
    }

    public static void updateHitcolor() {
        NativeImage nativeImage = de.luludodo.hitcolor.client.Hitcolor.texture.getImage();
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                if (i < 8) {
                    nativeImage.setColor(j, i, processedColor);
                    continue;
                }
                int k = (int)((1.0f - (float)j / 15.0f * 0.75f) * 255.0f);
                nativeImage.setColor(j, i, k << 24 | 0xFFFFFF);
            }
        }
        RenderSystem.activeTexture(GlConst.GL_TEXTURE1);
        de.luludodo.hitcolor.client.Hitcolor.texture.bindTexture();
        nativeImage.upload(0, 0, 0, 0, 0, nativeImage.getWidth(), nativeImage.getHeight(), false, true, false, false);
        RenderSystem.activeTexture(GlConst.GL_TEXTURE0);
    }
}
