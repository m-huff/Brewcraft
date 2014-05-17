package redgear.brewcraft.client;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import redgear.brewcraft.blocks.barrel.ModelBarrel;
import redgear.brewcraft.blocks.barrel.TileEntityBarrel;
import redgear.brewcraft.core.Brewcraft;
import redgear.core.util.StringHelper;

public class TileRendererBarrel extends TileEntitySpecialRenderer {

	ModelBarrel model = new ModelBarrel();

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {  //I think f is the shadow or something?
		TileEntityBarrel tile = (TileEntityBarrel) tileentity;
		ResourceLocation texture = StringHelper.parseModelTexture(Brewcraft.inst.modId, "barrel_" + tile.woodType);
		
		bindTexture(texture);

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

		GL11.glScalef(1.0F, -1F, -1F); //Uh, not sure what this does
		model.render(0.0625F);//I don't know what that number is. 
		GL11.glPopMatrix();
	}

}
