package net.vladick.animalistic.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.custom.BurrowingFrogEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BurrowingFrogRenderer extends GeoEntityRenderer<BurrowingFrogEntity> {

    public BurrowingFrogRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BurrowingFrogModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(BurrowingFrogEntity instance) {
        return new ResourceLocation(Animalistic.MOD_ID, "textures/entity/burrowingfrog/burrowingfrog.png");
    }


    @Override
    public RenderType getRenderType(BurrowingFrogEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
            stack.scale(1F, 1F, 1F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
