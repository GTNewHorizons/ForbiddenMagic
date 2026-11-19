package fox.spiteful.forbidden.mixins.early;

import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;

import fox.spiteful.forbidden.items.baubles.ItemConsumptionCincture;

@Mixin(EntityPlayer.class)
public class MixinEntityPlayer_ConsumptionCincture {

    @WrapMethod(method = "canEat")
    private boolean forbiddenmagic$consumptionCincture(boolean p_71043_1_ /* alwaysEdible, used for golden apples */,
            Operation<Boolean> original) {
        return ItemConsumptionCincture.wearingBelt((EntityPlayer) (Object) this) || original.call(p_71043_1_);
    }
}
