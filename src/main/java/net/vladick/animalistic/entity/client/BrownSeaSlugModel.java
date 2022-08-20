package net.vladick.animalistic.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.custom.BrownSeaSlugEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BrownSeaSlugModel extends AnimatedGeoModel<BrownSeaSlugEntity> {
    @Override
    public ResourceLocation getModelLocation(BrownSeaSlugEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "geo/brown_sea_slug.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BrownSeaSlugEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "textures/entity/brown_sea_slugy/brown_sea_slugy.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BrownSeaSlugEntity animatable) {
        return new ResourceLocation(Animalistic.MOD_ID, "animations/brown_sea_slug.animation.json");
    }
}
