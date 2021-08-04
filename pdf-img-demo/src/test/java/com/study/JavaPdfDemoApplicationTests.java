package com.study;

import com.study.utils.PdfUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JavaPdfDemoApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(PdfUtil.pdf2Image("D:\\1.pdf", "D:", 72));
    }

}
