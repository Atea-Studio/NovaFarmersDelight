package fr.ateastudio.farmersdelight.mixin;

import fr.ateastudio.farmersdelight.nms.NmsHooks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ComparatorBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Lets comparators read Farmer's Delight Nova tile entities (cooking pot, cutting board), whose vanilla
 * backing states have no analog output. Mirrors vanilla: a source directly behind the comparator replaces
 * the input; through one solid block, the larger signal is kept.
 */
@Mixin(ComparatorBlock.class)
abstract class ComparatorBlockMixin {

    @Inject(method = "getInputSignal", at = @At("RETURN"), cancellable = true, require = 0)
    private void farmersdelight$readNovaSignal(Level level, BlockPos pos, BlockState state, CallbackInfoReturnable<Integer> cir) {
        Direction direction = state.getValue(HorizontalDirectionalBlock.FACING);
        BlockPos target = pos.relative(direction);
        int signal = NmsHooks.readComparatorSignal(level, target);
        if (signal >= 0) {
            cir.setReturnValue(signal);
            return;
        }

        int current = cir.getReturnValue();
        if (current < 15 && level.getBlockState(target).isRedstoneConductor(level, target)) {
            int behind = NmsHooks.readComparatorSignal(level, target.relative(direction));
            if (behind > current)
                cir.setReturnValue(behind);
        }
    }
}
