
package net.minecraft.GraveStone.tileentity;

import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;

public class GSGraveStoneDeathText {
    
    // grave text
    private Random random = new Random();
    private String deathText = "";
    private static final String[] DEATH_TEXT = {
        " was slain by Wolf",
        " was slain by Spider",
        " was slain by Cave Spider",
        " was slain by Zombie",
        " was slain by Zombie Pigman",
        " was slain by Slime",
        " was slain by Magma Cube",
        " was slain by Blaze",
        " was slain by Enderman",
        " was slain by Iron Golem",
        " was slain by Wither",
        " was fireballed by Ghast",
        " was shot by Skeleton",
        " was killed by Witch",
        " was killed by magic",
        " was pricked to death",
        " starved to death",
        " burned to death",
        " blew up",
        " withered away",
        " suffocated in a wall",
        " hit the ground too hard",
        " fell out of the world",
        " tried to swim in lava",
        " drowned",
        " was blew up by creeper",
        " was brutally murdered by Herobrine",
        " was punished capitally",
        " was die in cruel tortures"
    };
    private static final String[] DEATH_NAME = {
        "Steve", "Jeb", "Notch", "Leeroy Jenkins", "Zod",
        "Alex", "Alice", "Alan", "Ashley",
        "Barry", "Bextrix", "Benjamin", "Billy",
        "Calvin", "Carolyn", "Cate", "Chandler", "Chuck", "Clyde",
        "David", "Denice", "Dorian",
        "Edison", "Edna", "Edward", "Eliot", "Elisa", "Emily",
        "Flynn",
        "Garry", "Glen", "Gordon",
        "Hall", "Hank",
        "Jack", "Jacob", "Jenny", "Jimmy", "Joey",
        "Kate", "Keeley",
        "Leslie", "Lex", "Lucy",
        "Mabel", "Madeline", "Marcus", "Max",
        "Nick",
        "Oliver",
        "Quentin",
        "Ralph", "Rex", "Ricky",
        "Scotty", "Sherry", "Sophy",
        "Tad", "Terence", "Toby",
        "Wendy", "Willy"
    };
    
    
    public void readText(NBTTagCompound nbtTag) {
        deathText = nbtTag.getString("DeathText");
    }
    
    public void saveText(NBTTagCompound nbtTag) {
        nbtTag.setString("DeathText", deathText);
    }
    
    public String getDeathText() {
        return deathText;
    }
    
    public void setDeathText(String text) {
        deathText = text;
    }
    
    public void setRandomDeathText() {
        deathText = DEATH_NAME[random.nextInt(DEATH_NAME.length)] + DEATH_TEXT[random.nextInt(DEATH_TEXT.length)];
    }
}
