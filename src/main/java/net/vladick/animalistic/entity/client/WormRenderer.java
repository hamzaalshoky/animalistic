package net.vladick.animalistic.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.entity.custom.WormEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class WormRenderer extends GeoEntityRenderer<WormEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("animalistic:textures/entity/worm/worm.png");
    
    public WormRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WormModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(WormEntity instance) {
        return TEXTURE;
    }


    @Override
    public RenderType getRenderType(WormEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(1F, 1F, 1F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
