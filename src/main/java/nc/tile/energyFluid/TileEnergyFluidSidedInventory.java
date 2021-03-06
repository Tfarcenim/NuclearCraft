package nc.tile.energyFluid;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import nc.tile.internal.energy.EnergyConnection;
import nc.tile.internal.fluid.FluidConnection;
import nc.tile.internal.inventory.InventoryConnection;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public abstract class TileEnergyFluidSidedInventory extends TileEnergyFluidInventory {
	
	public TileEnergyFluidSidedInventory(String name, int size, @Nonnull InventoryConnection[] inventoryConnections, int capacity, @Nonnull EnergyConnection[] energyConnections, int fluidCapacity, List<String> allowedFluids, @Nonnull FluidConnection[] fluidConnections) {
		super(name, size, inventoryConnections, capacity, capacity, energyConnections, fluidCapacity, fluidCapacity, allowedFluids, fluidConnections);
	}
	
	public TileEnergyFluidSidedInventory(String name, int size, @Nonnull InventoryConnection[] inventoryConnections, int capacity, @Nonnull EnergyConnection[] energyConnections, @Nonnull List<Integer> fluidCapacity, List<List<String>> allowedFluids, @Nonnull FluidConnection[] fluidConnections) {
		super(name, size, inventoryConnections, capacity, capacity, energyConnections, fluidCapacity, fluidCapacity, allowedFluids, fluidConnections);
	}
	
	public TileEnergyFluidSidedInventory(String name, int size, @Nonnull InventoryConnection[] inventoryConnections, int capacity, @Nonnull EnergyConnection[] energyConnections, int fluidCapacity, int maxFluidTransfer, List<String> allowedFluids, @Nonnull FluidConnection[] fluidConnections) {
		super(name, size, inventoryConnections, capacity, capacity, energyConnections, fluidCapacity, maxFluidTransfer, allowedFluids, fluidConnections);
	}
	
	public TileEnergyFluidSidedInventory(String name, int size, @Nonnull InventoryConnection[] inventoryConnections, int capacity, @Nonnull EnergyConnection[] energyConnections, @Nonnull List<Integer> fluidCapacity, @Nonnull List<Integer> maxFluidTransfer, List<List<String>> allowedFluids, @Nonnull FluidConnection[] fluidConnections) {
		super(name, size, inventoryConnections, capacity, capacity, energyConnections, fluidCapacity, maxFluidTransfer, allowedFluids, fluidConnections);
	}
	
	public TileEnergyFluidSidedInventory(String name, int size, @Nonnull InventoryConnection[] inventoryConnections, int capacity, int maxTransfer, @Nonnull EnergyConnection[] energyConnections, int fluidCapacity, List<String> allowedFluids, @Nonnull FluidConnection[] fluidConnections) {
		super(name, size, inventoryConnections, capacity, maxTransfer, energyConnections, fluidCapacity, fluidCapacity, allowedFluids, fluidConnections);
	}
	
	public TileEnergyFluidSidedInventory(String name, int size, @Nonnull InventoryConnection[] inventoryConnections, int capacity, int maxTransfer, @Nonnull EnergyConnection[] energyConnections, @Nonnull List<Integer> fluidCapacity, List<List<String>> allowedFluids, @Nonnull FluidConnection[] fluidConnections) {
		super(name, size, inventoryConnections, capacity, maxTransfer, energyConnections, fluidCapacity, fluidCapacity, allowedFluids, fluidConnections);
	}
	
	public TileEnergyFluidSidedInventory(String name, int size, @Nonnull InventoryConnection[] inventoryConnections, int capacity, int maxTransfer, @Nonnull EnergyConnection[] energyConnections, int fluidCapacity, int maxFluidTransfer, List<String> allowedFluids, @Nonnull FluidConnection[] fluidConnections) {
		super(name, size, inventoryConnections, capacity, maxTransfer, energyConnections, fluidCapacity, maxFluidTransfer, allowedFluids, fluidConnections);
	}
	
	public TileEnergyFluidSidedInventory(String name, int size, @Nonnull InventoryConnection[] inventoryConnections, int capacity, int maxTransfer, @Nonnull EnergyConnection[] energyConnections, @Nonnull List<Integer> fluidCapacity, @Nonnull List<Integer> maxFluidTransfer, List<List<String>> allowedFluids, @Nonnull FluidConnection[] fluidConnections) {
		super(name, size, inventoryConnections, capacity, maxTransfer, energyConnections, fluidCapacity, maxFluidTransfer, allowedFluids, fluidConnections);
	}
	
	// Capability
	
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing side) {
		if (!getInventoryStacks().isEmpty() && hasInventorySideCapability(side) && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new SidedInvWrapper(this, nonNullSide(side)));
		}
		return super.getCapability(capability, side);
	}
}
