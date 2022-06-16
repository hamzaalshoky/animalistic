package net.vladick.animalistic.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.custom.CaracalEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CaracalModel extends AnimatedGeoModel<CaracalEntity> {
    @Override
    public ResourceLocation getModelLocation(CaracalEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "geo/caracal.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CaracalEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "textures/entity/caracal/caracal.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CaracalEntity animatable) {
        return new ResourceLocation(Animalistic.MOD_ID, "animations/caracal.animation.json");
    }
}
