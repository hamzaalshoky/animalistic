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
import net.vladick.animalistic.entity.custom.OlmEntity;
import net.vladick.animalistic.entity.variant.OlmVariant;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;

public class OlmRenderer extends GeoEntityRenderer<OlmEntity> {
    public static final Map<OlmVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(OlmVariant.class), (p_114874_) -> {
                p_114874_.put(OlmVariant.NORMAL,
                        new ResourceLocation(Animalistic.MOD_ID, "textures/entity/olm/olm.png"));
                p_114874_.put(OlmVariant.GRAY,
                        new ResourceLocation(Animalistic.MOD_ID, "textures/entity/olm/olm_gray.png"));
                p_114874_.put(OlmVariant.PINK,
                        new ResourceLocation(Animalistic.MOD_ID, "textures/entity/olm/olm_pink.png"));
                p_114874_.put(OlmVariant.BLUE,
                        new ResourceLocation(Animalistic.MOD_ID, "textures/entity/olm/olm_blue.png"));
            });

    public OlmRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OlmModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(OlmEntity instance) {
        return LOCATION_BY_VARIANT.get(instance.getVariant());
    }


    @Override
    public RenderType getRenderType(OlmEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        if(animatable.isBaby()) {
            stack.scale(0.4F, 0.4F, 0.4F);
        } else {
            stack.scale(1F, 1F, 1F);
        }
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
