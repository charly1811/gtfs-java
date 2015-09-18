import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class FeedClassesGenerator {
    
    public static void main(String[] args) throws IOException {
        File folder = new File(args[0]);
        
            File gen = new File("gen");
            gen.mkdir();
            
            File feedObjectClass = new File(gen,"FeedObject.java");
            feedObjectClass.createNewFile();
               
            FileWriter feedObjectWriter = new FileWriter(feedObjectClass,true);
            feedObjectWriter.write("import java.util.HashMap;\n\n");
            feedObjectWriter.write("public class FeedObject extends HashMap<String,String> {}");
            feedObjectWriter.flush();
            
        for(File file : folder.listFiles()) {

            String className = getCamelCaseName(file.getName());
            System.out.println("Generating "+className+".java");
            
            String classCode = "";
            Set<String> methods = new HashSet<>();
            Scanner scanner = new Scanner(file);
            String[] fields = scanner.nextLine().split(",");
            for (String field : fields) {
                String fieldName = getCamelCaseName(field);
                String methodSignature = String.format("public String get%s()", fieldName);
                String methodDeclaration = String.format("%s{\n\treturn String.valueOf(get(\"%s\").replace(\"null\",\"\"));\n}",methodSignature,field);
                methods.add(methodDeclaration);
            }
            classCode = "import java.util.HashMap;\n\n";
            classCode = classCode.concat(String.format("public class %s extends FeedObject{\n\n",className));
            for(String method: methods) {
                classCode = classCode.concat(method);
                classCode = classCode.concat("\n\n");
            }
            classCode = classCode.concat("}");
            
            File javaCode = new File(gen,className+".java");
            javaCode.createNewFile();
            FileWriter writer = new FileWriter(javaCode);
            writer.write(classCode);
            writer.flush();
            System.out.println("Saved under "+javaCode.getPath());
        }
    }

    private static String getCamelCaseName(String name) {
        String camelCaseName = "";
        String[] parts = name.replace("txt","").replace("s.","").replace(".","").split("_");
        for (String part : parts) {
            camelCaseName = camelCaseName.concat(Character.toUpperCase(part.charAt(0))+part.substring(1));
        }
        return camelCaseName;
    }
}
