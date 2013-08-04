package com.teammetallurgy.agriculture.worldgen;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CLAY;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

import com.teammetallurgy.agriculture.AgricultureBlocks;

import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenSalt extends WorldGenerator implements IWorldGenerator
{
	private static int saltPerChunk = 1;
	
    /** The block ID for clay. */
    private int clayBlockId;

    /** The number of blocks to generate. */
    private int numberOfBlocks;

    public WorldGenSalt(int par1)
    {
        this.clayBlockId = AgricultureBlocks.salt.blockID;
        this.numberOfBlocks = par1;
    }
    
	@Override
	public void generate(Random randomGenerator, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
	{
        boolean doGen = TerrainGen.decorate(world, randomGenerator, chunkX, chunkZ, CLAY);
        for (int i = 0; doGen && i < saltPerChunk; ++i)
        {
            int randX = chunkX*16 + randomGenerator.nextInt(16) + 8;
            int randZ = chunkZ*16 + randomGenerator.nextInt(16) + 8;
            this.generate(world, randomGenerator, randX, world.getTopSolidOrLiquidBlock(randX, randZ), randZ);
        }
	}

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        if (par1World.getBlockMaterial(par3, par4, par5) != Material.water)
        {
            return false;
        }
        else
        {
            int l = par2Random.nextInt(this.numberOfBlocks - 2) + 2;
            byte b0 = 1;

            for (int i1 = par3 - l; i1 <= par3 + l; ++i1)
            {
                for (int j1 = par5 - l; j1 <= par5 + l; ++j1)
                {
                    int k1 = i1 - par3;
                    int l1 = j1 - par5;

                    if (k1 * k1 + l1 * l1 <= l * l)
                    {
                        for (int i2 = par4 - b0; i2 <= par4 + b0; ++i2)
                        {
                            int j2 = par1World.getBlockId(i1, i2, j1);

                            if (j2 == Block.dirt.blockID || j2 == Block.blockClay.blockID)
                            {
                                par1World.setBlock(i1, i2, j1, this.clayBlockId, 0, 2);
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}
