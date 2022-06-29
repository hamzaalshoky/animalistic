package net.vladick.animalistic.entity.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.Tag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.scores.Team;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.ForgeEventFactory;
import net.vladick.animalistic.effects.ModEffects;
import net.vladick.animalistic.entity.custom.ai.DanceGoal;
import net.vladick.animalistic.item.ModItems;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;

public class CockroachEntity extends TamableAnimal implements IAnimatable{

    private AnimationFactory factory = new AnimationFactory(this);

    private boolean cockroachDance;
    private BlockPos jukeboxPosition;
    int moreFoodTicks;
    int slots = 10;

    public CockroachEntity(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_) {
        super(p_21803_, p_21804_);
    }

    private static final EntityDataAccessor<Boolean> SHAKING =
            SynchedEntityData.defineId(CavyEntity.class, EntityDataSerializers.BOOLEAN);


    public static void dance(LevelAccessor world, BlockPos pos, boolean dancing) {
        for(CockroachEntity cockroach : world.getEntitiesOfClass(CockroachEntity.class, (new AABB(pos)).inflate(4.0D)))
            cockroach.party(pos, dancing);
    }


    public void party(BlockPos pos, boolean isPartying) {
        // A separate method, due to setPartying being side-only.
        jukeboxPosition = pos;
        cockroachDance = isPartying;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void setRecordPlayingNearby(@Nonnull BlockPos pos, boolean isPartying) {
        party(pos, isPartying);
    }

    public boolean isDancing() {
        return cockroachDance;
    }


    public static AttributeSupplier setAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 3.0D)
                .add(Attributes.ATTACK_DAMAGE, 1.0f)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.35f).build();
    }



    protected void registerGoals() {
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new DanceGoal(this));
        this.goalSelector.addGoal(2, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(5, (new HurtByTargetGoal(this)).setAlertOthers());
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Items.SUGAR), false));
    }
    
    public void customServerAiStep() {
        if (this.moreFoodTicks > 0) {
            this.moreFoodTicks -= this.random.nextInt(3);
            if (this.moreFoodTicks < 0) {
                this.moreFoodTicks = 0;
            }
        }
    }

    // ANIMATIONS //

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.SILVERFISH_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.SILVERFISH_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SILVERFISH_DEATH;
    }

    protected float getSoundVolume() {
        return 0.2F;
    }


    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.isInWaterOrBubble()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("drown", true));
            return PlayState.CONTINUE;
        }

        if (this.isDancing()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("dance", true));
            return PlayState.CONTINUE;
        }

        if (this.isShaking()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("vibrate", true));
            return PlayState.CONTINUE;
        }

        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }


    public boolean isMarky(){
        String s = ChatFormatting.stripFormatting(this.getName().getString());
        return s.toLowerCase().equals("markey") || s.toLowerCase().equals("marky");
    }

    public boolean isJoey(){
        String s = ChatFormatting.stripFormatting(this.getName().getString());
        return s.toLowerCase().equals("joey");
    }

    public boolean isDeeDee(){
        String s = ChatFormatting.stripFormatting(this.getName().getString());
        return s.toLowerCase().equals("deedee") || s.toLowerCase().equals("DeeDee") || s.toLowerCase().equals("dee") && s.toLowerCase().equals("dee");
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    boolean wantsMoreStuff() {
        return this.moreFoodTicks == 0;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    public void addAdditionalSaveData(CompoundTag p_29697_) {
        super.addAdditionalSaveData(p_29697_);
        p_29697_.putInt("MoreFoodTicks", this.moreFoodTicks);
    }

    public void readAdditionalSaveData(CompoundTag p_29684_) {
        super.readAdditionalSaveData(p_29684_);
        this.moreFoodTicks = p_29684_.getInt("MoreFoodTicks");
    }

    static class RaidGardenGoal extends MoveToBlockGoal {
        private final CockroachEntity cocker;
        private boolean wantsToRaid;
        private boolean canRaid;

        public RaidGardenGoal(CockroachEntity p_29782_) {
            super(p_29782_, (double)0.7F, 16);
            this.cocker = p_29782_;
        }

        public boolean canUse() {
            if (this.nextStartTick <= 0) {
                if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.cocker.level, this.cocker)) {
                    return false;
                }

                this.canRaid = false;
                this.wantsToRaid = this.cocker.wantsMoreStuff();
                this.wantsToRaid = true;
            }

            return super.canUse();
        }

        public boolean canContinueToUse() {
            return this.canRaid && super.canContinueToUse();
        }

        public void tick() {
            super.tick();
            this.cocker.getLookControl().setLookAt((double)this.blockPos.getX() + 0.5D, (double)(this.blockPos.getY() + 1), (double)this.blockPos.getZ() + 0.5D, 10.0F, (float)this.cocker.getMaxHeadXRot());
            if (this.isReachedTarget()) {
                Level level = this.cocker.level;
                BlockPos blockpos = this.blockPos.above();
                BlockState blockstate = level.getBlockState(blockpos);
                Block block = blockstate.getBlock();
                if (this.canRaid && block instanceof CropBlock) {
                    int i = blockstate.getValue(CropBlock.AGE);
                    if (i == 0) {
                        level.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 2);
                        level.destroyBlock(blockpos, true, this.cocker);
                    } else {
                        level.setBlock(blockpos, blockstate.setValue(CropBlock.AGE, Integer.valueOf(i - 1)), 2);
                        level.levelEvent(2001, blockpos, Block.getId(blockstate));
                    }

                    this.cocker.moreFoodTicks = 40;
                }

                this.canRaid = false;
                this.nextStartTick = 10;
            }

        }

        protected boolean isValidTarget(LevelReader p_29785_, BlockPos p_29786_) {
            BlockState blockstate = p_29785_.getBlockState(p_29786_);
            if (blockstate.is(Blocks.FARMLAND) && this.wantsToRaid && !this.canRaid) {
                blockstate = p_29785_.getBlockState(p_29786_.above());
                if (blockstate.getBlock() instanceof CropBlock && ((CropBlock)blockstate.getBlock()).isMaxAge(blockstate)) {
                    this.canRaid = true;
                    return true;
                }
            }

            return false;
        }
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
        return false;
    }

    public void setShaking(boolean sitting) {
        this.entityData.set(SHAKING, sitting);
        this.setOrderedToSit(sitting);
    }

    public boolean isShaking() {
        return this.entityData.get(SHAKING);
    }


    public void playerTouch(LivingEntity p_29617_) {
        if (p_29617_ instanceof LivingEntity && p_29617_.hurt(DamageSource.mobAttack(this), (float)(1))) {
            setShaking(true);
        }

    }
}
