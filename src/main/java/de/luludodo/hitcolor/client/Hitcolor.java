package de.luludodo.hitcolor.client;

import com.mojang.blaze3d.platform.GlConst;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class Hitcolor implements ClientModInitializer {
    public static int color = 0x11FF0000;
    //                        0xAABBGGRR

    // Texture used to update color
    public static NativeImageBackedTexture texture = null;

    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register(
                (dispatcher, registryAccess) -> {
                    dispatcher.register(
                            literal("hitcolor").
                                    then(
                                            argument("color", IntegerArgumentType.integer()).
                                                    executes(cmd -> {
                                                        color = cmd.getArgument("color", Integer.class);
                                                        // Code from OverlayTexture.<init> to update Image with new colors
                                                        NativeImage nativeImage = texture.getImage();
                                                        for (int i = 0; i < 16; ++i) {
                                                            for (int j = 0; j < 16; ++j) {
                                                                if (i < 8) {
                                                                    nativeImage.setColor(j, i, color);
                                                                    continue;
                                                                }
                                                                int k = (int)((1.0f - (float)j / 15.0f * 0.75f) * 255.0f);
                                                                nativeImage.setColor(j, i, k << 24 | 0xFFFFFF);
                                                            }
                                                        }
                                                        RenderSystem.activeTexture(GlConst.GL_TEXTURE1);
                                                        texture.bindTexture();
                                                        nativeImage.upload(0, 0, 0, 0, 0, nativeImage.getWidth(), nativeImage.getHeight(), false, true, false, false);
                                                        RenderSystem.activeTexture(GlConst.GL_TEXTURE0);
                                                        // Update Image end
                                                        return Command.SINGLE_SUCCESS;
                                                    })
                                    )
                    );
                });
    }
}
