package tornato.harness_bizarreness;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;

public class HarnessBizarreness implements ModInitializer {
    public static final String ID = "harness_bizarreness";
    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(ID, path);
    }

    public final static TagKey<Item> HARNESSES = TagKey.create(Registries.ITEM, id("harnesses"));

    public final static Multimap<Holder<Attribute>, AttributeModifier> FAST_SPEED = HashMultimap.create(1,1);
    public final static Multimap<Holder<Attribute>, AttributeModifier> NORMAL_SPEED = HashMultimap.create(1,1);

    static {
        FAST_SPEED.put(Attributes.FLYING_SPEED, new AttributeModifier(HarnessBizarreness.id("harness"), 0.08, AttributeModifier.Operation.ADD_VALUE));
        NORMAL_SPEED.put(Attributes.FLYING_SPEED, new AttributeModifier(HarnessBizarreness.id("harness"), 0.05, AttributeModifier.Operation.ADD_VALUE));
    }

    @Override
    public void onInitialize() {
        HarnessItems.register();
    }
}
