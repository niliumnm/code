package action;

import com.opensymphony.xwork2.ActionSupport;

import java.io.*;


public class FileAction extends ActionSupport {
    private File upload;



    //文件名+FileName 不能自己改
    private String uploadFileName;

    public String upload() {
        System.out.println(uploadFileName);
        try {
            String ext = uploadFileName.substring(uploadFileName.indexOf("."));

            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(upload);
            FileOutputStream fos = new FileOutputStream("D:/Game/" + System.currentTimeMillis()+ext);
            int len = fis.read(buffer);
            while (len != -1) {
                fos.write(buffer);
                len = fis.read(buffer);
            }
            fos.close();
            fis.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return SUCCESS;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }


    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
}
