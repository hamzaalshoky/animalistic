package net.vladick.animalistic.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.custom.GeckoEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GeckoModel extends AnimatedGeoModel<GeckoEntity> {
    private static final ResourceLocation TAILLESS = new ResourceLocation("animalistic:geo/gecko_tailless.geo.json");
    private static final ResourceLocation NORMAL = new ResourceLocation("animalistic:geo/gecko.geo.json");
    @Override
    public ResourceLocation getModelLocation(GeckoEntity object) {
        return object.isBurrowed() ? TAILLESS : NORMAL;
    }

    @Override
    public ResourceLocation getTextureLocation(GeckoEntity object) {
        return GeckoRenderer.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GeckoEntity animatable) {
        return new ResourceLocation(Animalistic.MOD_ID, "animations/gecko.animation.json");
    }
}
