package ariss.nedoextras;

import ariss.nedoextras.rendering.block.PortalBlockEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.DimensionRenderingRegistry;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import paulevs.edenring.client.environment.renderers.EdenCloudRenderer;
import paulevs.edenring.client.environment.renderers.EdenSkyRenderer;
import paulevs.edenring.client.environment.renderers.EdenWeatherRenderer;

public class NedoextrasClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		Nedoextras.LOGGER.info("Nedoextras client initialized");

		BlockEntityRendererFactories.register(RegistryStuff.PORTAL_BE, PortalBlockEntityRenderer::new);

		DimensionRenderingRegistry.registerSkyRenderer(Nedoextras.spawnKey, new EdenSkyRenderer());
		DimensionRenderingRegistry.registerCloudRenderer(Nedoextras.spawnKey, new EdenCloudRenderer());
		DimensionRenderingRegistry.registerWeatherRenderer(Nedoextras.spawnKey, new EdenWeatherRenderer());
	}
}