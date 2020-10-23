package me.earth.earthhack.api.setting.settings;

import com.google.gson.JsonElement;
import me.earth.earthhack.api.setting.Setting;
import me.earth.earthhack.api.util.Bind;
import org.lwjgl.input.Keyboard;

public class BindSetting extends Setting<Bind>
{
    public BindSetting(String nameIn, Bind initialValue)
    {
        super(nameIn, initialValue);
    }

    @Override
    public void fromJson(JsonElement element)
    {
        this.fromString(element.getAsString());
    }

    @Override
    public boolean fromString(String string)
    {
        if ("none".equalsIgnoreCase(string))
        {
            value.setKey(-1);
        }
        else
        {
            this.setValue(Bind.fromString(string));
        }

        return true;
    }

    @Override
    public String getInputs(String string)
    {
        if (string == null || string.isEmpty())
        {
            return "<key>";
        }

        if ("none".startsWith(string.toLowerCase()))
        {
            return "none";
        }
        else
        {
            for (int i = 0; i < Keyboard.KEYBOARD_SIZE; i++)
            {
                String keyName = Keyboard.getKeyName(i);
                if (keyName != null && keyName.toLowerCase().startsWith(string.toLowerCase()))
                {
                    return keyName;
                }
            }
        }

        return "";
    }

    public void setKey(int key)
    {
        value.setKey(key);
    }

}
