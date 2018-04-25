package com.jason.rall.vtube.utils;

import org.mp4parser.IsoFile;
import org.mp4parser.boxes.iso14496.part12.MovieBox;
import org.mp4parser.boxes.iso14496.part12.MovieHeaderBox;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;

/**
 * @author jrall
 */
public class MP4Utils {

    public static MovieHeaderBox parseMetadata(Path path) throws IOException {
        FileChannel fc = new FileInputStream(path.toFile()).getChannel();
        IsoFile isoFile = new IsoFile(fc);
        MovieBox moov = isoFile.getMovieBox();
        MovieHeaderBox header = moov.getMovieHeaderBox();
        return header;
    }
}
