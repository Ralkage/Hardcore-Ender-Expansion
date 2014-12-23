package chylex.hee.tileentity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import org.apache.commons.lang3.ArrayUtils;
import chylex.hee.HardcoreEnderExpansion;
import chylex.hee.mechanics.energy.EnergyChunkData;
import chylex.hee.proxy.ModCommonProxy.MessageType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityTransportBeacon extends TileEntityAbstractEnergyInventory{
	private boolean hasEnergy, noTampering;
	private int actualX, actualY = -1, actualZ;
	private float beamAngle;
	
	@Override
	public void updateEntity(){
		if (!worldObj.isRemote){
			if (actualY == -1){
				actualX = xCoord;
				actualY = yCoord;
				actualZ = zCoord;
			}
			
			if (xCoord == actualX && yCoord == actualY && zCoord == actualZ && worldObj.provider.dimensionId == 1){
				if (!noTampering){
					noTampering = true;
					worldObj.addBlockEvent(xCoord,yCoord,zCoord,blockType,0,1);
				}
			}
			else if (noTampering){
				noTampering = false;
				worldObj.addBlockEvent(xCoord,yCoord,zCoord,blockType,0,0);
			}
		}
		
		super.updateEntity();
		
		if (worldObj.isRemote){
			beamAngle += 1.5F;
			
			EntityPlayer player = HardcoreEnderExpansion.proxy.getClientSidePlayer();
			
			if (player.getDistance(xCoord,yCoord,zCoord) < 8D && (Math.abs(player.lastTickPosX-player.posX) > 0.0001D || Math.abs(player.lastTickPosY-player.posY) > 0.0001D || Math.abs(player.lastTickPosZ-player.posZ) > 0.0001D)){
				worldObj.markBlockRangeForRenderUpdate(xCoord,yCoord,zCoord,xCoord,yCoord,zCoord);
			}
		}
	}
	
	public boolean teleportPlayer(){
		return false; // TODO
	}
	
	@Override
	public boolean receiveClientEvent(int eventId, int eventData){
		if (eventId == 0)noTampering = eventData == 1;
		else if (eventId == 1)hasEnergy = eventData == 1;
		HardcoreEnderExpansion.proxy.sendMessage(MessageType.TRANSPORT_BEACON_GUI,(hasEnergy ? 0b1 : 0)|(noTampering ? 0b10 : 0));
		return true;
	}
	
	@Override
	protected byte getDrainTimer(){
		return 1;
	}

	@Override
	protected float getDrainAmount(){
		return EnergyChunkData.energyDrainUnit*5F;
	}

	@Override
	protected boolean isWorking(){
		return !hasEnergy;
	}

	@Override
	protected void onWork(){
		hasEnergy = true;
		worldObj.addBlockEvent(xCoord,yCoord,zCoord,blockType,1,1);
	}

	public float getBeamAngle(){
		return beamAngle;
	}
	
	public boolean hasEnergyClient(){
		return hasEnergy;
	}
	
	public boolean hasNotBeenTampered(){
		return noTampering;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setBoolean("hasEng",hasEnergy);
		nbt.setIntArray("actualPos",new int[]{ actualX, actualY, actualZ });
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		hasEnergy = nbt.getBoolean("hasEng");
		
		int[] actualPos = nbt.getIntArray("actualPos");
		
		if (actualPos.length == 3){
			actualX = actualPos[0];
			actualY = actualPos[1];
			actualZ = actualPos[2];
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox(){
		return INFINITE_EXTENT_AABB;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public double getMaxRenderDistanceSquared(){
        return 16384D;
    }
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side){
		return ArrayUtils.EMPTY_INT_ARRAY;
	}

	@Override
	public int getSizeInventory(){
		return 0;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack is){
		return false;
	}

	@Override
	protected String getContainerDefaultName(){
		return "";
	}
}