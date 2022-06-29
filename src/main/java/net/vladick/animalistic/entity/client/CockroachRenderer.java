package net.vladick.animalistic.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.custom.CockroachEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CockroachRenderer extends GeoEntityRenderer<CockroachEntity> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("animalistic:textures/entity/cockroach/cockroach.png");
    private static final ResourceLocation MARKY = new ResourceLocation("animalistic:textures/entity/cockroach/cockroach_marky.png");
    private static final ResourceLocation JOEY= new ResourceLocation("animalistic:textures/entity/cockroach/cockroach_joey.png");
    private static final ResourceLocation DEEDEE = new ResourceLocation("animalistic:textures/entity/cockroach/cockroach_deedee.png");

    public CockroachRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CockroachModel());
        this.shadowRadius = 0.15f;
    }

    @Override
    public ResourceLocation getTextureLocation(CockroachEntity instance) {
        return instance.isMarky() ? MARKY : instance.isJoey() ? JOEY : instance.isDeeDee() ? DEEDEE : TEXTURE;
    }


    @Override
    public RenderType getRenderType(CockroachEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
            stack.scale(1F, 1F, 1F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
