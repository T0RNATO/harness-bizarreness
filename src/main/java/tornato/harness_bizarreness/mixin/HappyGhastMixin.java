package tornato.harness_bizarreness.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.HappyGhastEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tornato.harness_bizarreness.HarnessBizarreness;

@Mixin(HappyGhastEntity.class)
public abstract class HappyGhastMixin extends MobEntity {
    protected HappyGhastMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/HappyGhastEntity;setHasRopes(Z)V"))
    private void applySpeedBoost(CallbackInfo ci) {
        if (this.hasPassengers()) {
            this.getAttributes().addTemporaryModifiers(
                    this.equipment.get(EquipmentSlot.BODY).isIn(HarnessBizarreness.HARNESSES) ?
                            HarnessBizarreness.FAST_SPEED:
                            HarnessBizarreness.NORMAL_SPEED
            );
        } else {
            this.getAttributes().removeModifiers(HarnessBizarreness.FAST_SPEED);
            this.getAttributes().removeModifiers(HarnessBizarreness.NORMAL_SPEED);
        }
    }

    @ModifyArg(method = "createHappyGhastAttributes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;add(Lnet/minecraft/registry/entry/RegistryEntry;D)Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;", ordinal = 2), index = 1)
    private static double modifyBaseSpeed(double baseValue) {
        return baseValue * 0.2;
    }

}
