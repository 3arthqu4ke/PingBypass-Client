package me.earth.earthhack.impl.event.events.keyboard;

import me.earth.earthhack.api.event.events.Event;

public class ClickMiddleEvent extends Event
{
    private boolean moduleCancelled;

    public void setModuleCancelled(boolean cancelled)
    {
        this.moduleCancelled = cancelled;
    }

    public boolean isModuleCancelled()
    {
        return moduleCancelled;
    }

}
