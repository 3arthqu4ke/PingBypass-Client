package me.earth.earthhack.impl.util.minecraft;

import me.earth.earthhack.api.util.Globals;
import me.earth.earthhack.impl.core.ducks.IEntityLivingBase;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.util.EnumHand;

import java.util.Objects;

public class ArmUtil implements Globals
{

    public static void swingPacket(EnumHand hand)
    {
        Objects.requireNonNull(mc.getConnection()).sendPacket(new CPacketAnimation(hand));
    }

    public static void swingArmNoPacket(EnumHand hand)
    {
        if (!mc.player.isSwingInProgress
                || mc.player.swingProgressInt >= ((IEntityLivingBase) mc.player).armSwingAnimationEnd() / 2
                || mc.player.swingProgressInt < 0)
        {
            mc.player.swingProgressInt = -1;
            mc.player.isSwingInProgress = true;
            mc.player.swingingHand = hand;
        }
    }

    public static void swingArm(EnumHand hand)
    {
        mc.player.swingArm(hand);
    }

}
