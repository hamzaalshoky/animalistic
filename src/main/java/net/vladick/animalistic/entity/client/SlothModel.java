package net.vladick.animalistic.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.custom.SlothEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SlothModel extends AnimatedGeoModel<SlothEntity> {
    @Override
    public ResourceLocation getModelLocation(SlothEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "geo/sloth.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SlothEntity object) {
        return new ResourceLocation(Animalistic.MOD_ID, "textures/entity/sloth/sloth.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SlothEntity animatable) {
        return new ResourceLocation(Animalistic.MOD_ID, "animations/sloth.animation.json");
    }
}
