package tornato.harness_bizarreness.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.happyghast.HappyGhast;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tornato.harness_bizarreness.HarnessBizarreness;

@Mixin(HappyGhast.class)
public abstract class HappyGhastMixin extends Mob {
    protected HappyGhastMixin(EntityType<? extends Mob> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/happyghast/HappyGhast;setLeashHolder(Z)V"))
    private void applySpeedBoost(CallbackInfo ci) {
        if (this.isVehicle()) {
            this.getAttributes().addTransientAttributeModifiers(
                    this.equipment.get(EquipmentSlot.BODY).is(HarnessBizarreness.HARNESSES) ?
                            HarnessBizarreness.FAST_SPEED:
                            HarnessBizarreness.NORMAL_SPEED
            );
        } else {
            this.getAttributes().removeAttributeModifiers(HarnessBizarreness.FAST_SPEED);
            this.getAttributes().removeAttributeModifiers(HarnessBizarreness.NORMAL_SPEED);
        }
    }

    @ModifyArg(method = "createAttributes", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;add(Lnet/minecraft/core/Holder;D)Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;", ordinal = 2), index = 1)
    private static double modifyBaseSpeed(double baseValue) {
        return baseValue * 0.2;
    }

}
