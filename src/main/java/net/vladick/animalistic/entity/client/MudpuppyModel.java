package net.vladick.animalistic.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.custom.MudpuppyEntity;
import net.vladick.animalistic.entity.custom.MudpuppyEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MudpuppyModel extends AnimatedGeoModel<MudpuppyEntity> {
    @Override
    public ResourceLocation getModelLocation(MudpuppyEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "geo/mudpuppy.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MudpuppyEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "textures/entity/mudpuppy/mudpuppy.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MudpuppyEntity animatable) {
        return new ResourceLocation(Animalistic.MOD_ID, "animations/mudpuppy.animation.json");
    }
}
