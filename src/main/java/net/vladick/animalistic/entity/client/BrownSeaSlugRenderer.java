package net.vladick.animalistic.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.custom.BrownSeaSlugEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BrownSeaSlugRenderer extends GeoEntityRenderer<BrownSeaSlugEntity> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("animalistic:textures/entity/brown_sea_slug/brown_sea_slug.png");
    private static final ResourceLocation INKY = new ResourceLocation("animalistic:textures/entity/brown_sea_slug/brown_sea_slug_inky.png");
    
    public BrownSeaSlugRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BrownSeaSlugModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(BrownSeaSlugEntity instance) {
        return instance.isInky() ? INKY : TEXTURE;
    }


    @Override
    public RenderType getRenderType(BrownSeaSlugEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
            stack.scale(1F, 1F, 1F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
