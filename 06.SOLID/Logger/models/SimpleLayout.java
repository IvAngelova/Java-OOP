package T06_SOLID.Logger.models;

import T06_SOLID.Logger.interfaces.Layout;

public class SimpleLayout implements Layout {

    @Override
    public String getLayout() {
        return "%s - %s - %s";
    }
}
