package net.vladick.animalistic.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.entity.custom.HippoEntity;
import net.vladick.animalistic.entity.custom.MudpuppyEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class HippoRenderer extends GeoEntityRenderer<HippoEntity> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("animalistic:textures/entity/hippo/hippo.png");
    private static final ResourceLocation VLADA = new ResourceLocation("animalistic:textures/entity/hippo/hippo_vlada.png");
    private static final ResourceLocation MUDDY = new ResourceLocation("animalistic:textures/entity/hippo/hippo_muddy.png");

    public HippoRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new HippoModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(HippoEntity instance) {
        return instance.isVlada() ? VLADA : instance.isMuddy() ? MUDDY : TEXTURE;
    }


    @Override
    public RenderType getRenderType(HippoEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
            if ((animatable.isBaby())){
                stack.scale(0.4F, 0.4F, 0.4F);
            }else{
                stack.scale(1F, 1F, 1F);
            }
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
