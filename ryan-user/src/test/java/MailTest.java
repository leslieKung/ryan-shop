import com.ryan.UserApplication;
import com.ryan.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName MailTest
 * @Author Ryan
 * @Date 2026/3/6 18:23
 * @Version 1.0.0
 * @Description 邮箱发送验证功能测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
@Slf4j
public class MailTest {

    @Autowired
    private MailService mailService;

    @Test
    public void testSendMail() {
        mailService.sendMail("2893860172@qq.com", "测试邮件", "测试邮件内容");
    }

}
