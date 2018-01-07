package com.sajjanm.ingarm.init;

import com.sajjanm.ingarm.init.util.ProcessExcel;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author Sajjan Mishra
 */
@Singleton
@Startup
public class DirectoryWatcher {
    
    @Inject
    ProcessExcel processExcel;

    @PostConstruct
    public void init() {
        
        Thread newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                watch();
                try {
                    this.wait(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DirectoryWatcher.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        newThread.start();
    }

    public void watch() {
        System.out.println("@Startup Function being called ");
        System.out.println("Inside the watch class!");

        try {
            WatchService watcher = FileSystems.getDefault().newWatchService();
//             for windows dev mode on
//            java.nio.file.Path dir = Paths.get("E:/Test");
            java.nio.file.Path dir = Paths.get("/data/excel-input");
            System.out.println("now watching /data/excel-input");

            dir.register(watcher, ENTRY_CREATE);

            System.out.println("Watch Service registered for dir: " + dir.getFileName());

            while (true) {
                WatchKey key = null;
                try {
                    key = watcher.take();
                } catch (InterruptedException ex) {
                    Logger.getLogger(DirectoryWatcher.class.getName()).log(Level.SEVERE, null, ex);
                }

                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();

                    @SuppressWarnings("unchecked")
                    WatchEvent<java.nio.file.Path> ev = (WatchEvent<java.nio.file.Path>) event;
                    java.nio.file.Path fileName = ev.context();

                    // Displays event type and respective file name
                    System.out.println(kind.name() + ": " + fileName);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(DirectoryWatcher.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String extension = "";
                    int i = fileName.toString().lastIndexOf('.');
                    if (i > 0) {
                        extension = fileName.toString().substring(i + 1);
                    }
                    System.out.println("The File Extension was :  " + extension);

                    if (extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx")) {
                        //read excel File

//                        System.out.println(processExcel.process(dir.toString() + "/" + fileName.toString() ));
                        processExcel.process(dir.toString() + "/" + fileName.toString());
//                        System.out.println(new ProcessExcel().process(dir.toString() + "\\" + fileName.toString()));
                        deleteFile(dir, fileName);
                    } else {
                        deleteFile(dir, fileName);
                    }

                }

                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DirectoryWatcher.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } catch (IOException ex) {
            System.err.println(ex);
        }

    }

    private static void deleteFile(java.nio.file.Path dir, java.nio.file.Path fileName) {
        try {
            String tempFilename = dir.toString() + "/" + fileName.toString();
            System.out.println("Deleting   " + tempFilename);
            Files.delete(Paths.get(tempFilename));
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", fileName);
        } catch (DirectoryNotEmptyException x) {
            System.err.format("%s not empty%n", fileName);
        } catch (IOException x) {
            // File permission problems are caught here.
            System.err.println(x);
        }

    }
}
