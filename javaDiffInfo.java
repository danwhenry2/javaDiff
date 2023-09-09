class javaDiffInfo {
    javaDiffInfo() {
        super();
        m_file1 = new javaDiffFile();
        m_file2 = new javaDiffFile();
    }
    public javaDiffFile m_file1;
    public javaDiffFile m_file2;

    public static int loadFiles(javaDiffFile file1, javaDiffFile file2) {
        int status = javaDiffFile.loadFile(file1);
        if ( status!=0 ) return status;

        status = javaDiffFile.loadFile(file2);
        if ( status!=0 ) return status;

        return status;
    }
}

