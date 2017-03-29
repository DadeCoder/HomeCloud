//package com.dade.core.test;
//
//
//
//import com.dade.common.utils.LogUtil;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.HttpException;
//import org.apache.commons.httpclient.methods.PostMethod;
//import org.apache.commons.httpclient.methods.multipart.FilePart;
//import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
//import org.apache.commons.httpclient.methods.multipart.Part;
//import org.apache.commons.httpclient.methods.multipart.StringPart;
//import org.apache.commons.io.FilenameUtils;
//
//import java.io.File;
//import java.io.IOException;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Random;
//
///**
// * Created by Dade on 2017/3/28.
// */
//public class PostFile {
//    public static void main(String[] args) throws Exception {
//
//        postData();
//
////        HttpClient httpclient = new DefaultHttpClient();
////        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
////
////        HttpPost httppost = new HttpPost("http://localhost:9000/upload");
////        File file = new File("C:\\Users\\joao\\Pictures\\bla.jpg");
////
////        MultipartEntity mpEntity = new MultipartEntity();
////        ContentBody cbFile = new FileBody(file, "image/jpeg");
////        mpEntity.addPart("userfile", cbFile);
////
////
////        httppost.setEntity(mpEntity);
////        System.out.println("executing request " + httppost.getRequestLine());
////        HttpResponse response = httpclient.execute(httppost);
////        HttpEntity resEntity = response.getEntity();
////
////        System.out.println(response.getStatusLine());
////        if (resEntity != null) {
////            System.out.println(EntityUtils.toString(resEntity));
////        }
////        if (resEntity != null) {
////            resEntity.consumeContent();
////        }
////
////        httpclient.getConnectionManager().shutdown();
//    }
//
//    public static void postData() {
//
//        LogUtil.info("postData");
//        try {
//
//            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//            String name = df.format(new Date());
//
//            Random random = new Random();
//            for(int i = 0 ;i<3 ;i++){
//                name += random.nextInt(10);
//            }
//
//            LogUtil.info(name);
//
//            File f = new File("F:/Liemeng/featured-SpringIO.png/");
//
////            String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
//
//            File nf = new File("F:/Liemeng/" + name +".png/");
//
////            f.renameTo(nf);
//
//            PostMethod postMessage = new PostMethod("http://127.0.0.1:8089/");
//            Part[] parts = {
//                    new StringPart("param_name", "value"),
//                    new FilePart(f.getName(), f)
//            };
//            postMessage.setRequestEntity(new MultipartRequestEntity(parts, postMessage.getParams()));
//            HttpClient client = new HttpClient();
//
//            int status = client.executeMethod(postMessage);
//
//            LogUtil.info( "status: " + status);
//
//        } catch (HttpException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            //  TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//}
