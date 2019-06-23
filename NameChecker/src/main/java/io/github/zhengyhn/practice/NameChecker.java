package io.github.zhengyhn.practice;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementScanner8;
import javax.tools.Diagnostic;

public class NameChecker extends ClassLoader {
    private final Messager messager;
    private NameCheckScanner nameCheckScanner = new NameCheckScanner();

    public NameChecker(ProcessingEnvironment processingEnvironment) {
        this.messager = processingEnvironment.getMessager();
    }

    public void checkNames(Element element) {
        nameCheckScanner.scan(element);
    }

    private class NameCheckScanner extends ElementScanner8<Void, Void> {
        @Override
        public Void visitType(TypeElement element,  Void p) {
            super.scan(element.getTypeParameters(), p);

            String name = element.getSimpleName().toString();
            messager.printMessage(Diagnostic.Kind.NOTE, name);
            if (!Character.isUpperCase(name.charAt(0))) {
                messager.printMessage(Diagnostic.Kind.WARNING, "Class name should start with capital character");
            }
            super.visitType(element, p);
            return null;
        }
    }
}
