package handle_annotation.util;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class ClassArchive {

    public static List<Class<?>> getClassesOfPackage(String packageName) {
        return getAllClassesOfPackage(packageName);
    }

    /**
     * The method uses the "ClassGraph" library,
     * which include via Maven.
     */
    private static List<Class<?>> getAllClassesOfPackage(String packageName) {
        try (ScanResult scanResult = new ClassGraph()
                .whitelistPackages(packageName)
                .scan()) {

            return scanResult.getAllClasses().stream()
                    .parallel()
                    .map(n -> {
                        try {
                            return Class.forName(n.getName());
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
    }
}
