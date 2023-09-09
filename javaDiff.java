import java.io.File;

public class javaDiff {

    static final int FILE_NOT_FOUND      = -2;
    static final int IO_ERROR            = -3;
    static final int OUT_OF_MEMORY_ERROR = -4;

    static final int LINE_ALLOC_BLOCK_SIZE = 1000;

    public static void usage() {
        System.out.println("usage: javadiff <file1> <file2>");
        return;
    }

    public static int parseArguments(String args[],javaDiffInfo diffInfo) {
        int onFilename = 1;
        int status = 0;
        for (int i=0;i<args.length;++i) {
            if ( onFilename==1 ) {
                File cur = new File(args[i]);
                if (cur.exists()) {
                    diffInfo.m_file1.setFilename(args[i]);
                } else {
                    System.out.println("Could not find file "+ args[i]);
                    return FILE_NOT_FOUND;
                }
                ++onFilename;
            } else if ( onFilename==2 ) {
                File cur = new File(args[i]);
                if (cur.exists()) {
                    diffInfo.m_file2.setFilename(args[i]);
                } else {
                    System.out.println("Could not find file "+ args[i]);
                    return FILE_NOT_FOUND;
                }
            }
        }
        return 0;
    }

    public static void main (String args[]) {
        javaDiffInfo diffInfo = new javaDiffInfo();
        int status = parseArguments(args,diffInfo);
        if (status!=0) {
            usage();
            return;
        }
        diffInfo.loadFiles(diffInfo.m_file1,diffInfo.m_file2);
    }
}
