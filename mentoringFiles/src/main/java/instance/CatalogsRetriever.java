package instance;

import java.io.File;
import java.util.Arrays;

public class CatalogsRetriever {
    public static void main(String[] args) {
        File dir = new File("/Users/angener/Documents");
        retrieveCatalogs(dir);
    }

    private static void retrieveCatalogs(File dir) {
        File[] files = dir.listFiles();
        if (files != null){
            Arrays.stream(files)
                    .peek(file -> System.out.println(file.getAbsolutePath()))
                    .filter(File::isDirectory)
                    .forEach(CatalogsRetriever::retrieveCatalogs);
        }
    }

//    private static void retrieveCatalogs(File dir) {
//        File[] files = dir.listFiles();
//        if (files != null) {
//            for (File file : files) {
//                System.out.println(file.getAbsolutePath());
//                if (file.isDirectory()) {
//                    retrieveCatalogs(file);
//                }
//            }
//        }
//    }
}