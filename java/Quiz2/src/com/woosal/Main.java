package com.woosal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        class C1 {}
        class C2 extends C1 { }
        class C3 extends C2 { }
        class C4 extends C1 {}

        C1 c1 = new C1();
        C2 c2 = new C2();
        C3 c3 = new C3();
        C4 c4 = new C4();

    }
}