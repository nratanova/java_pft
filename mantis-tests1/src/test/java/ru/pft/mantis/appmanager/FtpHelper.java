package ru.pft.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Natalya on 24.03.2017.
 */
public class FtpHelper {
    private final ApplicationManager app;
    private FTPClient ftp;

    public FtpHelper(ApplicationManager app) {
        this.app = app;
        ftp = new FTPClient();
    }

    //Метод загружает новый файл и переименовывает старый
    //file файл, target - имя файла, backup - имя резервного файла
    public void upload(File file, String target, String backup) throws IOException {
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        //Удаление резервной копии
        ftp.deleteFile(backup);
        //Переименование уд.файла, делаем резервную копию
        ftp.rename(target, backup);
        //Режим передачи данных
        ftp.enterLocalPassiveMode();
        //Передача файла
        ftp.storeFile(target, new FileInputStream(file));
        ftp.disconnect();
    }

    //Метод восстанавливает старый файл
    public void restore(String backup, String target) throws IOException {
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        ftp.deleteFile(target);
        ftp.rename(backup, target);
        ftp.disconnect();
    }
}
