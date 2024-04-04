package cn.magicst.mcaddition.mixin;

import cn.magicst.mcaddition.fakefqapi.PacketSender;
import cn.magicst.mcaddition.network.PcaSyncProtocol;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {
	@Shadow
	@Final
	private MinecraftServer server;
	// quilt-fabric api ServerPlayConnectionEvents.JOIN
    @ModifyVariable(method = "onPlayerConnect", at = @At("TAIL"), argsOnly = true)
    private ServerPlayerEntity handleDisconnection(ServerPlayerEntity player) {
        PcaSyncProtocol.onJoin(player.networkHandler, new PacketSender(), this.server);
        return player;
    }
}
