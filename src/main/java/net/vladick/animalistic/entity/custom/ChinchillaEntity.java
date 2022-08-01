package net.vladick.animalistic.entity.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.scores.Team;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.ForgeEventFactory;
import net.vladick.animalistic.entity.custom.ai.ChinchillaBeg;
import net.vladick.animalistic.entity.variant.CavyVariant;
import net.vladick.animalistic.item.ModItems;
import net.vladick.animalistic.sound.ModSounds;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.UUID;

public class ChinchillaEntity extends TamableAnimal implements IAnimatable{

    private static final EntityDataAccessor<Boolean> BEGGING =
            SynchedEntityData.defineId(ChinchillaEntity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Boolean> BATHING = 
            SynchedEntityData.defineId(ChinchillaEntity.class, EntityDataSerializers.BOOLEAN);

    public float batheProgress;
    public float prevBatheProgress;

    private AnimationFactory factory = new AnimationFactory(this);

    public float begProgress;
    public float prevBegProgress;

    public ChinchillaEntity(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_) {
        super(p_21803_, p_21804_);
    }


    public static AttributeSupplier setAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0D)
                .add(Attributes.ATTACK_DAMAGE, 4.0f)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.3f).build();
    }



    protected void registerGoals() {
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new RandomSwimmingGoal(this,1.0D, 1));
        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(5, (new HurtByTargetGoal(this)).setAlertOthers());
        this.goalSelector.addGoal(1, new ChinchillaBeg(this, 1.0D));
    }

    // ANIMATIONS //

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.RABBIT_JUMP, 0.15F, 1.0F);
    }

    protected SoundEvent getAmbientSound() {
        return ModSounds.CAVY_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSounds.CAVY_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return ModSounds.CAVY_DEATH.get();
    }

    protected float getSoundVolume() {
        return 0.2F;
    }


    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }

        if (this.isBegging()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("beg", true));
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

    // VARIANTS //

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_21815_) {
        super.readAdditionalSaveData(p_21815_);
        this.setBathing(p_21815_.getBoolean("Bathing")); 
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("Bathing", isBathing());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BEGGING, Boolean.valueOf(false));
        this.entityData.define(BATHING, Boolean.valueOf(false));
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();

        Item itemForTaming = Items.BEETROOT_SEEDS;
        Item itemForTaming2 = Items.MELON_SEEDS;
        Item itemForTaming3 = Items.PUMPKIN_SEEDS;
        Item itemForTaming4 = Items.WHEAT_SEEDS;

        if (item == itemForTaming || item == itemForTaming2 || item == itemForTaming3 || item == itemForTaming4 && !isTame()) {
            if (this.level.isClientSide) {
                return InteractionResult.CONSUME;
            } else {
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }
                if (!ForgeEventFactory.onAnimalTame(this, player)) {
                    if (!this.level.isClientSide) {
                        super.tame(player);
                        this.navigation.recomputePath();
                        this.setTarget(null);
                        this.level.broadcastEntityEvent(this, (byte)7);
                    }
                }

                return InteractionResult.SUCCESS;
            }
        }else if(this.isBathing() && item == itemForTaming || item == itemForTaming2 || item == itemForTaming3 || item == itemForTaming4){
            spawnAtLocation(ModItems.CHINCHILLA_FUR.get());
            this.setBathing(false);
        }

        if (itemstack.getItem() == itemForTaming) {
            return InteractionResult.PASS;
        }

        return super.mobInteract(player, hand);
    }

    @Override
    public void setTame(boolean tamed) {
        super.setTame(tamed);
    }

    @Override
    public Team getTeam() {
        return super.getTeam();
    }

    public boolean canBeLeashed(Player player) {
        return true;
    }

    public void tick() {
        super.tick();
        this.prevBatheProgress = batheProgress;
        this.prevBegProgress = begProgress;
        if (this.isBegging() && begProgress < 5F) {
            begProgress++;
        }
        if (!this.isBegging() && begProgress > 0F) {
            begProgress--;
        }
        if (this.isBathing() && batheProgress < 5F) {
            batheProgress++;
        }
        if (!this.isBathing() && batheProgress > 0F) {
            batheProgress--;
        }
        if (!this.level.isClientSide) {
            if (this.level.isDay() && this.getLastHurtByMob() == null) {
                if (this.getRandom().nextInt(100) == 0) {
                    this.setBathing(true);
                }
            } else if (this.isBathing()) {
                this.setBathing(false);
            }
        }
    }

    public boolean isBathing() {
        return this.entityData.get(BATHING).booleanValue();
    }

    public void setBathing(boolean bathing) {
        this.entityData.set(BATHING, Boolean.valueOf(bathing));
    }

    public boolean isBegging() {
        return this.entityData.get(BEGGING).booleanValue();
    }

    public void setBegging(boolean begging) {
        this.entityData.set(BEGGING, Boolean.valueOf(begging));
    }

    public boolean hurt(DamageSource source, float amount) {
        boolean prev = super.hurt(source, amount);
        if (prev) {
            this.setBathing(false);
            return prev;
        }
        return prev;
    }

}
