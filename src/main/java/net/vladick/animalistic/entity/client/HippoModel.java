package net.vladick.animalistic.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.custom.HippoEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HippoModel extends AnimatedGeoModel<HippoEntity> {

    @Override
    public ResourceLocation getModelLocation(HippoEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "geo/hippo.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(HippoEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "textures/entity/hippo/hippo.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(HippoEntity animatable) {
        return new ResourceLocation(Animalistic.MOD_ID, "animations/hippo.animation.json");
    }
}
