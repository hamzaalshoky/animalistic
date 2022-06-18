package net.vladick.animalistic.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.custom.SeaSlugEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SeaSlugModel extends AnimatedGeoModel<SeaSlugEntity> {
    @Override
    public ResourceLocation getModelLocation(SeaSlugEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "geo/sea_slug.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SeaSlugEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "textures/entity/sea_slugy/sea_slugy.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SeaSlugEntity animatable) {
        return new ResourceLocation(Animalistic.MOD_ID, "animations/sea_slug.animation.json");
    }
}
