package net.vladick.animalistic.entity.custom;

import net.minecraft.ChatFormatting;
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
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.vladick.animalistic.entity.ModEntityCreator;
import net.vladick.animalistic.entity.custom.ai.RandomActuallySwimGoal;
import net.vladick.animalistic.entity.custom.ai.SemiAquaticMob;
import net.vladick.animalistic.entity.custom.ai.WaterMovementControl;
import net.vladick.animalistic.entity.variant.MudpuppyVariant;
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

public class MudpuppyEntity extends Animal implements IAnimatable, Bucketable, SemiAquaticMob, FlyingAnimal {

    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT =
            SynchedEntityData.defineId(MudpuppyEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(MudpuppyEntity.class, EntityDataSerializers.BOOLEAN);

    private AnimationFactory factory = new AnimationFactory(this);

    public MudpuppyEntity(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
        this.moveControl = new WaterMovementControl(this, 1.0F, 15F);
    }


    public static AttributeSupplier setAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0D)
                .add(Attributes.ATTACK_DAMAGE, 1.0f)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.3f).build();
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
        this.goalSelector.addGoal(5, new RandomActuallySwimGoal(this, 1F, 12, 5));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0D, 10));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, (double)1.2F, true));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(6, (new HurtByTargetGoal(this)).setAlertOthers());
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Cod.class, 5, true, true, (Predicate<LivingEntity>)null));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, TropicalFish.class, 5, true, true, (Predicate<LivingEntity>)null));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, KrillEntity.class, 5, true, true, (Predicate<LivingEntity>)null));
    }

    protected void playSwimSound(float f) {
        if(random.nextInt(2) == 0){
            this.playSound(this.getSwimSound(), 0.2F, 1.3F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
        }
    }

    protected SoundEvent getSwimSound() {
        return SoundEvents.FISH_SWIM;
    }

    public void travel(Vec3 travelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), travelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            float f = 0.6F;
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.9D, f, 0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(travelVector);
        }
    }

    @Nullable
    public AgeableMob getBreedOffspring(ServerLevel p_149112_, AgeableMob p_149113_) {
        MudpuppyEntity mudpuppy = ModEntityCreator.MUDPUPPY.get().create(p_149112_);
        if (mudpuppy != null) {
            MudpuppyVariant mudpuppy$variant;
            if (useRareVariant(this.random)) {
                mudpuppy$variant = MudpuppyVariant.getRareSpawnVariant(this.random);
            } else {
                mudpuppy$variant = this.random.nextBoolean() ? this.getVariant() : ((MudpuppyEntity)p_149113_).getVariant();
            }

            mudpuppy.setVariant(mudpuppy$variant);
            mudpuppy.setPersistenceRequired();
        }

        return mudpuppy;
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
            if (p_149135_ instanceof MudpuppyEntity.MudpuppyGroupData) {
                if (((MudpuppyEntity.MudpuppyGroupData)p_149135_).getGroupSize() >= 2) {
                    flag = true;
                }
            } else {
                p_149135_ = new MudpuppyEntity.MudpuppyGroupData(MudpuppyVariant.getCommonSpawnVariant(this.level.random), MudpuppyVariant.getCommonSpawnVariant(this.level.random));
            }

            this.setVariant(((MudpuppyEntity.MudpuppyGroupData)p_149135_).getVariant(this.level.random));
            if (flag) {
                this.setAge(-24000);
            }

            return super.finalizeSpawn(p_149132_, p_149133_, p_149134_, p_149135_, p_149136_);
        }
    }

    @Override
    public boolean shouldEnterWater() {
        return false;
    }

    @Override
    public boolean shouldLeaveWater() {
        return false;
    }

    @Override
    public boolean shouldStopMoving() {
        return false;
    }

    @Override
    public int getWaterSearchRange() {
        return 0;
    }

    @Override
    public boolean isFlying() {
        return false;
    }

    public static class MudpuppyGroupData extends AgeableMob.AgeableMobGroupData {
        public final MudpuppyVariant[] types;

        public MudpuppyGroupData(MudpuppyVariant... p_149204_) {
            super(false);
            this.types = p_149204_;
        }

        public MudpuppyVariant getVariant(Random p_149206_) {
            return this.types[p_149206_.nextInt(this.types.length)];
        }
    }

    public MudpuppyVariant getVariant() {
        return MudpuppyVariant.byId(this.entityData.get(DATA_ID_TYPE_VARIANT));
    }

    private void setVariant(MudpuppyVariant p_149118_) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, p_149118_.getId());
    }

    private static boolean useRareVariant(Random p_149143_) {
        return p_149143_.nextInt(100) == 0;
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
        this.entityData.define(FROM_BUCKET, false);

    }

    public void addAdditionalSaveData(CompoundTag p_149158_) {
        super.addAdditionalSaveData(p_149158_);
        p_149158_.putInt("Variant", this.getVariant().getId());
        p_149158_.putBoolean("FromBucket", this.fromBucket());
    }

    public void readAdditionalSaveData(CompoundTag p_149145_) {
        super.readAdditionalSaveData(p_149145_);
        this.entityData.set(DATA_ID_TYPE_VARIANT, p_149145_.getInt("Variant"));
        this.setFromBucket(p_149145_.getBoolean("FromBucket"));
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

    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    public void setFromBucket(boolean p_149196_) {
        this.entityData.set(FROM_BUCKET, p_149196_);
    }

    public void saveToBucketTag(ItemStack stack) {
        Bucketable.saveDefaultDataToBucketTag(this, stack);
    }


    public void loadFromBucketTag(CompoundTag tag) {
        Bucketable.loadDefaultDataFromBucketTag(this, tag);
    }


    public ItemStack getBucketItemStack() {
        return new ItemStack(ModItems.MUDPUPPY_BUCKET.get());
    }


    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand){
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();

        Item itemForBartering = ModItems.KRILL_BUCKET.get();
        if (item == itemForBartering && this.isInWaterOrBubble()){
            if(Math.random() < 0.25){
                this.spawnAtLocation(Items.KELP);
            }else if(Math.random() < 0.25){
                this.spawnAtLocation(Items.SAND);
            }else if(Math.random() < 0.25){
                this.spawnAtLocation(Items.GRAVEL);
            }else{
                if(Math.random() < 0.1){
                    this.spawnAtLocation(Items.COD);
                }else if(Math.random() < 0.1){
                    this.spawnAtLocation(Items.SALMON);
                }else if(Math.random() < 0.05){
                    this.spawnAtLocation(Items.PUFFERFISH);
                }
            }
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
        }
        level = player.getLevel();
        if(!level.isClientSide()){

        }
        return InteractionResult.CONSUME;
    }

    public boolean isPuppy() {
        String s = ChatFormatting.stripFormatting(this.getName().getString());
        return s != null && s.toLowerCase().contains("puppy");
    }
}
