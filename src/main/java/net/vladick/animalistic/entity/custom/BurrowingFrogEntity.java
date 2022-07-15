package net.vladick.animalistic.entity.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.RandomSource;
import net.vladick.animalistic.entity.ModEntityTypes;
import net.vladick.animalistic.item.ModItems;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.EnumSet;
import java.util.function.Predicate;

public class BurrowingFrogEntity extends Animal implements IAnimatable{

    private AnimationFactory factory = new AnimationFactory(this);

    private static final EntityDataAccessor<Boolean> BURROWED =
            SynchedEntityData.defineId(CavyEntity.class, EntityDataSerializers.BOOLEAN);
    public float burrowProgress;
    public float prevBurrowProgress;
    private int burrowCooldown = 0;

    public BurrowingFrogEntity(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }


    public static AttributeSupplier setAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.ATTACK_DAMAGE, 1.0f)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.1f)
                .build();
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new RandomSwimmingGoal(this,1.0D, 1));
        this.goalSelector.addGoal(5, new AIBurrow());
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(6, (new HurtByTargetGoal(this)).setAlertOthers());
    }

    // ANIMATIONS //

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, 0.15F, 1.0F);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.CAT_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.WOLF_GROWL;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.WOLF_DEATH;
    }

    protected float getSoundVolume() {
        return 0.2F;
    }


    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }

        if (this.isBurrowed()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("burrow", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    public static boolean canBurrowingFrogSpawn(EntityType animal, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource random) {
        boolean spawnBlock = worldIn.getBlockState(pos.below()).is(BlockTags.SAND);
        return spawnBlock && worldIn.getLevelData() != null && worldIn.getLevelData().isRaining();
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob p_146744_) {
        return null;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BURROWED, false);
    }

    public void setBurrowed(boolean sitting) {
        this.entityData.set(BURROWED, sitting);
    }

    public boolean isBurrowed() {
        return this.entityData.get(BURROWED);
    }

    private class AIBurrow extends Goal {
        private BlockPos sand = null;
        private int burrowedTime = 0;

        public AIBurrow() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            if (!BurrowingFrogEntity.this.isBurrowed() && BurrowingFrogEntity.this.burrowCooldown == 0 && BurrowingFrogEntity.this.random.nextInt(200) == 0) {
                this.burrowedTime = 0;
                sand = findSand();
                return sand != null;
            }
            return false;
        }

        @Override
        public boolean canContinueToUse() {
            return burrowedTime < 300;
        }

        public BlockPos findSand() {
            BlockPos blockpos = null;

            for (BlockPos blockpos1 : BlockPos.betweenClosed(Mth.floor(BurrowingFrogEntity.this.getX() - 4.0D), Mth.floor(BurrowingFrogEntity.this.getY() - 1.0D), Mth.floor(BurrowingFrogEntity.this.getZ() - 4.0D), Mth.floor(BurrowingFrogEntity.this.getX() + 4.0D), BurrowingFrogEntity.this.getBlockY(), Mth.floor(BurrowingFrogEntity.this.getZ() + 4.0D))) {
                if (BurrowingFrogEntity.this.level.getBlockState(blockpos1).is(BlockTags.SAND)) {
                    blockpos = blockpos1;
                    break;
                }
            }
            return blockpos;
        }

        public void tick() {
            if (BurrowingFrogEntity.this.isBurrowed()) {
                burrowedTime++;
                if (!BurrowingFrogEntity.this.getBlockStateOn().is(BlockTags.SAND) || BurrowingFrogEntity.this.getBlockStateOn().is(Blocks.WATER)) {
                    BurrowingFrogEntity.this.setBurrowed(false);
                }
            } else if (sand != null) {
                BurrowingFrogEntity.this.getNavigation().moveTo(sand.getX() + 0.5F, sand.getY() + 1F, sand.getZ() + 0.5F, 1F);
                if (BurrowingFrogEntity.this.getBlockStateOn().is(BlockTags.SAND) && !BurrowingFrogEntity.this.getBlockStateOn().is(Blocks.WATER)) {
                    BurrowingFrogEntity.this.setBurrowed(true);
                    BurrowingFrogEntity.this.getNavigation().stop();
                    sand = null;
                } else {
                    BurrowingFrogEntity.this.setBurrowed(false);
                }
            }
        }

        public void stop() {
            BurrowingFrogEntity.this.setBurrowed(false);
            BurrowingFrogEntity.this.burrowCooldown = 120 + random.nextInt(1200);
            this.sand = null;
        }
    }

    public void tick() {
        super.tick();
        prevBurrowProgress = burrowProgress;
        if (this.isBurrowed() && burrowProgress < 5F) {
            burrowProgress += 0.5F;
        }
        if (!this.isBurrowed() && burrowProgress > 0F) {
            burrowProgress -= 0.5F;
        }
        if (this.burrowCooldown > 0) {
            this.burrowCooldown--;
        }
        if (!this.level.isClientSide) {
            if (!this.level.isRaining() && this.getLastHurtByMob() == null && !this.isBurrowed()) {
                if (tickCount % 10 == 0 && this.getRandom().nextInt(750) == 0) {
                    this.setBurrowed(true);
                }
            } else if (this.isBurrowed()) {
                this.setBurrowed(false);
            }
        }
    }

    public boolean hurt(DamageSource source, float amount) {
        boolean prev = super.hurt(source, amount);
        if (prev && source.getDirectEntity() instanceof LivingEntity) {
            this.setBurrowed(false);
        }
        return prev;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return source == DamageSource.IN_WALL || source == DamageSource.FALLING_BLOCK || super.isInvulnerableTo(source);
    }
}
