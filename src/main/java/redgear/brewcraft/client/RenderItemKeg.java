package redgear.brewcraft.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import redgear.brewcraft.blocks.keg.TileEntityKeg;
import redgear.core.render.SimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class RenderItemKeg extends SimpleBlockRenderingHandler {

	public final int renderId;

	public RenderItemKeg(int renderId) {
		super(renderId);
		this.renderId = renderId;
		RenderingRegistry.registerBlockHandler(renderId, this);
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		String type = getStringFromMeta(metadata);
		TileEntityKeg blank = new TileEntityKeg(type);

		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		TileEntityRendererDispatcher.instance.renderTileEntityAt(blank, 0.0D, 0.0D, 0.0D, 0.0F);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int i) {
		return true;
	}

	public String getStringFromMeta(int metadata) {
		switch (metadata) {
		case 0: return "oak";
		case 1: return "birch";
		case 2: return "jungle";
		case 3: return "spruce";
		case 4: return "dark";
		case 5: return "acacia";
		case 6: return "iron";
		case 7: return "sealed";
		case 8: return "plated";
		default: return "";
		}
	}
}
