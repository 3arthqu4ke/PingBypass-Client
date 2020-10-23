package me.earth.earthhack.api.module;

import me.earth.earthhack.api.event.bus.api.Listener;
import me.earth.earthhack.api.event.bus.api.Subscriber;
import me.earth.earthhack.api.event.bus.instance.Bus;
import me.earth.earthhack.api.module.data.DefaultData;
import me.earth.earthhack.api.module.data.ModuleData;
import me.earth.earthhack.api.setting.Setting;
import me.earth.earthhack.api.setting.settings.BindSetting;
import me.earth.earthhack.api.setting.settings.BooleanSetting;
import me.earth.earthhack.api.util.Bind;
import me.earth.earthhack.api.util.Globals;
import me.earth.earthhack.api.util.TextColor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A module.
 *
 * All modules of the client follow the singleton pattern.
 */
public abstract class Module extends Hideable implements Globals, Subscriber
{
    protected final List<Listener<?>> listeners = new ArrayList<>();
    private final Setting<Boolean> enabled;
    private final Setting<Bind> bind;
    private final Category category;
    private ModuleData data;

    public Module(String name, Category category)
    {
        super(name);
        this.bind     = register(new BindSetting("Bind", Bind.none()));
        this.enabled  = register(new BooleanSetting("Enabled", false));
        this.category = category;
        this.data     = new DefaultData<>(this);
    }

    public final void toggle()
    {
        if (isEnabled())
        {
            disable();
        }
        else
        {
            enable();
        }
    }

    public final void enable()
    {
        if (!isEnabled())
        {
            enabled.setValue(true);
            onEnable();
            if (isEnabled()) //recheck if we disabled in onEnable.
            {
                Bus.EVENT_BUS.subscribe(this);
            }
        }
    }

    public final void disable()
    {
        if (isEnabled())
        {
            enabled.setValue(false);
            onDisable();
            if (!isEnabled()) //recheck if we enabled in onDisable.
            {
                Bus.EVENT_BUS.unsubscribe(this);
            }
        }
    }

    public final void load()
    {
        if (this.isEnabled() && !Bus.EVENT_BUS.isSubscribed(this))
        {
            Bus.EVENT_BUS.subscribe(this);
        }

        onLoad();
    }

    public final boolean isEnabled()
    {
        return enabled.getValue();
    }

    public String getDisplayInfo()
    {
        return null;
    }

    public final String getHudName()
    {
        return this.getDisplayName()
                + (this.getDisplayInfo() == null
                    ? ""
                    : TextColor.GRAY
                        + " [" + TextColor.RESET
                        + this.getDisplayInfo()
                        + TextColor.GRAY + "]");
    }

    public Category getCategory()
    {
        return category;
    }

    public ModuleData getData()
    {
        return data;
    }

    public Bind getBind()
    {
        return bind.getValue();
    }

    protected void onEnable()
    {
        /* Implemented by the module */
    }

    protected void onDisable()
    {
        /* Implemented by the module */
    }

    protected void onLoad()
    {
        /* Implemented by the module */
    }

    protected void setData(ModuleData data)
    {
        this.data = data;
    }

    @Override
    public Collection<Listener<?>> getListeners()
    {
        return listeners;
    }

}
