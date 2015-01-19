package chylex.hee.gui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
class GuiButtonPageArrow extends GuiButton{
	private final boolean isRightArrow;

	public GuiButtonPageArrow(int id, int x, int y, boolean isRightArrow){
		super(id,x,y,23,13,"");
		this.isRightArrow = isRightArrow;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY){
		if (visible){
			GL11.glColor4f(1F,1F,1F,1F);
			mc.getTextureManager().bindTexture(GuiEnderCompendium.texPage);
			drawTexturedModalRect(xPosition,yPosition,mouseX >= xPosition && mouseY >= yPosition && mouseX < xPosition+width && mouseY < yPosition+height?23:0,231+(!isRightArrow?13:0),23,13);
		}
	}
}
