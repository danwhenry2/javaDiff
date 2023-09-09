import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class javaDiffFile {
    private String m_filename;
    private String[] m_fileLines;
    private int m_numLinesUsed;

    public javaDiffFile() {
        m_filename     = "";
        m_fileLines    = null;
        m_numLinesUsed = 0;
    }

    public javaDiffFile(String filename) {
        setFilename(filename);
    }
    public void setFilename(String filename) {
        m_filename = filename;
        File curFile = new File(m_filename);
        m_filename = curFile.getAbsolutePath();
    }
    public String getFilename() {
        return m_filename;
    }

    public static int loadFile(javaDiffFile file) {
        FileReader frFile = null;
        BufferedReader brFile = null;
        try {
            frFile = new FileReader(file.getFilename());
        } catch (java.io.FileNotFoundException FNFex) {
            return javaDiff.FILE_NOT_FOUND;
        }
        brFile = new BufferedReader(frFile);
        if (brFile==null) {
            return javaDiff.OUT_OF_MEMORY_ERROR;
        }

        int status = 0;
        for (;;) {
            String line;
            try {
                line = brFile.readLine();
            } catch (java.io.IOException IOex) {
                status = javaDiff.IO_ERROR;
                break;
            }
            if (line==null) break;
            if (file.m_fileLines == null) {
                file.m_fileLines = new String[javaDiff.LINE_ALLOC_BLOCK_SIZE];
            } else if (file.m_numLinesUsed >= file.m_fileLines.length) {
                String[] temp = new String[file.m_fileLines.length + javaDiff.LINE_ALLOC_BLOCK_SIZE];
                System.arraycopy(file.m_fileLines, 0, temp, 0, file.m_fileLines.length);
                file.m_fileLines = temp;
            }

            file.m_fileLines[file.m_numLinesUsed++] = line;

        }
        try {
            brFile.close();
        } catch (java.io.IOException IOex) {
            System.out.println("error on close " + file.getFilename());
            status = javaDiff.IO_ERROR;
        }
        try {
            frFile.close();
        } catch (java.io.IOException IOex) {
            System.out.println("error on close " + file.getFilename());
            status = javaDiff.IO_ERROR;
        }
        dumpFile(file);

        return status;
    }

    public static void dumpFile(javaDiffFile file) {
        System.out.println("dumpFile in");
        for (int i = 0; i<file.m_numLinesUsed; ++i) {
            System.out.println("    i="+i+" m_fileLines["+i+"]="+file.m_fileLines[i]);
        }
    }
}

