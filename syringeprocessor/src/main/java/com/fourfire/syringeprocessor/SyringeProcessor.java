package com.fourfire.syringeprocessor;

import com.fourfire.syringeannotation.BindView;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class SyringeProcessor extends AbstractProcessor {

    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(BindView.class.getCanonicalName());
    }

    // 不管有多少注解，每次编译只会走一遍这个方法，所以从env中拿出来的所有ele，就是所有带有注解的东东。
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindView.class);
        // key-value对应的是Activity和每个Activity中注解的元素
        Map<TypeElement, List<Element>> activityWithField = new HashMap<>();

        for (Element element : elements) {
            // 改API返回的是封装ele的最内层的元素，bindview注解的是变量，所以拿到的是activity
            TypeElement activity = (TypeElement) element.getEnclosingElement();

            List<Element> bindingView = activityWithField.get(activity);
            if (bindingView == null) {
                bindingView = new ArrayList<>();
                activityWithField.put(activity, bindingView);
            }
            bindingView.add(element);
        }

        for (Map.Entry<TypeElement, List<Element>> activityWithBindingView : activityWithField.entrySet()) {
            TypeElement activity = activityWithBindingView.getKey();

            String activityNameStr = activity.getSimpleName().toString();

            List<Element> bindingViews = activityWithBindingView.getValue();

            // 构造方法
            MethodSpec.Builder constructorBuilder = MethodSpec.constructorBuilder()
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(ClassName.bestGuess(activityNameStr), "targetActivity")
                    .addStatement("this.targetActivity = targetActivity");

            // 类
            TypeSpec.Builder
                    classBuilder =
                    TypeSpec.classBuilder(activityNameStr + "_Syringe")
                            .addField(ClassName.bestGuess(activityNameStr), "targetActivity")
                            .addModifiers(Modifier.PUBLIC);

            for (Element bindingView : bindingViews) {
                String viewName = bindingView.getSimpleName().toString();

                int bindingValue = bindingView.getAnnotation(BindView.class).value();

                constructorBuilder.addStatement("targetActivity." +
                        viewName +
                        " = targetActivity.findViewById(" +
                        bindingValue +
                        ");");
            }
            classBuilder.addMethod(constructorBuilder.build());
            JavaFile
                    javaFile =
                    JavaFile.builder(((PackageElement) activity.getEnclosingElement()).getQualifiedName()
                                    .toString(),
                            classBuilder.build()).build();
            try {
                javaFile.writeTo(filer);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        // 测试
//        StringBuilder stringBuilder = new StringBuilder();
//        for (Element element : elements) {
//            stringBuilder.append(element.getSimpleName() + "\n");
//        }
//
//        try {
//            Writer writer = filer.createSourceFile("com.fourfire.syringeprocessor.generated.log").openWriter();
//            writer.write(stringBuilder.toString() + "1");
//            writer.flush();
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return true;
    }

}

