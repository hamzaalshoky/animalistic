package net.vladick.animalistic.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.vladick.animalistic.effects.ModEffects;
import net.vladick.animalistic.entity.ModEntityCreator;
import net.vladick.animalistic.entity.variant.OlmVariant;
import net.vladick.animalistic.item.ModItems;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Predicate;

public class OlmEntity extends Animal implements IAnimatable{

    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT =
            SynchedEntityData.defineId(OlmEntity.class, EntityDataSerializers.INT);

    private AnimationFactory factory = new AnimationFactory(this);

    public OlmEntity(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }


    public static AttributeSupplier setAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0D)
                .add(Attributes.ATTACK_DAMAGE, 1.0f)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.15f).build();
    }

    protected void handleAirSupply(int p_149194_) {
        if (this.isAlive() && !this.isInWaterRainOrBubble()) {
            this.setAirSupply(p_149194_ - 1);
            if (this.getAirSupply() == -20) {
                this.setAirSupply(0);
                this.hurt(DamageSource.DRY_OUT, 2.0F);
            }
        } else {
            this.setAirSupply(this.getMaxAirSupply());
        }

    }

    public void rehydrate() {
        int i = this.getAirSupply() + 1800;
        this.setAirSupply(Math.min(i, this.getMaxAirSupply()));
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(2, new RandomSwimmingGoal(this, 1.0D, 10));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0D, 10));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, (double)1.2F, true));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(6, (new HurtByTargetGoal(this)).setAlertOthers());
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Cod.class, 5, true, true, (Predicate<LivingEntity>)null));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, TropicalFish.class, 5, true, true, (Predicate<LivingEntity>)null));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, KrillEntity.class, 5, true, true, (Predicate<LivingEntity>)null));
    }

    @Nullable
    public AgeableMob getBreedOffspring(ServerLevel p_149112_, AgeableMob p_149113_) {
        OlmEntity olm = ModEntityCreator.OLM.get().create(p_149112_);
        if (olm != null) {
            OlmVariant olm$variant;
            if (useRareVariant(this.random)) {
                olm$variant = OlmVariant.getRareSpawnVariant(this.random);
            } else {
                olm$variant = this.random.nextBoolean() ? this.getVariant() : ((OlmEntity)p_149113_).getVariant();
            }

            olm.setVariant(olm$variant);
            olm.setPersistenceRequired();
        }

        return olm;
    }

    public boolean isFood(ItemStack pStack) {
        return pStack.getItem() == Items.COD || pStack.getItem() == ModItems.RAW_KRILL.get();
    }

    // ANIMATIONS //

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_149132_, DifficultyInstance p_149133_, MobSpawnType p_149134_, @Nullable SpawnGroupData p_149135_, @Nullable CompoundTag p_149136_) {
        boolean flag = false;
        if (p_149134_ == MobSpawnType.BUCKET) {
            return p_149135_;
        } else {
            if (p_149135_ instanceof OlmEntity.OlmGroupData) {
                if (((OlmEntity.OlmGroupData)p_149135_).getGroupSize() >= 2) {
                    flag = true;
                }
            } else {
                p_149135_ = new OlmEntity.OlmGroupData(OlmVariant.getCommonSpawnVariant(this.level.random), OlmVariant.getCommonSpawnVariant(this.level.random));
            }

            this.setVariant(((OlmEntity.OlmGroupData)p_149135_).getVariant(this.level.random));
            if (flag) {
                this.setAge(-24000);
            }

            return super.finalizeSpawn(p_149132_, p_149133_, p_149134_, p_149135_, p_149136_);
        }
    }

    public static class OlmGroupData extends AgeableMobGroupData {
        public final OlmVariant[] types;

        public OlmGroupData(OlmVariant... p_149204_) {
            super(false);
            this.types = p_149204_;
        }

        public OlmVariant getVariant(Random p_149206_) {
            return this.types[p_149206_.nextInt(this.types.length)];
        }
    }

    public OlmVariant getVariant() {
        return OlmVariant.byId(this.entityData.get(DATA_ID_TYPE_VARIANT));
    }

    private void setVariant(OlmVariant p_149118_) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, p_149118_.getId());
    }

    private static boolean useRareVariant(Random p_149143_) {
        return p_149143_.nextInt(500) == 0;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, 0.15F, 1.0F);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.AXOLOTL_IDLE_WATER;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.AXOLOTL_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.AXOLOTL_DEATH;
    }

    protected float getSoundVolume() {
        return 0.2F;
    }

    public boolean isPushedByFluid() {
        return false;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);

    }

    public void addAdditionalSaveData(CompoundTag p_149158_) {
        super.addAdditionalSaveData(p_149158_);
        p_149158_.putInt("Variant", this.getVariant().getId());
    }

    public void readAdditionalSaveData(CompoundTag p_149145_) {
        super.readAdditionalSaveData(p_149145_);
        this.entityData.set(DATA_ID_TYPE_VARIANT, p_149145_.getInt("Variant"));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving() || this.isInWaterOrBubble()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("swim", true));
            return PlayState.CONTINUE;
        }

        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand){
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();

        Item itemForBartering = Items.SALMON;
        if (item == itemForBartering){
            player.addEffect(new MobEffectInstance(ModEffects.SENSING.get(), 200, 0), this);
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
        }
        level = player.getLevel();
        if(!level.isClientSide()){

        }
        return InteractionResult.CONSUME;
    }
}
