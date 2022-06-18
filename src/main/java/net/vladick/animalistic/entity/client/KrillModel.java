package net.vladick.animalistic.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.custom.KrillEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KrillModel extends AnimatedGeoModel<KrillEntity> {
    @Override
    public ResourceLocation getModelLocation(KrillEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "geo/krill.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(KrillEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "textures/entity/krill/krill.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(KrillEntity animatable) {
        return new ResourceLocation(Animalistic.MOD_ID, "animations/krill.animation.json");
    }
}
