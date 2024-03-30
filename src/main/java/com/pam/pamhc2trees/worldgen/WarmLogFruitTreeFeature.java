package com.pam.pamhc2trees.worldgen;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;
import com.pam.pamhc2trees.config.ChanceConfig;
import com.pam.pamhc2trees.config.DimensionConfig;
import com.pam.pamhc2trees.config.EnableConfig;
import com.pam.pamhc2trees.init.BlockRegistry;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class WarmLogFruitTreeFeature extends Feature<NoFeatureConfig> {
	public WarmLogFruitTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactory) {
		super(configFactory);
	}

	@Override
	public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random random,
			BlockPos pos, NoFeatureConfig config) {
		if (random.nextInt(100000) != 0
				|| DimensionConfig.blacklist.get().contains(world.getDimension().getType().getId())
				|| !DimensionConfig.whitelist.get().contains(world.getDimension().getType().getId()))
			return false;

		if (world.getBlockState(pos.down()).getBlock().isIn(BlockTags.DIRT_LIKE)
				&& world.getBlockState(pos).getMaterial().isReplaceable()) {
			int type = (int) ((Math.random() * 9) + 1);
			generateTree(world, pos, random, type);
			return true;
		}
		return false;
	}

	public static void generateTree(IWorld world, BlockPos pos, Random random, int verify) {
		BlockState trunk = getTrunk(verify);
		BlockState leaves = getLeaves(verify);
		BlockState fruit = getFruit(verify, random);

		world.setBlockState(pos.up(0), fruit, 3);
		world.setBlockState(pos.up(1), fruit, 3);
		world.setBlockState(pos.up(2), fruit, 3);
		world.setBlockState(pos.up(3), fruit, 3);
		world.setBlockState(pos.up(4), fruit, 3);
		
		//Layer 1
		if (world.getBlockState(pos.up(4).north().north()).getMaterial().isReplaceable())
			world.setBlockState(pos.up(4).north().north(), leaves, 3);
		if (world.getBlockState(pos.up(4).south().south()).getMaterial().isReplaceable())
			world.setBlockState(pos.up(4).south().south(), leaves, 3);
		if (world.getBlockState(pos.up(4).east().east()).getMaterial().isReplaceable())
			world.setBlockState(pos.up(4).east().east(), leaves, 3);
		if (world.getBlockState(pos.up(4).west().west()).getMaterial().isReplaceable())
			world.setBlockState(pos.up(4).west().west(), leaves, 3);
		
		//Layer 2
		if (world.getBlockState(pos.up(5)).getMaterial().isReplaceable())
			world.setBlockState(pos.up(5), trunk, 3);
		if (world.getBlockState(pos.up(5).north()).getMaterial().isReplaceable())
			world.setBlockState(pos.up(5).north(), leaves, 3);
		if (world.getBlockState(pos.up(5).north().north()).getMaterial().isReplaceable())
			world.setBlockState(pos.up(5).north().north(), leaves, 3);
		if (world.getBlockState(pos.up(5).north().east()).getMaterial().isReplaceable())
			world.setBlockState(pos.up(5).north().east(), leaves, 3);
		if (world.getBlockState(pos.up(5).north().west()).getMaterial().isReplaceable())
			world.setBlockState(pos.up(5).north().west(), leaves, 3);
		if (world.getBlockState(pos.up(5).south()).getMaterial().isReplaceable())
			world.setBlockState(pos.up(5).south(), leaves, 3);
		if (world.getBlockState(pos.up(5).south().south()).getMaterial().isReplaceable())
			world.setBlockState(pos.up(5).south().south(), leaves, 3);
		if (world.getBlockState(pos.up(5).south().east()).getMaterial().isReplaceable())
			world.setBlockState(pos.up(5).south().east(), leaves, 3);
		if (world.getBlockState(pos.up(5).south().west()).getMaterial().isReplaceable())
			world.setBlockState(pos.up(5).south().west(), leaves, 3);
		if (world.getBlockState(pos.up(5).east()).getMaterial().isReplaceable())
			world.setBlockState(pos.up(5).east(), leaves, 3);
		if (world.getBlockState(pos.up(5).east().east()).getMaterial().isReplaceable())
			world.setBlockState(pos.up(5).east().east(), leaves, 3);
		if (world.getBlockState(pos.up(5).west()).getMaterial().isReplaceable())
			world.setBlockState(pos.up(5).west(), leaves, 3);
		if (world.getBlockState(pos.up(5).west().west()).getMaterial().isReplaceable())
			world.setBlockState(pos.up(5).west().west(), leaves, 3);

		//Layer 3
		if (world.getBlockState(pos.up(6)).getMaterial().isReplaceable())
			world.setBlockState(pos.up(6), leaves, 3);
		
	}
	
	private static BlockState getLeaves(int verify)
	{
		return Blocks.JUNGLE_LEAVES.getDefaultState().with(BlockStateProperties.DISTANCE_1_7, Integer.valueOf(1));
	}
	
	private static BlockState getTrunk(int verify)
	{
		return Blocks.JUNGLE_LOG.getDefaultState();
	}
		
	private static BlockState getFruit(int verify, Random random)
	{
		int i = random.nextInt(2);
		switch (verify) {
		case 1:
			if (EnableConfig.cinnamon_worldgen != null)
			return BlockRegistry.pamcinnamon.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 2:
			if (EnableConfig.paperbark_worldgen != null)
			return BlockRegistry.pampaperbark.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		default:
			return BlockRegistry.pamalmond.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		}
	}
}
