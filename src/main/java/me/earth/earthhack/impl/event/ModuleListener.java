package me.earth.earthhack.impl.event;

import me.earth.earthhack.api.event.bus.EventListener;
import me.earth.earthhack.api.event.bus.api.Listener;
import me.earth.earthhack.api.util.Globals;

/**
 * Convenience class, wraps an Object with
 * an event listener so we don't have to add
 * the object as field to every eventListener.
 *
 * Constructor of every ModuleListener should be protected.
 *
 * @param <M> the Object type.
 * @param <E> the Event type.
 */
public abstract class ModuleListener<M, E> extends EventListener<E> implements Globals, Listener<E>
{
    /** The wrapped object. */
    protected final M module;

    public ModuleListener(M module, Class<? super E> target)
    {
        this(module, target, 10);
    }

    public ModuleListener(M module, Class<? super E> target, int priority)
    {
        this(module, target, priority, null);
    }

    public ModuleListener(M module, Class<? super E> target, Class<?> type)
    {
        this(module, target, 10, type);
    }

    public ModuleListener(M module, Class<? super E> target, int priority, Class<?> type)
    {
        super(target, priority, type);
        this.module = module;
    }

}
