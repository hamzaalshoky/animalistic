package net.vladick.animalistic.event;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.effects.ModEffects;
import net.vladick.animalistic.entity.ModEntityTypes;
import net.vladick.animalistic.entity.custom.CaracalEntity;
import net.vladick.animalistic.item.ModItems;

@Mod.EventBusSubscriber(modid = Animalistic.MOD_ID)
public class EntityEvents {

    @SubscribeEvent
    public static void onInteractEntity(PlayerInteractEvent.EntityInteract event) {
        LivingEntity entity = (LivingEntity) event.getTarget();
        Player player = event.getPlayer();
        ItemStack stack = event.getItemStack();
        if (stack.getItem() == Items.WATER_BUCKET && entity.isAlive()) {
            ItemStack bucket = ItemStack.EMPTY;
            if (entity.getType() == ModEntityTypes.MUDPUPPY.get()) {
                bucket = new ItemStack(ModItems.MUDPUPPY_BUCKET.get());
            } else {
                return;
            }

            player.swing(event.getHand());
            entity.playSound(SoundEvents.BUCKET_FILL_FISH, 1.0F, 1.0F);
            stack.shrink(1);

            if (entity.hasCustomName()) {
                bucket.setHoverName(entity.getCustomName());
            }

            if (!event.getWorld().isClientSide) {
                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, bucket);
            }

            if (stack.isEmpty()) {
                player.setItemInHand(event.getHand(), bucket);
            } else if (!player.getInventory().add(bucket)) {
                player.drop(bucket, false);
            }

            entity.discard();
        }else if (stack.getItem() == Items.WATER_BUCKET && entity.isAlive()) {
            ItemStack bucket = ItemStack.EMPTY;
            if (entity.getType() == ModEntityTypes.KRILL.get()) {
                bucket = new ItemStack(ModItems.KRILL_BUCKET.get());
            } else {
                return;
            }

            player.swing(event.getHand());
            entity.playSound(SoundEvents.BUCKET_FILL_FISH, 1.0F, 1.0F);
            stack.shrink(1);

            if (entity.hasCustomName()) {
                bucket.setHoverName(entity.getCustomName());
            }

            if (!event.getWorld().isClientSide) {
                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, bucket);
            }

            if (stack.isEmpty()) {
                player.setItemInHand(event.getHand(), bucket);
            } else if (!player.getInventory().add(bucket)) {
                player.drop(bucket, false);
            }

            entity.discard();
        }
    }

}
