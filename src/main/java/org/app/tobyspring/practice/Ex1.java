package org.app.tobyspring.practice;

interface Callback{
    int execute(final int b);
}

class Template {
    private int num;

    public int getNum() {return num;}
    public void setNum(int num) {this.num = num;}

    int workflow(Callback cb) {
        System.out.println("Workflow 시작");
        return cb.execute(num);
    }

    public Template(int a) {
        this.setNum(a);
    }
}


public class Ex1 {
    public static void main(String[] args) {
        int a = 5;
        int b = 10;
        Template t1 = new Template(5);
        Template t2 = new Template(b);
        int result1 = t1.workflow(new Callback() {
            @Override
            public int execute(final int n) {
                return n * n;
            }
        });
        int result2 = t2.workflow(new Callback() {
            @Override
            public int execute(final int n) {
                return n + n;
            }
        });

        System.out.println(result1);
        System.out.println(result2);
    }
}
