package me.earth.earthhack.impl.util.minecraft;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

// == would suffice probably
public class ItemUtil
{

    public static boolean areSame(Block block1, Block block2)
    {
        return Block.getIdFromBlock(block1) == Block.getIdFromBlock(block2);
    }

    public static boolean areSame(Item item1, Item item2)
    {
        return Item.getIdFromItem(item1) == Item.getIdFromItem(item2);
    }

    public static boolean areSame(Block block, Item item)
    {
        return item instanceof ItemBlock && areSame(block, ((ItemBlock) item).getBlock());
    }

    public static boolean areSame(ItemStack stack, Block block)
    {
        return stack != null && areSame(block, stack.getItem());
    }

    public static boolean areSame(ItemStack stack, Item item)
    {
        return stack != null && areSame(stack.getItem(), item);
    }

}
