package ddd.demo;

import com.sun.corba.se.impl.activation.ServerMain;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class App {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        // 启动本地服务，然后hold住本地服务
        synchronized (ServerMain.class) {
            while (true) {
                try {
                    ServerMain.class.wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
