package nightkosh.gravestone.core.commands;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SubCommandCustomGraveItems implements ISubCommand {

    public static final String COMMAND_NAME = "fill_grave";
    public static final String COMMAND_USAGE = Command.MAIN_COMMAND_NAME + COMMAND_NAME + " <grave x coordinate> <grave y coordinate> <grave z coordinate>  <chest x coordinate> <chest y coordinate> <chest z coordinate>";

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public String getCommandUsage() {
        return COMMAND_USAGE;
    }

    //TODO
//    @Override
//    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
//        LOGGER.info("Custom grave items command received");
//
//        if (args.length >= 7) {
//            try {
//                int graveXCoord = Integer.parseInt(args[1]);
//                int graveYCoord = Integer.parseInt(args[2]);
//                int graveZCoord = Integer.parseInt(args[3]);
//
//                int chestXCoord = Integer.parseInt(args[4]);
//                int chestYCoord = Integer.parseInt(args[5]);
//                int chestZCoord = Integer.parseInt(args[6]);
//
//                BlockEntity graveTE = sender.getEntityWorld().getBlockEntity(new BlockPos(graveXCoord, graveYCoord, graveZCoord));
//                BlockEntity chestTE = sender.getEntityWorld().getBlockEntity(new BlockPos(chestXCoord, chestYCoord, chestZCoord));
//                if (graveTE != null && chestTE != null) {
//                    if (graveTE instanceof GraveStoneBlockEntity && chestTE instanceof TileEntityChest) {
//                        List<ItemStack> items = new ArrayList<ItemStack>();
//                        ItemStack item;
//                        for (int slot = 0; slot < ((TileEntityChest) chestTE).getSizeInventory(); slot++) {
//                            item = ((TileEntityChest) chestTE).getStackInSlot(slot);
//                            if (item != null) {
//                                items.add(item.copy());
//                            }
//                        }
//                        ((GraveStoneBlockEntity) graveTE).getInventory().setAdditionalItems(items.toArray(new ItemStack[items.size()]));
//                    } else {
//                        sender.sendMessage(new TextComponentTranslation("commands.fill_grave.wrong_block").setStyle(new Style().setColor(TextFormatting.RED)));
//                    }
//                } else {
//                    sender.sendMessage(new TextComponentTranslation("commands.fill_grave.empty").setStyle(new Style().setColor(TextFormatting.RED)));
//                }
//            } catch (NumberFormatException e) {
//                sender.sendMessage(new TextComponentTranslation("commands.coordinate_error").setStyle(new Style().setColor(TextFormatting.RED)));
//            }
//        } else {
//            sender.sendMessage(new TextComponentTranslation("commands.not_enough_parameters").setStyle(new Style().setColor(TextFormatting.RED)));
//        }
//    }
}
