package com.shadowedhunter.world.traps.traps;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.resources.TrapMessages;
import com.shadowedhunter.world.traps.Trap;
import com.shadowedhunter.world.traps.TrapType;

public class PortcullisTrap extends Trap {
    public PortcullisTrap() {
        super(TrapType.PORTCULLIS);
    }

    @Override
    public String trigger(GameEngine engine) {
        // Portcullis doesn't damage, just messages
        return TrapMessages.getPortcullisMessage(false, random.nextInt(4));
    }
}
