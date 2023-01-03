package miku.entity.boss;

import miku.lib.api.ProtectedEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.world.World;

public class MazeGod extends EntityLivingBase implements ProtectedEntity {
    public MazeGod(World worldIn) {
        super(worldIn);
    }

    @Override
    public Iterable<ItemStack> getArmorInventoryList() {
        return null;
    }

    @Override
    public ItemStack getItemStackFromSlot(EntityEquipmentSlot slotIn) {
        return null;
    }

    @Override
    public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack) {

    }

    @Override
    public EnumHandSide getPrimaryHand() {
        return null;
    }

    @Override
    public boolean CanBeKilled() {
        return true;
    }

    @Override
    public boolean DEAD() {
        return false;
    }

    @Override
    public void SetHealth(int health) {

    }

    @Override
    public int GetHealth() {
        return 0;
    }

    @Override
    public void Hurt(int amount) {

    }
}
