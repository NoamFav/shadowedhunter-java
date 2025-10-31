package com.shadowedhunter.resources;

public class DialogTexts {
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

    private static final String[] FINAL_STAGE_MESSAGES = {
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

    public static String[] getIntroMessages() {
        return INTRO_MESSAGES.clone();
    }

    public static String[] getFinalStageMessages() {
        return FINAL_STAGE_MESSAGES.clone();
    }
}
