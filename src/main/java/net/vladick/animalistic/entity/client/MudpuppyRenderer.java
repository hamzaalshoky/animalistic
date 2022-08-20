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
import net.vladick.animalistic.entity.custom.MudpuppyEntity;
import net.vladick.animalistic.entity.variant.MudpuppyVariant;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;

public class MudpuppyRenderer extends GeoEntityRenderer<MudpuppyEntity> {
    public static final Map<MudpuppyVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MudpuppyVariant.class), (p_114874_) -> {
                p_114874_.put(MudpuppyVariant.NORMAL,
                        new ResourceLocation(Animalistic.MOD_ID, "textures/entity/mudpuppy/mudpuppy.png"));
                p_114874_.put(MudpuppyVariant.GOLDEN,
                        new ResourceLocation(Animalistic.MOD_ID, "textures/entity/mudpuppy/mudpuppy_golden.png"));
            });

    private static final ResourceLocation PUP = new ResourceLocation("animalistic:textures/entity/mudpuppy/mud_puppy.png");

    public MudpuppyRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new MudpuppyModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(MudpuppyEntity instance) {
        return LOCATION_BY_VARIANT.get(instance.getVariant());
    }


    @Override
    public RenderType getRenderType(MudpuppyEntity animatable, float partialTicks, PoseStack stack,
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
