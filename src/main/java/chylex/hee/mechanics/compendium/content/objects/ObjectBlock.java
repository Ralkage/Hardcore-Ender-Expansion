package chylex.hee.mechanics.compendium.content.objects;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import chylex.hee.mechanics.compendium.content.objects.ObjectBlock.BlockMetaWrapper;

public class ObjectBlock implements IKnowledgeObjectInstance<BlockMetaWrapper>{
	private final BlockMetaWrapper wrapper;
	
	public ObjectBlock(Block block){
		this.wrapper = new BlockMetaWrapper(block,-1);
	}
	
	public ObjectBlock(Block block, int metadata){
		this.wrapper = new BlockMetaWrapper(block,metadata);
	}

	@Override
	public BlockMetaWrapper getUnderlyingObject(){
		return wrapper;
	}
	
	@Override
	public ItemStack createItemStackToRender(){
		return new ItemStack(wrapper.block,1,wrapper.metadata == OreDictionary.WILDCARD_VALUE ? 0 : wrapper.metadata);
	}

	@Override
	public boolean checkEquality(Object o){
		if (!(o instanceof BlockMetaWrapper))return false;
		
		BlockMetaWrapper bmw = (BlockMetaWrapper)o;
		return bmw.block == wrapper.block && (bmw.metadata == wrapper.metadata || bmw.metadata == -1 || wrapper.metadata == -1);
	}
	
	public static class BlockMetaWrapper{
		public final Block block;
		public final byte metadata;
		
		public BlockMetaWrapper(Block block, int metadata){
			this.block = block;
			this.metadata = (byte)metadata;
		}
	}
}