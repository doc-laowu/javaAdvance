package com.neusoft.reflectClass;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

/**
 *  孙子类访问祖父类的方法实现
 */
class AccGrandClass {

    class GrandFather {
        void thinking() {
            System.out.println("i am grandfather");
        }
    }

    class Father extends GrandFather {
        void thinking() {
            System.out.println("i am father");
        }
    }

    class Son extends Father {
        void thinking() {
            try {

                /**
                 * 在JDK 7 Update 9之前能访问到
                 */
//                MethodType mt = MethodType.methodType(void.class);
//                MethodHandle mh = lookup().findSpecial(GrandFather.class,
//                        "thinking", mt, getClass());
//                mh.invoke(this);

                MethodType mt = MethodType.methodType(void.class);
                Field lookupImpl = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                lookupImpl.setAccessible(true);
                MethodHandle mh = ((MethodHandles.Lookup)lookupImpl.get(null))
                        .findSpecial(GrandFather.class,"thinking", mt, GrandFather.class);
                mh.invoke(this);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        (new AccGrandClass().new Son()).thinking();
    }
}

