package com.example.yongjie.tvdemo;


import com.example.yongjie.tvdemo.activity.MainActivity;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import java.util.Arrays;

/**
 * @author liuyongjie create on 2018/9/18.
 */
public class Test {

    public static void main(String[] args) {

//        String string = "{_currentIndex+1}页";
//        String string = "_currentIndex_";
//        String string = "none";
//        String string = "{currentIndex+1}/,{total}页";
//        String[] split = string.split(",");
//        System.out.println(Arrays.toString(split));


//        String string = "${_currentIndex+1}页";
//        String[] split = string.split("\\{");
//        System.out.println("length:" + split.length + ", " + Arrays.toString(split));
//
//        String substring = string.substring(0, string.lastIndexOf("_") + 1);
//        System.out.println(substring);

//        String result = runScript(JAVA_CALL_JS_FUNCTION, "Test", new String[]{});
//        System.out.println(result);

//        runJS();

        String result = "{_currentItem_}/${_itemCount_}页";
        String[] $s = result.split("\\$");
        System.out.println($s.length + "," + Arrays.toString($s));


    }


    /**
     * Java执行js的方法
     */
    private static final String JAVA_CALL_JS_FUNCTION = "function Test(){ return 'java call js Rhino'; }";

    public static void runJS() {
        Context ctx = Context.enter();
        Scriptable scope = ctx.initStandardObjects();

        String jsStr = "100*20/10";
        Object result = ctx.evaluateString(scope, jsStr, null, 0, null);
        System.out.println("result=" + result);
    }

    /**
     * 执行JS
     *
     * @param js             js代码
     * @param functionName   js方法名称
     * @param functionParams js方法参数
     * @return
     */
    public static String runScript(String js, String functionName, Object[] functionParams) {
        Context rhino = Context.enter();
        rhino.setOptimizationLevel(-1);
        try {
            Scriptable scope = rhino.initStandardObjects();
//            ScriptableObject.putProperty(scope, "javaContext", Context.javaToJS(MainActivity.this, scope));
//            ScriptableObject.putProperty(scope, "javaLoader", Context.javaToJS(MainActivity.class.getClassLoader(), scope));
//
            rhino.evaluateString(scope, js, null, 1, null);
            Function function = (Function) scope.get(functionName, scope);

            Object result = function.call(rhino, scope, scope, functionParams);
            if (result instanceof String) {
                return (String) result;
            } else if (result instanceof NativeJavaObject) {
                return (String) ((NativeJavaObject) result).getDefaultValue(String.class);
            } else if (result instanceof NativeObject) {
                return (String) ((NativeObject) result).getDefaultValue(String.class);
            }
            return result.toString();
        } finally {
            Context.exit();
        }
    }

    public static void getOut() {
        System.out.println("Hello World LamBa1");
        System.out.println("Hello World LamBa2");
    }

}
