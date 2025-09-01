package tornato.harness_bizarreness;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class HarnessBizarreness implements ModInitializer {
    public static final String ID = "harness_bizarreness";
    public static Identifier id(String path) {
        return Identifier.of(ID, path);
    }

    public final static TagKey<Item> HARNESSES = TagKey.of(RegistryKeys.ITEM, id("harnesses"));

    public final static Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> FAST_SPEED = HashMultimap.create(1,1);
    public final static Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> NORMAL_SPEED = HashMultimap.create(1,1);

    static {
        FAST_SPEED.put(EntityAttributes.FLYING_SPEED, new EntityAttributeModifier(HarnessBizarreness.id("harness"), 0.08, EntityAttributeModifier.Operation.ADD_VALUE));
        NORMAL_SPEED.put(EntityAttributes.FLYING_SPEED, new EntityAttributeModifier(HarnessBizarreness.id("harness"), 0.05, EntityAttributeModifier.Operation.ADD_VALUE));
    }

    @Override
    public void onInitialize() {
        HarnessItems.register();
    }
}
