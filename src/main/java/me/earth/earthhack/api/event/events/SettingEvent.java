package me.earth.earthhack.api.event.events;

import me.earth.earthhack.api.setting.Setting;

public class SettingEvent<T> extends Event
{
    private final Setting<T> setting;
    private T value;

    public SettingEvent(Setting<T> setting, T value)
    {
        this.setting = setting;
        this.value = value;
    }

    public Setting<?> getSetting()
    {
        return setting;
    }

    public T getValue()
    {
        return value;
    }

    public void setValue(T value)
    {
        this.value = value;
    }

}
