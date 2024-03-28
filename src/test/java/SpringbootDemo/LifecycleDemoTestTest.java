package SpringbootDemo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
@SpringBootTest()
class LifecycleDemoTestTest {
    @Autowired
    private ApplicationContext context;
    @Test
    public void testBeanLifecycle() {
        System.out.println("获取LifecycleDemoBean实例..");
        LifecycleDemoBean bean = context.getBean(LifecycleDemoBean.class);
    }
    //运行单元测试
    public static void main(String[] args) {
        SpringApplication.run(LifecycleDemoTestTest.class, args);
    }
}