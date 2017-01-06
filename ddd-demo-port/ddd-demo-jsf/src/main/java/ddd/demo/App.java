package ddd.demo;

import com.jd.pop.common.page.PaginatedInfo;
import com.jd.pop.order.around.soa.maliciousorderapply.MaliciousOrderApplyService;
import com.sun.corba.se.impl.activation.ServerMain;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.jd.pop.order.around.soa.maliciousorderapply.MaliciousOrderApplyService;


public class App {

    public static void main(String[] args) {

        System.out.println(System.getProperty("user.dir"));

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        MaliciousOrderApplyService service = (MaliciousOrderApplyService) context.getBean("MaliciousOrderApplyService");
        service.cancel(100L);

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
