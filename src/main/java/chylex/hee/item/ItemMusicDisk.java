package chylex.hee.item;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.block.Block;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.BlockJukebox.TileEntityJukebox;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import chylex.hee.packets.PacketPipeline;
import chylex.hee.packets.client.C02PlayRecord;
import chylex.hee.sound.CustomMusicTicker;
import chylex.hee.system.abstractions.Pos;
import chylex.hee.system.collections.CollectionUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMusicDisk extends ItemRecord{
	private static final List<String[]> musicNames = new ArrayList<>();
	private static final List<ResourceLocation> musicResources = new ArrayList<>();
	
	static{
		musicNames.add(new String[]{ "Banjolic", "records.qwertygiy.banjolic" });
		musicNames.add(new String[]{ "In The End", "records.qwertygiy.intheend" });
		musicNames.add(new String[]{ "Asteroid", "records.qwertygiy.asteroid" });
		musicNames.add(new String[]{ "Stewed", "records.qwertygiy.stewed" });
		musicNames.add(new String[]{ "Beat The Dragon", "records.qwertygiy.beatthedragon" });
		musicNames.add(new String[]{ "Granite", "records.qwertygiy.granite" });
		musicNames.add(new String[]{ "Remember This", "records.qwertygiy.rememberthis" });
		musicNames.add(new String[]{ "Spyder", "records.qwertygiy.spyder" });
		musicNames.add(new String[]{ "Onion", "records.qwertygiy.onion" });
		musicNames.add(new String[]{ "Crying Soul", "records.qwertygiy.cryingsoul" });
		
		for(String[] data:musicNames)musicResources.add(new ResourceLocation("hardcoreenderexpansion",data[1]));
	}
	
	public static int getRecordCount(){
		return musicNames.size();
	}
	
	public static String[] getRecordData(int damage){
		return CollectionUtil.getClamp(musicNames,damage);
	}
	
	public static ResourceLocation getRecordResource(int damage){
		return CollectionUtil.getClamp(musicResources,damage);
	}
	
	private IIcon[] iconArray;
	
	public ItemMusicDisk(){
		super("");
		setHasSubtypes(true);
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ){
		Pos pos = Pos.at(x,y,z);
		
		Block block = pos.getBlock(world);
		if (!(block instanceof BlockJukebox))return false;
		
		Optional<TileEntityJukebox> tile = pos.castTileEntity(world,TileEntityJukebox.class);
		if (!tile.isPresent())return false;
		
		TileEntityJukebox jukebox = tile.get();
		if (jukebox.func_145856_a() != null)return false;
		
		if (!world.isRemote){
			((BlockJukebox)block).func_149926_b(world,x,y,z,is);
			PacketPipeline.sendToDimension(world.provider.dimensionId,new C02PlayRecord(pos,(byte)is.getItemDamage()));
			--is.stackSize;
		}
		
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int damage){
		return CollectionUtil.getClamp(iconArray,damage);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getRecordNameLocal(){
		return "MUSIC DISC ERROR";
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer player, List textLines, boolean showAdvancedInfo){
		textLines.add("qwertygiy - "+CollectionUtil.getClamp(musicNames,is.getItemDamage())[0]);
		
		if (!CustomMusicTicker.canPlayMusic()){
			textLines.add(EnumChatFormatting.RED+I18n.format("music.notEnabled"));
		}
		
		SoundEventAccessorComposite sound = Minecraft.getMinecraft().getSoundHandler().getSound(getRecordResource(is.getItemDamage()));
		
		if (sound == null || sound.func_148720_g() == SoundHandler.missing_sound){ // OBFUSCATED getSoundEntry
			textLines.add(EnumChatFormatting.RED+I18n.format("music.missingResourcePack"));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list){
		for(int a = 0; a < musicNames.size(); a++){
			list.add(new ItemStack(item,1,a));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
		iconArray = new IIcon[musicNames.size()];

		for(int index = 0; index < iconArray.length; index++){
			iconArray[index] = iconRegister.registerIcon(iconString+"_"+(index+1));
		}
	}
}
