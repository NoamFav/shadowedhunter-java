package com.shadowedhunter.resources;

public class TrapMessages {

    // POISON TRAP
    private static final String[][] POISON = {
        // Death messages
        {
            "You hit a tripwire, poisoning insects fall on you, you try to get them off but one"
                + " manages to bite you. You died",
            "You hit a pressure plate, a troll falls from it, you try to fight him, but he takes"
                + " the upper hand and smashes your head. You died",
            "Out of the blue, a zombie pops out, bites you, and runs away, you try to suck out the"
                + " saliva, without success. You 'kinda' died",
            "A tarantula climbs silently onto your shoulder, sneaking her way to your neck, you"
                + " don't feel a thing, she bites you. You died"
        },
        // Survival messages
        {
            "You hit a tripwire, poisoning insects fall on you, you successfully get them off you."
                + " -25HP",
            "You hit a pressure plate, a troll falls from it, you engage in fierce combat, you take"
                + " the upper hand and stick your sword in his skull. -25HP",
            "Out of the blue, a zombie pops out, tries to bite you. Your reflexes save you, you"
                + " swing your sword and cut his head clean off. -25HP",
            "A tarantula climbs silently onto your shoulder, you realize that, and throw her into"
                + " the wall like a baseball. -25HP"
        }
    };

    // SPIKE TRAP
    private static final String[][] SPIKE = {
        {
            "You walk on a pressure plate, and the floor crumbles under your feet, revealing"
                + " spikes, you end up impaled. You died",
            "The tile you just stepped on breaks under your weight, rotating blades were hidden"
                + " underneath, and you get shredded to pieces. You died",
            "You step on a rug, a hole is hidden by it. You fall into a pit of spiders. You get"
                + " scared and have a heart attack. You died",
            "You walk on a weighted tile, and spikes rise through the floor, cutting holes in you."
                + " You died"
        },
        {
            "You walk on a pressure plate, and the floor crumbles under your feet, revealing"
                + " spikes, you manage to grab the ledge. -75HP",
            "The tile you just stepped on breaks under your weight, the rotating blades were hidden"
                + " underneath, and the blades were off. -75HP",
            "You step on a rug, a hole is hidden by it. You fall into a pit of spiders. You"
                + " brandish your sword to drive them away and climb out. -75HP",
            "You walk on a weighted tile, and spikes rise through the floor, by pure luck, not a"
                + " single one hits you. -75HP"
        }
    };

    // FALLING TRAP
    private static final String[][] FALLING = {
        {
            "The floor starts to shake violently, and a tile from the ceiling breaks and drops on"
                + " you, it hits your head and shatters your skull. You died",
            "You see a vase on the side of the corridor, you pick it up, and a mechanism triggers."
                + " Spikes fall on you, one falls right through you. You died",
            "You hit a tripwire on the floor, nothing happens then suddenly, spikes fall on you,"
                + " you try to roll away, to no avail. You died",
            "You see a lever on the wall, curious, you pull it, and gold falls on you, then a giant"
                + " brick falls on you and bursts your thorax. You died"
        },
        {
            "The floor starts to shake violently, and a tile from the ceiling breaks and drops on"
                + " you, you manage to avoid it. -40HP",
            "You see a vase on the side of the corridor, you pick it up, and a mechanism triggers."
                + " Spikes fall on you, you successfully avoid all of them. -40HP",
            "You hit a tripwire on the floor, nothing happens then suddenly, spikes fall on you,"
                + " you do the best somersault ever, and avoid them all. -40HP",
            "You see a lever on the wall, curious, you pull it, gold falls on you, then a giant"
                + " brick falls. You jump aside to avoid it. -40HP"
        }
    };

    // PROJECTILE TRAP
    private static final String[][] PROJECTILE = {
        {
            "You walk on a pressure plate, arrows come out of holes in the wall, to make other"
                + " holes in your body. You died",
            "You trigger a tripwire, you hear gears in the wall, suddenly spikes come out of the"
                + " wall, turning you into Swiss cheese. You died",
            "An arrow falls on you, when you pick it up you realize it is poisoned, a few seconds"
                + " later you convulse on the floor. You died",
            "A skeleton dropped from the ceiling scaring you to death. You died"
        },
        {
            "You walk on a pressure plate, arrows come out of holes in the wall, and your dance"
                + " class helps you avoid them. -50HP",
            "You trigger a tripwire, you hear gears in the wall, suddenly spikes come out, luckily"
                + " they stopped right in front of your nose. -50HP",
            "An arrow falls on you, when you pick it up you realize it is poisoned, you quickly"
                + " suck the poison out. -50HP",
            "A skeleton dropped from the ceiling, scared, you punch the skeleton with everything"
                + " you've got. -50HP"
        }
    };

    // WALL TRAP
    private static final String[][] WALL = {
        {
            "Your foot gets stuck between two tiles, and suddenly the walls around you start to"
                + " shrink, you slowly get squeezed to death. You died",
            "You curiously pull the lever that's sticking to the wall, the walls shrink in an"
                + " instant, and you with it. You died",
            "A werewolf appears from the corridor. You attempt to escape, but he grabs you and"
                + " throws you into a wall, splattering it with your blood. You died",
            "You walked on a pressure plate, the tile springs you out into the ceiling and crushes"
                + " you like a soda can. You died"
        },
        {
            "Your foot gets stuck between two tiles, and suddenly the walls start to shrink, you"
                + " manage to free your feet and escape. -90HP",
            "You curiously pull the lever, by fear you jump away, and the wall shrinks in an"
                + " instant behind you. -90HP",
            "A werewolf appears from the corridor. You successfully avoid his first attack and stab"
                + " him in the back. -90HP",
            "You walked on a pressure plate, the tile springs out, but luckily you managed to avoid"
                + " it. -90HP"
        }
    };

    // BLADE TRAP (always kills)
    private static final String[][] BLADE = {
        {
            "You hit a tripwire, and four axes pop out the walls, cutting you into five equal"
                + " pieces. You died",
            "You step on a pressure plate, and two giant sledgehammers swing down from the ceiling"
                + " bashing your skull to shreds. You died",
            "A blade swings down from the ceiling making a perfect hole in your head. You died",
            "A black knight appears in the corridor. Despite your attempts to defend yourself, it's"
                + " to no avail. With one swift swing, he cuts your head off. You died"
        },
        {
            "You hit a tripwire, and four axes pop out the walls, cutting you into five equal"
                + " pieces. You died",
            "You step on a pressure plate, and two giant sledgehammers swing down from the ceiling"
                + " bashing your skull to shreds. You died",
            "A blade swings down from the ceiling making a perfect hole in your head. You died",
            "A black knight appears in the corridor. Despite your attempts to defend yourself, it's"
                + " to no avail. With one swift swing, he cuts your head off. You died"
        }
    };

    // HOLE TRAP
    private static final String[][] HOLE = {
        {
            "You step on a rug in the middle of the corridor, it makes you fall into the previous"
                + " floor, your legs go into your body. You died",
            "You whistle into the long corridor, you don't pay attention to the hole in the floor,"
                + " you fall and break your legs, an ogre finishes the job. You died",
            "You step on a plank in the middle of the corridor, it snaps under your feet, when"
                + " hitting the floor, the plank hits your head. You died",
            "You trip on a tile and fall in a hole, you fall head first and break your neck. You"
                + " died"
        },
        {
            "You step on a rug in the middle of the corridor, it makes you fall into the previous"
                + " floor. -20HP",
            "You whistle into the long corridor, you don't pay attention to the hole in the floor,"
                + " you fall into the previous floor. -20HP",
            "You step on a plank in the middle of the corridor, it snaps under your feet, and you"
                + " fall into the previous floor. -20HP",
            "You trip on a tile and fall into a hole in the middle of the corridor, you fall into"
                + " the previous floor. -20HP"
        }
    };

    // PORTCULLIS (doesn't kill)
    private static final String[][] PORTCULLIS = {
        {
            "You pass under a portcullis, you feel anxious passing under those spikes.",
            "When passing under the portcullis, you hear a click, you quickly jump forward and"
                + " avoid the spikes. The gate comes back up a moment later",
            "The portcullis in front of you feels scary, by fear, you run to pass it, the gate"
                + " didn't budge though.",
            "Scared by the spikes of the portcullis you throw something underneath, the gate"
                + " quickly closes on it, and when it goes back up, you safely roll underneath."
        },
        {
            "You pass under a portcullis, you feel anxious passing under those spikes.",
            "When passing under the portcullis, you hear a click, you quickly jump forward and"
                + " avoid the spikes. The gate comes back up a moment later",
            "The portcullis in front of you feels scary, by fear, you run to pass it, the gate"
                + " didn't budge though.",
            "Scared by the spikes of the portcullis you throw something underneath, the gate"
                + " quickly closes on it, and when it goes back up, you safely roll underneath."
        }
    };

    public static String getPoisonMessage(boolean dies, int variant) {
        return POISON[dies ? 0 : 1][variant];
    }

    public static String getSpikeMessage(boolean dies, int variant) {
        return SPIKE[dies ? 0 : 1][variant];
    }

    public static String getFallingMessage(boolean dies, int variant) {
        return FALLING[dies ? 0 : 1][variant];
    }

    public static String getProjectileMessage(boolean dies, int variant) {
        return PROJECTILE[dies ? 0 : 1][variant];
    }

    public static String getWallMessage(boolean dies, int variant) {
        return WALL[dies ? 0 : 1][variant];
    }

    public static String getBladeMessage(boolean dies, int variant) {
        return BLADE[0][variant]; // Always dies
    }

    public static String getHoleMessage(boolean dies, int variant) {
        return HOLE[dies ? 0 : 1][variant];
    }

    public static String getPortcullisMessage(boolean dies, int variant) {
        return PORTCULLIS[0][variant]; // Never dies
    }
}
