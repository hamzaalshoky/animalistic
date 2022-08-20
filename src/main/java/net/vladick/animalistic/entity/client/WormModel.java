package net.vladick.animalistic.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.custom.WormEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WormModel extends AnimatedGeoModel<WormEntity> {
    @Override
    public ResourceLocation getModelLocation(WormEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "geo/worm.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(WormEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "textures/entity/worm/worm.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(WormEntity animatable) {
        return new ResourceLocation(Animalistic.MOD_ID, "animations/worm.animation.json");
    }
}
