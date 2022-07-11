package net.vladick.animalistic.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.custom.TurkeyEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class TurkeyRenderer extends GeoEntityRenderer<TurkeyEntity> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("animalistic:textures/entity/turkey/turkey.png");
    private static final ResourceLocation BEST_PUN_EVER = new ResourceLocation("animalistic:textures/entity/turkey/turkey_bestpunever.png");

    public TurkeyRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new TurkeyModel());
        this.shadowRadius = 0.15f;
    }

    @Override
    public ResourceLocation getTextureLocation(TurkeyEntity instance) {
        return instance.isTurkeyLand() ? BEST_PUN_EVER : TEXTURE;
    }


    @Override
    public RenderType getRenderType(TurkeyEntity animatable, float partialTicks, PoseStack stack,
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
