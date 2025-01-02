package megameklab.ui;

public interface FileNameManager {
    String getFileName();
    boolean hasEntityNameChanged();
    void setFileName(String fileName);
}
