package chylex.hee.gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiVoidChest extends GuiContainer{
	private static final ResourceLocation texChest = new ResourceLocation("textures/gui/container/generic_54.png");
	
	private final IInventory invPlayer;
	private final IInventory invVoidChest;
	private final byte inventoryHeight;
	
	public GuiVoidChest(IInventory inventory, IInventory chest, EntityPlayer player){
		super(new ContainerVoidChest(inventory,chest,player));
		this.invPlayer = inventory;
		this.invVoidChest = chest;
		allowUserInput = false;
		inventoryHeight = (byte)(chest.getSizeInventory()*2);
		ySize = 114+inventoryHeight;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
		fontRendererObj.drawString(invVoidChest.hasCustomName() ? invVoidChest.getDisplayName().getUnformattedText() : I18n.format(invVoidChest.getDisplayName().getUnformattedText()),8,6,4210752);
		fontRendererObj.drawString(invPlayer.hasCustomName() ? invPlayer.getDisplayName().getUnformattedText() : I18n.format(invPlayer.getDisplayName().getUnformattedText()),8,ySize-94,4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTickTime, int mouseX, int mouseY){
		GL11.glColor4f(1F,1F,1F,1F);
		mc.getTextureManager().bindTexture(texChest);
		
		int x = (width-xSize)>>1, y = (height-ySize)>>1;
		drawTexturedModalRect(x,y,0,0,xSize,inventoryHeight+17);
		drawTexturedModalRect(x,y+inventoryHeight+17,0,126,xSize,96);
	}
}
