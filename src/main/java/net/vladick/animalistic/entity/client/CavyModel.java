package net.vladick.animalistic.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.custom.CavyEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CavyModel extends AnimatedGeoModel<CavyEntity> {
    @Override
    public ResourceLocation getModelLocation(CavyEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "geo/cavy.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CavyEntity object) {
        return CavyRenderer.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CavyEntity animatable) {
        return new ResourceLocation(Animalistic.MOD_ID, "animations/cavy.animation.json");
    }
}
