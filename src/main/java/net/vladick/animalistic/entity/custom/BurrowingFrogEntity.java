package net.vladick.animalistic.entity.custom;

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
import net.minecraft.world.entity.ai.control.JumpControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.RandomSource;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.vladick.animalistic.sound.ModSounds;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.EnumSet;

public class BurrowingFrogEntity extends Animal implements IAnimatable{

    private AnimationFactory factory = new AnimationFactory(this);

    private static final EntityDataAccessor<Boolean> BURROWED = SynchedEntityData.defineId(CavyEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> JUMP_ACTIVE = SynchedEntityData.defineId(BurrowingFrogEntity.class, EntityDataSerializers.BOOLEAN);
    public float burrowProgress;
    public float prevBurrowProgress;
    private int burrowCooldown = 0;
    public float reboundProgress;
    public float prevReboundProgress;
    public float jumpProgress;
    public float prevJumpProgress;
    private int jumpTicks;
    private int jumpDuration;
    private boolean wasOnGround;
    private int currentMoveTypeDuration;

    public BurrowingFrogEntity(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
        this.jumpControl = new BurrowingFrogEntity.JumpHelperController(this);
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
        this.playSound(SoundEvents.SAND_STEP, 0.15F, 1.0F);
    }

    protected SoundEvent getAmbientSound() {
        return ModSounds.BURROWING_FROG_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return  ModSounds.BURROWING_FROG_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return  ModSounds.BURROWING_FROG_DEATH.get();
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
        this.entityData.define(JUMP_ACTIVE, false);
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
        this.prevJumpProgress = jumpProgress;
        this.prevReboundProgress = reboundProgress;
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
        if (!level.isClientSide) {
            this.entityData.set(JUMP_ACTIVE, !this.isOnGround());
        }
        if (this.entityData.get(JUMP_ACTIVE)) {
            if (jumpProgress < 5F) {
                jumpProgress += 1F;
                if (reboundProgress > 0) {
                    reboundProgress--;
                }
            }
            if (jumpProgress >= 5F) {
                if (reboundProgress < 5F) {
                    reboundProgress += 1;
                }
            }
        } else {
            if (reboundProgress > 0) {
                reboundProgress = Math.max(reboundProgress - 1F, 0);
            }
            if (jumpProgress > 0) {
                jumpProgress = Math.max(jumpProgress - 1F, 0);
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

    public float getJumpCompletion(float partialTicks) {
        return this.jumpDuration == 0 ? 0.0F : ((float) this.jumpTicks + partialTicks) / (float) this.jumpDuration;
    }

    protected float getJumpPower() {
        return horizontalCollision ? super.getJumpPower() + 0.2F : 0.25F + random.nextFloat() * 0.15F;
    }

    protected void jumpFromGround() {
        super.jumpFromGround();
        double d0 = this.moveControl.getSpeedModifier();
        if (d0 > 0.0D) {
            double d1 = this.getDeltaMovement().horizontalDistance();
            if (d1 < 0.01D) {
            }
        }

        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte) 1);
        }

    }

    public void setMovementSpeed(double newSpeed) {
        this.getNavigation().setSpeedModifier(newSpeed);
        this.moveControl.setWantedPosition(this.moveControl.getWantedX(), this.moveControl.getWantedY(), this.moveControl.getWantedZ(), newSpeed);
    }

    public void startJumping() {
        this.setJumping(true);
        this.jumpDuration = 10;
        this.jumpTicks = 0;
    }

    private void checkLandingDelay() {
        this.updateMoveTypeDuration();
        this.disableJumpControl();
    }

    private void calculateRotationYaw(double x, double z) {
        this.setYRot((float) (Mth.atan2(z - this.getZ(), x - this.getX()) * (double) (180F / (float) Math.PI)) - 90.0F);
    }

    private void enableJumpControl() {
        if (jumpControl instanceof BurrowingFrogEntity.JumpHelperController) {
            ((BurrowingFrogEntity.JumpHelperController) this.jumpControl).setCanJump(true);
        }
    }

    private void disableJumpControl() {
        if (jumpControl instanceof BurrowingFrogEntity.JumpHelperController) {
            ((BurrowingFrogEntity.JumpHelperController) this.jumpControl).setCanJump(false);
        }
    }

    private void updateMoveTypeDuration() {
        if (this.moveControl.getSpeedModifier() < 2.2D) {
            this.currentMoveTypeDuration = 2;
        } else {
            this.currentMoveTypeDuration = 1;
        }

    }

    public void customServerAiStep() {
        super.customServerAiStep();

        if (this.currentMoveTypeDuration > 0) {
            --this.currentMoveTypeDuration;
        }

        if (this.onGround) {
            if (!this.wasOnGround) {
                this.setJumping(false);
                this.checkLandingDelay();
            }

            if (this.currentMoveTypeDuration == 0) {
                LivingEntity livingentity = this.getTarget();
                if (livingentity != null && this.distanceToSqr(livingentity) < 16.0D) {
                    this.calculateRotationYaw(livingentity.getX(), livingentity.getZ());
                    this.moveControl.setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), this.moveControl.getSpeedModifier());
                    this.startJumping();
                    this.wasOnGround = true;
                }
            }
            if (this.jumpControl instanceof BurrowingFrogEntity.JumpHelperController) {
                BurrowingFrogEntity.JumpHelperController rabbitController = (BurrowingFrogEntity.JumpHelperController) this.jumpControl;
                if (!rabbitController.getIsJumping()) {
                    if (this.moveControl.hasWanted() && this.currentMoveTypeDuration == 0) {
                        Path path = this.navigation.getPath();
                        Vec3 vector3d = new Vec3(this.moveControl.getWantedX(), this.moveControl.getWantedY(), this.moveControl.getWantedZ());
                        if (path != null && !path.isDone()) {
                            vector3d = path.getNextEntityPos(this);
                        }

                        this.calculateRotationYaw(vector3d.x, vector3d.z);
                        this.startJumping();
                    }
                } else if (!rabbitController.canJump()) {
                    this.enableJumpControl();
                }
            }
        }

        this.wasOnGround = this.onGround;
    }

    public void aiStep() {
        super.aiStep();
        if (this.jumpTicks != this.jumpDuration) {
            ++this.jumpTicks;
        } else if (this.jumpDuration != 0) {
            this.jumpTicks = 0;
            this.jumpDuration = 0;
            this.setJumping(false);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte id) {
        if (id == 1) {
            this.spawnSprintParticle();
            this.jumpDuration = 10;
            this.jumpTicks = 0;
        } else {
            super.handleEntityEvent(id);
        }

    }

    public boolean hasJumper() {
        return jumpControl instanceof JumpHelperController;
    }

    static class MoveHelperController extends MoveControl {
        private final BurrowingFrogEntity forg;
        private double nextJumpSpeed;

        public MoveHelperController(BurrowingFrogEntity forg) {
            super(forg);
            this.forg = forg;
        }

        public void tick() {
            if (this.forg.hasJumper() && this.forg.onGround && !this.forg.jumping && !((BurrowingFrogEntity.JumpHelperController) this.forg.jumpControl).getIsJumping()) {
                this.forg.setMovementSpeed(0.0D);
            } else if (this.hasWanted()) {
                this.forg.setMovementSpeed(this.nextJumpSpeed);
            }
            if (this.operation == MoveControl.Operation.MOVE_TO) {
                this.operation = MoveControl.Operation.WAIT;
                Vec3 vector3d = new Vec3(this.wantedX - forg.getX(), this.wantedY - forg.getY(), this.wantedZ - forg.getZ());
                double d0 = vector3d.length();
                forg.setDeltaMovement(forg.getDeltaMovement().add(vector3d.scale(this.speedModifier * 1.0F * 0.05D / d0)));

            }
            super.tick();

        }

        /**
         * Sets the speed and location to move to
         */
        public void setWantedPosition(double x, double y, double z, double speedIn) {
            if (this.forg.isInWater()) {
                speedIn = 1.5D;
            }

            super.setWantedPosition(x, y, z, speedIn);
            if (speedIn > 0.0D) {
                this.nextJumpSpeed = speedIn;
            }

        }
    }

    public class JumpHelperController extends JumpControl {
        private final BurrowingFrogEntity forg;
        private boolean canJump;

        public JumpHelperController(BurrowingFrogEntity forg) {
            super(forg);
            this.forg = forg;
        }

        public boolean getIsJumping() {
            return this.jump;
        }

        public boolean canJump() {
            return this.canJump;
        }

        public void setCanJump(boolean canJumpIn) {
            this.canJump = canJumpIn;
        }

        public void tick() {
            if (this.jump) {
                this.forg.startJumping();
                this.jump = false;
            }

        }
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }
}
