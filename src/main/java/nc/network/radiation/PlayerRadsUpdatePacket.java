package nc.network.radiation;

import io.netty.buffer.ByteBuf;
import nc.capability.radiation.entity.IEntityRads;
import nc.util.NCUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PlayerRadsUpdatePacket implements IMessage {
	
	boolean messageValid;
	
	protected double totalRads;
	protected double radiationLevel;
	protected double radiationResistance;
	protected boolean radXWoreOff;
	protected double radawayBuffer, radawayBufferSlow;
	protected double poisonBuffer;
	protected boolean consumed;
	protected double radawayCooldown;
	protected double radXCooldown;
	protected double radiationImmunityTime;
	protected boolean shouldWarn;
	
	public PlayerRadsUpdatePacket() {
		messageValid = false;
	}
	
	public PlayerRadsUpdatePacket(IEntityRads playerRads) {
		totalRads = playerRads.getTotalRads();
		radiationLevel = playerRads.getRadiationLevel();
		radiationResistance = playerRads.getRadiationResistance();
		radXWoreOff = playerRads.getRadXWoreOff();
		radawayBuffer = playerRads.getRadawayBuffer(false);
		radawayBufferSlow = playerRads.getRadawayBuffer(true);
		poisonBuffer = playerRads.getPoisonBuffer();
		consumed = playerRads.getConsumedMedicine();
		radawayCooldown = playerRads.getRadawayCooldown();
		radXCooldown = playerRads.getRadXCooldown();
		radiationImmunityTime = playerRads.getRadiationImmunityTime();
		shouldWarn = playerRads.getShouldWarn();
		
		messageValid = true;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			totalRads = buf.readDouble();
			radiationLevel = buf.readDouble();
			radiationResistance = buf.readDouble();
			radXWoreOff = buf.readBoolean();
			radawayBuffer = buf.readDouble();
			radawayBufferSlow = buf.readDouble();
			poisonBuffer = buf.readDouble();
			consumed = buf.readBoolean();
			radawayCooldown = buf.readDouble();
			radXCooldown = buf.readDouble();
			radiationImmunityTime = buf.readDouble();
			shouldWarn = buf.readBoolean();
		} catch (IndexOutOfBoundsException ioe) {
			NCUtil.getLogger().catching(ioe);
			return;
		}
		messageValid = true;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (!messageValid) return;
		
		buf.writeDouble(totalRads);
		buf.writeDouble(radiationLevel);
		buf.writeDouble(radiationResistance);
		buf.writeBoolean(radXWoreOff);
		buf.writeDouble(radawayBuffer);
		buf.writeDouble(radawayBufferSlow);
		buf.writeDouble(poisonBuffer);
		buf.writeBoolean(consumed);
		buf.writeDouble(radawayCooldown);
		buf.writeDouble(radXCooldown);
		buf.writeDouble(radiationImmunityTime);
		buf.writeBoolean(shouldWarn);
	}
	
	public static class Handler implements IMessageHandler<PlayerRadsUpdatePacket, IMessage> {

		@Override
		public IMessage onMessage(PlayerRadsUpdatePacket message, MessageContext ctx) {
			if (!message.messageValid && ctx.side != Side.CLIENT) return null;
			Minecraft.getMinecraft().addScheduledTask(() -> processMessage(message));
			return null;
		}
		
		void processMessage(PlayerRadsUpdatePacket message) {
			EntityPlayerSP player = Minecraft.getMinecraft().player;
			if (player == null || !player.hasCapability(IEntityRads.CAPABILITY_ENTITY_RADS, null)) return;
			IEntityRads playerRads = player.getCapability(IEntityRads.CAPABILITY_ENTITY_RADS, null);
			if (playerRads == null) return;
			playerRads.setTotalRads(message.totalRads, false);
			playerRads.setRadiationLevel(message.radiationLevel);
			playerRads.setRadiationResistance(message.radiationResistance);
			playerRads.setRadXWoreOff(message.radXWoreOff);
			playerRads.setRadawayBuffer(false, message.radawayBuffer);
			playerRads.setRadawayBuffer(true, message.radawayBufferSlow);
			playerRads.setPoisonBuffer(message.poisonBuffer, Double.MAX_VALUE);
			playerRads.setConsumedMedicine(message.consumed);
			playerRads.setRadawayCooldown(message.radawayCooldown);
			playerRads.setRadXCooldown(message.radXCooldown);
			playerRads.setRadiationImmunityTime(message.radiationImmunityTime);
			playerRads.setShouldWarn(message.shouldWarn);
		}
	}
}
