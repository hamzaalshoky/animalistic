package net.vladick.animalistic.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.custom.BurrowingFrogEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BurrowingFrogModel extends AnimatedGeoModel<BurrowingFrogEntity> {
    @Override
    public ResourceLocation getModelLocation(BurrowingFrogEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "geo/burrowingfrog.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BurrowingFrogEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "textures/entity/burrowingfrog/burrowingfrog.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BurrowingFrogEntity animatable) {
        return new ResourceLocation(Animalistic.MOD_ID, "animations/burrowingfrog.animation.json");
    }
}
