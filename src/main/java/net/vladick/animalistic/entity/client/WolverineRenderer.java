package net.vladick.animalistic.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.entity.custom.WolverineEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class WolverineRenderer extends GeoEntityRenderer<WolverineEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("animalistic:textures/entity/wolverine/wolverine.png");
    private static final ResourceLocation MARVEL_WOLVERINE = new ResourceLocation("animalistic:textures/entity/wolverine/wolverine_marvel.png");
    private static final ResourceLocation LOGAN_WOLVERINE = new ResourceLocation("animalistic:textures/entity/wolverine/wolverine_logan.png");

    public WolverineRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WolverineModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(WolverineEntity instance) {
        return instance.isMarvelWolverine() ? MARVEL_WOLVERINE : instance.isLogan() ? LOGAN_WOLVERINE : TEXTURE;
    }


    @Override
    public RenderType getRenderType(WolverineEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
            stack.scale(1F, 1F, 1F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
