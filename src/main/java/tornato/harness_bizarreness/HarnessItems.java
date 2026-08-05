package tornato.harness_bizarreness;

import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.CommonColors;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.equipment.Equippable;

public class HarnessItems {
    public static final List<SimplePolymerItem> ITEMS = Stream.of(
            Map.entry(DyeColor.WHITE, Items.HARNESS.white()),
            Map.entry(DyeColor.ORANGE, Items.HARNESS.orange()),
            Map.entry(DyeColor.MAGENTA, Items.HARNESS.magenta()),
            Map.entry(DyeColor.LIGHT_BLUE, Items.HARNESS.lightBlue()),
            Map.entry(DyeColor.YELLOW, Items.HARNESS.yellow()),
            Map.entry(DyeColor.LIME, Items.HARNESS.lime()),
            Map.entry(DyeColor.PINK, Items.HARNESS.pink()),
            Map.entry(DyeColor.GRAY, Items.HARNESS.gray()),
            Map.entry(DyeColor.LIGHT_GRAY, Items.HARNESS.lightGray()),
            Map.entry(DyeColor.CYAN, Items.HARNESS.cyan()),
            Map.entry(DyeColor.PURPLE, Items.HARNESS.purple()),
            Map.entry(DyeColor.BLUE, Items.HARNESS.blue()),
            Map.entry(DyeColor.BROWN, Items.HARNESS.brown()),
            Map.entry(DyeColor.GREEN, Items.HARNESS.green()),
            Map.entry(DyeColor.RED, Items.HARNESS.red()),
            Map.entry(DyeColor.BLACK, Items.HARNESS.black())
    ).map(entry -> {
        var dyeColor = entry.getKey();
        var id = HarnessBizarreness.id(dyeColor.getName() + "_harness");
        return Registry.register(BuiltInRegistries.ITEM, id, new SimplePolymerItem(
                new Item.Properties()
                        .stacksTo(1)
                        .component(DataComponents.EQUIPPABLE, Equippable.harness(dyeColor))
                        .component(DataComponents.LORE, new ItemLore(
                                Component.nullToEmpty(" x1.5 Fly Speed").toFlatList(Style.EMPTY.withColor(CommonColors.LIGHT_GRAY).withItalic(false))
                        ))
                        .setId(ResourceKey.create(Registries.ITEM, id)),
                entry.getValue()
        ));
    }).toList();

    public static void register() {}
}
