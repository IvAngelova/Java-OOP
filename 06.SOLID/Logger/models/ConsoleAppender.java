package T06_SOLID.Logger.models;

import T06_SOLID.Logger.interfaces.Layout;


public class ConsoleAppender extends BaseAppender {

    public ConsoleAppender(Layout layout) {
        super(layout);
    }

    @Override
    protected void append(String text) {
        System.out.println(text);
    }


}
