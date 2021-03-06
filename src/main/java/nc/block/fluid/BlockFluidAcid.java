package nc.block.fluid;

import nc.fluid.FluidAcid;
import nc.util.PotionHelper;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidAcid extends NCBlockFluid {
	
	public static DamageSource acid_burn = new DamageSource("acid_burn");

	public BlockFluidAcid(Fluid fluid) {
		super(fluid, Material.WATER);
	}
	
	public BlockFluidAcid(FluidAcid fluid) {
		super(fluid, Material.WATER);
	}
	
	@Override
	public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		entityIn.attackEntityFrom(acid_burn, 3.0F);
		if (entityIn instanceof EntityLivingBase) {
			((EntityLivingBase) entityIn).addPotionEffect(PotionHelper.newEffect(18, 2, 100));
			((EntityLivingBase) entityIn).addPotionEffect(PotionHelper.newEffect(19, 2, 100));
		}
	}
}
