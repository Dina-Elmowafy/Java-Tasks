package exception_handler;

public class Task10 {
    public static void main(String[] args) throws IOException {
        try {
            readFile();
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        }


    }
    static void readFile() throws IOException {
        System.out.println("reading the file...");

        boolean fileExists = false;
        if (!fileExists) {
            throw new IOException("File Not Found");
        }
    }
}


