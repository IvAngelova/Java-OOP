package T06_SOLID.Logger.models;

import T06_SOLID.Logger.interfaces.File;
import T06_SOLID.Logger.interfaces.Layout;

public class FileAppender extends BaseAppender {

    private File file;

    public FileAppender(Layout layout) {
        super(layout);
        this.file = new LogFile();
    }

    public void setFile(File file){
        this.file = file;
    }

    @Override
    protected void append(String text) {
        this.file.write(text);
    }


    @Override
    public String toString() {
        return String.format("%s, File size: %d", super.toString(), this.file.getSize());
    }

}
