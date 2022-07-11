package net.vladick.animalistic.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.custom.EchidnaEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EchidnaModel extends AnimatedGeoModel<EchidnaEntity> {
    @Override
    public ResourceLocation getModelLocation(EchidnaEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "geo/echidna.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EchidnaEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "textures/entity/echidna/echidna.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EchidnaEntity animatable) {
        return new ResourceLocation(Animalistic.MOD_ID, "animations/echidna.animation.json");
    }
}
