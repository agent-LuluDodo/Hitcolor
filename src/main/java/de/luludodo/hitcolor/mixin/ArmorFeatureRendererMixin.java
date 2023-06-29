package de.luludodo.hitcolor.mixin;

import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorFeatureRenderer.class)
public abstract class ArmorFeatureRendererMixin {

    @Inject(method = "renderArmor", at = @At(value = "TAIL"))
    private <T extends LivingEntity, A extends BipedEntityModel<T>> void renderHurt(MatrixStack matrices, VertexConsumerProvider vertexConsumers, T entity, EquipmentSlot armorSlot, int light, A model, CallbackInfo ci) {
        // Agastfgjdshgdskgklsdjgkdjhkdjhskdhjskdjhkfjhkdjhkdjhklhsjdkhjsdhlsadjhljljöähasljhflhlhjaahlhaljhjöhajflöhfajlahjflsöölahjöfljöjöäolflfhafdljöäahfljafdhjlöäafhdljöäöfhadljöö
    }

    /*
    @ModifyArg(method = "renderArmorParts", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/model/BipedEntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;IIFFFF)V"), index = 2)
    private int modifyLight(int light) {
        return -1;
    }

    @ModifyArg(method = "renderArmorParts", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/model/BipedEntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;IIFFFF)V"), index = 3)
    private int modifyOverlay(int overlay) {
        RenderSystem.setShaderTexture(0, Hitcolor.texture.getGlId());
        return OverlayTexture.packUv(0, OverlayTexture.getV(true));
    }

    @ModifyArg(method = "renderArmorParts", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/model/BipedEntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;IIFFFF)V"), index = 4)
    private float modifyRed(float red) {
        return ((Hitcolor.color) & 0x000000FF)/255f*(1-((Hitcolor.color >> 24) & 0x000000FF)/255f);
    }

    @ModifyArg(method = "renderArmorParts", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/model/BipedEntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;IIFFFF)V"), index = 5)
    private float modifyGreen(float green) {
        return ((Hitcolor.color >> 8) & 0x000000FF)/255f*(1-((Hitcolor.color >> 24) & 0x000000FF)/255f);
    }

    @ModifyArg(method = "renderArmorParts", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/model/BipedEntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;IIFFFF)V"), index = 6)
    private float modifyBlue(float blue) {
        return ((Hitcolor.color >> 16) & 0x000000FF)/255f*(1-((Hitcolor.color >> 24) & 0x000000FF)/255f);
    }
    */
}
