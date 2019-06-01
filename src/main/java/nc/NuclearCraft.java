package nc;

import nc.config.NCConfig;
import nc.handler.GuiHandler;
import nc.proxy.CommonProxy;
import nc.render.BlockHighlightTracker;
import nc.util.NCUtil;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLModIdMappingEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = Global.MOD_ID, name = Global.MOD_NAME, version = Global.VERSION, dependencies = Global.DEPENDENCIES, guiFactory = Global.GUI_FACTORY)
public class NuclearCraft {
	
	static {
		FluidRegistry.enableUniversalBucket();
	}
	
	@Instance(Global.MOD_ID)
	public static NuclearCraft instance;
	
	@SidedProxy(clientSide = Global.NC_CLIENT_PROXY, serverSide = Global.NC_COMMON_PROXY)
	public static CommonProxy proxy;
	
	public BlockHighlightTracker blockOverlayTracker = new BlockHighlightTracker();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent preEvent) {
		NCUtil.getLogger().info("Pre Initializing...");
		NCConfig.preInit();
		proxy.preInit(preEvent);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		NCUtil.getLogger().info("Initializing...");
		proxy.init(event);
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent postEvent) {
		NCUtil.getLogger().info("Post Initializing...");
		proxy.postInit(postEvent);
	}
	
	@EventHandler
	public void serverStart(FMLServerStartingEvent serverStartEvent) {
		NCUtil.getLogger().info("Server Loading...");
		proxy.serverStart(serverStartEvent);
	}
	
	@EventHandler
	public void serverStop(FMLServerStoppedEvent serverStopEvent) {
		NCUtil.getLogger().info("Server Closing...");
		proxy.serverStop(serverStopEvent);
	}
	
	@EventHandler
	public void onIdMapping(FMLModIdMappingEvent idMappingEvent) {
		proxy.onIdMapping(idMappingEvent);
	}
}
