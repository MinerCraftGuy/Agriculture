package com.teammetallurgy.agriculture.crops;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

import com.teammetallurgy.agriculture.Agriculture;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSpice extends BlockFlower {
    private ItemStack drop;
    private float growthRate = 0.5f;
    private Icon[] iconArray;

    public BlockSpice(final int par1)
    {
        super(par1);
        drop = new ItemStack(0, 0, 0);
    }

    @Override
    public boolean canPlaceBlockAt(final World par1World, final int par2, final int par3, final int par4)
    {
        final int id = par1World.getBlockId(par2, par3 - 1, par4);
        return id == Block.tilledField.blockID;
    }

    @Override
    public boolean canSustainPlant(final World world, final int x, final int y, final int z, final ForgeDirection direction, final IPlantable plant)
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIcon(final int par1, int par2)
    {
        if (par2 < 0 || par2 > 7)
        {
            par2 = 7;
        }

        return iconArray[par2];
    }

    @Override
    public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer player, final int side, final float xOffset, final float yOffset, final float zOffset)
    {
        if (player.getHeldItem() != null)
        {
            if (player.getHeldItem().itemID == Item.dyePowder.itemID && player.getHeldItem().getItemDamage() == 15)
            {
                if (world.getBlockMetadata(x, y, z) < 6)
                {
                    final float temp = growthRate;
                    growthRate = 100;
                    updateTick(world, x, y, z, new Random());
                    growthRate = temp;

                    --player.getHeldItem().stackSize;

                    return true;
                }
            }
        }
        if (Agriculture.debug)
        {
            final float temp = growthRate;
            growthRate = 100;
            updateTick(world, x, y, z, new Random());
            growthRate = temp;
        }

        return false;
    }

    @Override
    public void onBlockClicked(final World par1World, final int x, final int y, final int z, final EntityPlayer par5EntityPlayer)
    {
        final int meta = par1World.getBlockMetadata(x, y, z);
        if (meta > 0)
        {
            dropBlockAsItem_do(par1World, x, y, z, drop.copy());
            par1World.setBlock(x, y, z, blockID, meta - 1, 2);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(final IconRegister par1IconRegister)
    {
        iconArray = new Icon[8];

        for (int i = 0; i < iconArray.length; ++i)
        {
            iconArray[i] = par1IconRegister.registerIcon(getTextureName() + "_stage_" + i);
        }
    }

    public BlockSpice setDrop(final ItemStack drop)
    {
        this.drop = drop.copy();
        return this;
    }

    @Override
    public void updateTick(final World par1World, final int par2, final int par3, final int par4, final Random par5Random)
    {
        super.updateTick(par1World, par2, par3, par4, par5Random);

        if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9)
        {
            int l = par1World.getBlockMetadata(par2, par3, par4);
            if (l < 6)
            {
                if (par5Random.nextInt((int) (25.0F / growthRate) + 1) == 0)
                {
                    ++l;
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
                }
            }
        }
    }
}
