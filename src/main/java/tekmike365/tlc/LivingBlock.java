package tekmike365.tlc;

import net.minecraft.block.BlockState;

public class LivingBlock {
    private BlockState incasedState;

    public LivingBlock(BlockState incasedState) {
        this.incasedState = incasedState;
    }

    public BlockState getIncasedState() {
        return incasedState;
    }
}
