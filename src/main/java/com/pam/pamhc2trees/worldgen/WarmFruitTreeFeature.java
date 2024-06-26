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

public class WarmFruitTreeFeature extends Feature<NoFeatureConfig> {
	public WarmFruitTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactory) {
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

		world.setBlockState(pos.up(0), trunk, 3);
		world.setBlockState(pos.up(1), trunk, 3);
		world.setBlockState(pos.up(2), trunk, 3);
		world.setBlockState(pos.up(3), trunk, 3);
		world.setBlockState(pos.up(4), trunk, 3);
		
		//Fruit Layer 1
		if (world.getBlockState(pos.up(3).north().north()).getMaterial().isReplaceable())
			world.setBlockState(pos.up(3).north().north(), fruit, 3);
		if (world.getBlockState(pos.up(3).south().south()).getMaterial().isReplaceable())
			world.setBlockState(pos.up(3).south().south(), fruit, 3);
		if (world.getBlockState(pos.up(3).east().east()).getMaterial().isReplaceable())
			world.setBlockState(pos.up(3).east().east(), fruit, 3);
		if (world.getBlockState(pos.up(3).west().west()).getMaterial().isReplaceable())
			world.setBlockState(pos.up(3).west().west(), fruit, 3);
		
		//Fruit Layer 2
		if (world.getBlockState(pos.up(4).north()).getMaterial().isReplaceable())
			world.setBlockState(pos.up(4).north(), fruit, 3);
		if (world.getBlockState(pos.up(4).south()).getMaterial().isReplaceable())
			world.setBlockState(pos.up(4).south(), fruit, 3);
		if (world.getBlockState(pos.up(4).east()).getMaterial().isReplaceable())
			world.setBlockState(pos.up(4).east(), fruit, 3);
		if (world.getBlockState(pos.up(4).west()).getMaterial().isReplaceable())
			world.setBlockState(pos.up(4).west(), fruit, 3);
		
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
			if (EnableConfig.almond_worldgen != null)
			return BlockRegistry.pamalmond.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 2:
			if (EnableConfig.apricot_worldgen != null)
			return BlockRegistry.pamapricot.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 3:
			if (EnableConfig.banana_worldgen != null)
			return BlockRegistry.pambanana.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 4:
			if (EnableConfig.cashew_worldgen != null)
			return BlockRegistry.pamcashew.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 5:
			if (EnableConfig.coconut_worldgen != null)
			return BlockRegistry.pamcoconut.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 6:
			if (EnableConfig.date_worldgen != null)
			return BlockRegistry.pamdate.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 7:
			if (EnableConfig.dragonfruit_worldgen != null)
			return BlockRegistry.pamdragonfruit.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 8:
			if (EnableConfig.durian_worldgen != null)
			return BlockRegistry.pamdurian.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 9:
			if (EnableConfig.fig_worldgen != null)
			return BlockRegistry.pamfig.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 10:
			if (EnableConfig.grapefruit_worldgen != null)
			return BlockRegistry.pamgrapefruit.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 11:
			if (EnableConfig.lime_worldgen != null)
			return BlockRegistry.pamlime.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 12:
			if (EnableConfig.mango_worldgen != null)
			return BlockRegistry.pammango.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 13:
			if (EnableConfig.olive_worldgen != null)
			return BlockRegistry.pamolive.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 14:
			if (EnableConfig.papaya_worldgen != null)
			return BlockRegistry.pampapaya.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 15:
			if (EnableConfig.pecan_worldgen != null)
			return BlockRegistry.pampecan.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 16:
			if (EnableConfig.peppercorn_worldgen != null)
			return BlockRegistry.pampeppercorn.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 17:
			if (EnableConfig.persimmon_worldgen != null)
			return BlockRegistry.pampersimmon.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 18:
			if (EnableConfig.pistachio_worldgen != null)
			return BlockRegistry.pampistachio.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 19:
			if (EnableConfig.pomegranate_worldgen != null)
			return BlockRegistry.pampomegranate.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 20:
			if (EnableConfig.starfruit_worldgen != null)
			return BlockRegistry.pamstarfruit.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 21:
			if (EnableConfig.vanillabean_worldgen != null)
			return BlockRegistry.pamvanillabean.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 22:
			if (EnableConfig.breadfruit_worldgen != null)
			return BlockRegistry.pambreadfruit.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 23:
			if (EnableConfig.guava_worldgen != null)
			return BlockRegistry.pamguava.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 24:
			if (EnableConfig.jackfruit_worldgen != null)
			return BlockRegistry.pamjackfruit.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 25:
			if (EnableConfig.lychee_worldgen != null)
			return BlockRegistry.pamlychee.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 26:
			if (EnableConfig.passionfruit_worldgen != null)
			return BlockRegistry.pampassionfruit.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 27:
			if (EnableConfig.rambutan_worldgen != null)
			return BlockRegistry.pamrambutan.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		case 28:
			if (EnableConfig.tamarind_worldgen != null)
			return BlockRegistry.pamtamarind.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		default:
			return BlockRegistry.pamalmond.getDefaultState().with(BlockStateProperties.AGE_0_7, Integer.valueOf(i));
		}
	}
}
