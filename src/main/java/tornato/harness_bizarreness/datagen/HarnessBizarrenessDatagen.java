package tornato.harness_bizarreness.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import tornato.harness_bizarreness.HarnessItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class HarnessBizarrenessDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        var pack = fabricDataGenerator.createPack();
        pack.addProvider(RecipeDatagen::new);
    }

    private static class RecipeDatagen extends FabricRecipeProvider {
        public RecipeDatagen(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {
            return new RecipeProvider(recipes, advancements) {
                @Override
                public void buildRecipes() {
                    HarnessItems.ITEMS.forEach(item -> shaped(RecipeCategory.TRANSPORTATION, item)
                            .define('c', Items.COPPER_INGOT)
                            .define('a', Items.AMETHYST_SHARD)
                            .define('h', item.getPolymerItem(null, null))
                            .define('n', Items.NETHERITE_SCRAP)
                            .pattern("aca")
                            .pattern("cnc")
                            .pattern("hca")
                            .group("enhanced_harnesses")
                            .unlockedBy("has_harness", has(ItemTags.HARNESSES))
                            .save(output));
                    var dyes = List.of(
                            Items.DYE.white(),
                            Items.DYE.orange(),
                            Items.DYE.magenta(),
                            Items.DYE.lightBlue(),
                            Items.DYE.yellow(),
                            Items.DYE.lime(),
                            Items.DYE.pink(),
                            Items.DYE.gray(),
                            Items.DYE.lightGray(),
                            Items.DYE.cyan(),
                            Items.DYE.purple(),
                            Items.DYE.blue(),
                            Items.DYE.brown(),
                            Items.DYE.green(),
                            Items.DYE.red(),
                            Items.DYE.black()
                    );
                    //noinspection unchecked - I hate Java.
                    this.colorItemWithDye(dyes, (List<Item>)(List<?>)HarnessItems.ITEMS, "dye_enchanced_harness", RecipeCategory.TRANSPORTATION);
                }
            };
        }

        @Override
        public String getName() {
            return "why does this exist lol";
        }
    }
}
