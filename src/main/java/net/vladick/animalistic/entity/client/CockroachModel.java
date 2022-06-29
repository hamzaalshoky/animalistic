package net.vladick.animalistic.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.custom.CockroachEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CockroachModel extends AnimatedGeoModel<CockroachEntity> {
    @Override
    public ResourceLocation getModelLocation(CockroachEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "geo/cockroach.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CockroachEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "textures/entity/cockroach/cockroach.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CockroachEntity animatable) {
        return new ResourceLocation(Animalistic.MOD_ID, "animations/cockroach.animation.json");
    }
}
