package net.vladick.animalistic.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {
    public static final CreativeModeTab ANIMALISTIC_TAB = new CreativeModeTab("animalistic_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.DUMPLINGS.get());
        }
    };
}
