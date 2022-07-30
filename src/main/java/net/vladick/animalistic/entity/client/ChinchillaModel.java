package net.vladick.animalistic.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.custom.ChinchillaEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ChinchillaModel extends AnimatedGeoModel<ChinchillaEntity> {
    @Override
    public ResourceLocation getModelLocation(ChinchillaEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "geo/chinchilla.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ChinchillaEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "textures/entity/chinchilla/chinchilla.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ChinchillaEntity animatable) {
        return new ResourceLocation(Animalistic.MOD_ID, "animations/chinchilla.animation.json");
    }
}
