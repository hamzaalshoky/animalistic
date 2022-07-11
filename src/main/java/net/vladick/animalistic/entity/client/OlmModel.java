package net.vladick.animalistic.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.custom.OlmEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class OlmModel extends AnimatedGeoModel<OlmEntity> {
    @Override
    public ResourceLocation getModelLocation(OlmEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "geo/olm.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(OlmEntity object) {
        return OlmRenderer.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(OlmEntity animatable) {
        return new ResourceLocation(Animalistic.MOD_ID, "animations/olm.animation.json");
    }
}
