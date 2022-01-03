package T06_SOLID.Logger.interfaces;

import T06_SOLID.Logger.enums.ReportLevel;

public interface Appender {
    void appendMessage(String dateTme, ReportLevel reportLevel, String message);

    void setReportLevel(ReportLevel reportLevel);
}
