package ru.leroron.essentiels.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import static ru.leroron.essentiels.utils.IntegerUtil.a;

@SuppressWarnings("All")
public class LocationUtil {


    private static String WXYZYP = "%s, %s;%s;%s, %s;%s";

    public static String toStringWXYZYP(Location loc) {
        return String.format(WXYZYP, loc.getWorld().getName(), a(loc.getX()),
                a(loc.getY()), a(loc.getZ()), a(loc.getYaw()), a(loc.getPitch()));
    }

    public static Location fromStringWXYZYP(String s) {
        String[] split = s.split(", ");
        if(split.length != 3) throw new IllegalStateException("String " + s + " is not corrected.");
        String world = split[0];
        String coordinates = split[1];
        String rotation = split[2];

        World w = Bukkit.getWorld(world);
        if(w == null) throw new IllegalArgumentException("World not found at: " + s);
        String[] location = coordinates.split(";");
        String[] rotations = rotation.split(";");

        double x = -0xFA, y = -0xFA, z = -0xFA;
        float yaw = -0xFA, pitch = -0xFA;

        try {
            x = a(Double.parseDouble(location[0]));
            y = a(Double.parseDouble(location[1]));
            z = a(Double.parseDouble(location[2]));
            yaw = a(Float.parseFloat(rotations[0]));
            pitch = a(Float.parseFloat(rotations[1]));
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("String " + s + " is not corrected.");
        }
        return new Location(w, x,y,z,yaw,pitch);
    }


}