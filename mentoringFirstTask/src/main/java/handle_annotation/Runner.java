package handle_annotation;


import handle_annotation.entity.Hoover;
import handle_annotation.util.HandlerSmellsCode;
import handle_annotation.util.ProdRunner;

public class Runner {
    public static void main(String[] args) {
        new HandlerSmellsCode("handle_annotation.entity");
        ProdRunner.run(new Hoover());
    }
}
