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
import net.vladick.animalistic.entity.custom.CavyEntity;
import net.vladick.animalistic.entity.variant.CavyVariant;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;

public class CavyRenderer extends GeoEntityRenderer<CavyEntity> {
    public static final Map<CavyVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(CavyVariant.class), (p_114874_) -> {
                p_114874_.put(CavyVariant.AMERICAN,
                        new ResourceLocation(Animalistic.MOD_ID, "textures/entity/cavy/cavy_american.png"));
                p_114874_.put(CavyVariant.MERINO,
                        new ResourceLocation(Animalistic.MOD_ID, "textures/entity/cavy/cavy_merino.png"));
                p_114874_.put(CavyVariant.PERUVIAN,
                        new ResourceLocation(Animalistic.MOD_ID, "textures/entity/cavy/cavy_peruvian.png"));
            });

    private static final ResourceLocation FISTASHKA = new ResourceLocation("animalistic:textures/entity/cavy/cavy_fistashka.png");
    private static final ResourceLocation MYHDALKA = new ResourceLocation("animalistic:textures/entity/cavy/cavy_myhdalka.png");
    private static final ResourceLocation BEAN = new ResourceLocation("animalistic:textures/entity/cavy/cavy_fasolka.png");
    private static final ResourceLocation CARROT = new ResourceLocation("animalistic:textures/entity/cavy/cavy_morkovka.png");
    private static final ResourceLocation HAMZA = new ResourceLocation("animalistic:textures/entity/cavy/cavy_hamza.png");
    
    
    
    public CavyRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CavyModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(CavyEntity instance) {
        return instance.isFistashka() ? FISTASHKA : instance.isMyhdalka() ? MYHDALKA : instance.isHamza() ? HAMZA : instance.isBean() ? BEAN : instance.isCarrot() ? CARROT : LOCATION_BY_VARIANT.get(instance.getVariant());
    }


    @Override
    public RenderType getRenderType(CavyEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(0.8F, 0.8F, 0.8F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
