package net.vladick.animalistic.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.custom.TurkeyEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TurkeyModel extends AnimatedGeoModel<TurkeyEntity> {
    @Override
    public ResourceLocation getModelLocation(TurkeyEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "geo/turkey.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TurkeyEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "textures/entity/turkey/turkey.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TurkeyEntity animatable) {
        return new ResourceLocation(Animalistic.MOD_ID, "animations/turkey.animation.json");
    }
}
