package chylex.hee.mechanics;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import chylex.hee.block.BlockCrossedDecoration;
import chylex.hee.block.BlockList;
import chylex.hee.item.ItemList;
import cpw.mods.fml.common.registry.GameRegistry;

public final class RecipeList{
	public static void addRecipes(){ // TODO void chest
		
		// SHAPED
		
		GameRegistry.addShapedRecipe(new ItemStack(ItemList.altar_nexus),
			"DED",
			'D',Items.diamond, 'E',Items.ender_eye
		);
		
		GameRegistry.addShapedRecipe(new ItemStack(BlockList.essence_altar),
			"LLL", "BNB", "OOO",
			'B',Blocks.bookshelf, 'L',Items.leather,
			'O',Blocks.obsidian, 'N',ItemList.altar_nexus
		);
		
		GameRegistry.addShapedRecipe(new ItemStack(BlockList.decomposition_table),
			"PBP", "SRS", "INI",
			'P', ItemList.end_powder,
			'B', Blocks.iron_bars,
			'S', Blocks.stone,
			'R', ItemList.igneous_rock,
			'N', BlockList.endium_block,
			'I', Blocks.iron_block
		);
		
		GameRegistry.addShapedRecipe(new ItemStack(BlockList.energy_extraction_table),
			"PBP", "SES", "INI",
			'P', ItemList.end_powder,
			'B', Blocks.iron_bars,
			'S', Blocks.stone,
			'E', ItemList.ectoplasm,
			'N', BlockList.endium_block,
			'I', Blocks.iron_block
		);
		
		GameRegistry.addShapedRecipe(new ItemStack(ItemList.biome_compass),
			" N ", "NSN", " N ",
			'N', ItemList.endium_ingot,
			'S', ItemList.stardust
		);
		
		GameRegistry.addShapedRecipe(new ItemStack(BlockList.soul_charm),
			"EEE", "EXE", "EEE",
			'E', ItemList.ectoplasm,
			'X', ItemList.enderman_head
		);
		
		GameRegistry.addShapelessRecipe(
			new ItemStack(ItemList.corporeal_mirage_orb),
			ItemList.instability_orb,ItemList.ectoplasm
		);
		
		GameRegistry.addShapedRecipe(new ItemStack(ItemList.charm_pouch),
			"PLP", "LRL", "PLP",
			'P', ItemList.end_powder,
			'L', Items.leather,
			'R', new ItemStack(ItemList.rune,1,OreDictionary.WILDCARD_VALUE)
		);
		
		GameRegistry.addShapedRecipe(new ItemStack(BlockList.ravaged_brick_slab,6),
			"XXX",
			'X', new ItemStack(BlockList.ravaged_brick,1,OreDictionary.WILDCARD_VALUE)
		);
		
		GameRegistry.addShapedRecipe(new ItemStack(BlockList.ravaged_brick_stairs,4),
			"  X", " XX", "XXX",
			'X', new ItemStack(BlockList.ravaged_brick,1,OreDictionary.WILDCARD_VALUE)
		);
		
		GameRegistry.addShapedRecipe(new ItemStack(BlockList.ravaged_brick_fence,6),
			"XXX", "XXX",
			'X', new ItemStack(BlockList.ravaged_brick,1,OreDictionary.WILDCARD_VALUE)
		);
		
		GameRegistry.addShapedRecipe(new ItemStack(BlockList.spooky_log),
			"XXX", "XXX", "XXX",
			'X', ItemList.dry_splinter
		);

		GameRegistry.addShapelessRecipe(new ItemStack(BlockList.spooky_leaves),
			ItemList.dry_splinter, Blocks.deadbush, Blocks.sand
		);
		
		GameRegistry.addShapedRecipe(new ItemStack(ItemList.scorching_pickaxe),
			"FFF", "FPF", "FFF",
			'F', ItemList.fire_shard,
			'P', Items.golden_pickaxe
		);
		
		GameRegistry.addShapedRecipe(new ItemStack(BlockList.endium_block),
			"XXX", "XXX", "XXX",
			'X', ItemList.endium_ingot
		);
		
		GameRegistry.addShapelessRecipe(new ItemStack(ItemList.endium_ingot,9),
			BlockList.endium_block
		);
		
		for(int a = 0; a < 14; a++){
			GameRegistry.addShapelessRecipe(
				new ItemStack(Items.dye,2,13),
				new ItemStack(BlockList.death_flower,1,a)
			);
		}
		
		GameRegistry.addShapelessRecipe(
			new ItemStack(Items.dye,2,8),
			new ItemStack(BlockList.death_flower,1,15)
		);
		
		GameRegistry.addShapelessRecipe(
			new ItemStack(Items.dye,2,14),
			new ItemStack(BlockList.crossed_decoration,1,BlockCrossedDecoration.dataLilyFire)
		);
		
		GameRegistry.addShapelessRecipe(
			new ItemStack(Items.ender_eye,1),
			ItemList.enhanced_ender_pearl,Items.blaze_powder
		);
		
		// SMELTING
		
		FurnaceRecipes.smelting().func_151393_a(BlockList.endium_ore,new ItemStack(ItemList.endium_ingot),0.9F);
	}
	
	private RecipeList(){}
}