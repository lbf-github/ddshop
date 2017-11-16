import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * User: Administrator
 * Date: 2017/11/16
 * Time: 19:33
 * Version:V1.0
 */
public class FtpUploadTest {

    @Test
    public void testFtpUpload() throws IOException {
        //创建FTPClient客户端
        FTPClient ftpClient = new FTPClient();
        //创建FTP连接
        ftpClient.connect("47.93.246.82",21);
        //登录
        ftpClient.login("ftpuser","ph147896");
        //读取本地文件
        FileInputStream fileInputStream = new FileInputStream(new File("F:\\aaa\\3.jpg"));
        //配置上传参数
        ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //上传文件
        ftpClient.storeFile("hello.jpg",fileInputStream);
        //关闭连接
        fileInputStream.close();
        ftpClient.logout();

    }

}
