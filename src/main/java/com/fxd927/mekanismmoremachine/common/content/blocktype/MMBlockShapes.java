package com.fxd927.mekanismmoremachine.common.content.blocktype;

import mekanism.common.util.EnumUtils;
import mekanism.common.util.VoxelShapeUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MMBlockShapes {
    private MMBlockShapes() {
    }

    private static VoxelShape box(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return Block.box(minX, minY, minZ, maxX, maxY, maxZ);
    }

    public static final VoxelShape[] ENERGY_CATALYST_MACHINE = new VoxelShape[EnumUtils.HORIZONTAL_DIRECTIONS.length];

    static {
        VoxelShapeUtils.setShape(VoxelShapeUtils.combine(
                box(0, 13, 12, 16, 16, 16),
                box(0, 4, 12, 16, 6, 16),
                box(0, 0, 0, 16, 4, 16),
                box(14, 4, 0, 16, 14, 2),
                box(0, 4, 0, 2, 14, 2),
                box(0, 14, 0, 16, 16, 12),
                box(1, 12, 12, 15, 13, 16),
                box(1, 10, 12, 15, 11, 16),
                box(1, 8, 12, 15, 9, 16),
                box(1, 6, 12, 15, 7, 16),
                box(0, 7, 12, 16, 8, 16),
                box(0, 9, 12, 16, 10, 16),
                box(0, 11, 12, 16, 12, 16),
                box(14, 4, 2, 16, 8, 12),
                box(0, 4, 2, 2, 8, 12),
                box(14, 9, 2, 16, 10, 12),
                box(0, 9, 2, 2, 10, 12),
                box(14, 10, 2, 15, 11, 12),
                box(1, 10, 2, 2, 11, 12),
                box(14, 8, 2, 15, 9, 12),
                box(1, 8, 2, 2, 9, 12),
                box(14, 11, 2, 16, 14, 12),
                box(0, 11, 2, 2, 14, 12),
                box(2, 12, 5, 14, 13, 7),
                box(7, 12, 1, 9, 13, 5),
                box(7, 12, 6, 9, 13, 12),
                box(6, 10, 4, 10, 12, 8),
                box(7, 8, 5, 9, 10, 7),
                box(2, 12, 0, 14, 14, 1),
                box(4, 4, 16, 12, 12, 16),
                box(4, 4, 16, 12, 12, 16),
                box(2, 4, 0, 14, 12, 0)
                ), ENERGY_CATALYST_MACHINE);
    }
}

