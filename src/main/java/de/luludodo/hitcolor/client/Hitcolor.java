package de.luludodo.hitcolor.client;

import com.mojang.brigadier.Command;
import de.luludodo.hitcolor.config.HitcolorConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImageBackedTexture;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class Hitcolor implements ClientModInitializer {
    public final static String MOD_ID = "hitcolor";
    public static NativeImageBackedTexture texture = null;

    private static boolean openConfig = false;

    @Override
    public void onInitializeClient() {
        HitcolorConfig.init(MOD_ID, HitcolorConfig.class);
        ClientCommandRegistrationCallback.EVENT.register(
                (dispatcher, registryAccess) -> {
                    dispatcher.register(
                            literal("hitcolor").
                                    executes(cmd -> {
                                        openConfig = true;
                                        return Command.SINGLE_SUCCESS;
                                    })
                    );
                });
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (openConfig) {
                openConfig = false;
                MinecraftClient.getInstance().setScreen(HitcolorConfig.getScreen(client.currentScreen, MOD_ID));
            }
        });
    }
}
