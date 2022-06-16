package net.vladick.animalistic.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.custom.CaracalEntity;
import net.vladick.animalistic.entity.custom.MudpuppyEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CaracalRenderer extends GeoEntityRenderer<CaracalEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("animalistic:textures/entity/caracal/caracal.png");
    private static final ResourceLocation CHEF_FLOPPA = new ResourceLocation("animalistic:textures/entity/caracal/caracal_chef.png");
    private static final ResourceLocation SAD_FLOPPA = new ResourceLocation("animalistic:textures/entity/caracal/sad_caracal.png");

    public CaracalRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CaracalModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(CaracalEntity instance) {
        return instance.isChefFloppa() ? CHEF_FLOPPA : TEXTURE;
    }


    @Override
    public RenderType getRenderType(CaracalEntity animatable, float partialTicks, PoseStack stack,
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
