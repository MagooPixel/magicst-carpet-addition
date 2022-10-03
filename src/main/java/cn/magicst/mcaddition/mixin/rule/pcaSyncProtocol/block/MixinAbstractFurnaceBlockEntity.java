package cn.magicst.mcaddition.mixin.rule.pcaSyncProtocol.block;

import cn.magicst.mcaddition.Main;
import cn.magicst.mcaddition.PcaSettings;
import cn.magicst.mcaddition.network.PcaSyncProtocol;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.recipe.RecipeInputProvider;
import net.minecraft.recipe.RecipeUnlocker;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class MixinAbstractFurnaceBlockEntity extends LockableContainerBlockEntity implements SidedInventory, RecipeUnlocker, RecipeInputProvider {


	protected MixinAbstractFurnaceBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
		super(blockEntityType, blockPos, blockState);
	}

	@Override
	public void markDirty() {
		super.markDirty();
		if (PcaSettings.pcaSyncProtocol && PcaSyncProtocol.syncBlockEntityToClient(this)) {
			Main.LOGGER.debug("update AbstractFurnaceBlockEntity: {}", this.pos);
		}
	}
}
