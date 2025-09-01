package tornato.harness_bizarreness;

import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.DyeColor;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class HarnessItems {
    public static final List<SimplePolymerItem> ITEMS = Stream.of(
            Map.entry(DyeColor.WHITE, Items.WHITE_HARNESS),
            Map.entry(DyeColor.ORANGE, Items.ORANGE_HARNESS),
            Map.entry(DyeColor.MAGENTA, Items.MAGENTA_HARNESS),
            Map.entry(DyeColor.LIGHT_BLUE, Items.LIGHT_BLUE_HARNESS),
            Map.entry(DyeColor.YELLOW, Items.YELLOW_HARNESS),
            Map.entry(DyeColor.LIME, Items.LIME_HARNESS),
            Map.entry(DyeColor.PINK, Items.PINK_HARNESS),
            Map.entry(DyeColor.GRAY, Items.GRAY_HARNESS),
            Map.entry(DyeColor.LIGHT_GRAY, Items.LIGHT_GRAY_HARNESS),
            Map.entry(DyeColor.CYAN, Items.CYAN_HARNESS),
            Map.entry(DyeColor.PURPLE, Items.PURPLE_HARNESS),
            Map.entry(DyeColor.BLUE, Items.BLUE_HARNESS),
            Map.entry(DyeColor.BROWN, Items.BROWN_HARNESS),
            Map.entry(DyeColor.GREEN, Items.GREEN_HARNESS),
            Map.entry(DyeColor.RED, Items.RED_HARNESS),
            Map.entry(DyeColor.BLACK, Items.BLACK_HARNESS)
    ).map(entry -> {
        var dyeColor = entry.getKey();
        var id = HarnessBizarreness.id(dyeColor.getId() + "_harness");
        return Registry.register(Registries.ITEM, id, new SimplePolymerItem(
                new Item.Settings()
                        .maxCount(1)
                        .component(DataComponentTypes.EQUIPPABLE, EquippableComponent.ofHarness(dyeColor))
                        .component(DataComponentTypes.LORE, new LoreComponent(
                                Text.of(" x1.5 Fly Speed").getWithStyle(Style.EMPTY.withColor(Colors.LIGHT_GRAY).withItalic(false))
                        ))
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, id)),
                entry.getValue()
        ));
    }).toList();

    public static void register() {}
}
