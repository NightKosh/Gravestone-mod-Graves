
package GraveStone.tileentity;

import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GSGraveStoneDeathText {
    
    // grave text
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
        " went up in flames",
        " blew up",
        " withered away",
        " suffocated in a wall",
        " hit the ground too hard",
        " fell out of the world",
        " tried to swim in lava",
        " drowned",
        " was blown up by creeper",
        " was brutally murdered by Herobrine",
        " received a death sentence",
        " was die in cruel tortures"
    };
    private static final String[] MEMORIAL_TEXT = {
      "Earth has no sorrow that Heaven cannot heal",
      "See, I am sending an angel ahead of you to guard you along the way and to bring you to the place I have prepared",
      "It's like we are awake here and asleep in heaven until we die, then we are asleep here and awake there",
      "What we have once enjoyed and deeply loved we can never lose, for all that we love deeply becomes a part of us"
    };
    private static final String[] DOGS_MEMORIAL_TEXT = {
        "Everybody is born so that they can learn how to live a good life and love others - dogs already know how to do that so they don't have to stay as long",
        "Dog's lives are too short. Their only fault, really"
    };
    private static final String[] NAMES = {
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
    private static final String[] DOG_NAMES = {
        "Buddy",
        "Chomper",
        "Max",
        "Rex", "Rover", "Rusty",
        "Spike", "Sparky"
    };
    private static final String[] CAT_NAMES = {
        "Bella",
        "Max", "Molly",
        "Oscar",
        "Sam",
        "Tiger"
    };
    private TileEntityGSGrave tileEntity;
    
    public GSGraveStoneDeathText(TileEntityGSGrave tileEntity) {
        this.tileEntity = tileEntity;
    }
    
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
    
    public void setRandomDeathText(Random random, byte graveType, boolean isMemorial) {
        if (isMemorial && random.nextInt(5) > 2) {
            deathText = MEMORIAL_TEXT[random.nextInt(MEMORIAL_TEXT.length)];
            return;
        }
        switch (graveType) {
            case 3:
                deathText = DOG_NAMES[random.nextInt(DOG_NAMES.length)];
                break;
            case 4:
                deathText = CAT_NAMES[random.nextInt(CAT_NAMES.length)];
                break;
            default:
                deathText = NAMES[random.nextInt(NAMES.length)];
        }
        deathText += DEATH_TEXT[random.nextInt(DEATH_TEXT.length)];
    }
}
