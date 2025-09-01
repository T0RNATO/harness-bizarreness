package tornato.harness_bizarreness.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
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
        public RecipeDatagen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
            return new RecipeGenerator(wrapperLookup, recipeExporter) {
                @Override
                public void generate() {
                    HarnessItems.ITEMS.forEach(item -> createShaped(RecipeCategory.TRANSPORTATION, item)
                            .input('c', Items.COPPER_INGOT)
                            .input('a', Items.AMETHYST_SHARD)
                            .input('h', item.getPolymerItem(null, null))
                            .input('n', Items.NETHERITE_SCRAP)
                            .pattern("aca")
                            .pattern("cnc")
                            .pattern("hca")
                            .group("enhanced_harnesses")
                            .criterion("has_harness", conditionsFromTag(ItemTags.HARNESSES))
                            .offerTo(exporter));
                    var dyes = List.of(
                            Items.WHITE_DYE,
                            Items.ORANGE_DYE,
                            Items.MAGENTA_DYE,
                            Items.LIGHT_BLUE_DYE,
                            Items.YELLOW_DYE,
                            Items.LIME_DYE,
                            Items.PINK_DYE,
                            Items.GRAY_DYE,
                            Items.LIGHT_GRAY_DYE,
                            Items.CYAN_DYE,
                            Items.PURPLE_DYE,
                            Items.BLUE_DYE,
                            Items.BROWN_DYE,
                            Items.GREEN_DYE,
                            Items.RED_DYE,
                            Items.BLACK_DYE
                    );
                    //noinspection unchecked - I hate Java.
                    this.offerDyeableRecipes(dyes, (List<Item>)(List<?>)HarnessItems.ITEMS, "dye_enchanced_harness", RecipeCategory.TRANSPORTATION);
                }
            };
        }

        @Override
        public String getName() {
            return "why does this exist lol";
        }
    }
}
