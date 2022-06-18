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
import net.vladick.animalistic.entity.custom.KrillEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;

public class KrillRenderer extends GeoEntityRenderer<KrillEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("animalistic:textures/entity/krill/krill.png");
    private static final ResourceLocation BARABERE = new ResourceLocation("animalistic:textures/entity/krill/krill_barbare.png");
    
    public KrillRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new KrillModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(KrillEntity instance) {
        return instance.isBarbare() ? BARABERE : TEXTURE;
    }


    @Override
    public RenderType getRenderType(KrillEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(0.8F, 0.8F, 0.8F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
