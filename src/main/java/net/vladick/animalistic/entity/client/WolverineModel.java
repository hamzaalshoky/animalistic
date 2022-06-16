package net.vladick.animalistic.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.custom.WolverineEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WolverineModel extends AnimatedGeoModel<WolverineEntity> {
    @Override
    public ResourceLocation getModelLocation(WolverineEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "geo/wolverine.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(WolverineEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "textures/entity/wolverine/wolverine.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(WolverineEntity animatable) {
        return new ResourceLocation(Animalistic.MOD_ID, "animations/wolverine.animation.json");
    }
}
