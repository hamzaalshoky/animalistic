package net.vladick.animalistic.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.custom.GeckoEntity;
import net.vladick.animalistic.entity.variant.GeckoVariant;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;

public class GeckoRenderer extends GeoEntityRenderer<GeckoEntity> {
    public static final Map<GeckoVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(GeckoVariant.class), (p_114874_) -> {
                p_114874_.put(GeckoVariant.BROWN,
                        new ResourceLocation(Animalistic.MOD_ID, "textures/entity/gecko/gecko_brown.png"));
                p_114874_.put(GeckoVariant.GOLDEN,
                        new ResourceLocation(Animalistic.MOD_ID, "textures/entity/gecko/gecko_golden.png"));
                p_114874_.put(GeckoVariant.AQUA,
                        new ResourceLocation(Animalistic.MOD_ID, "textures/entity/gecko/gecko_aqua.png"));
            });


    public GeckoRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GeckoModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(GeckoEntity instance) {
        return LOCATION_BY_VARIANT.get(instance.getVariant());
    }


    @Override
    public RenderType getRenderType(GeckoEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(1F, 1F, 1F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
