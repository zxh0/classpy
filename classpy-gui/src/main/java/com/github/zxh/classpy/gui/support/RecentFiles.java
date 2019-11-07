package com.github.zxh.classpy.gui.support;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Recent open file list.
 */
public class RecentFiles {

    public static final RecentFiles INSTANCE = new RecentFiles();


    private final LinkedList<RecentFile> list = new LinkedList<>();
    private boolean listChanged = false;

    private RecentFiles() {
        loadFromTmp();
        Runtime.getRuntime()
                .addShutdownHook(new Thread(this::saveToTmp));
    }

    public File getLastOpenFile(FileType ft) {
        for (RecentFile rf : list) {
            if (rf.type == ft && rf.url.startsWith("file")) {
                try {
                    return new File(new URL(rf.url).toURI());
                } catch (MalformedURLException | URISyntaxException e) {
                    e.printStackTrace(System.err);
                }
            }
        }

        return null;
    }

    public List<RecentFile> getAll() {
        return list;
    }

    public void add(FileType fileType, String fileURL) {
        add(new RecentFile(fileType, fileURL));
    }

    private void add(RecentFile rf) {
        listChanged = true;
        list.remove(rf);
        list.addFirst(rf);
        // todo
        if (list.size() > 20) {
            list.removeLast();
        }
    }

    private void saveToTmp() {
        if (!list.isEmpty() && listChanged) {
            byte[] bytes = list.stream()
                    .map(RecentFile::toString)
                    .collect(Collectors.joining("\n"))
                    .getBytes(StandardCharsets.UTF_8);

            Path tmp = Paths.get(System.getProperty("java.io.tmpdir"), "classpy.tmp");
            System.out.println("saving " + tmp + " ...");
            try {
                Files.write(tmp, bytes);
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
        }
    }

    private void loadFromTmp() {
        Path tmp = Paths.get(System.getProperty("java.io.tmpdir"), "classpy.tmp");
        if (Files.exists(tmp)) {
            System.out.println("loading " + tmp + " ...");
            try {
                List<String> rfs = Files.readAllLines(tmp, StandardCharsets.UTF_8);
                for (String rf : rfs) {
                    if (rf.contains("#=>")) {
                        list.addLast(new RecentFile(rf));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
        }
    }

}
