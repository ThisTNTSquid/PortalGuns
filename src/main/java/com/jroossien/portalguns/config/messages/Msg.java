package com.jroossien.portalguns.config.messages;

import com.jroossien.portalguns.util.Str;
import com.jroossien.portalguns.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public enum Msg {
    NO_PERMISSION(Cat.GENERAL, "&cInsufficient permissions."),
    PLAYER_COMMAND(Cat.GENERAL, "&cThis is a player command only."),
    INVALID_USAGE(Cat.GENERAL, "&cInvalid usage! &7{usage}"),
    PRIMARY(Cat.GENERAL, "primary"),
    SECONDARY(Cat.GENERAL, "secondary"),
    NOBODY(Cat.GENERAL, "&cnobody"),

    NO_ITEM_SPECIFIED(Cat.ITEM_PARSER, "&cNo item specified!"),
    UNKNOWN_ITEM_NAME(Cat.ITEM_PARSER, "&cThe item &4{input} &cis not a valid item!"),
    MISSING_META_VALUE(Cat.ITEM_PARSER, "&cNo value specified for meta &4{meta}&c!"),
    NOT_A_NUMBER(Cat.ITEM_PARSER, "&4{input} &cis not a number!"),
    INVALID_COLOR(Cat.ITEM_PARSER, "&4{input} &cis not a valid color!"),
    INVALID_DYE_COLOR(Cat.ITEM_PARSER, "&4{input} &cis not a valid dye color!"),
    INVALID_FIREWORK_SHAPE(Cat.ITEM_PARSER, "&4{input} &cis not a valid firework shape!"),
    MISSING_FIREWORK_SHAPE(Cat.ITEM_PARSER, "&cTo create a firework effect, you need to specify the shape!"),
    MISSING_FIREWORK_COLOR(Cat.ITEM_PARSER, "&cTo create a firework effect, you need to set at least one color!"),
    INVALID_ENCHANT_VALUE(Cat.ITEM_PARSER, "&4{input} &cis not a valid enchantment level."),
    INVALID_POTION_VALUE(Cat.ITEM_PARSER, "&4{input} &cis not a valid potion effect value. It should be {duration}.{amplifier}&c."),

    GUN_NAME(Cat.GUN, "&6&lPortal &9&lGun"),
    INACTIVE_GUN(Cat.GUN, "&c&lBroken Portal Gun"),
    GUN_UID_PREFIX(Cat.GUN, "&8&o"),
    GUN_OWNER(Cat.GUN, "&6Owner&8: &a"),
    GLOBAL_OWNER(Cat.GUN, "&e(Global gun!)"),
    GUN_DURABILITY_PREFIX(Cat.GUN, "&a"),
    GUN_DURABILITY_SEPERATOR(Cat.GUN, "&7/"),
    GUN_DURABILITY_SUFFIX(Cat.GUN, "&2"),
    GUN_DESCRIPTION(Cat.GUN, "&7Left click to create the primary portal.\n&7Right click to create the secondary portal.\n&aShift click &7to open the &acontrol panel&7!"),
    CRAFT_NEW(Cat.GUN, "&7Craft a new portal gun!"),
    CRAFT_COPY(Cat.GUN, "&7Craft a copy of your previous gun!"),
    CRAFT_GUN_INFO_SEPARATOR(Cat.GUN, "&7------------------"),

    MENU_TITLE(Cat.CONTROL_PANEL, "&9&lControl Panel"),
    DELETE_NAME(Cat.CONTROL_PANEL, "&c&lDelete Portal"),
    DELETE_DESC(Cat.CONTROL_PANEL, "&7Delete the {type} portal if it's active."),
    PERSISTENT_NAME(Cat.CONTROL_PANEL, "&6&lPersistent"),
    PERSISTENT_DESC(Cat.CONTROL_PANEL, "&7This portal is persistent.\n&7The portal won't get deleted when cleanup happens.\n&eClick &7to make it non persistent."),
    NOT_PERSISTENT_NAME(Cat.CONTROL_PANEL, "&e&lNot Persistent"),
    NOT_PERSISTENT_DESC(Cat.CONTROL_PANEL, "&7This portal is not persistent.\n&7The portal will get deleted when cleanup happens.\n&6Click &7to make it persistent."),
    SHARES_NAME(Cat.CONTROL_PANEL, "&6&lShares"),
    SHARES_DESC(Cat.CONTROL_PANEL, "&7&oAdd friends to allow them to use your portals!\n&7They can not use your portal gun!\n&eLeft click &7to &eadd &7people.\n&cRight click &7to &cremove &7people.\n" +
            "&6Currently shared with&8:\n{shares}"),
    PRIMARY_NAME(Cat.CONTROL_PANEL, "&9&lPrimary Portal"),
    SECONDARY_NAME(Cat.CONTROL_PANEL, "&6&lSecondary Portal"),
    PORTAL_DESC(Cat.CONTROL_PANEL, "&7This is your {type} portal.\n&7Below you can adjust the portal color!\n&6Color&8: &a{color}\n&6Location&8: &a{location}"),
    PORTAL_DESC_INACTIVE(Cat.CONTROL_PANEL, "&7This is your {type} portal.\n&7Below you can adjust the portal color!\n&c&lThis portal is not active!\n&cClick while holding your gun to create a portal!"),
    COLOR_FORMAT(Cat.CONTROL_PANEL, "&c{red}&8,&a{green}&8,&9{blue}"),
    SEPERATOR_LEFT(Cat.CONTROL_PANEL, "&9Primary Portal"),
    SEPERATOR_RIGHT(Cat.CONTROL_PANEL, "&6Secondary Portal"),
    SEPERATOR_CENTER(Cat.CONTROL_PANEL, "&9Primary &7<-> &6Secondary"),
    RED(Cat.CONTROL_PANEL, "&cRed &8[&6{value}&8]"),
    GREEN(Cat.CONTROL_PANEL, "&aGreen &8[&6{value}&8]"),
    BLUE(Cat.CONTROL_PANEL, "&9Blue &8[&6{value}&8]"),
    COLOR_DESC_INCREASE(Cat.CONTROL_PANEL, "increase"),
    COLOR_DESC_DECREASE(Cat.CONTROL_PANEL, "decrease"),
    COLOR_DESC(Cat.CONTROL_PANEL, "&aLeft click &7to {type} the color by &a1&7.\n&2Shift+Left click &7to {type} the color by &25&7.\n&eRight click &7to {type} the color by &e20&7.\n&6Shift+Right click &7to {type} the color by &650&7."),



    ;


    private Cat cat;
    private String msg;

    Msg(Cat cat, String msg) {
        this.cat = cat;
        this.msg = msg;
    }


    public String getDefault() {
        return msg;
    }

    public String getName() {
        return toString().toLowerCase().replace("_", "-");
    }

    public String getCategory() {
        return cat.toString().toLowerCase().replace("_", "-");
    }

    public String getMsg() {
        return MessageCfg.inst().getMessage(getCategory(), getName());
    }

    public String getMsg(Param... params) {
        return getMsg(false, false, params);
    }

    public String getMsg(boolean prefix, boolean color, Param... params) {
        String message = (prefix ? MessageCfg.inst().prefix : "") + getMsg();
        for (Param p : params) {
            message = message.replace(p.getParam(), p.toString());
        }
        if (color) {
            message = Str.color(message);
        }
        return message;
    }


    public void broadcast(Param... params) {
        broadcast(true, true, params);
    }

    public void broadcast(boolean prefix, boolean color, Param... params) {
        Bukkit.broadcastMessage(getMsg(prefix, color, params));
    }

    public void send(CommandSender sender, Param... params) {
        send(sender, true, true, params);
    }

    public void send(CommandSender sender, boolean prefix, boolean color, Param... params) {
        if (sender != null) {
            sender.sendMessage(getMsg(prefix, color, params));
        }
    }


    private enum Cat {
        GENERAL,
        ITEM_PARSER,
        GUN,
        CONTROL_PANEL,
        ;
    }
}
