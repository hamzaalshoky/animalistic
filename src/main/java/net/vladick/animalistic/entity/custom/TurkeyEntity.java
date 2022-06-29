package net.vladick.animalistic.entity.custom;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.vladick.animalistic.entity.ModEntityTypes;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.function.Predicate;

public class TurkeyEntity extends Animal implements IAnimatable {
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
    public int eggTime = this.random.nextInt(6000) + 6000;
    public boolean isTurkeyJockey;

    private AnimationFactory factory = new AnimationFactory(this);

    public TurkeyEntity(EntityType<? extends Animal> p_28236_, Level p_28237_) {
        super(p_28236_, p_28237_);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, (double)1.2F, false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Rabbit.class, 5, true, true, (Predicate<LivingEntity>)null));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Pig.class, 5, true, true, (Predicate<LivingEntity>)null));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    protected float getStandingEyeHeight(Pose p_28251_, EntityDimensions p_28252_) {
        return this.isBaby() ? p_28252_.height * 0.85F : p_28252_.height * 0.92F;
    }

    public static AttributeSupplier setAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8.0D)
                .add(Attributes.ATTACK_DAMAGE, 1.0f)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.25f).build();
    }


    public boolean causeFallDamage(float p_148875_, float p_148876_, DamageSource p_148877_) {
        return true;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.CHICKEN_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_28262_) {
        return SoundEvents.CHICKEN_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.CHICKEN_DEATH;
    }

    protected void playStepSound(BlockPos p_28254_, BlockState p_28255_) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
    }

    public AgeableMob getBreedOffspring(ServerLevel p_148884_, AgeableMob p_148885_) {
        return ModEntityTypes.TURKEY.get().create(p_148884_);
    }

    public boolean isFood(ItemStack p_28271_) {
        return FOOD_ITEMS.test(p_28271_);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
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

    protected int getExperienceReward(Player p_28259_) {
        return this.isTurkeyJockey() ? 10 : super.getExperienceReward(p_28259_);
    }

    public void readAdditionalSaveData(CompoundTag p_28243_) {
        super.readAdditionalSaveData(p_28243_);
        this.isTurkeyJockey = p_28243_.getBoolean("IsTurkeyJockey");
        if (p_28243_.contains("EggLayTime")) {
            this.eggTime = p_28243_.getInt("EggLayTime");
        }

    }

    public boolean isTurkeyLand(){
        String s = ChatFormatting.stripFormatting(this.getName().getString());
        return s.toLowerCase().equals("bestpunever") || s != null && (s.toLowerCase().contains("best") && s.toLowerCase().contains("pun") && s.toLowerCase().contains("ever"));
    }

    public void setTurkeyPun(boolean turkus){
        if(turkus = true){
            this.getName().getString().toLowerCase().equals("bestpunever");
        }
    }

    public void addAdditionalSaveData(CompoundTag p_28257_) {
        super.addAdditionalSaveData(p_28257_);
        p_28257_.putBoolean("IsTurkeyJockey", this.isTurkeyJockey);
        p_28257_.putInt("EggLayTime", this.eggTime);
    }

    public boolean removeWhenFarAway(double p_28266_) {
        return this.isTurkeyJockey();
    }

    public void positionRider(Entity p_28269_) {
        super.positionRider(p_28269_);
        float f = Mth.sin(this.yBodyRot * ((float)Math.PI / 180F));
        float f1 = Mth.cos(this.yBodyRot * ((float)Math.PI / 180F));
        float f2 = 0.1F;
        float f3 = 0.0F;
        p_28269_.setPos(this.getX() + (double)(0.1F * f), this.getY(0.5D) + p_28269_.getMyRidingOffset() + 0.0D, this.getZ() - (double)(0.1F * f1));
        if (p_28269_ instanceof LivingEntity) {
            ((LivingEntity)p_28269_).yBodyRot = this.yBodyRot;
        }

    }

    public boolean isTurkeyJockey() {
        return this.isTurkeyJockey;
    }

    public void setTurkeyJockey(boolean p_28274_) {
        this.isTurkeyJockey = p_28274_;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();

        Item Carpet = Items.RED_CARPET;

         if (item == Carpet && !isTurkeyLand()) {
            if (this.level.isClientSide) {
                return InteractionResult.CONSUME;
            } else {
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }
                setTurkeyPun(true);

                return InteractionResult.SUCCESS;
            }
        }if (itemstack.getItem() == Carpet) {
            return InteractionResult.PASS;
        }

        return super.mobInteract(player, hand);


    }

}