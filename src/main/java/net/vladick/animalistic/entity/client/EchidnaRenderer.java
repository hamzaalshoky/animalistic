package net.vladick.animalistic.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.entity.custom.EchidnaEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class EchidnaRenderer extends GeoEntityRenderer<EchidnaEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("animalistic:textures/entity/echidna/echidna.png");
    private static final ResourceLocation KNUCKLES = new ResourceLocation("animalistic:textures/entity/echidna/echidna_knuckles.png");
    

    public EchidnaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EchidnaModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(EchidnaEntity instance) {
        return instance.isKnuckles() ? KNUCKLES : TEXTURE;
    }


    @Override
    public RenderType getRenderType(EchidnaEntity animatable, float partialTicks, PoseStack stack,
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
