package com.ShadowedHunter;

import java.util.*;

public class TextResources {
    private static final Random rand = new Random();

    // Trap type enum
    public enum TrapType {
        POISON,
        SPIKE,
        FALLING,
        PROJECTILE,
        WALL,
        INSTANT_DEATH,
        HOLE,
        PORTCULLIS
    }

    // Store intro/final messages
    private static final String[] INTRO_MESSAGES = {
        "Before we start the game I will give more information about the game.",
        "The game is a text-based, rogue-like game, this means that you won't finish it in one"
            + " life.",
        "Hint: you can use a sheet of paper to write your way.",
        "The directions are simple: North/South/East/West.",
        "For simplicity, moving one tile only requires the initial of the direction (n/s/e/w).",
        "You can jump two tiles like this: 'jump <direction>' (ex: jump north).",
        "You open doors by typing: 'open <direction> -ern door' (ex: open northern door).",
        "When meet with a stairs, you can either go up or down them by typing: 'climb up/down the"
            + " stairs'.",
        "The GUI you see gives you different information:",
        "Your health, your timer, the number of times you died (life), and your inventory.",
        "In your inventory, you can see that there are four items.",
        "There are two interactive items, the keys, and the health potions.",
        "Both can be found across the map, and beware, they aren't that easy to find.",
        "Keys can be used to open a locked door by typing: 'unlock <direction> -ern door' (ex:"
            + " unlock the northern door).",
        "When unlocked, the door is unlocked for the entirety of the game.",
        "Keys aren't linked to a certain door, but they only can open one.",
        "Health potions restore your life to the max by typing: 'use potion'.",
        "Health potions are also single use, so be careful of how you use them.",
        "To pick up an item, when standing on them just time: 'pick up item'.",
        "Furthermore you can 'look around' to see what surrounds you.",
        "The place is full of traps, in your play through you will have to trigger some.",
        "When you trigger one, it can't be triggered again, even if you die, and go through the"
            + " same tile.",
        "Also when you die, you won't lose your inventory.",
        "You can also stab yourself by typing: 'stab myself'.",
        "Or kill yourself, if you are stuck for example, by typing 'kill myself'.",
        "Finally to leave, type 'exit', and to see the cheatsheet, type 'help'.",
        "Have fun, and enjoy the game:",
        "Hello adventurer, are you ready for today's adventure?",
        "Here stand in front of you a giant dungeon, filled with mysteries.",
        "You've been hired by the king of this realm to kill the Lord ruling the lands you stepped"
            + " in.",
        "The reason isn't clear to you, probably a question of power.",
        "But you don't really care, the reward is so large that you couldn't refuse in good"
            + " conscience.",
        "In this dungeon, you'll meet: 4 floors, about 280 rooms to discover, dozens of monsters"
            + " blocking your way,",
        "traps at every corner to prevent you from ascending the dungeon, and more.",
        "Your goal is simple, reach the top floor of the dungeon.",
        "By the instruction of the king, his throne is on the biggest circular room on the top"
            + " floor",
        "Beware of your surroundings tho, because there is no safe place in this nightmare.",
        "You're standing in front of a huge door, you can here scream inside.",
        "Determined, you push the door and enter, the dungeon.",
        "The door slowly closes behind you, there is no turning back down.",
        "What do you do?"
    };

    private static final String[] FINAL_STAGE = {
        "You open the door to the supposed location of the Lord.",
        "When entering you feel a cold breeze on your neck.",
        "Then a voice starts speaking: ",
        "'You finally arrived adventurer! I've been waiting for you. You took your sweet time.'",
        "'I know why you are here, the king finally succeeded in sending someone skilled enough to"
            + " reach me.'",
        "You then say: 'So you know I have to kill you then ?!'",
        "He answered: 'Yes, it is the natural flow of time.'",
        "'I'm getting old now. it's many dozen winters now that I was in your shoes.'",
        "'Young and looking for wealth, no matter where.'",
        "You say surprised: 'What do you mean in my shoes?'",
        "'I was hired by the king to kill the Lord of this dungeon too.'",
        "'I didn't know then but when I arrived to this exact same room I knew'",
        "'There has to be someone on this throne, the balance of the Realm depends on it.'",
        "'You have to be the villain for the Realm to prosper.'",
        "You say: 'I get to take your place?'",
        "He replies: 'No, you HAVE to, when I'm ready you won't be able to resist.'",
        "'You will stab me, and take my place as Dark Lord of the Realm.'",
        "'And when you reach my age, someone else will come to kill you, and take your place.'",
        "I reply: 'But I don't want to do that, I'm not a bad guy.'",
        "He answers: 'Your free will ended the moment you stepped in this place'",
        "'This is your destiny",
        "He finished his sentence, then suddenly an uncontrollable urge flowed through me.",
        "I run to him and stab him in the chest. He smiles as his body drops to the floor.",
        "As you see his blood dripping on the floor, you feel something, something strange.",
        "His body start to shake, then suddenly, a black cloud leave and start twirling around"
            + " you.",
        "After a moment the cloud enters your body, you feel different, powerful, more than ever",
        "You then sit on the immense throne in the middle of the room.",
        "This is your life now, until the end, you say:",
        "'I can work with that with a huge smile.",
        "The end."
    };

    // Lore text storage
    private static final Map<String, String> LORE_MAP = new HashMap<>();

    static {
        // Floor 0
        LORE_MAP.put(
                "1",
                "An old broken table lies in the middle of the room, and a pile of rotten leather"
                    + " lies in the north-west corner of the room.");
        LORE_MAP.put(
                "3",
                "Someone has scrawled a fell symbol on the north wall, and patches of mushrooms"
                    + " grow in the south-west corner of the room.");
        LORE_MAP.put(
                "4",
                "A set of demonic war masks hangs on the north wall, and someone has scrawled 'This"
                    + " is not a secret door' on the south wall.");
        LORE_MAP.put(
                "6",
                "Someone has scrawled 'two, two, two, seven' in draconic script on the north wall,"
                    + " and the sound of horns fills the room. There is a key in the room.");
        LORE_MAP.put(
                "7",
                "Several square holes are cut into the ceiling and floor, and a rusted amulet lies"
                    + " in the north-west corner of the room.");
        LORE_MAP.put("8", "There is a health potion in the room.");
        LORE_MAP.put(
                "10",
                "Someone has scrawled 'Abandon all hope' in goblin runes on the north wall, and"
                    + " several pieces of rotten leather are scattered throughout the room. There"
                    + " is a key in the room.");
        LORE_MAP.put(
                "11",
                "Several iron cages are scattered throughout the room, and several pieces of"
                    + " rotting wood are scattered throughout the room.");
        LORE_MAP.put("12", "There is a key in the room.");
        LORE_MAP.put("13", "There is a key and a health potion in the room.");
        LORE_MAP.put("15", "There is a key in the room.");
        LORE_MAP.put(
                "18",
                "A mural of an undead goddess covers the ceiling, and someone has scrawled 'There"
                    + " is no way out' in orcish runes on the north wall. There is a health potion"
                    + " in the room.");
        LORE_MAP.put(
                "20",
                "Someone has scrawled 'We've run out of rope' on the south wall, and a sulphurous"
                    + " odor fills the north- west corner of the room. There is a key in the"
                    + " room.");
        LORE_MAP.put("21", "There is a key in the room.");
        LORE_MAP.put(
                "22",
                "The room has a high domed ceiling, and a faded and torn tapestry hangs from the"
                    + " west wall. There is a key in the room.");
        LORE_MAP.put(
                "23",
                "A chute descends from the room into the next dungeon level down, and a fountain"
                    + " and statue of a Draconic God sits in the east side of the room.");
        LORE_MAP.put(
                "24",
                "Part of the north wall has collapsed into the room, and a forge and anvil sit in"
                    + " the center of the room. There is a key in the room.");
        LORE_MAP.put(
                "25",
                "A tile labyrinth covers the floor, and someone has scrawled nine, eight, three in"
                    + " draconic script on the north wall.");
        LORE_MAP.put(
                "27",
                "A balcony hangs from the south wall, and the floor is covered with broken glass.");
        LORE_MAP.put(
                "28",
                "A shallow pit lies in the north side of the room, and a putrid odor fills the"
                    + " south-west corner of the room.");
        LORE_MAP.put(
                "29",
                "A stair ascends to a wooden platform in the south- west corner of the room, and a"
                    + " carved stone statue stands in the south-west corner of the room.");
        LORE_MAP.put(
                "30",
                "A mural of ancient mythology covers the ceiling, and several adventurer corpses"
                    + " are scattered throughout the room. There is a key in the room.");
        LORE_MAP.put(
                "33",
                "A stair ascends to a balcony hanging from the north wall, and the sound of"
                    + " footsteps can be faintly heard near the west wall.");
        LORE_MAP.put("34", "There is a key in the room.");
        LORE_MAP.put(
                "35",
                "A tile mosaic of legendary monsters covers the floor, and a simple fireplace sits"
                    + " against the north wall.");
        LORE_MAP.put(
                "37",
                "A crater has been blasted into the floor in the south- east corner of the room,"
                    + " and a rusted amulet lies in the north-east corner of the room.");
        LORE_MAP.put(
                "39",
                "Someone has scrawled 'The walls listen' on the east wall, and several barrel"
                    + " staves are scattered throughout the room. There is a key in the room.");
        LORE_MAP.put("40", "There is a key in the room.");
        LORE_MAP.put(
                "42",
                "A group of monstrous faces have been carved into the north wall, and a circle of"
                    + " tall stones stands in the south-east corner of the room. There is a key in"
                    + " the room.");
        LORE_MAP.put(
                "43",
                "A set of demonic war masks hangs on the west wall, and someone has scrawled 'I've"
                    + " forgotten my name' on the south wall. There is a health potion in the"
                    + " room.");
        LORE_MAP.put("44", "There is a key in the room.");
        LORE_MAP.put(
                "46",
                "An iron sarcophagus sits in the south-east corner of the room, and the floor is"
                    + " covered with mud.");
        LORE_MAP.put(
                "47",
                "Someone has scrawled 'Twist the cog to reset the trap' on the west wall, and"
                    + " laughter can be faintly heard near the south wall.");
        LORE_MAP.put(
                "48",
                "A magical mosaic on the east wall depicts the betrayal of whomever views it, and a"
                    + " faded and torn tapestry hangs from the south wall.");
        LORE_MAP.put("49", "there is a key in the room.");
        LORE_MAP.put(
                "50",
                "A forge and anvil sit in the north side of the room, and a clanking sound can be"
                    + " faintly heard near the west wall. There is a key in the room.");
        LORE_MAP.put(
                "52",
                "A forge and anvil sit in the north side of the room, and a stream of oil flows"
                    + " through the room.");
        LORE_MAP.put(
                "53",
                "A tapestry of ancient mythology hangs from the west wall, and the sound of"
                    + " footsteps can be heard in the east side of the room. There is a health"
                    + " potion in the room.");
        LORE_MAP.put(
                "54",
                "The floor is covered in perfect hexagonal tiles, and a pile of shattered weapons"
                    + " lies in the west side of the room.");
        LORE_MAP.put(
                "55",
                "A stone dais sits in the east side of the room, and someone has scrawled a large X"
                    + " on the south wall.");
        LORE_MAP.put("56", "There is a key in the room.");
        LORE_MAP.put(
                "57",
                "The walls have been engraved with arcane runes, and a pile of iron spikes lies in"
                    + " the west side of the room.");
        LORE_MAP.put(
                "58",
                "A well lies in the north-west corner of the room, and spirals of yellow stones"
                    + " cover the floor. There is a health potion in the room.");
        LORE_MAP.put(
                "61",
                "A wooden platform hangs over a deep pit in the north-west corner of the room, and"
                    + " an acrid odor fills the south-west corner of the room.");
        LORE_MAP.put(
                "63",
                "A tile labyrinth covers the floor, and someone has scrawled 'The gold dragon is"
                    + " not a dragon' on the east wall.");
        LORE_MAP.put("64", "There is a health potion in the room.");
        LORE_MAP.put(
                "65",
                "A tile labyrinth covers the floor, and someone has scrawled The Legion of the"
                    + " Veiled Dagger looted this place on the west wall.");
        LORE_MAP.put(
                "67",
                "Several square holes are cut into the north and west walls, and an altar of evil"
                    + " sits in the south side of the room.");
        LORE_MAP.put(
                "68",
                "A mural of legendary monsters covers the ceiling, and someone has scrawled 'The"
                    + " last wards have fallen' on the south wall.");
        LORE_MAP.put(
                "69",
                "A stone ramp ascends towards the south wall, and someone has scrawled an arrow"
                    + " pointing down on the west wall.");
        LORE_MAP.put(
                "70",
                "Part of the west wall has collapsed into the room, and a pile of torn paper lies"
                    + " in the south-west corner of the room.");
        LORE_MAP.put(
                "71",
                "A stone stair ascends towards the east wall, and a rotting odor fills the"
                    + " south-east corner of the room.");
        LORE_MAP.put(
                "73",
                "Someone has scrawled 'In the Dominion of Tomes, when the Black Ferret is chained"
                    + " and light becomes shadow, the Adventurers of the Silver Wolf shall fall' on"
                    + " the north wall.");
        LORE_MAP.put(
                "74",
                "A magical statue in the south-east corner of the room speaks riddles and cryptic"
                    + " prophecies, and a metallic odor fills the south side of the room.");
        LORE_MAP.put(
                "75",
                "The south and east walls are covered with scorch marks, and a pile of bent copper"
                    + " coins lies in the center of the room.");
        LORE_MAP.put(
                "76",
                "A cube of solid stone stands in the east side of the room, and someone has"
                    + " scrawled  Mind the gap on the south wall. There is a health potion in the"
                    + " room.");
        LORE_MAP.put(
                "78",
                "Someone has scrawled 'The curse can never be broken' on the west wall, and the"
                    + " walls are covered with scorch marks.");
        LORE_MAP.put(
                "80",
                "A magical mosaic on the south wall depicts the betrayal of whomever views it, and"
                    + " sporadic knocking fills the room.");
        LORE_MAP.put(
                "81",
                "A rope ascends to a catwalk hanging between the north and south walls, and a faded"
                    + " and torn tapestry hangs from the north wall.");
        LORE_MAP.put(
                "82",
                "Numerous pillars line the west wall, and an iron sarcophagus sits in the south"
                    + " side of the room.");
        LORE_MAP.put(
                "85",
                "Someone has scrawled 'The Heroes of Bacot killed nine goblins here' on the east"
                    + " wall, and a sundered shield lies in the west side of the room.");
        LORE_MAP.put(
                "86",
                "A set of demonic war masks hangs on the south wall, and a crushed helm lies in the"
                    + " south-east corner of the room.");
        LORE_MAP.put(
                "88",
                "Someone has scrawled 'Don't lose your head' in blood on the south wall, and"
                    + " several pieces of rotting wood are scattered throughout the room.");

        // Floor 1
        LORE_MAP.put(
                "102",
                "The floor is covered in square tiles, alternating white and black, and a pile of"
                    + " spoiled meat lies in the south side of the room. There is a health potion"
                    + " in the room.");
        LORE_MAP.put(
                "103",
                "An iron chandelier hangs from the ceiling in the center of the room. A pentagram"
                    + " is carved in the floor. There is a key in the room.");
        LORE_MAP.put(
                "105",
                "The floor is covered in perfect hexagonal tiles, and several pieces of rotten"
                    + " bread are scattered throughout the room. There is a key in the room.");
        LORE_MAP.put(
                "106",
                "Someone has scrawled 'The Sapphire Foxes looted this place' on the west wall, and"
                    + " the ceiling is covered with cobwebs.");
        LORE_MAP.put(
                "108",
                "Someone has scrawled 'Death comes on silent wings' on the north wall, and numerous"
                    + " monstrous skulls lie within niches in the north and south walls.");
        LORE_MAP.put("109", "There is a key in the room.");
        LORE_MAP.put("110", "There is a key in the room.");
        LORE_MAP.put("115", "There is a key in the room.");
        LORE_MAP.put("116", "There is a key in the room.");
        LORE_MAP.put(
                "118",
                "The floor is covered in perfect hexagonal tiles, and iron chains hang from the"
                    + " ceiling in the south side of the room.");
        LORE_MAP.put("120", "There is a key in the room.");
        LORE_MAP.put(
                "122",
                "A set of demonic war masks hangs on the north wall, and lit candles are scattered"
                    + " across the floor.");
        LORE_MAP.put(
                "123",
                "A fountain decorated with five water-breathing dragon heads sits in the north-east"
                    + " corner of the room, and several corroded iron spikes are scattered"
                    + " throughout the room.");
        LORE_MAP.put(
                "124",
                "A wooden ladder rests against the north wall, and a pile of barrel staves lies in"
                    + " the west side of the room.");
        LORE_MAP.put(
                "125",
                "A ruined siege weapon sits in the west side of the room, and someone has scrawled"
                    + " 'Trespassers will be flayed alive' in blood on the north wall.");
        LORE_MAP.put("128", "There is a key in the room.");
        LORE_MAP.put("129", "There is a health potion in the room.");
        LORE_MAP.put(
                "134",
                "Spirals of red stones cover the floor, and a pile of bent copper coins lies in the"
                    + " south-east corner of the room.");
        LORE_MAP.put(
                "135",
                "The floor is covered in perfect hexagonal tiles, and mysterious levers and"
                    + " mechanisms cover the east and west walls.");
        LORE_MAP.put("136", "There is a key in the room.");
        LORE_MAP.put("137", "There is a key in the room.");
        LORE_MAP.put(
                "138",
                "A wooden platform hangs over a deep pit in the north-east corner of the room, and"
                    + " a sour odor fills the room.");
        LORE_MAP.put(
                "139",
                "A simple fireplace sits against the east wall, and the ceiling is covered with"
                    + " pale stalactites. There is a key in the room.");
        LORE_MAP.put("140", "There is a health potion in the room.");
        LORE_MAP.put(
                "141",
                "Someone has scrawled 'Upon the fourth day of the Autumn of Blood, when sea becomes"
                    + " sky, the Tempest of Lust shall be freed' on the north wall. There is a key"
                    + " in the room.");
        LORE_MAP.put(
                "142",
                "Ghostly wailing can be faintly heard near the east wall, and a shattered sword"
                    + " lies in the north-east corner of the room.");
        LORE_MAP.put(
                "143",
                "A wooden platform hangs over a deep pit in the west side of the room, and someone"
                    + " has scrawled a demonic face on the south wall.");
        LORE_MAP.put(
                "144",
                "A stack of crates filled with rocks stands against the south wall, and the south"
                    + " and west walls are covered with slime.");
        LORE_MAP.put(
                "145",
                "Lit candles are scattered across the floor, and the ceiling is covered with scorch"
                    + " marks.");
        LORE_MAP.put(
                "146",
                "The room has a high domed ceiling, and a pile of rotten apples lies in the"
                    + " south-east corner of the room.");
        LORE_MAP.put("147", "There is a key in the room.");
        LORE_MAP.put(
                "148",
                "A wooden ladder rests against the east wall, and a pile of torches lies in the"
                    + " north-east corner of the room. There is a key in the room.");
        LORE_MAP.put(
                "149",
                "A tile labyrinth covers the floor, and a pile of broken arrows lies in the north"
                    + " side of the room. There is a health potion in the room.");
        LORE_MAP.put(
                "150",
                "Someone has scrawled 'The axe is cursed' in dwarvish runes on the east wall, and a"
                    + " charred wooden chest lies in the south-east corner of the room. There is a"
                    + " key in the room.");
        LORE_MAP.put("152", "There is a health potion in the room.");
        LORE_MAP.put("154", "There is a key in the room.");
        LORE_MAP.put(
                "159",
                "The south and east walls have been engraved with geometric patterns, and a simple"
                    + " fireplace sits against the west wall. There is a key in the room.");
        LORE_MAP.put(
                "160",
                "An acrid odor fills the west side of the room, and a shattered sword lies in the"
                    + " west side of the room.");
        LORE_MAP.put("162", "There is a health potion in the room.");
        LORE_MAP.put(
                "163",
                "The room has a high domed ceiling, and a group of draconic faces have been carved"
                    + " into the south wall.");
        LORE_MAP.put("164", "There is a key in the room.");
        LORE_MAP.put(
                "167",
                "The north and west walls have been engraved with alien symbols, and several pieces"
                    + " of rotting wood are scattered throughout the room.");
        LORE_MAP.put("168", "There is a health potion in the room.");
        LORE_MAP.put(
                "169",
                "A faded and torn tapestry hangs from the south wall, and several pieces of"
                    + " blood-soaked clothing are scattered throughout the room.");
        LORE_MAP.put(
                "170",
                "A group of monstrous faces have been carved into the north wall, and a sundered"
                    + " amulet lies in the south side of the room.");
        LORE_MAP.put(
                "171",
                "A tile mosaic of ghoulish carnage covers the floor, and numerous humanoid skulls"
                    + " are scattered throughout the room. There is a health potion in the room.");
        LORE_MAP.put(
                "172",
                "A group of monstrous faces have been carved into the south wall, and several iron"
                    + " cages are scattered throughout the room.");
        LORE_MAP.put(
                "174",
                "The walls have been engraved with geometric patterns, and rusting iron spikes line"
                    + " the south wall. There is a key in the room.");

        // Floor 2
        LORE_MAP.put(
                "201",
                "The scent of smoke fills the room, and a charred wooden shield lies in the center"
                    + " of the room. There is a key in the room.");
        LORE_MAP.put(
                "202",
                "The south and east walls have been engraved with strange symbols, and several bent"
                    + " copper coins are scattered throughout the room.");
        LORE_MAP.put("204", "A set of demonic war masks hangs on the south wall.");
        LORE_MAP.put(
                "206",
                "Someone has scrawled 'There is nothing we can do' in goblin runes on the west"
                    + " wall, and mournful weeping can be heard in the north side of the room.");
        LORE_MAP.put("207", "There is a key in the room.");
        LORE_MAP.put(
                "209",
                "Several alcoves are cut into the north wall, and a ruined siege weapon sits in the"
                    + " south-east corner of the room.");
        LORE_MAP.put("210", "There is a key in the room.");
        LORE_MAP.put(
                "211",
                "Someone has scrawled There is no way out on the north wall, and a pile of spoiled"
                    + " meat lies in the west side of the room. There is a health potion in the"
                    + " room.");
        LORE_MAP.put("212", "There is a health potion in the room.");
        LORE_MAP.put(
                "213",
                "A mural of a god of trickery covers the ceiling, and someone has scrawled 'Death"
                    + " is the only exit' on the south wall. There is a health potion in the"
                    + " room.");
        LORE_MAP.put(
                "214",
                "A well lies in the east side of the room, and a simple fireplace sits against the"
                    + " north wall. There is a key in the room.");
        LORE_MAP.put(
                "216",
                "A shallow pit lies in the south-east corner of the room, and the walls are covered"
                    + " with sword cuts.");
        LORE_MAP.put(
                "218",
                "Clouds of flying insects fill the south-west corner of the room, and a pile of"
                    + " rotting wood lies in the east side of the room.");
        LORE_MAP.put("220", "There is a key in the room.");
        LORE_MAP.put(
                "222",
                "The floor is covered in perfect hexagonal tiles, and a scratching sound can be"
                    + " faintly heard near the west wall.");
        LORE_MAP.put("223", "There is a key in the room.");
        LORE_MAP.put(
                "225",
                "A set of demonic war masks hangs on the east wall, and someone has scrawled"
                    + " 'Sigoaro died here' in blood on the east wall. There is a key in the"
                    + " room.");
        LORE_MAP.put("226", "There is a key in the room.");
        LORE_MAP.put(
                "228",
                "A magical shrine of a god of dwarves in the east side of the room heals all wounds"
                    + " of whomever sacrifices a magical item upon it (but only once). There is a"
                    + " key in the room.");
        LORE_MAP.put(
                "229",
                "A stone stair ascends towards the north wall, and several pieces of rotten rope"
                    + " are scattered throughout the room. There is a health potion in the room.");
        LORE_MAP.put("231", "A sundered helm lies in the north-west corner of the room.");
        LORE_MAP.put("232", "There is a key in the room.");
        LORE_MAP.put("236", "There is a key in the room.");
        LORE_MAP.put(
                "237",
                "A forge and anvil sit in the south-west corner of the room, and a corroded key"
                    + " lies in the south side of the room.");
        LORE_MAP.put(
                "238",
                "A wooden platform hangs over a deep pit in the north-east corner of the room, and"
                    + " someone has scrawled 'Twist the cog to reset the trap' on the west wall.");
        LORE_MAP.put(
                "240",
                "Someone has scrawled 'We've run out of swords' on the west wall, and several"
                    + " shattered weapons are scattered throughout the room.");
        LORE_MAP.put(
                "241",
                "Someone has scrawled a basic map of the dungeon on the south wall, and a rusted"
                    + " gauntlet lies in the south-west corner of the room. There is a key in the"
                    + " room.");
        LORE_MAP.put(
                "242",
                "Someone has scrawled 'Ran out of arrows' in blood on the east wall, and a rusted"
                    + " gauntlet lies in the south-east corner of the room. There is a key in the"
                    + " room.");
        LORE_MAP.put(
                "243",
                "A set of demonic war masks hangs on the east wall, and the sound of drums can be"
                    + " heard in the west side of the room. There is a key in the room.");
        LORE_MAP.put("244", "There is a key in the room.");
        LORE_MAP.put("245", "There is a key in the room.");
        LORE_MAP.put(
                "246",
                "Someone has scrawled Who took my dwarf skull in goblin runes on the north wall,"
                    + " and a hole has been blasted into the east wall. There is a key in the"
                    + " room.");
        LORE_MAP.put(
                "247",
                "Someone has scrawled a large X on the south wall, and a hissing noise can be heard"
                    + " in the north-west corner of the room. There is a health potion.");
        LORE_MAP.put(
                "249",
                "A ladder ascends to a balcony hanging from the south wall, and a shallow pool of"
                    + " water lies in the north-west corner of the room.");
        LORE_MAP.put("251", "There is a health potion in the room.");
        LORE_MAP.put(
                "254",
                "Spirals of green stones cover the floor, and the south and west walls are covered"
                    + " with veins of metal.");
        LORE_MAP.put("255", "There is a key in the room.");
        LORE_MAP.put("256", "There is a key in the room.");
        LORE_MAP.put(
                "259",
                "Someone has scrawled The Cohort of Zazer looted this place on the north wall, and"
                    + " an iron chain hangs from the ceiling in the north-west corner of the room."
                    + " There is a key in the room.");
        LORE_MAP.put("260", "There is a key in the room.");
        LORE_MAP.put(
                "263",
                "Various torture devices are scattered throughout the room, and someone has"
                    + " scrawled 'Willes Grysor fell here, his luck ran out before his arrows' on"
                    + " the east wall.");
        LORE_MAP.put(
                "264",
                "Someone has scrawled 'Don't lose your head' on the south wall, and several wax"
                    + " blobs are scattered throughout the room.");
        LORE_MAP.put(
                "269",
                "Skeletons hang from chains and manacles against the east and west walls, and"
                    + " rusting iron spikes line the north wall.");
        LORE_MAP.put(
                "271",
                "A cube of solid stone stands in the south-east corner of the room, and someone has"
                    + " scrawled 'Beware the basilisk' on the south wall.");
        LORE_MAP.put(
                "273",
                "A chute falls into the room from above, and someone has scrawled 'left, straight,"
                    + " door, right, straight' on the east wall. There is a health potion in the"
                    + " room.");
        LORE_MAP.put(
                "274",
                "A stone sarcophagus sits in the north-west corner of the room, and a pile of"
                    + " rotten rope lies in the south- east corner of the room.");
        LORE_MAP.put(
                "275",
                "A wooden platform hangs over a deep pit in the center of the room, and a tapestry"
                    + " of a legendary battle hangs from the west wall.");
        LORE_MAP.put(
                "276",
                "A tile mosaic of a legendary battle covers the floor, and someone has scrawled a"
                    + " monstrous face on the west wall.");
        LORE_MAP.put(
                "277",
                "A faded and torn tapestry hangs from the east wall, and burning torches in iron"
                    + " sconces line the north and east walls.");
        LORE_MAP.put("279", "There is a health potion in the room.");
        LORE_MAP.put("281", "There is a key in the room.");
        LORE_MAP.put(
                "282",
                "The floor is covered in square tiles, alternating white and black, and someone has"
                    + " scrawled a drawing of a castle on the east wall.");
        LORE_MAP.put(
                "283",
                "Spirals of blue stones cover the floor, and a ruined siege weapon sits in the"
                    + " south side of the room.");

        // Floor 3
        LORE_MAP.put(
                "302",
                "A tile mosaic of a god of illusions covers the floor, and a broken spear lies in"
                    + " the west side of the room.");
        LORE_MAP.put(
                "304",
                "A foul odor fills the north side of the room, and a sundered helm lies in the east"
                    + " side of the room.");
        LORE_MAP.put(
                "305",
                "Several alcoves are cut into the south and east walls, and a pile of rotten"
                    + " leather lies in the north- east corner of the room.");
        LORE_MAP.put("307", "There is a key in the room.");
        LORE_MAP.put(
                "308",
                "A mural of ghoulish carnage covers the ceiling, and the south and west walls are"
                    + " covered with veins of metal.");
        LORE_MAP.put("309", "There is a key in the room.");
        LORE_MAP.put("310", "There is a key in the room.");
        LORE_MAP.put(
                "311",
                "Someone has scrawled 'The walls listen' on the south wall, and a whistling noise"
                    + " can be heard in the center of the room. There is a key in the room.");
        LORE_MAP.put(
                "312",
                "A tapestry of arcane patterns hangs from the south wall, and burning torches in"
                    + " iron sconces line the south and west walls.");
        LORE_MAP.put("314", "There is a health potion in the room.");
        LORE_MAP.put(
                "315",
                "Someone has scrawled 'You cannot kill it with wizardry' in draconic script on the"
                    + " west wall, and several adventurer corpses are scattered throughout the"
                    + " room. There is a health potion in the room.");
        LORE_MAP.put("316", "There is a health potion in the room.");
        LORE_MAP.put(
                "318",
                "A mural of a goddess of illusions covers the ceiling, and the floor is covered"
                    + " with mud.");
        LORE_MAP.put(
                "320",
                "The floor is covered with dead insects, and a pile of rotten apples lies in the"
                    + " north-east corner of the room. There is a key in the room.");
        LORE_MAP.put(
                "323",
                "Several square holes are cut into the east and west walls, and someone has"
                    + " scrawled 'Good spot for trap' in goblin runes on the west wall. There is a"
                    + " key in the room.");
        LORE_MAP.put(
                "324",
                "A set of demonic war masks hangs on the west wall, and someone has scrawled 'The"
                    + " gold dragon is not a dragon' in blood on the north wall.");
        LORE_MAP.put("325", "There is a key in the room.");
        LORE_MAP.put(
                "327",
                "Several alcoves are cut into the west wall, and a putrid odor fills the room."
                    + " There is a key in the room.");
        LORE_MAP.put(
                "328",
                "Several square holes are cut into the ceiling and floor, and chanting can be"
                    + " faintly heard near the north wall. There is a key in the room.");
        LORE_MAP.put(
                "329",
                "A tile mosaic of a legendary battle covers the floor, and a putrid odor fills the"
                    + " center of the room.");
        LORE_MAP.put(
                "331",
                "A carved stone statue stands in the south-west corner of the room, and someone has"
                    + " scrawled 'You cannot kill it with swords' in draconic script on the east"
                    + " wall.");
        LORE_MAP.put("332", "There is a health potion in the room.");
        LORE_MAP.put(
                "333",
                "Someone has scrawled 'The gold is cursed' on the north wall, and a ruined gauntlet"
                    + " lies in the west side of the room. There is a health potion in the room.");
        LORE_MAP.put("334", "There is a health potion in the room.");
    }

    public String[] getIntroMessages() {
        return INTRO_MESSAGES;
    }

    public String[] getFinalStageMessages() {
        return FINAL_STAGE;
    }

    public String getLoreText(String roomCode) {
        return LORE_MAP.get(roomCode);
    }

    public String getRandomSecretDoorText() {
        String[] texts = {
            "The wall in front of you feels weird, you try to touch it, the wall moves, it was a"
                + " secret door!!",
            "You see a door knob on the wall, you turn it, the wall move to reveal a secret"
                + " pathway.",
            "You see circular trace in front of this wall, you try to pull the wall in the"
                + " direction of the trace, a secret door opens.",
            "The wall start moving, opening a secret pathway, you hide behind it, a goblin walk out"
                + " of it, you sneak past him into the new path."
        };
        return texts[rand.nextInt(texts.length)];
    }

    public String getRandomTrapDoorDeathText() {
        String[] texts = {
            "When opening the door, a mechanism triggers, and the door blow up in your face. You"
                + " died",
            "The door knob seems to be stiff, you break the door open, when the door opens, a"
                + " goblin stands behind it, jump on you and start eating your face. You died",
            "You open the door, a string was connected to it, a blade come out of the wall and cut"
                + " your head off. You died",
            "You barely got time to turn the door knob that a giant sledge hammer hits you in the"
                + " back of the head, bursting it open. You died"
        };
        return texts[rand.nextInt(texts.length)];
    }

    public String getRandomTrapDoorSurviveText() {
        String[] texts = {
            "When opening the door, a mechanism triggers, your reflex saves you, you jump back"
                + " avoiding the blast. -50HP",
            "The door knob seems to be stiff, you break the door open, when the door opens, a"
                + " goblin stands behind it, you react sufficiently fast and stab him when he jump"
                + " on you. -50HP",
            "You open the door, a string was connected to it, a blade come out of the wall, you"
                + " manage to avoid it. -50HP",
            "You barely got time to turn the door knob that a giant sledge hammer swing down on"
                + " you. You crouch to avoid it. -50HP"
        };
        return texts[rand.nextInt(texts.length)];
    }

    public String getTrapText(TrapType trap, boolean dies, int variant) {
        String[][] trapTexts = getTrapTextArray(trap);
        return trapTexts[dies ? 0 : 1][variant];
    }

    private String[][] getTrapTextArray(TrapType trap) {
        return switch (trap) {
            case POISON ->
                    new String[][] {
                        {
                            "You hit a tripwire, poisoning insects fall on you, you try to get them"
                                + " off but one manages to bite you. You died",
                            "You hit a pressure plate, a troll fall from it, you try to fight him,"
                                + " but he takes the upper hand and smash your head with his"
                                + " weapons. You died",
                            "Out of the blue, a zombie pop out, bits you, and run away, you try to"
                                + " suck out the saliva, without success. You 'kinda' died",
                            "A tarantula climbs silently onto your shoulder, sneaking her way to"
                                + " your neck, you don't feel a thing, she bits you. You died"
                        },
                        {
                            "You hit a tripwire, poisoning insects falls on you, you successfully"
                                + " get them off you. -25HP",
                            "You hit a pressure plate, a troll fall from it, you engage into a"
                                + " fierce combats whit him, you take the upper hand and stick you"
                                + " sword in his skulls. -25HP",
                            "Out of the blue, a zombie pop out, try to bits you. Your reflex save"
                                + " you, you swing your sword and cut his head clean off. -25HP",
                            "A tarantula climb silently onto your shoulder, sneaking her way to"
                                + " your neck, you realise that, and throw her into the wall like a"
                                + " baseball. -25HP"
                        }
                    };

            case SPIKE ->
                    new String[][] {
                        {
                            "You walk on a pressure plate, and the floor crumbles under your feet,"
                                + " revealing spikes, you end up impaled. You died",
                            "The tile you just stepped in breaks under your weight, rotating blade"
                                + " where hidden underneath, and you get shredded to pieces. You"
                                + " died",
                            "You step on a rug, a hole is hidden by it. you fall into a pit of"
                                + " spiders. You get scared and have a heart attack. You died",
                            "You walk on a weighted tile, and spikes rise through the flour,"
                                + " cutting holes in you. You died"
                        },
                        {
                            "You walk on a pressure plate, and the floor crumbles under your feet,"
                                + " revealing spikes, you manage to grab the ledge. -75HP",
                            "The tile you just stepped in break under your weight, the rotating"
                                + " blades where hidden underneath, and the blades where off."
                                + " -75HP",
                            "You step on a rug, a hole is hidden by it. you fall into a pit of"
                                + " spiders. You brandish your sword to drive them away and climb"
                                + " out of the hole. -75HP",
                            "You walk on a weighted tile, and spikes rise through the flour, by"
                                + " pure luck, not a single one hits you. -75HP"
                        }
                    };

            case FALLING ->
                    new String[][] {
                        {
                            "The floor starts to shake violently, and a tile from the ceiling break"
                                + " and drop on you, it hits your head and shatter your skull. You"
                                + " died",
                            "You see a vase on the side of the corridor, you pick it up, and a"
                                + " mechanism triggers. Spikes fall on you, and you try to avoid"
                                + " them but one falls right trough you. You died",
                            "You hit a tripwire on the floor, nothing happens then suddenly, spikes"
                                + " fall on you, you try to roll away, to no avail, and one hit you"
                                + " mid-roll in the back. You died",
                            "You see a lever on the wall, curious, you pull it, and gold falls on"
                                + " you, out of nowhere a giant brick falls on you and bursts your"
                                + " thorax. You died"
                        },
                        {
                            "The floor starts to shake violently, and a tile from the ceiling"
                                + " breaks and drops on you, you manage to avoid it. -40HP",
                            "You see a vase on the side of the corridor, you pick it up, and a"
                                + " mechanism triggers. Spikes fall on you, you successfully avoid"
                                + " all of them. -40HP",
                            "You hit a tripwire on the floor, nothing happens then suddenly, spikes"
                                + " fall on you, you do the best somersault ever, and avoid them"
                                + " all. -40HP",
                            "You see a lever on the wall, curious, you pull it, gold falls on you,"
                                + " then out of nowhere a giant brick falls on you. You jump on the"
                                + " side to avoid it. -40HP"
                        }
                    };

            case PROJECTILE ->
                    new String[][] {
                        {
                            "You walk on a pressure plate, arrows come out of holes in the wall, to"
                                + " make other holes in your body. You died",
                            "You trigger a tripwire, you hear gears in the wall, suddenly spikes"
                                + " come out of the wall, turning you into Swiss cheese. You died",
                            "An arrow falls on you, when you pick it up you realize it is poisoned,"
                                + " a few second later you convulse on the floor. You died",
                            "A skeleton dropped from the ceiling scaring you to death. You died"
                        },
                        {
                            "You walk on a pressure plate, arrows come out of holes in the wall,"
                                + " and your dance class helps you avoid them. -25HP",
                            "You trigger a tripwire, you hear gears in the wall, suddenly spikes"
                                + " come out of the wall, luckily they stopped right in front of"
                                + " your nose. -25HP",
                            "An arrow falls on you, when you pick it up you realize it is poisoned,"
                                + " you quickly suck the poison out. -25HP",
                            "A skeleton dropped from the ceiling, scared, you punch the skeleton"
                                + " with everything you've got. -25HP"
                        }
                    };

            case WALL ->
                    new String[][] {
                        {
                            "Your foot gets stuck between two tiles, and suddenly the walls around"
                                + " you start to shrink into you, you try to escape but you slowly"
                                + " get squeezed to death. You died",
                            "You curiously pull the lever that's sticking to the wall, you don't"
                                + " even have time to react the walls shrink in an instant, and you"
                                + " with it. You died",
                            "A werewolf appears from the corridor. You attempt to escape, but he"
                                + " grabs you and throws you into a wall, splattering it with your"
                                + " blood. You died",
                            "You walked a pressure plate, the tile you were in springs you out into"
                                + " the ceiling crushes you like a soda can. You died"
                        },
                        {
                            "Your foot gets stuck between two tiles, and suddenly the walls around"
                                + " you start to shrink into you, you manage to free your feet and"
                                + " escape the wall. -90HP",
                            "You curiously pull the lever that's sticking to the wall, by fear the"
                                + " moment you pull the lever, you jump away, and the wall shrinks"
                                + " in an instant behind you. -90HP",
                            "A werewolf appears from the corridor. You successfully avoid his first"
                                + " attack and stab him in the back. -90HP",
                            "You walked a pressure plate, the tile you stepped in spring out, but"
                                + " luckily you managed to avoid it. -90HP"
                        }
                    };

            case INSTANT_DEATH ->
                    new String[][] {
                        {
                            "You hit a tripwire, and four axes pop out the walls, cutting you into"
                                + " five equal pieces. You died",
                            "You step on a pressure plate, and two giant sledgehammers swing down"
                                + " from the ceiling bashing your skulls to shred. You died",
                            "A blade swings down from the ceiling making a perfect hole in your"
                                + " head. You died",
                            "A black knight appears in the corridor. Despite your attempts to"
                                + " defend yourself, it's to no avail. With one swift swing of his"
                                + " long sword, he cuts your head right off. You died."
                        },
                        {
                            "You hit a tripwire, and four axes pop out the walls, cutting you into"
                                + " five equal pieces. You died",
                            "You step on a pressure plate, and two giant sledgehammers swing down"
                                + " from the ceiling bashing your skulls to shred. You died",
                            "A blade swings down from the ceiling making a perfect hole in your"
                                + " head. You died",
                            "A black knight appears in the corridor. Despite your attempts to"
                                + " defend yourself, it's to no avail. With one swift swing of his"
                                + " long sword, he cuts your head right off. You died."
                        }
                    };

            case HOLE ->
                    new String[][] {
                        {
                            "You step on a rug in the middle of the corridor, it makes you fall"
                                + " into the previous floor, your legs go into your body. You died",
                            "You whistle into the long corridor of the dungeon, you don't pay"
                                + " attention to the hole in the floor, you fall and break your"
                                + " legs, an ogre passing by finish the job. You died",
                            "You step on a plank in the middle of the corridor, it snaps under your"
                                + " feet when hitting the floor, the plank hits your head. You"
                                + " died",
                            "You trip on a tile and fall in a hole in the middle of the corridor,"
                                + " you fall into the hole head first and break your neck. You died"
                        },
                        {
                            "You step on a rug in the middle of the corridor, it makes you fall"
                                + " into the previous floor. -20HP",
                            "You whistle into the long corridor of the dungeon, you don't pay"
                                + " attention to the hole in the floor, you fall into the previous"
                                + " floor. -20HP",
                            "You step on a plank in the middle of the corridor, it snaps under your"
                                + " feet, and you fall into the previous floor. -20HP",
                            "You trip on a tile and fall into a hole in the middle of the corridor,"
                                + " you fall into the previous floor. -20HP"
                        }
                    };

            case PORTCULLIS ->
                    new String[][] {
                        {
                            "You pass under a portcullis, you feel anxious passing under those"
                                + " spikes.",
                            "When passing under the portcullis, you hear a click, you quickly jump"
                                + " forward and avoid the spikes of the gate. The gate comes back"
                                + " up a moment later",
                            "The portcullis in front of you feel scary, by fear, you run to pass"
                                + " it, the gate didn't budge tho.",
                            "Scared by the spikes of the portcullis you throw something underneath,"
                                + " the gate quickly closes on it, and when it goes back up, you"
                                + " safely roll underneath."
                        },
                        {
                            "You pass under a portcullis, you feel anxious passing under those"
                                + " spikes.",
                            "When passing under the portcullis, you hear a click, you quickly jump"
                                + " forward and avoid the spikes of the gate. The gate comes back"
                                + " up a moment later",
                            "The portcullis in front of you feel scary, by fear, you run to pass"
                                + " it, the gate didn't budge tho.",
                            "Scared by the spikes of the portcullis you throw something underneath,"
                                + " the gate quickly closes on it, and when it goes back up, you"
                                + " safely roll underneath."
                        }
                    };
        };
    }

    public int getRandomVariant(int maxVariants) {
        return rand.nextInt(maxVariants);
    }
}
